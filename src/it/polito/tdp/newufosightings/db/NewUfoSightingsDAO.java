package it.polito.tdp.newufosightings.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import it.polito.tdp.newufosightings.model.Sighting;
import it.polito.tdp.newufosightings.model.State;

public class NewUfoSightingsDAO {

	public List<Sighting> loadAllSightings() {
		String sql = "SELECT * FROM sighting";
		List<Sighting> list = new ArrayList<>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);	
			ResultSet res = st.executeQuery();

			while (res.next()) {
				list.add(new Sighting(res.getInt("id"), res.getTimestamp("datetime").toLocalDateTime(),
						res.getString("city"), res.getString("state"), res.getString("country"), res.getString("shape"),
						res.getInt("duration"), res.getString("duration_hm"), res.getString("comments"),
						res.getDate("date_posted").toLocalDate(), res.getDouble("latitude"),
						res.getDouble("longitude")));
			}

			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		}

		return list;
	}

	public List<State> loadAllStates(Map<String, State>idMap) {
		String sql = "SELECT * FROM state";
		List<State> result = new ArrayList<State>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				if(idMap.get(rs.getString("ID"))==null) {
				State state = new State(rs.getString("id"), rs.getString("Name"), rs.getString("Capital"),
						rs.getDouble("Lat"), rs.getDouble("Lng"), rs.getInt("Area"), rs.getInt("Population"),
						rs.getString("Neighbors"));
				result.add(state);
				idMap.put(state.getId(), state);
				}
				else 
					result.add(idMap.get(rs.getString("ID")));
			}

			conn.close();
			return result;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		}
	}
	public List<String> loadAllShapes(int s){
		String sql = "SELECT DISTINCT shape FROM sighting WHERE YEAR(datetime)=?";
		List<String> result = new ArrayList<String>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, s);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				result.add(rs.getString("shape"));
			}

			conn.close();
			return result;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		}
	}
	public List<State> getVicini(Map<String,State>idMap, State state){
		String sql = "SELECT state1 FROM neighbor WHERE state2=? ";
		List<State> result = new ArrayList<State>();
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, state.getId());
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				State s = idMap.get(rs.getString("state1"));
				if(s == null)
					System.out.println("ERRORE");
				else 
					result.add(s);
			}

			conn.close();
			return result;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		}
	}

	public int getCorrelazione(State state, State s, String forma, int anno) {
		String sql = "SELECT COUNT(*) \n" + 
				"FROM  sighting AS s1, sighting AS s2 \n" + 
				"WHERE s1.shape = s2.shape \n" + 
				"AND s1.shape = ? \n" + 
				"AND s1.state=? \n" + 
				"AND s2.state=? AND s2.shape=? \n" + 
				"AND YEAR(s1.DATETIME)=YEAR(s2.DATETIME)AND YEAR(s1.DATETIME)=? ";
		int count=0;		
		try {
					Connection conn = ConnectDB.getConnection();
					PreparedStatement st = conn.prepareStatement(sql);
					st.setString(1, state.getId());
					st.setString(2, s.getId());
					st.setString(3, forma);
					st.setString(4, forma);
					st.setInt(5, anno);

					ResultSet res = st.executeQuery();

					while (res.next()) {
					count ++;	
						
					}
					

					conn.close();

				} catch (SQLException e) {
					throw new RuntimeException(e);
				}

				return count;

	}

}
























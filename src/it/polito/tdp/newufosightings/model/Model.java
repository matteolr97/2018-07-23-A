package it.polito.tdp.newufosightings.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.newufosightings.db.NewUfoSightingsDAO;

public class Model {

	private NewUfoSightingsDAO dao;
	private Graph<State,DefaultWeightedEdge> grafo;
	private Map<String, State> idMap;
	private List<State> neighbors;
	private List<State> states;
public Model() {
	dao = new NewUfoSightingsDAO();
	neighbors = new ArrayList<State>();
	idMap = new HashMap<String, State>();
	states = new ArrayList<>();
}
public List<String> getListaForme(int anno){
	return dao.loadAllShapes(anno);
}
public void creaGrafo(String forma, int anno) {
	grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
	dao.loadAllStates(idMap);
	states = dao.loadAllStates(idMap);
	Graphs.addAllVertices(grafo, idMap.values());
	for(State state:this.grafo.vertexSet()) {
	neighbors = dao.getVicini(idMap, state);
	for(State s: neighbors) {
		int correlazione = dao.getCorrelazione(state,s,forma, anno);
		DefaultWeightedEdge d = grafo.getEdge(state, s);
		if(d==null)
			Graphs.addEdge(grafo, state, s, correlazione);
	}
		
	}
	System.out.println("Vertici = "+grafo.vertexSet().size());
	System.out.println("Archi = "+grafo.edgeSet().size());
}
public int getVertexSize() {
	// TODO Auto-generated method stub
	return this.grafo.vertexSet().size();
}
public int getEdgeSize() {
	// TODO Auto-generated method stub
	return this.grafo.edgeSet().size();
}
public void calcolaVicini() {
	for(State s : idMap.values()) {
		List<State> vicini = Graphs.neighborListOf(grafo, s);
		int somma = 0;
		for(State vicino : vicini) {
			somma+=grafo.getEdgeWeight(grafo.getEdge(s, vicino));
		}
		s.setSomma(somma);
	}	
}
public List<State> getStati() {
	return states;
}
}
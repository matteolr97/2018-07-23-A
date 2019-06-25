package it.polito.tdp.newufosightings.model;

public class Vicini {

	private State s1;

	private int correlazione;

	public Vicini(State s1, int correlazione) {
		super();
		this.s1 = s1;
		this.correlazione = correlazione;
	}

	public State getS1() {
		return s1;
	}

	public void setS1(State s1) {
		this.s1 = s1;
	}

	public int getCorrelazione() {
		return correlazione;
	}

	public void setCorrelazione(int correlazione) {
		this.correlazione = correlazione;
	}
	

	
}

package it.polito.tdp.newufosightings.model;

public class Vicini {
	private State stato1;
	private State stato2;
	private int peso;
	public Vicini(State stato1, State stato2, int peso) {
		super();
		this.stato1 = stato1;
		this.stato2 = stato2;
		this.peso = peso;
	}
	public State getStato1() {
		return stato1;
	}
	public State getStato2() {
		return stato2;
	}
	public int getPeso() {
		return peso;
	}
	@Override
	public String toString() {
		return String.format("Collegamento [stato1=%s, stato2=%s, peso=%s]", stato1, stato2, peso);
	}
	

	
}

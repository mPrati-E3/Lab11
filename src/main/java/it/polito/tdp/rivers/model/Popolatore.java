package it.polito.tdp.rivers.model;

import java.util.Date;

//javabean che serve per popolare correttamente le textfield nel controller
public class Popolatore {
	
	private Date inizio;
	private Date fine;
	private int n_misurazioni;
	private float media;
	
	public Date getInizio() {
		return inizio;
	}
	public void setInizio(Date inizio) {
		this.inizio = inizio;
	}
	public Date getFine() {
		return fine;
	}
	public void setFine(Date fine) {
		this.fine = fine;
	}
	public int getN_misurazioni() {
		return n_misurazioni;
	}
	public void setN_misurazioni(int n_misurazioni) {
		this.n_misurazioni = n_misurazioni;
	}
	public float getMedia() {
		return media;
	}
	public void setMedia(float media) {
		this.media = media;
	}
	public Popolatore(Date inizio, Date fine, int n_misurazioni, float media) {
		super();
		this.inizio = inizio;
		this.fine = fine;
		this.n_misurazioni = n_misurazioni;
		this.media = media;
	}
	
	public Popolatore() {
		super();
		this.inizio = null;
		this.fine = null;
		this.n_misurazioni = 0;
		this.media = 0.0f;
	}
	
	
	

}

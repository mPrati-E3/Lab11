package it.polito.tdp.rivers.model;

import java.sql.Date;

public class PrimaMedia {
	
	Date primo;
	float media;
	
	public Date getPrimo() {
		return primo;
	}
	public void setPrimo(Date primo) {
		this.primo = primo;
	}
	public float getMedia() {
		return media;
	}
	public void setMedia(float media) {
		this.media = media;
	}
	
	public PrimaMedia(Date primo, float media) {
		super();
		this.primo = primo;
		this.media = media;
	}
	
	
	
	

}

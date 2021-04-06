package it.polito.tdp.rivers.model;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

import it.polito.tdp.rivers.db.RiversDAO;

public class Simulator {
	
	//numero di giorni su cui lavoro
	private final int GIORNI = 30;
	
	//capienza del bacino in metri cubi
	private float Q;
	//quantità di acqua nel bacino
	private float C;
	//flusso di uscita minimo
	private float Foutmin; 
	
	//fiume che voglio trattare
	River r;
	
	//dichiarazione del dao
	RiversDAO dao;
	
	//dichiarazione della coda di eventi
	private PriorityQueue<Flow> queue;
	
	//definisco i vari campi tramite costruttore
	public Simulator(float K, float Fmed, RiversDAO dao, River r) {
		super();
		this.Q = K * Fmed * GIORNI * 86400;
		this.C = Q / 2;
		this.Foutmin = (0.8f*Fmed)*86400;
		this.dao=dao;
		this.r=r;
	}

	//faccio partire il simulatore che mi ritornerà il messaggio da scrivere
	public String run() {
		
		//definisco la coda di flussi
		this.queue = new PriorityQueue<Flow>();
		//prendo e carico tutti i flussi di un fiume
		this.queue.addAll(dao.getAllFlowsOfRiver(r));
		
		//lista di capacità ovvero dei valori dei flussi
		List<Float> capacity = new ArrayList<Float>();
		
		//numero di giorni di fallimento
		int Gfal = 0;
		//capacità media
		float Cmed = 0;
		
		//flusso di appoggio
		Flow flow;
		
		//finchè ho dei flussi nella coda, eseguo
		while ((flow=this.queue.poll())!=null) {
			
			//definisco un flusso di uscita iniziale a quello minimo
			float Fout = Foutmin;
			
			//caso in cui serva tanta acqua 
			if (Math.random()>0.95) {
				Fout = 10*Foutmin;
			}
			
			//incremento la quantità di acqua nel bacino
			C = C + (flow.getFlow()*86400);
			
			if (C>Q) {
				//tracimazione
				C=Q;
			}
			
			if (C<Fout) {
				//non riesco a garantire la quantità minima
				Gfal++;
				C=0;
				
			} else {
				//esce la quantità giornaliera
				C = C - Fout;
			}
			
			capacity.add(C);
			
		}
		
		//calcolo media
		for (Float c : capacity) {
			Cmed+=c;
		}
		
		Cmed=Cmed/capacity.size();
		
		return "Giorni in cui non si è potuta garantire l'erogazione minima: "+Gfal+
				" \nOccupazione media del bacino: "+(int)Cmed+" m^3 \n";
	}
	
	
	

}

package it.polito.tdp.rivers.model;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

import it.polito.tdp.rivers.db.RiversDAO;

public class Simulator {
	
	private final int GIORNI = 30;
	private final int GIORNI_ANNO = 365;
	
	private float Q; 
	private float C;
	private float Foutmin; 
	private int N;
	private Flow Fin;
	
	River r;
	
	RiversDAO dao;
	
	private PriorityQueue<Flow> queue;
	
	public Simulator(float K, float Fmed, int N, Flow Fin, RiversDAO dao, River r) {
		super();
		this.Q = K * Fmed * GIORNI * 86400;
		this.C = Q / 2;
		this.Foutmin = (0.8f*Fmed)*86400;
		this.N = N;
		this.Fin=Fin;
		this.dao=dao;
		this.r=r;
	}

	public String run() {
		
		this.queue = new PriorityQueue<Flow>();
		this.queue.addAll(dao.getAllFlowsOfRiver(r));
		
		List<Float> capacity = new ArrayList<Float>();
		
		int Gfal = 0;
		float Cmed = 0;
		
		Flow flow;
		
		while ((flow=this.queue.poll())!=null) {
			
			float Fout = Foutmin;
			
			if (Math.random()>0.95) {
				Fout = 10*Foutmin;
			}
			
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
		
		for (Float c : capacity) {
			Cmed+=c;
		}
		
		Cmed=Cmed/capacity.size();
		
		return "Giorni in cui non si è potuta garantire l'erogazione minima: "+Gfal+
				" \nOccupazione media del bacino: "+(int)Cmed+" m^3 \n";
	}
	
	
	

}

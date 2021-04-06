package it.polito.tdp.rivers.model;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.polito.tdp.rivers.db.RiversDAO;

public class Model {
	
	//dichiaro il dao
	RiversDAO dao;
	
	//dichiaro la collezzione di fiumi
	List<River> listRivers;
	Map<Integer, River> mapRivers;
	
	//dichiaro la collezzione di flussi
	List<Flow> listFlows;
	Map<Integer, Flow> mapFlows;
	
	//dichiaro il simulatore
	Simulator sim;
	
	public Model() {
		
		//definisco il dao
		this.dao = new RiversDAO();
		
		//definisco la collezione di fiumi
		this.listRivers=dao.getAllRivers();
		
		this.mapRivers = new HashMap<Integer, River>();
		for (River r : listRivers) {
			mapRivers.put(r.getId(), r);
		}
		
		//definisco la collezione di flussi
		this.listFlows=dao.getAllFlows(mapRivers);
		
		this.mapFlows = new HashMap<Integer, Flow>();
		for (Flow f : listFlows) {
			mapFlows.put(f.getId(), f);
		}
		
	}
	
	//ritorna tutti i fiumi del database
	public List<River> TuttiFiumi() {
		return this.listRivers;
	}

	//creo il popolatore per il controller
	public Popolatore popolaTxtDatoFiume(River r) {
	
		Date fine=null;
		int n=0;

		//numero di flows del popolatore
		for(int i=0; i<listFlows.size(); i++) {
			if (listFlows.get(i).getRiver().getId()==r.getId()) {
				n++;			
			}
		}
		
		
		//setta la fine del mio popolatore
		for(int i=listFlows.size()-1; i>=0; i--) {
			if (listFlows.get(i).getRiver().getId()==r.getId()) {
				fine=listFlows.get(i).getDay();
				break;
			}
		}
		
		//setta inizio del popolatore e media
		PrimaMedia pm = dao.dammiPrimaMedia(r);
		
		return new Popolatore(pm.getPrimo(),fine,n,pm.getMedia());
	}

	//dati in input gli elementi del controller, recupero il flow e 
	//il river che mi servono e chiamo il simulatore
	public String Simulatore(River R, String Fin, String Fend, int N, float Fmed, float K) {
		
		sim = new Simulator (K,Fmed,dao,R);
		
		return sim.run();
	}

}

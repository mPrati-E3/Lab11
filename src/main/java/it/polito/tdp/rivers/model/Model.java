package it.polito.tdp.rivers.model;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.polito.tdp.rivers.db.RiversDAO;

public class Model {
	
	RiversDAO dao;
	
	List<River> listRivers;
	Map<Integer, River> mapRivers;
	
	List<Flow> listFlows;
	Map<Integer, Flow> mapFlows;
	
	Simulator sim;
	
	public Model() {
		
		this.dao = new RiversDAO();
		
		this.listRivers=dao.getAllRivers();
		
		this.mapRivers = new HashMap<Integer, River>();
		for (River r : listRivers) {
			mapRivers.put(r.getId(), r);
		}
		
		this.listFlows=dao.getAllFlows(mapRivers);
		
		this.mapFlows = new HashMap<Integer, Flow>();
		for (Flow f : listFlows) {
			mapFlows.put(f.getId(), f);
		}
		
	}
	
	public List<River> TuttiFiumi() {
		return this.listRivers;
	}

	public Popolatore popolaTxtDatoFiume(River r) {
	
		Date fine=null;
		int n=0;

		
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
		
		PrimaMedia pm = dao.dammiPrimaMedia(r);
		
		return new Popolatore(pm.getPrimo(),fine,n,pm.getMedia());
	}

	public String Simulatore(River R, String Fin, String Fend, int N, float Fmed, float K) {
		
		for (Flow f : listFlows) {
			if (R.getId()==f.getRiver().getId() && Fin.equals(f.getDay().toString())) {
				sim = new Simulator (K,Fmed,N,f,dao,R);
				break;
			}
		}
		
		return sim.run();
	}

}

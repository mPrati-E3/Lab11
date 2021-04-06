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

	public String Simulatore(River value, String text, String text2, String text3, String text4, String text5) {
		// TODO Auto-generated method stub
		return null;
	}

}

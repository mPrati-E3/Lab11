package it.polito.tdp.rivers.db;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import it.polito.tdp.rivers.model.Flow;
import it.polito.tdp.rivers.model.PrimaMedia;
import it.polito.tdp.rivers.model.River;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RiversDAO {

	public List<River> getAllRivers() {
		
		final String sql = "SELECT id, name FROM river";

		List<River> rivers = new LinkedList<River>();

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				rivers.add(new River(res.getInt("id"), res.getString("name")));
			}

			conn.close();
			
		} catch (SQLException e) {
			//e.printStackTrace();
			throw new RuntimeException("SQL Error");
		}

		return rivers;
	}

	public List<Flow> getAllFlows(Map<Integer, River> map) {
		
		final String sql = "SELECT id,day,flow,river  FROM flow";

		List<Flow> flows = new LinkedList<Flow>();

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				
				River r = map.get(res.getInt("river"));
				
				Flow f = new Flow(
						res.getInt("id"),
						res.getDate("day"),
						res.getFloat("flow"),
						r);
				
				flows.add(f);
			}

			conn.close();
			
		} catch (SQLException e) {
			//e.printStackTrace();
			throw new RuntimeException("SQL Error");
		}

		return flows;
	}

	public PrimaMedia dammiPrimaMedia(River r) {
		
		final String sql = "SELECT flow.day AS inizio, AVG(flow.flow) AS media\n"
				+ "FROM flow\n"
				+ "WHERE river=?";

		try {
			
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setInt(1, r.getId());
			
			ResultSet res = st.executeQuery();

			while (res.next()) {
				
				PrimaMedia pm = new PrimaMedia (res.getDate("inizio"), res.getFloat("media"));
				
				conn.close();
				
				return pm;

			}

			
		} catch (SQLException e) {
			//e.printStackTrace();
			throw new RuntimeException("SQL Error");
		}
		return null;

	}
}

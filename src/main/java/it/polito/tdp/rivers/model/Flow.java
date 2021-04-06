package it.polito.tdp.rivers.model;

import java.sql.Date;

//javabean che rappresenta i flussi
//è più complesso del solito perchè comparabile
public class Flow implements Comparable<Flow>{
	
	private int id;
	private Date day;
	private float flow;
	private River river;

	public Flow(int id, Date day, float flow, River river) {
		this.id=id;
		this.day = day;
		this.flow = flow;
		this.river = river;
	}

	public Date getDay() {
		return day;
	}

	public void setDay(Date day) {
		this.day = day;
	}

	public float getFlow() {
		return flow;
	}

	public void setFlow(float flow) {
		this.flow = flow;
	}

	@Override
	public String toString() {
		return "Flow [day=" + day + ", flow=" + flow + ", river=" + river + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public River getRiver() {
		return river;
	}

	public void setRiver(River river) {
		this.river = river;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Flow other = (Flow) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public int compareTo(Flow o) {
		return this.day.compareTo(o.getDay());
	}
	
	
	
	

	
}

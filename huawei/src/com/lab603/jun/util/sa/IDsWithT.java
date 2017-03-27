package com.lab603.jun.util.sa;

import java.util.ArrayList;
import java.util.List;

public class IDsWithT {
	List<Integer> ids = new ArrayList<>();
	double T;
	public IDsWithT(List<Integer> ids, double T) {
		this.ids.addAll(ids);
		this.T = T;
	}
	public List<Integer> getIds() {
		return ids;
	}
	public void setIds(List<Integer> ids) {
		this.ids = ids;
	}
	public double getT() {
		return T;
	}
	public void setT(double t) {
		T = t;
	}
	
}

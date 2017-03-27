package com.lab603.module;

import java.util.Arrays;
import java.util.List;

public class Chromosome {
	
	private int[] gene;
	
	ResultPathsAndCost pathsAndCost;
	
	List<Integer> serversId;
	
	double x = 0;
	
	double T;
	
	double P;
	
	public Chromosome() {

	}
	
	public Chromosome(List<Integer> id, double x, double t) {
		this.serversId = id;
		this.x = x;
		T = t;
		P = Math.exp(-x/T);
	}
	
	public Chromosome(List<Integer> ids) {
		if(ids.get(0) == 0){
			ids.remove(0);
			gene = new int[ids.get(0)];
			for(int i=1;i<ids.size();i++)
				gene[ids.get(i)]=1;
		}else{
			gene = new int[ids.get(0)];
			for(int i=1;i<ids.get(0);i++)
				gene[i]= Math.random() > 0.5 ? 1:0;
		}
		
	}
	
	public ResultPathsAndCost getPathsAndCost() {
		return pathsAndCost;
	}

	public void setPathsAndCost(ResultPathsAndCost pathsAndCost) {
		this.pathsAndCost = pathsAndCost;
	}

	public int[] getGene() {
		return gene;
	}
	public void setGene(int index,int value) {
		gene[index] = value;
	}
	
	public List<Integer> getServersId() {
		return serversId;
	}

	public void setServersId(List<Integer> serversId) {
		this.serversId = serversId;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getT() {
		return T;
	}

	public void setT(double t) {
		T = t;
	}

	public double getP() {
		return P;
	}

	public void setP(double p) {
		P = p;
	}

	public void setGene(int[] gene) {
		this.gene = gene;
	}
	
	@Override
	public String toString() {
		return "Chromosome [gene=" + Arrays.toString(gene) + ", pathsAndCost=" + pathsAndCost + "]";
	}
}
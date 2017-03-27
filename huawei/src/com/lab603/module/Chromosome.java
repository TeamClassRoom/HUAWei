package com.lab603.module;

import java.util.Arrays;
import java.util.List;

public class Chromosome {
	
	private int[] gene;
	
	List<Integer> serversId;
	
	double x = 0;
	
	double T;
	
	double P;
	
	int cost;

	
	public Chromosome(List<Integer> id, double x, int cost, double t) {
		this.serversId = id;
		this.x = x;
		T = t;
		P = Math.exp(-x/T);
		this.cost = cost;
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
	
	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}


	@Override
	public String toString() {
		return "Chromosome [gene=" + Arrays.toString(gene) + ", serversId=" + serversId + ", x=" + x + ", T=" + T
				+ ", P=" + P + ", cost=" + cost + "]";
	}


}
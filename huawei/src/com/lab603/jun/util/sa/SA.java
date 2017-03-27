package com.lab603.jun.util.sa;

import java.util.ArrayList;
import java.util.List;

import com.lab603.module.Chromosome;
import com.lab603.module.Net;
import com.lab603.util.getResult;

public class SA {
	static Net net;
	double initT;
	static final double r = 0.925;// 0.8-0.99
	List<Integer> initIds;
	public SA(Net net) {
		this.net = net;
		initIds = getResult.allLinkedNodesIds(net);
		// 设置初始种群
	}

	
	
	// 使用个体变异得到多个新个体  个体，需要的个数
	public static List<Chromosome> getChangeOnceByOne(Chromosome chromosome, int size) {
		List<Chromosome> res = new ArrayList<Chromosome>();
		while(size-- >=0) {
			// 随机产生一个新的ids    变异的规则 可以在这个方法中修改
			List<Integer> newIds = TransRule.transIds(chromosome.getServersId(), net);
			int cost = chromosome.getCost();
			int newCost = getResult.getOneResult(net, newIds).getCosts();// 新的花费
			double x = cost - newCost;
			double T = chromosome.getT();
			if (x >= 0) {// 变好
				Chromosome tempChromosome = new Chromosome(newIds, x, newCost, T);
				res.add(tempChromosome);
			} else {// 变差一定几率保留
				if (retain(x, T)) {
					Chromosome tempChromosome = new Chromosome(newIds, x, newCost, T * r);
					res.add(tempChromosome);
				}
			}
		}
		return res;
	}
	
	// 变坏时 计算是否保留
	public static boolean retain(double x, double T) {
		return Math.exp( x / T) > Math.random();
	}
	
}

package com.lab603.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.lab603.chenzuo.module.GeneticAlgorithm;
import com.lab603.chenzuo.util.GACriterion;
import com.lab603.jun.util.ServerNodeSet;
import com.lab603.jun.util.ServerNodeSetV2;
import com.lab603.module.CostNode;
import com.lab603.module.Net;
import com.lab603.module.Node;
import com.lab603.module.ResultPathsAndCost;
import com.lab603.yj.module.MinCostFlow;

public class getResult {
	static List<List<Integer>> someIds = new ArrayList<>();
	// main algorithm
	public static String[] formNet(Net net) {
		int best = Integer.MAX_VALUE;
		ServerNodeSetV2 serverNodeSet = new ServerNodeSetV2(net);
		long time0 = System.currentTimeMillis();
		ResultPathsAndCost resultPathsAndCost = null;
		boolean first = true;
		List<Integer> lastIds = null;
		List<Integer> initIDs = allLinkedNodesIds(net);
		int count = 100;
		int deltaESum = 0;
		while(true) {
			List<Integer> ids = new ArrayList<>();
			if (first) {
				first = false;
				ids.addAll(initIDs);
				lastIds = ids;
			} else {
				ids = serverNodeSet.randomServer(lastIds);
				if (count-- == 0) {
					count = net.getNodes().size();
					ids = new ArrayList<>();
					ids.addAll(someIds.get((int) (Math.random() * someIds.size())));
				}
				
			}
			 
			
			// 4.set
			ResultPathsAndCost tempResult = getOneResult(net, ids);
			System.err.println(tempResult.getCosts());
			if (tempResult.getCosts() > 0) {
				deltaESum += Math.abs(144000 - tempResult.getCosts());
			}
			
			if (count == 1) {
				System.out.println(deltaESum / 99);
			}
  			if (tempResult.getCosts() >= 0 && tempResult.getCosts() < best) {
  				someIds.add(ids);
  				lastIds = ids;
  				best = tempResult.getCosts();
//				System.err.println(best);
   				resultPathsAndCost = tempResult;
			}
			long time2 = System.currentTimeMillis();
			if (time2 - time0 > 50000) {
				break;
			}
			
		}
		// verification
//		if (resultPathsAndCost.getCosts()>0 && VERI.verification(resultPathsAndCost, net) == false) {
//			System.out.println(1);
//		}
		System.err.println("***" + VERI.verification(resultPathsAndCost, net));
		return transReultToStrings(resultPathsAndCost);
	}
	
	//GA 
	public static String[] formNetWithGA(Net net) {
		
		//make criterion 
		GeneticAlgorithm GA = new GeneticAlgorithm(net,new GACriterion(30, 0.8f, 0.9f, 8000, 1000));
		
		//GA 
		ResultPathsAndCost bestGroupInTime = GA.evolve();
		
		return transReultToStrings(bestGroupInTime);
	}


	public static ResultPathsAndCost getOneResult(Net net, List<Integer> ids) {
		MinCostFlow m = new MinCostFlow(net);
		m.TransNet2Flow();
		m.setServer(ids);
		
       	return  m.min_cost_flow();
	}
	


	public static List<Integer> allLinkedNodesIds(Net net) {
		
		List<Integer> res = new ArrayList<>();
		for (CostNode costNode : net.getCostNodes()) {
			res.add(costNode.getLinkedNodeId());
		}
		return res;
	}





	private static List<Integer> addIdsFromServerNodes(List<Node> serverNodes) {
		List<Integer> res = new ArrayList<Integer>();
		for (Node node : serverNodes) {
			res.add(node.getId());
		}
		return res;
	}

	private static String[] transReultToStrings(ResultPathsAndCost resultPathsAndCost) {
		// System.out.println(resultPathsAndCost);
		return resultPathsAndCost.result();
	}
}

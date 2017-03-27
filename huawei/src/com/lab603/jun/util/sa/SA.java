package com.lab603.jun.util.sa;

import java.util.List;

import com.lab603.module.Net;
import com.lab603.util.getResult;

public class SA {
	Net net;
	double initT;
	static final double r = 0.925;// 0.8-0.99
	List<Integer> initIds;
	public SA(Net net) {
		this.net = net;
		initIds = getResult.allLinkedNodesIds(net);
		
	}
	
	
	
}

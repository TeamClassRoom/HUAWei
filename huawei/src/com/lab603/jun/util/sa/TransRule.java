package com.lab603.jun.util.sa;

import java.util.List;

import com.lab603.module.Net;
import com.lab603.util.getResult;

public class TransRule {

	static List<Integer> transIds(List<Integer> ids, Net net) {// 暂定 随机变异
		ids = getResult.allLinkedNodesIds(net);
		int random1 = (int) (Math.random() * ids.size());
		ids.remove(random1);
		if (Math.random() > 0.5) {
			int random2 = (int) (Math.random() * ids.size());
			ids.remove(random2);
		}
		
		int random3 = (int) (Math.random() * net.getNodes().size()); 
		ids.add(random3);
		return ids;
	}
}

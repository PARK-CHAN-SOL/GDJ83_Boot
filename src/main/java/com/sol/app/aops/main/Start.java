package com.sol.app.aops.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sol.app.aops.transfers.Transfer;

@Component
public class Start {

	@Autowired
	private Transfer transfer;
	
	public void go() {
		transfer.takeBus(50);
		transfer.takeSubway(15L, "winter");
		transfer.walk();
	}
	
}

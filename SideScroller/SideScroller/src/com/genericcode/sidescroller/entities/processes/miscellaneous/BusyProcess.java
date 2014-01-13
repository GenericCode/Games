package com.genericcode.sidescroller.entities.processes.miscellaneous;

import com.lostcode.javalib.entities.EntityWorld;
import com.lostcode.javalib.entities.components.generic.Item;
import com.lostcode.javalib.entities.processes.Process;
import com.lostcode.javalib.entities.processes.ProcessState;

public class BusyProcess extends Process {

	Item item;
	boolean state;
	
	public BusyProcess(Item item, boolean state) {
		this.item = item;
		this.state = state;
	}
	
	@Override
	public void update(EntityWorld world, float deltaTime) {
		this.end(ProcessState.SUCCEEDED);
	}

	@Override
	public void onEnd(EntityWorld world, ProcessState endState) {
		item.busy = state;
	}

}

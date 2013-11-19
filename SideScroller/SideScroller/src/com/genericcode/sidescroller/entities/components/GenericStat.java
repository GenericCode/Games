package com.genericcode.sidescroller.entities.components;

import com.lostcode.javalib.entities.components.ComponentManager;
import com.lostcode.javalib.entities.components.abstracted.Stat;

/**
 * A Stat used to store any kind of numerical statistics for an entity.
 * 
 * @param max
 *            The maximum/initial value of the stat.
 */
public class GenericStat extends Stat {
	
	public GenericStat(double max) {
		super(max);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onAdd(ComponentManager container) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onRemove(ComponentManager container) {
		// TODO Auto-generated method stub

	}

}

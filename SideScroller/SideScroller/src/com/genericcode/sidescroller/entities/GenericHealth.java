package com.genericcode.sidescroller.entities;

import com.lostcode.javalib.entities.Entity;
import com.lostcode.javalib.entities.EntityWorld;
import com.lostcode.javalib.entities.components.generic.Health;
import com.lostcode.javalib.entities.components.physical.Transform;
import com.lostcode.javalib.entities.events.EventCallback;
import com.lostcode.javalib.utils.SoundManager;

public class GenericHealth extends Health {

	public GenericHealth(Entity owner, EntityWorld world, float max) {
		super(owner, world, max);
	}

	
	
}

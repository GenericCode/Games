package com.genericcode.sidescroller.entities.components;

import com.lostcode.javalib.entities.Entity;
import com.lostcode.javalib.entities.EntityWorld;
import com.lostcode.javalib.entities.components.abstracted.Stat;
import com.lostcode.javalib.entities.components.generic.Cooldown;

public class SniperGun extends Gun {

	public SniperGun(String type, Entity owner, EntityWorld world, Stat damage, Cooldown fireDelay, Stat range, Stat ammo, Cooldown reloadTime) {
		super(type, owner, world, damage, fireDelay, range, ammo, reloadTime);
	}
	
	@Override
	public void shoot() {
		
	}

}

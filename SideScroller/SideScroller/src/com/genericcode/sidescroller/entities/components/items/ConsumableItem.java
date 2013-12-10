package com.genericcode.sidescroller.entities.components.items;

import com.genericcode.sidescroller.entities.components.GenericStat;
import com.lostcode.javalib.entities.components.abstracted.Stat;
import com.lostcode.javalib.entities.components.generic.Item;
import com.lostcode.javalib.entities.components.render.Sprite;

/**
 * Base class for Consumable items.
 * @author GenericCode
 * @Created November 13, 2013
 */

public class ConsumableItem extends Item {

	protected float damage;
	protected float radius;
	protected Stat ammo;
	
	protected Sprite sprite;
	
	public ConsumableItem(String type, float damage, float useTime, float radius, GenericStat ammo) {
		super(type);
		
		this.damage = damage;
		this.useTime = useTime;
		this.ammo = ammo;
		
		// TODO Auto-generated constructor stub
	}

}

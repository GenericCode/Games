package com.genericcode.sidescroller.entities.components;

import com.lostcode.javalib.entities.Entity;
import com.lostcode.javalib.entities.EntityWorld;
import com.lostcode.javalib.entities.components.abstracted.Stat;
import com.lostcode.javalib.entities.components.generic.Cooldown;
import com.lostcode.javalib.entities.components.generic.Item;
import com.lostcode.javalib.entities.components.render.Sprite;

/**
 * Base class for Gun-type components.
 * @author GenericCode
 * @Created November 13, 2013
 */

public class Gun extends Item {

	private Entity owner;
	private EntityWorld world;
	
	protected Stat damage;
	protected Cooldown fireDelay;
	protected Stat range;
	
	protected Stat ammo;
	protected Cooldown reloadTime;
	
	protected boolean ultra = false;
	
	protected Sprite sprite;
	
	/**
	 * Constructs a Gun component.
	 * 
	 * @param type
	 *            The type of Gun.
	 * @param damage
	 *            A {@link Stat} representing the damage of projectiles fired by the gun.
	 * @param fireDelay
	 *            A {@link Cooldown} representing the delay between shots fired by the gun.
	 * @param range
	 *            A {@link Stat} representing the range of projectiles fired by the gun.
	 * @param ammo
	 *            A {@link Stat} representing the current and maximum values of ammo for the gun.
	 * @param reloadTime
	 *            A {@link Cooldown} representing the delay between reloads of the gun.
	 *            
	 */
	public Gun(String type, Entity owner, EntityWorld world, Stat damage, Cooldown fireDelay, Stat range, Stat ammo, Cooldown reloadTime) {
		super(type);
		
		this.owner = owner;
		this.world = world;
		
		this.damage = damage;
		this.fireDelay = fireDelay;
		this.range = range;
		
		this.ammo = ammo;
		this.reloadTime = reloadTime;
	}
	
	public void shoot() {}
}

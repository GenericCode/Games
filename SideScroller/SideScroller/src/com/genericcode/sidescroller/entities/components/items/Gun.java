package com.genericcode.sidescroller.entities.components.items;

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

	protected Stat damage;
	protected Cooldown fireDelay;
	protected Stat range;
	protected Stat bulletVelocity;
	
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
	 * @param bulletVelocity
	 *            A {@link Stat} representing the initial velocity of projectiles fired by the gun.           
	 * @param ammo
	 *            A {@link Stat} representing the current and maximum values of ammo for the gun.
	 * @param reloadTime
	 *            A {@link Cooldown} representing the delay between reloads of the gun.
	 *            
	 */
	public Gun(String type, Stat damage, Cooldown fireDelay, Stat range, Stat bulletVelocity, Stat ammo, Cooldown reloadTime) {
		super(type);
		
		this.damage = damage;
		this.fireDelay = fireDelay;
		this.range = range;
		this.bulletVelocity = bulletVelocity;
		
		this.ammo = ammo;
		this.reloadTime = reloadTime;
	}
	
	/**
	 * Constructs a Gun component.
	 * 
	 * @param firer
	 *            The {@link Entity} firing the gun.
	 * @param world
	 *            The {@link EntityWorld} containing the firer.
	 * @param fireAngle
	 *            The angle the bullet should fire at in degrees, from 0.        
	 */
	public void shoot( Entity firer, EntityWorld world, float fireAngle ) {}
}

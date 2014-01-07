package com.genericcode.sidescroller.entities.components.items;

import com.badlogic.gdx.math.Vector2;
import com.genericcode.sidescroller.entities.components.GenericStat;
import com.lostcode.javalib.entities.Entity;
import com.lostcode.javalib.entities.EntityWorld;
import com.lostcode.javalib.entities.components.generic.Item;
import com.lostcode.javalib.entities.components.render.Sprite;

/**
 * Base class for Gun-type items.
 * @author GenericCode
 * @Created November 13, 2013
 */

public class Gun extends Item {

	public float damage;
	public float fireDelay;
	public float range;
	public float bulletVelocity;
	
	public GenericStat ammo;
	public float reloadTime;
	
	public boolean ultra = false;
	public boolean busy = false;//Whether the Gun is busy or not.
	
	public Sprite sprite;
	
	/**
	 * Constructs a Gun component.
	 * 
	 * @param type
	 *            The type of Gun.
	 * @param damage
	 *            A float representing the damage of projectiles fired by the gun.
	 * @param fireDelay
	 *            A float representing the delay between shots fired by the gun.
	 * @param range
	 *            A float representing the range of projectiles fired by the gun.
	 * @param bulletVelocity
	 *            A float representing the initial velocity of projectiles fired by the gun.           
	 * @param ammo
	 *            A {@link GenericStat} representing the current and maximum values of ammo for the gun.
	 * @param reloadTime
	 *            A float representing the delay between reloads of the gun.
	 *            
	 */
	public Gun(String type, float damage, float fireDelay, float range, float bulletVelocity, GenericStat ammo, float reloadTime) {
		super(type);
		
		this.damage = damage;
		this.fireDelay = fireDelay;
		this.useTime = fireDelay;
		this.range = range;
		this.bulletVelocity = bulletVelocity;
		
		this.ammo = ammo;
		this.reloadTime = reloadTime;
	}
	
	/**
	 * Called when the gun is fired. Returns whether firing was successful.
	 * 
	 * @param firer
	 *            The {@link Entity} firing the gun.
	 * @param world
	 *            The {@link EntityWorld} containing the firer.
	 * @param fireAngle
	 *            The vector the bullet should fire at. Will be scaled to proper bullet speed.        
	 */
	public boolean shoot( Entity firer, EntityWorld world, Vector2 fireAngle ) {
		return false;
	}
	
	public boolean secondary( Entity firer, EntityWorld world, Vector2 fireAngle ) {
		return false;
	}
	
	/**
	 * Called when the gun is reloaded. Returns whether reloading was successful.        
	 */
	public boolean reload() {
		return false;
	}
}

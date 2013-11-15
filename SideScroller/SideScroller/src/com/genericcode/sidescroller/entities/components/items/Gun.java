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

	protected float damage;
	protected Cooldown fireDelay;
	protected float range;
	protected float bulletVelocity;
	
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
	public Gun(String type, float damage, Cooldown fireDelay, float range, float bulletVelocity, Stat ammo, Cooldown reloadTime) {
		super(type);
		
		this.damage = damage;
		this.fireDelay = fireDelay;
		this.range = range;
		this.bulletVelocity = bulletVelocity;
		
		this.ammo = ammo;
		this.reloadTime = reloadTime;
	}
	
	@Override
	public boolean use( String use, Object... args ) {
		if( use == "shoot") {
			this.shoot((Entity)args[0], (EntityWorld)args[1], (Float)args[2]);
		}
		return false;
	}
	/**
	 * Called when the gun is fired. Returns whether firing was successful.
	 * 
	 * @param firer
	 *            The {@link Entity} firing the gun.
	 * @param world
	 *            The {@link EntityWorld} containing the firer.
	 * @param fireAngle
	 *            The angle the bullet should fire at in degrees, from 0.        
	 */
	public boolean shoot( Entity firer, EntityWorld world, float fireAngle ) {
		return false;
	}
	
	/**
	 * Called when the gun is reloaded. Returns whether reloading was successful.        
	 */
	public boolean reload() {
		return false;
	}
	
	/**
	 * Sets the Gun's damage to a new value.
	 * 
	 * @param damage
	 *            The new damage of the gun.     
	 */
	public void setDamage(float damage) {
		this.damage = damage;
	}
	
	/**
	 * Sets the delay between the Gun's to a new value.
	 * 
	 * @param delay
	 *            The new fire delay of the gun.     
	 */
	public void setFireDelay(float delay) {
		this.fireDelay.setMaxValue(delay);
		this.fireDelay.setCurrentValue(delay);
	}
	
	/**
	 * Sets the Gun's range to a new value.
	 * 
	 * @param range
	 *            The new range of the gun.     
	 */
	public void setRange(float range) {
		this.range = range;
	}
	
	/**
	 * Sets the velocity of the Gun's bullets to a new value.
	 * 
	 * @param velocity
	 *            The new bullet velocity for the gun.     
	 */
	public void setBulletVelocity(float velocity) {
		this.bulletVelocity = velocity;
	}
	
	/**
	 * Sets the Gun's maximum ammo to a new value.
	 * 
	 * @param ammo
	 *            The new maximum ammo of the gun.     
	 */
	public void setMaxAmmo(float ammo) {
		this.ammo.setMaxValue(ammo);
		this.ammo.setCurrentValue(ammo);
	}
	
	/**
	 * Sets the time the Gun takes to reload to a new value.
	 * 
	 * @param time
	 *            The new reload time of the gun.     
	 */
	public void setReloadTime(float time) {
		this.reloadTime.setMaxValue(time);
		this.reloadTime.setCurrentValue(time);
	}
}

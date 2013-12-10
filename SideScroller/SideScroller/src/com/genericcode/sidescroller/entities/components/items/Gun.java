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

	protected float damage;
	protected float fireDelay;
	protected float range;
	protected float bulletVelocity;
	
	protected GenericStat ammo;
	protected float reloadTime;
	
	protected boolean ultra = false;
	
	protected Sprite sprite;
	
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
	 * Sets the delay between the Gun's shots to a new value.
	 * 
	 * @param delay
	 *            The new fire delay of the gun.     
	 */
	public void setFireDelay(float delay) {
		this.fireDelay = delay;
		this.useTime = delay;
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
		this.reloadTime = time;
	}
	
	/**
	 * Returns the Gun's damage value.     
	 */
	public float getDamage() {
		return this.damage;
	}
	
	/**
	 * Returns the fire delay of the gun.
	 */
	public float getFireDelay(float delay) {
		return this.fireDelay;
	}
	
	/**
	 * Returns the Gun's range.    
	 */
	public float getRange() {
		return this.range;
	}
	
	/**
	 * Returns the velocity of the Gun's bullets.     
	 */
	public float getBulletVelocity() {
		return this.bulletVelocity;
	}
	
	/**
	 * Returns the Gun's maximum ammo.     
	 */
	public float getMaxAmmo() {
		return (float) this.ammo.getMaxValue();
	}
	
	/**
	 * Returns the Gun's current ammo.     
	 */
	public float getCurrentAmmo() {
		return (float) this.ammo.getCurrentValue();
	}
	
	/**
	 * gets the time the Gun takes to reload to a new value.     
	 */
	public float getReloadTime() {
		return this.reloadTime;
	}
}

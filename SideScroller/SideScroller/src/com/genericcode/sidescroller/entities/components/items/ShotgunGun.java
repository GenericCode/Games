package com.genericcode.sidescroller.entities.components.items;

import com.badlogic.gdx.math.Vector2;
import com.genericcode.sidescroller.entities.components.GenericStat;
import com.lostcode.javalib.entities.Entity;
import com.lostcode.javalib.entities.EntityWorld;
import com.lostcode.javalib.entities.components.physical.Body;
import com.lostcode.javalib.entities.processes.ExpirationProcess;

public class ShotgunGun extends Gun {

	public int shotCount;
	
	/**
	 * Constructs a Shotgun Item
	 * 
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
	 * @param shotCount
	 * 			  An int representing the number of bullets the shotgun fires per shot.
	 *            
	 */
	public ShotgunGun( float damage, float fireDelay, float range, float bulletVelocity, GenericStat ammo, float reloadTime, int shotCount) {
		super("shotgun", damage, fireDelay, range, bulletVelocity, ammo, reloadTime);
		this.shotCount = shotCount;
	}

	@Override
	public boolean shoot( Entity firer, EntityWorld world, Vector2 fireAngle ) {
		Body b = (Body) firer.getComponent(Body.class);
		Vector2 position = b.getPosition();
		if(this.ammo.getCurrentValue() > 0) {
			for( int i = 0; i<shotCount; i++) {
				world.getProcessManager().attach( new ExpirationProcess( (float)( this.range/this.bulletVelocity),
						world.createEntity("Bullet", "yellow", position, fireAngle.cpy().rotate((float) (2*i-1*shotCount)).div(fireAngle.len()).scl(this.bulletVelocity), firer, this.damage )));
			}
			this.ammo.drain(1);
			return true;
		}
		return false;
	}
	
	@Override
	public boolean reload() {
		if(this.ammo.getCurrentValue() < this.ammo.getMaxValue()) {
			this.ammo.fillMax();
			return true;
		}
		return false;
	}
	
	@Override
	public boolean use( String use, Object... args ) {
		if( use == "primary") {
			return this.shoot((Entity)args[0], (EntityWorld)args[1], (Vector2)args[2]);
		}
		if( use == "reload") {
			return this.reload();
		}
		return false;
	}
}

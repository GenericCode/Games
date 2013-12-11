package com.genericcode.sidescroller.entities.components.items;

import com.badlogic.gdx.math.Vector2;
import com.genericcode.sidescroller.entities.components.GenericStat;
import com.genericcode.sidescroller.entities.processes.abilities.BulletstormProcess;
import com.lostcode.javalib.entities.Entity;
import com.lostcode.javalib.entities.EntityWorld;
import com.lostcode.javalib.entities.components.physical.Body;
import com.lostcode.javalib.entities.processes.ExpirationProcess;

public class SMGGun extends Gun {

	public SMGGun( float damage, float fireDelay, float range, float bulletVelocity, GenericStat ammo, float reloadTime) {
		super("smg", damage, fireDelay, range, bulletVelocity, ammo, reloadTime);
	}
	
	@Override
	public boolean shoot( Entity firer, EntityWorld world, Vector2 fireAngle ) {
		Body b = (Body) firer.getComponent(Body.class);
		Vector2 position = b.getPosition();
		if(this.ammo.getCurrentValue() > 0) {
			world.getProcessManager().attach( new ExpirationProcess( (float)( this.range/this.bulletVelocity),
					world.createEntity("Bullet", "red", position, fireAngle.div(fireAngle.len()).scl(this.bulletVelocity).rotate( (float) (20*Math.random()-10) ).add(b.getLinearVelocity()), firer, this.damage )));
			this.ammo.drain(1);
			return true;
		}
		return false;
	}
	
	@Override
	public boolean secondary( Entity firer, EntityWorld world, Vector2 fireAngle ) {
		if(this.ammo.getCurrentValue() > 0) {
			world.getProcessManager().attach( new BulletstormProcess( 10, firer, this, fireAngle));
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
		if( use == "secondary") {
			return this.secondary((Entity)args[0], (EntityWorld)args[1], (Vector2)args[2]);
		}
		if( use == "reload") {
			return this.reload();
		}
		return false;
	}
	
}

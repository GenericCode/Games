package com.genericcode.sidescroller.entities.components.items;

import com.badlogic.gdx.math.Vector2;
import com.genericcode.sidescroller.entities.components.GenericStat;
import com.lostcode.javalib.entities.Entity;
import com.lostcode.javalib.entities.EntityWorld;
import com.lostcode.javalib.entities.components.generic.Cooldown;
import com.lostcode.javalib.entities.components.physical.Body;
import com.lostcode.javalib.entities.processes.ExpirationProcess;

public class SniperGun extends Gun {

	public SniperGun( float damage, Cooldown fireDelay, float range, float bulletVelocity, GenericStat ammo, Cooldown reloadTime) {
		super("sniper", damage, fireDelay, range, bulletVelocity, ammo, reloadTime);
	}
	
	@Override
	public boolean shoot( Entity firer, EntityWorld world, Vector2 fireAngle ) {
		Body b = (Body) firer.getComponent(Body.class);
		Vector2 position = b.getPosition();
		if(this.fireDelay.isFinished() && this.ammo.getCurrentValue() > 0)
		{
			world.getProcessManager().attach( new ExpirationProcess( (float)( this.range/this.bulletVelocity),
					world.createEntity("Bullet", "red", position, fireAngle.div(fireAngle.len()).scl(this.bulletVelocity), firer, this.damage )));
			this.ammo.drain(1);
			this.fireDelay.fillMax();
			System.out.println("weeeeeeeeeeeeeeeeeeeeeeeeee");
			return true;
		}
		return false;
	}
	
	@Override
	public boolean reload() {
		if(this.reloadTime.isFinished() && this.ammo.getCurrentValue() < this.ammo.getMaxValue())
		{
			this.ammo.fillMax();
			this.reloadTime.fillMax();
			return true;
		}
		return false;
	}
	
	@Override
	public boolean use( String use, Object... args ) {
		if( use == "primary") {
			this.shoot((Entity)args[0], (EntityWorld)args[1], (Vector2)args[2]);
		}
		return false;
	}

}

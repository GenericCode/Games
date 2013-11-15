package com.genericcode.sidescroller.entities.components.items;

import com.badlogic.gdx.math.Vector2;
import com.lostcode.javalib.entities.Entity;
import com.lostcode.javalib.entities.EntityWorld;
import com.lostcode.javalib.entities.components.abstracted.Stat;
import com.lostcode.javalib.entities.components.generic.Cooldown;
import com.lostcode.javalib.entities.components.physical.Body;
import com.lostcode.javalib.entities.processes.ExpirationProcess;

public class SniperGun extends Gun {

	public SniperGun(String type, float damage, Cooldown fireDelay, float range, float bulletVelocity, Stat ammo, Cooldown reloadTime) {
		super("sniper", damage, fireDelay, range, bulletVelocity, ammo, reloadTime);
	}
	
	@Override
	public boolean shoot( Entity firer, EntityWorld world, float fireAngle ) {
		Body b = (Body) firer.getComponent(Body.class);
		Vector2 position = b.getPosition();
		if(this.fireDelay.isFinished() && this.ammo.getCurrentValue() > 0)
		{
			world.getProcessManager().attach( new ExpirationProcess( (float)( this.range/this.bulletVelocity),
					world.createEntity("Bullet", "red", position, new Vector2((float) this.bulletVelocity,0f).rotate(fireAngle), firer, this.damage )));
			this.ammo.drain(1);
			this.fireDelay.fillMax();
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

}

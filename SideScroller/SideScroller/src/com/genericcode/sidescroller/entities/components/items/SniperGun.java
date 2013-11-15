package com.genericcode.sidescroller.entities.components.items;

import com.badlogic.gdx.math.Vector2;
import com.lostcode.javalib.entities.Entity;
import com.lostcode.javalib.entities.EntityWorld;
import com.lostcode.javalib.entities.components.abstracted.Stat;
import com.lostcode.javalib.entities.components.generic.Cooldown;
import com.lostcode.javalib.entities.components.physical.Body;

public class SniperGun extends Gun {

	public SniperGun(String type, Stat damage, Cooldown fireDelay, Stat range, Stat bulletVelocity, Stat ammo, Cooldown reloadTime) {
		super("sniper", damage, fireDelay, range, bulletVelocity, ammo, reloadTime);
	}
	
	@Override
	public void shoot( Entity firer, EntityWorld world, float fireAngle ) {
		Body b = (Body) firer.getComponent(Body.class);
		Vector2 position = b.getPosition();
		
		world.createEntity("Bullet", "red", position, new Vector2((float) this.bulletVelocity.getCurrentValue(),0f).rotate(fireAngle), firer, this.damage );
	}

}

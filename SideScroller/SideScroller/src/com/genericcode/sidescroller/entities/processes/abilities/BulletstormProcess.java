package com.genericcode.sidescroller.entities.processes.abilities;

import com.badlogic.gdx.math.Vector2;
import com.genericcode.sidescroller.entities.components.items.SMGGun;
import com.lostcode.javalib.entities.Entity;
import com.lostcode.javalib.entities.EntityWorld;
import com.lostcode.javalib.entities.components.generic.Cooldown;
import com.lostcode.javalib.entities.components.physical.Body;
import com.lostcode.javalib.entities.processes.ExpirationProcess;
import com.lostcode.javalib.entities.processes.Process;
import com.lostcode.javalib.entities.processes.ProcessState;
import com.lostcode.javalib.utils.SoundManager;

/**
 * Ability process for secondary fire of the SMG
 * @author GenericCode
 * @Created November 13, 2013
 */

public class BulletstormProcess extends Process {

	private float duration;
	private Entity owner;
	private SMGGun gun;
	private Vector2 fireAngle;
	
	/**
	 * Creates a Bulletstorm.
	 * 
	 * @param duration The duration of the ability in seconds.
	 * @param owner The owner of the ability.
	 * @param gun The SMG gun using this ability.
	 * @param fireAngle The vector the ability will aim along.
	 */
	public BulletstormProcess( float duration, Entity owner, SMGGun gun, Vector2 fireAngle ) {
		this.duration = duration;
		this.owner = owner;
		this.gun = gun;
		this.fireAngle = fireAngle;
	}
	@Override
	public void update(EntityWorld world, float deltaTime) {
		Body b = (Body) owner.getComponent(Body.class);
		Cooldown c = (Cooldown) owner.getComponent(Cooldown.class);
		
		gun.ammo.drain(1);
		duration -= deltaTime;
		
		if( gun.ammo.getCurrentValue() <= 0 || duration <= 0 )
			end(ProcessState.SUCCEEDED);	
		
		c.fillMax();
		
		world.getProcessManager().attach( new ExpirationProcess( (float)( gun.range/gun.bulletVelocity ),
				world.createEntity("Bullet", "red", b.getPosition(), fireAngle.cpy().div(fireAngle.len()).scl(gun.bulletVelocity ).rotate( (float) (4*gun.inaccuracy*Math.random()-2*gun.inaccuracy) ).add(b.getLinearVelocity()), owner, gun.damage )));
		
		SoundManager.playSound("shot", 0.5f);
	}

	@Override
	public void onEnd(EntityWorld world, ProcessState endState) {
		Cooldown c = (Cooldown) owner.getComponent(Cooldown.class);
		c.setMaxValue(4);
		c.fillMax();
	}

}

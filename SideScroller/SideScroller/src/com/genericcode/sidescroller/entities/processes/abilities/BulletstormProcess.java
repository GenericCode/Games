package com.genericcode.sidescroller.entities.processes.abilities;

import com.badlogic.gdx.math.Vector2;
import com.genericcode.sidescroller.entities.components.items.SMGGun;
import com.lostcode.javalib.entities.Entity;
import com.lostcode.javalib.entities.EntityWorld;
import com.lostcode.javalib.entities.components.physical.Body;
import com.lostcode.javalib.entities.processes.ExpirationProcess;
import com.lostcode.javalib.entities.processes.Process;
import com.lostcode.javalib.entities.processes.ProcessState;

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
	 * @param gun The gun using this ability.
	 * @param fireAngle The vector the ability will aim upon.
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
		duration -= deltaTime;
		if( duration <= 0)
			end(ProcessState.SUCCEEDED);
		world.getProcessManager().attach( new ExpirationProcess( (float)( gun.getRange()/gun.getBulletVelocity() ),
				world.createEntity("Bullet", "red", b.getPosition(), fireAngle.div(fireAngle.len()).scl(gun.getBulletVelocity() ).rotate( (float) (20*Math.random()-10) ).add(b.getLinearVelocity()), owner, gun.getDamage() )));

	}

	@Override
	public void onEnd(EntityWorld world, ProcessState endState) {
		// TODO Auto-generated method stub

	}

}

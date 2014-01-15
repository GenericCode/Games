package com.genericcode.sidescroller.entities.processes;

import com.badlogic.gdx.utils.Array;
import com.lostcode.javalib.entities.Entity;
import com.lostcode.javalib.entities.EntityWorld;
import com.lostcode.javalib.entities.components.generic.Health;
import com.lostcode.javalib.entities.components.physical.Sensor;
import com.lostcode.javalib.entities.processes.Process;
import com.lostcode.javalib.entities.processes.ProcessState;

public class DamagingExplosionProcess extends Process {

	Entity explosion;
	float duration;
	final float DAMAGE;
	
	Array<Entity> damaged = new Array<Entity>();
	
	public DamagingExplosionProcess( Entity explosion, float duration, float damage ) {
		this.explosion = explosion;
		this.duration = duration;
		this.DAMAGE = damage;
	}
	@Override
	public void update(EntityWorld world, float deltaTime) {
		Sensor s = (Sensor)explosion.getComponent(Sensor.class);
		if (DAMAGE <= 0 )
			this.end(ProcessState.ABORTED);
		for( Entity e : s.getEntitiesInView() )
			if( e.hasComponent(Health.class) && !damaged.contains(e, true)) {
				Health h = (Health)e.getComponent(Health.class);
				h.drain(DAMAGE);
				damaged.add(e);
			}
		if( (duration -= deltaTime) <= 0 )
			this.end(ProcessState.SUCCEEDED);
				
	}

	@Override
	public void onEnd(EntityWorld world, ProcessState endState) {
		explosion.delete();
	}

}

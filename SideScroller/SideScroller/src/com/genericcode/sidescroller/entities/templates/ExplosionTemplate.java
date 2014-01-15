/**
 * 
 */
package com.genericcode.sidescroller.entities.templates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.genericcode.sidescroller.entities.processes.DamagingExplosionProcess;
import com.lostcode.javalib.entities.Entity;
import com.lostcode.javalib.entities.EntityWorld;
import com.lostcode.javalib.entities.components.generic.View;
import com.lostcode.javalib.entities.components.physical.Body;
import com.lostcode.javalib.entities.components.physical.Particle;
import com.lostcode.javalib.entities.components.render.ParticleEffect;
import com.lostcode.javalib.entities.templates.EntityTemplate;
import com.lostcode.javalib.utils.Convert;

public class ExplosionTemplate implements EntityTemplate {
	
	@Override
	public Entity buildEntity(Entity e, EntityWorld world, Object... args) {
		//args
		Vector2 position = (Vector2)args[0];
		final float DAMAGE = (Float)args[1];
		
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.DynamicBody;
		bodyDef.position.set(position);
		Body b = new Body(world, e, null);
		
		CircleShape circle = new CircleShape();
		circle.setRadius(Convert.pixelsToMeters((Float)args[2]));
		
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = circle;
		
		e.addComponent(b);
		
		//Sensor for DamagingExplosionProcess
		e.addComponent( new View( e, Convert.pixelsToMeters((Float)args[2]), 1f ) );
		
		//location of the explosion.
		e.addComponent(new Particle(e, position, 0f, new Vector2(0,0)));
		
		ParticleEffect p = (ParticleEffect) e.addComponent(new ParticleEffect(
				Gdx.files.internal("data/Particles/explosion"),
				Gdx.files.internal("data/Particles")));
		p.start();
		
		world.getProcessManager().attach( new DamagingExplosionProcess(e, 1f, DAMAGE) );
		
		return e;
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}

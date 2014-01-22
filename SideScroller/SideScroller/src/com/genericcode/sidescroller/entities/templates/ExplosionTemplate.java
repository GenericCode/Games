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
		final float DAMAGE = (Float) args[1];
		final float RADIUS = (Float) args[2];
		if( RADIUS > 0 && DAMAGE > 0 ) {
			System.out.println(RADIUS);
			
			BodyDef bodyDef = new BodyDef();
			bodyDef.type = BodyType.StaticBody;
			bodyDef.position.set(position);
			
			CircleShape circle = new CircleShape();
			circle.setRadius(0f);
			
			FixtureDef fixtureDef = new FixtureDef();
			fixtureDef.shape = circle;
			
			Body b = new Body(world, e, bodyDef, fixtureDef);
			
			e.addComponent(b);
			
			//Sensor for DamagingExplosionProcess
			View sensor = new View( e, Convert.metersToPixels(RADIUS), 1f, 20 );
			e.addComponent( sensor );
			
			world.getProcessManager().attach( new DamagingExplosionProcess(e, 1f, DAMAGE) );
		}
		
		//location of the explosion.
		e.addComponent(new Particle(e, position, 0f, new Vector2(0,0)));
		
		ParticleEffect p = (ParticleEffect) e.addComponent(new ParticleEffect(
				Gdx.files.internal("data/Particles/explosion"),
				Gdx.files.internal("data/Particles")));
		p.start();
		
		return e;
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}

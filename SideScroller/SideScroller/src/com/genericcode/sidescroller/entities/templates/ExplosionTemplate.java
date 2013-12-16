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
import com.lostcode.javalib.entities.Entity;
import com.lostcode.javalib.entities.EntityWorld;
import com.lostcode.javalib.entities.components.ComponentManager;
import com.lostcode.javalib.entities.components.generic.Health;
import com.lostcode.javalib.entities.components.physical.Body;
import com.lostcode.javalib.entities.components.physical.Collidable;
import com.lostcode.javalib.entities.components.physical.Particle;
import com.lostcode.javalib.entities.components.render.ParticleEffect;
import com.lostcode.javalib.entities.templates.EntityTemplate;
import com.lostcode.javalib.utils.Convert;

public class ExplosionTemplate implements EntityTemplate {
	
	public ExplosionTemplate() {}

	@Override
	public Entity buildEntity(Entity e, EntityWorld world, Object... args) {
		//args
		Vector2 position = (Vector2)args[0];
		final float DAMAGE = (Float)args[1];
		
		//Damaging radius of the explosion
		BodyDef bd = new BodyDef();
		bd.type = BodyType.StaticBody;
		
		CircleShape circle = new CircleShape();
		circle.setRadius(Convert.metersToPixels( (Float)args[2] ));
		
		FixtureDef fd = new FixtureDef();
		fd.shape = circle;
		
		bd.position.set(position);
		
		Body b  = new Body(world, e, bd, fd);
		
		e.addComponent(b);
		
		//location of the explosion.
		e.addComponent(new Particle(e, position, 0f, new Vector2(0,0)));
		
		ParticleEffect p = (ParticleEffect) e.addComponent(new ParticleEffect(
				Gdx.files.internal("data/Particles/explosion"),
				Gdx.files.internal("data/Particles")));
		p.start();
		
		new Collidable() {

			@Override
			public void onAdd(ComponentManager container) {
			}

			@Override
			public void onRemove(ComponentManager container) {
			}

			@Override
			public void onBeginContact(Entity container, Entity victim) {
				if ( victim.hasComponent(Health.class) ) {
					Health h = (Health) container.getComponent(Health.class);
					h.drain(DAMAGE);
				}
			}

			@Override
			public float continueCollision(Entity container, Entity victim) {
				return 0;
			}

		};
		
		return e;
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}

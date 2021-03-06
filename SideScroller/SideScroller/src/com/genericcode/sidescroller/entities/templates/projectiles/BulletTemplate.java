package com.genericcode.sidescroller.entities.templates.projectiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.lostcode.javalib.entities.Entity;
import com.lostcode.javalib.entities.EntityWorld;
import com.lostcode.javalib.entities.components.ComponentManager;
import com.lostcode.javalib.entities.components.generic.Bullet;
import com.lostcode.javalib.entities.components.generic.Health;
import com.lostcode.javalib.entities.components.physical.Collidable;
import com.lostcode.javalib.entities.components.physical.Particle;
import com.lostcode.javalib.entities.components.render.Sprite;
import com.lostcode.javalib.entities.templates.EntityTemplate;
import com.lostcode.javalib.utils.LogManager;

public class BulletTemplate implements EntityTemplate {

	private Texture bulletTex;
	private TextureRegion[] bulletRect;
	
	public BulletTemplate() {
		bulletTex = new Texture(Gdx.files.internal("data/Textures/bulletSprites.png"));
		bulletRect = new TextureRegion[]{
			new TextureRegion(bulletTex, 0, -1, 2, 1), //Blue
			new TextureRegion(bulletTex, 0, -2, 2, 1), //Red
			new TextureRegion(bulletTex, 2, -2, 2, 1), //Yellow
			new TextureRegion(bulletTex, 2, -1, 2, 1), //Purple
			new TextureRegion(bulletTex, 0, 2, 4, 2)  //Big
		};
	}
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@SuppressWarnings("unused")
	@Override
	public Entity buildEntity(Entity e, EntityWorld world, Object... args) {
		e.init("", "Bullet", "Projectile");
		
		//ARGS
		String color = (String)args[0];
		TextureRegion region = bulletRect[0]; //blue
		if(color.equalsIgnoreCase("red"))
			region = bulletRect[1];
		else if(color.equalsIgnoreCase("yellow"))
			region = bulletRect[2];
		else if(color.equalsIgnoreCase("purple"))
			region = bulletRect[3];
		else if(color.equalsIgnoreCase("big"))
			region = bulletRect[4];
		
		Vector2 position = (Vector2)args[1];
		Vector2 linearVelocity = (Vector2)args[2];
		float rotation = (float) Math.toRadians(linearVelocity.angle());
		Entity firer = (Entity)args[3];
		float damage = (Float)args[4];
		
		//Particle
		Particle p = (Particle)e.addComponent(new Particle(e, position, rotation, new Vector2(region.getRegionWidth()/2f, region.getRegionHeight()/2f)));
		
		if(linearVelocity.len() <= 0){
			LogManager.error("BulletCreation", "VELOCITY IS LESS THAN 0");
			linearVelocity = new Vector2(1,1);
		}
		
		p.setLinearVelocity(linearVelocity);
		
		

		//Bullet
		Bullet b = (Bullet)e.addComponent(new Bullet(firer, damage));
		
		//OnCollide
		Collidable col = (Collidable)e.addComponent(
			new Collidable(){
				@Override
				public void onAdd(ComponentManager container) {
				}
	
				@Override
				public void onRemove(ComponentManager container) {	
				}
	
				@Override
				public void onBeginContact(Entity container, Entity victim) {					
					Bullet b = (Bullet) container.getComponent(Bullet.class);
					
					//IF THE VICTIM HAS HEALTH! RAWR
					if(victim == null)
						return;
					if(victim.hasComponent(Health.class))
					{
						Health h = (Health) victim.getComponent(Health.class);
						h.drain(b.getDamage());
					}
					
					container.delete();
					
				}

				@Override
				public float continueCollision(Entity container, Entity victim) {
					Bullet b = (Bullet) container.getComponent(Bullet.class);
					
					if (victim == null) {
						container.delete();
						return 0;
					}
					
					//If the bullet hits the firer, continue firing the bullet.
					if(victim.getGroup().equals(b.getFirer().getGroup())) 
						return 1;
					else 
						return 0;
				}
			});
		
		e.addComponent(new Sprite(bulletTex, region));
		
		return e;
	}

}

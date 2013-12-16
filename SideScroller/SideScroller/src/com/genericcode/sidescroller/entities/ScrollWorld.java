package com.genericcode.sidescroller.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.lostcode.javalib.entities.EntityWorld;
import com.lostcode.javalib.entities.systems.generic.TrackingCameraSystem;
import com.lostcode.javalib.utils.Convert;
import com.genericcode.sidescroller.entities.systems.PlayerControlSystem;
import com.genericcode.sidescroller.entities.templates.PlayerTemplate;
import com.genericcode.sidescroller.entities.templates.projectiles.BulletTemplate;

@SuppressWarnings("deprecation")
public class ScrollWorld extends EntityWorld {
	
	public ScrollWorld(InputMultiplexer input, Camera camera) {
		super(input, camera, new Vector2(0, -9.8f));
		
		debugView.enabled = true;
		debugView.visible = true;//TODO DELETE
	}

	@Override
	public void process() {
		super.process();
	}

	@Override
	public Rectangle getBounds() {
		return Convert.pixelsToMeters(
				new Rectangle(
					-Gdx.graphics.getWidth() * 2, 
					-Gdx.graphics.getHeight() / 2, 
					Gdx.graphics.getWidth() * 4, 
					Gdx.graphics.getHeight()));
	}

	@Override
	protected void positionCamera() { }
	
	@Override
	protected void buildSystems() {		
		super.buildSystems();
		
		//Input
		systems.addSystem(new PlayerControlSystem(input));
		//Render
		systems.addSystem(new TrackingCameraSystem("Player", camera, getBounds()));

	}

	@Override
	protected void buildTemplates() {
		super.buildTemplates();
		
		addTemplate("Player", new PlayerTemplate());
		addTemplate("Bullet", new BulletTemplate());
		

	}

	@Override
	protected void buildEntities() {
		super.buildEntities();
		
		createEntity("Player", "Generic");
	}

	@Override
	protected void buildSpriteSheet() {
		// TODO Auto-generated method stub
		
	}
	
}

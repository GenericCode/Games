package com.genericcode.sidescroller.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.lostcode.javalib.entities.EntityWorld;
import com.lostcode.javalib.entities.systems.generic.TrackingCameraSystem;
import com.lostcode.javalib.entities.systems.render.HealthRenderSystem;
import com.lostcode.javalib.utils.Convert;
import com.genericcode.sidescroller.entities.systems.CooldownRenderSystem;
import com.genericcode.sidescroller.entities.systems.MookSystem;
import com.genericcode.sidescroller.entities.systems.PlayerControlSystem;
import com.genericcode.sidescroller.entities.templates.ExplosionTemplate;
import com.genericcode.sidescroller.entities.templates.MookTemplate;
import com.genericcode.sidescroller.entities.templates.PlayerTemplate;
import com.genericcode.sidescroller.entities.templates.projectiles.BulletTemplate;
import com.genericcode.sidescroller.entities.templates.structures.WallTemplate;

public class ScrollWorld extends EntityWorld {
	
	public ScrollWorld(InputMultiplexer input, Camera camera) {
		super(input, camera, new Vector2(0,0));
		
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
		systems.addSystem(new PlayerControlSystem(input, "Generic"));
		systems.addSystem(new MookSystem("mook"));
		//Render
		systems.addSystem(new TrackingCameraSystem("Generic", camera));
		systems.addSystem(new CooldownRenderSystem(camera, 
				Gdx.files.internal("data/Textures/cooldownbarback.png"),
				Gdx.files.internal("data/Textures/cooldownbarfront.png")));
		systems.addSystem(new HealthRenderSystem(camera, 
				Gdx.files.internal("data/Textures/healthbarback.png"),
				Gdx.files.internal("data/Textures/healthbarfront.png")));

	}

	@Override
	protected void buildTemplates() {
		super.buildTemplates();
		
		addTemplate("Player", new PlayerTemplate());
		addTemplate("Bullet", new BulletTemplate());
		addTemplate("Explosion", new ExplosionTemplate());
		addTemplate("Mook", new MookTemplate());
		addTemplate("Wall", new WallTemplate());
		

	}

	@Override
	protected void buildEntities() {
		super.buildEntities();
		
		createEntity("TileMap", "data/untitled.tmx", null);
		createEntity("Player", "Generic");
		createEntity("Wall", "invisible", 2000f,10f, new Vector2(0,210f));
		createEntity("Wall", "invisible", 2000f,10f, new Vector2(0,-240f));
		/*createEntity("Mook", "leftTeam", new Vector2(50,5));
		createEntity("Mook", "leftTeam", new Vector2(50,4));
		createEntity("Mook", "leftTeam", new Vector2(50,3));
		createEntity("Mook", "leftTeam", new Vector2(50,2));*/
		createEntity("Mook", "leftTeam", new Vector2(50,1));
	}

	@Override
	protected void buildSpriteSheet() {
		// TODO Auto-generated method stub
		
	}
	
}

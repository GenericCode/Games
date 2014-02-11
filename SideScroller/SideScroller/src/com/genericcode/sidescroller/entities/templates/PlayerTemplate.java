package com.genericcode.sidescroller.entities.templates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.utils.Array;
import com.genericcode.sidescroller.entities.components.GenericHealth;
import com.genericcode.sidescroller.entities.components.GenericStat;
import com.genericcode.sidescroller.entities.components.items.DualPistolsGun;
import com.genericcode.sidescroller.entities.components.items.FragGrenadeItem;
import com.genericcode.sidescroller.entities.components.items.SMGGun;
import com.genericcode.sidescroller.entities.components.items.ShotgunGun;
import com.genericcode.sidescroller.entities.components.items.SniperGun;
import com.lostcode.javalib.entities.Entity;
import com.lostcode.javalib.entities.EntityWorld;
import com.lostcode.javalib.entities.components.generic.Cooldown;
import com.lostcode.javalib.entities.components.generic.Inventory;
import com.lostcode.javalib.entities.components.generic.Item;
import com.lostcode.javalib.entities.components.physical.Body;
import com.lostcode.javalib.entities.components.render.Sprite;
import com.lostcode.javalib.entities.templates.EntityTemplate;
import com.lostcode.javalib.utils.Convert;

public class PlayerTemplate implements EntityTemplate {
	
	//private static final float BODY_RADIUS = 10f;
	
	private Texture playerTexture;
	private TextureRegion region;
	
	//private Texture shipsTexture;
	//private TextureRegion leftRegion;
	
	public PlayerTemplate() {
		playerTexture = new Texture(Gdx.files.internal("data/Textures/MAN.png"));
		region = new TextureRegion(playerTexture, 0, 0, 15, 48);
		//shipsTexture = new Texture(Gdx.files.internal("data/Textures/playerships.png"));
		
		//leftRegion = new TextureRegion(shipsTexture, 0, 0, 16, 16);
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Entity buildEntity(Entity e, EntityWorld world, Object... args) {
		String name = (String)args[0];
		
		e.init(name, "Player", "player");
		
		Vector2 pos = new Vector2(0,0);
		Sprite s = new Sprite();
		
		s = new Sprite(playerTexture, region);
		//s = new Sprite(shipsTexture, leftRegion);
		
		e.addComponent(s);
		
		BodyDef bd = new BodyDef();
		bd.type = BodyType.DynamicBody;
		
		CircleShape circle = new CircleShape();
		circle.setRadius(Convert.metersToPixels(.01f));//BODY_RADIUS));
		PolygonShape box = new PolygonShape();
		box.setAsBox(Convert.pixelsToMeters(7.5f), Convert.pixelsToMeters(5f));
		
		
		FixtureDef fd = new FixtureDef();
		fd.shape = box;//circle;
		
		bd.position.set(pos);
		
		Body b  = new Body(world, e, bd, fd);
		
		e.addComponent(b);
		
		GenericHealth h = new GenericHealth(e, world, 1);
		h.render = true;
		
		e.addComponent(h);
		
		Cooldown cd = new Cooldown(world,0);
		cd.render = true;
		
		e.addComponent(cd);
		
		Array<Item> contents = new Array<Item>();
		// Gun g = new Gun(float damage, float fireDelay, float range, float bulletVelocity, GenericStat ammo, float reloadTime, ...);
		SniperGun sg = new SniperGun(10f, 1f, Convert.metersToPixels(500), Convert.metersToPixels(600), new GenericStat(5f), 5f);
		ShotgunGun sgg = new ShotgunGun(1f, .75f, Convert.metersToPixels(100), Convert.metersToPixels(400), new GenericStat(10f), 2f, 10);
		SMGGun smg = new SMGGun(2f, .25f, Convert.metersToPixels(250), Convert.metersToPixels(400), new GenericStat(30f), 2f, 10);
		DualPistolsGun dpg = new DualPistolsGun( 3f, .75f, Convert.metersToPixels(300), Convert.metersToPixels(400), new GenericStat(10f), 2f);
		
		FragGrenadeItem fg = new FragGrenadeItem(50f, 0f, 20f, new GenericStat(3f));
		
		contents.add(sg);
		contents.add(sgg);
		contents.add(smg);
		contents.add(dpg);
		contents.add(fg);
		Inventory inv = new Inventory(e, world, 6, contents);
		inv.select(fg);//FIX ALL THIS
		
		e.addComponent(inv);
		
		return e;
	}

}

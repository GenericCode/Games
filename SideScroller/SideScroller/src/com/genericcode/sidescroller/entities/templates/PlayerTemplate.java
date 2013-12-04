package com.genericcode.sidescroller.entities.templates;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.utils.Array;
import com.genericcode.sidescroller.entities.components.GenericHealth;
import com.genericcode.sidescroller.entities.components.GenericStat;
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
	
	private static final float BODY_RADIUS = 10f;
	
	public PlayerTemplate() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Entity buildEntity(Entity e, EntityWorld world, Object... args) {
		String name = (String)args[0];
		//int[][] stats = (int[][])args[1];
		
		e.init(name, "Player", "Player");
		
		Vector2 pos = new Vector2(0,0);
		Sprite s = new Sprite();
		
		BodyDef bd = new BodyDef();
		bd.type = BodyType.DynamicBody;
		
		CircleShape circle = new CircleShape();
		circle.setRadius(Convert.metersToPixels(BODY_RADIUS));
		
		FixtureDef fd = new FixtureDef();
		fd.shape = circle;
		
		bd.position.set(pos);
		
		Body b  = new Body(world, e, bd, fd);
		
		e.addComponent(b);
		
		GenericHealth h = new GenericHealth(e, world, 0);
		
		e.addComponent(h);
		
		Cooldown cd = new Cooldown(world,0);
		
		e.addComponent(cd);
		
		Array<Item> contents = new Array<Item>();
		// Gun g = new Gun(float damage, float fireDelay, float range, float bulletVelocity, GenericStat ammo, float reloadTime, ...);
		SniperGun sg = new SniperGun(10f, 1f, Convert.metersToPixels(500), Convert.metersToPixels(700), new GenericStat(5f), 5f);
		ShotgunGun sgg = new ShotgunGun(3f, .75f, Convert.metersToPixels(100), Convert.metersToPixels(600), new GenericStat(10f), 2f, 10);
		SniperGun sm = new SniperGun(2f, .125f, Convert.metersToPixels(250), Convert.metersToPixels(600), new GenericStat(30f), 2f);
		contents.add(sg);
		contents.add(sgg);
		contents.add(sm);
		Inventory inv = new Inventory(e, world, 6, contents);
		inv.select(sg);//FIX ALL THIS
		
		e.addComponent(inv);
		
		return e;
	}

}

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
import com.lostcode.javalib.entities.components.abstracted.Stat;
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
		circle.setRadius(Convert.pixelsToMeters(BODY_RADIUS));
		
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
		SniperGun sg = new SniperGun(10, 1, 500, 220, new GenericStat(200000000f), 10);
		ShotgunGun sgg = new ShotgunGun(10, 1, 75, 220, new GenericStat(200000000f), 10, 5);
		contents.add(sg);
		contents.add(sgg);
		Inventory inv = new Inventory(e, world, 6, contents);
		//inv.select(sf);//FIX ALL THIS
		
		e.addComponent(inv);
		
		return e;
	}

}

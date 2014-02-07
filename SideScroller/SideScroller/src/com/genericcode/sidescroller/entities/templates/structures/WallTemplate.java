package com.genericcode.sidescroller.entities.templates.structures;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.lostcode.javalib.entities.Entity;
import com.lostcode.javalib.entities.EntityWorld;
import com.lostcode.javalib.entities.components.physical.Body;
import com.lostcode.javalib.entities.templates.EntityTemplate;

public class WallTemplate implements EntityTemplate {

	@Override
	public Entity buildEntity(Entity e, EntityWorld world, Object... args) {
		String group = (String)args[0];
		e.init("wall", group, "Wall");
		float l = (Float)args[1];
		float w = (Float)args[2];
		Vector2 pos = (Vector2)args[3];
		PolygonShape area = new PolygonShape();
		area.setAsBox(l, w);
		
		BodyDef bd = new BodyDef();
		bd.type = BodyType.StaticBody;
		
		FixtureDef fd = new FixtureDef();
		fd.shape = area;
		
		bd.position.set(pos);
		
		Body b  = new Body(world, e, bd, fd);
		
		e.addComponent(b);
		return e;
	}
	
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}

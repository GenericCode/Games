package com.genericcode.sidescroller.entities.systems;

import com.lostcode.javalib.entities.Entity;
import com.lostcode.javalib.entities.components.physical.Body;
import com.lostcode.javalib.entities.components.render.Renderable;
import com.lostcode.javalib.entities.systems.TagSystem;

/**
 * @author GenericCode
 *
 */

public class MookSystem extends TagSystem {
	
	public MookSystem(String tag) {
		super("mook");
	}

	@Override
	public void dispose() {}

	@Override
	protected void process(Entity e) {
		Renderable r = (Renderable) e.getComponent(Renderable.class);
		Body b = (Body) e.getComponent(Body.class);
		r.setLayer((int) -(b.getPosition().y-world.getBounds().height) );
	}
}

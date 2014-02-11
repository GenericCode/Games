package com.genericcode.sidescroller.entities.systems;

import com.badlogic.gdx.math.Vector2;
import com.lostcode.javalib.entities.Entity;
import com.lostcode.javalib.entities.components.physical.Body;
import com.lostcode.javalib.entities.components.physical.Sensor;
import com.lostcode.javalib.entities.components.render.Renderable;
import com.lostcode.javalib.entities.systems.TagSystem;
import com.lostcode.javalib.utils.Convert;

/**
 * @author GenericCode
 *
 */

public class MookSystem extends TagSystem {
	
	private static final float MOOK_SPEED = Convert.pixelsToMeters(200f);
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
		Sensor s = (Sensor)e.getComponent(Sensor.class);
		for( Entity target : s.getEntitiesInView() )
			//System.out.println(target.getType());
			if( target.getType() == "player")
			{
				Body targetBody = (Body) target.getComponent(Body.class);
				Vector2 velocity = targetBody.getPosition().cpy().sub(b.getPosition());
				velocity.nor().scl(MOOK_SPEED);
				System.exit(0);
			}
	}
}

package com.genericcode.sidescroller.entities.components.items;

import com.badlogic.gdx.math.Vector2;
import com.genericcode.sidescroller.entities.components.GenericStat;
import com.lostcode.javalib.entities.Entity;
import com.lostcode.javalib.entities.EntityWorld;
import com.lostcode.javalib.entities.components.physical.Body;
import com.lostcode.javalib.entities.processes.DelayProcess;
import com.lostcode.javalib.entities.processes.SpawnProcess;

public class FragGrenadeItem extends ConsumableItem {
	
	private float travelTime = 1f;
	
	/**
	 * Constructs a FragGrenadeItem component, a consumable item that causes a delayed explosion.
	 * 
	 * @param damage
	 *            A float representing the damage of the explosion.
	 * @param useTime
	 *            A float representing the delay allotted to uses of this Item.
	 * @param radius
	 *            A float that represents radius of the explosion.       
	 * @param ammo
	 *            A {@link GenericStat} representing the current and maximum values of ammo available for the Item.
	 *            
	 */
	public FragGrenadeItem( float damage, float useTime, float radius, GenericStat ammo) {
		super("frag", damage, useTime, radius, ammo);
	}
	
	@Override
	public boolean use( String use, Object... args ) {
		if( use == "primary") {
			return this.toss((Entity)args[0], (EntityWorld)args[1], (Vector2)args[2]);
		}
		return false;
	}
	
	public boolean toss( Entity firer, EntityWorld world, Vector2 target ) {
		Body b = (Body) firer.getComponent(Body.class);
		Vector2 position = b.getPosition();
		world.getProcessManager().attach(new DelayProcess(travelTime, new SpawnProcess("Explosion", target, damage, radius)));
		return true;
	}

}

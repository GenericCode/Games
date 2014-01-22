package com.genericcode.sidescroller.entities.components.items;

import com.genericcode.sidescroller.entities.components.GenericStat;
import com.lostcode.javalib.entities.components.abstracted.Stat;
import com.lostcode.javalib.entities.components.generic.Item;
import com.lostcode.javalib.entities.components.render.Sprite;

/**
 * Base class for Consumable items.
 * @author GenericCode
 * @Created November 13, 2013
 */

public class ConsumableItem extends Item {

	protected float damage;
	protected float radius;
	protected Stat ammo;
	
	protected Sprite sprite;
	
	/**
	 * Constructs a ConsumableItem component, a special item with limited uses.
	 * 
	 * @param type
	 *            The type of ConsumableItem.
	 * @param damage
	 *            A float representing the damage of associated with use of the Item.
	 * @param useTime
	 *            A float representing the delay allotted to uses of this Item.
	 * @param radius
	 *            A float representing the radius that the item will affect.       
	 * @param ammo
	 *            A {@link GenericStat} representing the current and maximum values of ammo available for the Item.
	 *            
	 */
	public ConsumableItem(String type, float damage, float useTime, float radius, GenericStat ammo) {
		super(type);
		
		this.damage = damage;
		this.useTime = useTime;
		this.radius = radius;
		this.ammo = ammo;
	}

}

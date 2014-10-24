package com.sideprojects.unrepentantwaiting.fantasyobjects;

import java.awt.Point;
import java.util.ArrayList;

import com.sideprojects.unrepentantwaiting.BaseInteractable;
import com.sideprojects.unrepentantwaiting.Nobody;

public class Weapon implements InventoryItemInterface 
{
	private boolean b_melee;
	private boolean b_loaded;
	private boolean b_verbose;
	private boolean b_magic;
	private boolean b_lootable;
	private boolean b_damageIsDirect;
	
	private double  d_weight;
	
	private int     i_damage;
	private int     i_health_max;
	private int     i_health;
	private int     i_hardness;
	private int     i_range;
	private int     i_inventorySize;
	
	private String  s_name;
	
	private Point   p_position;
	
	private WeaponType wt_type;
	
	private BaseInteractable bi_target;
	private BaseInteractable bi_owner;
	
	
	public enum WeaponType
	{
		DUAL, // DUAL items can be ranged OR melee.  Primary use is indicated by b_melee
		SIMPLE, 
		MARTIAL, 
		EXOTIC, 
		MONK, 
		WAND;
	}

	/**
	 * Weapons initially acquire the most common values for their fields.
	 */
	public Weapon()
	{
		init();
	}
	
	/**
	 * Load most common values for a weapon's fields.
	 */
	private void init()
	{
		
		b_magic    = false;
		b_loaded   = false;
		b_lootable = true;
		b_melee    = true;
		b_damageIsDirect = false;
		d_weight   = 1.0;
		i_range    = 0;
		i_damage   = 1;
		i_health_max = 5;
		i_health   = 5;
		i_hardness = 0;
		i_inventorySize = 1;
		s_name = new String();
		p_position = new Point(0,0);
		bi_owner = null;
		bi_target = null;
		wt_type = WeaponType.SIMPLE;
		
		bi_target = new Nobody();
		bi_owner = new Nobody();
	}
	
	public boolean isMagic() 
	{
		return b_magic;
	}
	
	@Override
	public boolean useItem()
	{
		if(!(b_melee) && b_loaded)
		{
			bi_owner.expendEquippedAmmunition();
		}
		
		if (!(bi_owner.hasLineOfSight(bi_target)))
		{
			return false;
		}
		
		if(Point.distance(p_position.x, p_position.y, bi_target.getPosition().x, bi_target.getPosition().y) >= i_range)
		{
			
			
			
			
			if (b_damageIsDirect)
			{
				bi_target.directDamage(i_damage);
			}
			else
				bi_target.indirectDamage(i_damage);
		}
		return false;
		
	}
	
	@Override
	public boolean useItem(BaseInteractable b)
	{
		bi_target = b;
		return useItem();
	}

	/**
	 * Non-standard items use this to alter if they can be used as an impromptu weapon.
	 * For instance, chairs would have this be true, balls of cotton would be false.
	 * For the Weapon class, this will always be true.
	 */
	@Override
	public boolean isWeapon() 
	{
		return true;
	}

	/**
	 * Edible weapons should be context-sensitive to the story, and won't use this.
	 */
	@Override
	public boolean isEdible() 
	{
		return false;
	}

	/**
	 * Weapons are generally not wearable.  However, this can be overridden again
	 * in a special case weapon class.
	 */
	@Override
	public boolean isWearable() {
		return false;
	}

	/**
	 * Standard name return.  
	 */
	@Override
	public String whoAmI() 
	{
		if (s_name.isEmpty())
		{
			return "A Weapon";
		}
		
		return s_name;
	}

	/**
	 * Weapons have a standard health of 5 hp.  
	 */
	@Override
	public int getHealth() {
		return i_health;
	}

	/**
	 * Apply damage to the weapon.  Deduct hardness from the damage.
	 * Damage to objects can be 0.
	 */
	@Override
	public void directDamage(int d) 
	{
		if (d - i_hardness > 0)
		{
			i_health = i_health - (d + i_hardness);
			if (b_verbose)
			{
				System.out.println((d-i_hardness) + " damage done to " + s_name);
			}
		}
	}

	/**
	 * Apply damage to weapon, ignoring hardness.
	 */
	@Override
	public void indirectDamage(int d) 
	{
		i_health = i_health -d;
		
	}

	
	/**
	 * Repairing an item cannot go over its max
	 */
	@Override
	public void removeDamage(int d) 
	{
		if (i_health == i_health_max)
			return;
		
		i_health += 1;
		removeDamage(d-1);
	}

	/**
	 * Item can be picked up by player
	 */
	@Override
	public void setLootable(boolean b) 
	{
		b_lootable = b;
	}

	/**
	 * For a weapon, this method should be used to generate other items that special weapons might produce.
	 * In general, a weapon will only generate ammo, and only from a ranged weapon.  This may not always be
	 * the case, and use should indicate its function dynamically.
	 */
	@Override
	public void generateRandItem(ItemType t) 
	{
		
		
	}

	/**
	 * Returns the position of the object.  If it has no owner (Actor or containing BaseInteractable), its physical
	 * BaseGrid location is given in the form of a Point.
	 */
	@Override
	public Point getPosition() {
		if(bi_owner.equals(null))
		{
			return p_position;
		}
		else
			return bi_owner.getPosition();
	}

	/**
	 * Weapons do not themselves carry their own ammunition.  When useItem() is called, 
	 * the owner's expendEquippedAmmunition() is called.
	 */
	@Override
	public void expendEquippedAmmunition() 
	{
		if (b_verbose)
		{
			System.out.println("Pew pew!");
		}
		
		
	}

	/**
	 * The weapon itself will have no inventory.  Ammo will associate with its owner,
	 * b_loaded will apply to ranged weapons, but no other representation will be used.
	 */
	@Override
	public ArrayList<InventoryItemInterface> getInventoryList() {
		return null;
	}

	/**
	 * Most weapons have an Inventory Size of 1.  Some may take up more space.
	 */
	@Override
	public int getInventorySize() 
	{
		return i_inventorySize;
	}

	/**
	 * A weapon does not inherently have any gold in it.
	 */
	@Override
	public int getGold() 
	{
		return 0;
	}

	/**
	 * Line of sight for a weapon is its owner's line of sight.
	 * Automated / intelligent weapons have a reference to themselves as a bi_owner
	 * in this case, so that they can have line of sight to targets.
	 */
	@Override
	public boolean hasLineOfSight(BaseInteractable bi_target) {
		if(bi_owner != null)
			return bi_owner.hasLineOfSight(bi_target);

		else 
			return false;
	}
	
}

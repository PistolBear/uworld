package com.sideprojects.unrepentantwaiting.fantasyobjects;

import com.sideprojects.unrepentantwaiting.NamedObject;

public class Weapon extends NamedObject implements InventoryItemInterface
{
	private boolean b_melee;
	private boolean b_loaded;
	private boolean b_verbose;
	private boolean b_magic;
	private double  d_weight;
	private int     i_damage;
	private int     i_health_max;
	private int     i_health;
	private int     i_hardness;
	private int     i_range;
	
	private WeaponType wt_type;
	private boolean b_lootable;
	
	public enum WeaponType
	{
		DUAL, // DUAL items can be ranged OR melee.  Primary use is indicated by b_melee
		SIMPLE, 
		MARTIAL, 
		EXOTIC, 
		MONK, 
		WAND;
	}

	public Weapon()
	{
		b_verbose  = true;
		b_magic    = false;
		b_loaded   = false;
		b_melee    = true;
		d_weight   = 0;
		i_range    = 0;
		i_damage   = 0;
		i_health_max = 5;
		i_health   = 5;
		i_hardness = 0;
		s_name = new String();
		
	}

	/**
	 * Non-standard items use this to alter if they can be used as an impromptu weapon.
	 * For instance, chairs would have this be true, balls of cotton would be false.
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
	 * Apply damaage to weapon, ignoring hardness.
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
	public void setLootable() 
	{
		if (b_lootable)
			b_lootable = false;
		else
			b_lootable = true;
	}

	@Override
	public void setName(String s)
	{
		if (s == null)
			s = "A weapon";
		s_name = s;
	}

	@Override
	public void setDescription(String s) 
	{
		if (s == null)
			s = "Used as a weapon";
		s_descriptionShort = s;
		
	}
	
}

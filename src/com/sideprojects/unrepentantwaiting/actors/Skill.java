package com.sideprojects.unrepentantwaiting.actors;

import java.util.List;

import com.sideprojects.unrepentantwaiting.Named;

public abstract class Skill extends Named
{
	Ability[] a_parentAbility;

	/**
	 * Individual instances of Skills must implement these
	 * methods.
	 */
	public abstract void setName();
	public abstract ActorInterface getOwner();
	public abstract void addAbility(Ability a);
	public abstract void setAbilities(List<Ability> a);

	public Ability[] getAbilities()
	{
		return a_parentAbility;
	}

	public String longDesc()
	{
		return s_descriptionLong;
	}

	public int doSkill()
	{
		int magnitude = 0;
		for (Ability eachAbility : a_parentAbility)
		{
			magnitude += (int) (eachAbility.getScore() * .75);
		}
		
		return magnitude;
	}
}
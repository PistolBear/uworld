package com.sideprojects.unrepentantwaiting.actors;

import java.util.List;

import com.sideprojects.unrepentantwaiting.Named;

public abstract class Skill extends Named
{
	public List<Ability> a_abilityRef;
	
	/**
	 * Individual instances of Skills must implement these
	 * methods.
	 */
	public abstract void setName();
	public abstract ActorInterface getOwner();
	public abstract void addAbility(Ability a);
	public abstract void setAbilities(List<Ability> a);

	public List<Ability> getAbilities()
	{
		return a_abilityRef;
	}
	
	public String longDesc()
	{
		return s_descriptionLong;
	}
	
	public int doSkill()
	{
		int magnitude = 0;
		for (Ability eachAbility : a_abilityRef)
		{
			magnitude += (int) (eachAbility.getScore() * .75);
		}
		
		return magnitude;
	}
}

package com.sideprojects.unrepentantwaiting.actors;

import java.util.List;

import com.sideprojects.unrepentantwaiting.Named;
import com.sideprojects.unrepentantwaiting.actors.ActorInterface.AbilityName;

public abstract class Ability extends Named
{
	private int i_score;
	
	public int getScore()
	{
		return i_score;
	}
	
	public void setAbility (AbilityName an)
	{
		s_name = an.toString();
	}
	
	/**
	 * Enforce AbilityNames to be used for the name.
	 */
	@Override
	public final void setName(String s)
	{
		if(s.contains("AGI"))
			s = "AGI";
		if(s.contains("CON"))
			s = "CON";
		if(s.contains("KNO"))
			s = "KNO";
		if(s.contains("WIL"))
			s = "WIL";
		else
			s = "NON";
		
		switch (s)
		{
		case ("AGI"):
			setAbility(AbilityName.AGI);
			break;
		case ("CON"):
			setAbility(AbilityName.CON);
			break;
		case ("KNO"):
			setAbility(AbilityName.KNO);
			break;
		case ("WIL"):
			setAbility(AbilityName.WIL);
			break;
		default:
			setAbility(AbilityName.NON);
		}
	}
	
	/**
	 *  Returns a List of the skills this ability affects.  Will depend on implementation.
	 */
	public abstract List<String> getSkills(); 
}

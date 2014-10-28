package com.sideprojects.unrepentantwaiting;

public interface BaseInteractable
{
	public final boolean b_canInteract = true;
	
	public int getHealth();
	public void directDamage(int d);
	public void indirectDamage(int d);
	public void removeDamage(int d);
	public void setLootable();


}

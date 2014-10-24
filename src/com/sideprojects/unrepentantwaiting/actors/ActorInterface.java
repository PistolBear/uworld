package com.sideprojects.unrepentantwaiting.actors;

import com.sideprojects.unrepentantwaiting.BaseInteractable;
import com.sideprojects.unrepentantwaiting.fantasyobjects.InventoryItemInterface;

public interface ActorInterface extends BaseInteractable
{
	public int getArmorValue();
	public String getArmorType();
	
	public boolean getHostile(ActorInterface a);
	public void setHostile(ActorInterface a);
	
	public void addInventoryItem(InventoryItemInterface i);
	public void addGold(double g);
}

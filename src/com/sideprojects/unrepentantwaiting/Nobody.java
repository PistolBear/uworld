package com.sideprojects.unrepentantwaiting;

import java.awt.Point;
import java.util.ArrayList;

import com.sideprojects.unrepentantwaiting.actors.ActorInterface;
import com.sideprojects.unrepentantwaiting.fantasyobjects.InventoryItemInterface;

public class Nobody implements BaseInteractable, ActorInterface 
{

	@Override
	public String whoAmI() {
		return "Nobody";
	}

	@Override
	public int getHealth() {
		return 0;
	}

	@Override
	public void directDamage(int d) {
		// No-op
	}

	@Override
	public void indirectDamage(int d) {
		// No-op

	}

	@Override
	public void removeDamage(int d) {
		// No-op

	}

	@Override
	public void setLootable(boolean b) 
	{
		// No-op

	}

	@Override
	public Point getPosition() 
	{
		return new Point(0,0);
	}

	@Override
	public void expendEquippedAmmunition() 
	{
		// No-op

	}

	@Override
	public ArrayList<InventoryItemInterface> getInventoryList() 
	{
		return null;
	}

	@Override
	public int getInventorySize() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getGold() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean hasLineOfSight(BaseInteractable bi_target) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getArmorValue() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getArmorType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean getHostile(ActorInterface a) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setHostile(ActorInterface a) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addInventoryItem(InventoryItemInterface i) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addGold(double g) {
		// TODO Auto-generated method stub
		
	}

}

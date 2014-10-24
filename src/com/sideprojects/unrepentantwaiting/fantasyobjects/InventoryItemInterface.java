package com.sideprojects.unrepentantwaiting.fantasyobjects;


/**
 * For use with items that can be put in an inventory
 * @author woody
 *
 */
public interface InventoryItemInterface extends FantasyObjectInterface
{
	boolean m_inventoryItem = true;
	public void generateRandItem(ItemType t);
	

}

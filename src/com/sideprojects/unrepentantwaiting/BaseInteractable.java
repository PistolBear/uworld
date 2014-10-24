package com.sideprojects.unrepentantwaiting;

import java.awt.Point;
import java.util.ArrayList;

import com.sideprojects.unrepentantwaiting.fantasyobjects.InventoryItemInterface;

/** 
 * BaseInteractable interface
 * 
 * Many things in a game can be interacted with.  For these purposes, "Interact" means:
 *   Anything which can be acted upon, including (but not limited to) actions such as
 *   Talking to the object
 *   Listening to the object 
 *   Attacking the object
 *   Picking up the object
 *   Removing the object from inventory
 *   Being attacked by the object
 *   Players creating the object
 *   
 * And other qualities the object may have, such as
 *   The object having an inventory
 *   The object having health
 *   The object having a lootable status
 *   The object having a name
 *   The object being movable
 *   The object being noteworthy
 *   
 * Qualities which DO NOT necessarily qualify an object as "interactable":
 *   The object is terrain
 *   The object being colorful
 *   The object is some other part of an environment's description
 *   The object is something that players 'might' want to pick up
 *   The object is animated
 *   The object appears to make other objects
 *   The object appears to be interactive, like being a creature
 *   -(a naked corpse on a pole is scenery, not a meaningful interactive object)
 *   -(an unreachable bird squawking high above the player is scenery too) 
 *  
 * If needing to define terrain features or other non-interactive objects, see
 * the interface BaseScenery.
 * 
 * 
 * @author woody
 *
 */
public interface BaseInteractable 
{
	public String whoAmI();
	public int getHealth();
	public void directDamage(int d);
	public void indirectDamage(int d);
	public void removeDamage(int d);
	public void setLootable(boolean b);
	public Point getPosition();
	
	public void expendEquippedAmmunition();
	
	public ArrayList<InventoryItemInterface> getInventoryList();
	public int getInventorySize();		// How much space does the item take up in an inventory
	public int getGold();
	public boolean hasLineOfSight(BaseInteractable bi_target);
}

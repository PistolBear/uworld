package com.sideprojects.unrepentantwaiting;

import java.util.ArrayList;
import java.util.List;

import com.sideprojects.unrepentantwaiting.actors.ActorFactoryInterface;
import com.sideprojects.unrepentantwaiting.actors.ActorInterface;

public class Loop extends Thread
{
	static List<ActorInterface> actors;
	public Loop()
	{
		super();
		
		actors = new ArrayList<ActorInterface>();
		
	}
	
	public void startLooping()
	{
		// Do some kind of check to make sure things will work.
		run();
	}
	
	@Override
	public void run()
	{
		for (int i = 0; i < 80; i++)
		{
			for (int l = 0; l < 80; l++)
			{
				System.out.print(".");
			}
			System.out.println();
			actors = ActorFactoryInterface.defaultFactory(1);
		}
	}

}

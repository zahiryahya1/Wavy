package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Player extends GameObject 
{
	Handler handler;
	Random r = new Random();
	
	public Player(int x, int y, ID id, Handler handler) 
	{
		super(x, y, id);
		this.handler = handler;
		
	}
	
	public Rectangle getBounds()
	{
		return new Rectangle((int)x, (int)y, 32, 32);
	}

	public void tick() 
	{
		x += velX;
		y += velY;
		
		x = Game.clamp(x, 0, Game.WIDTH - 32);
		y = Game.clamp(y, 0, Game.HEIGHT - 56);
		
		  handler.addObject(new Trail(x, y, ID.Trail, Color.white, handler, 32, 32, 0.05f));

		collision();
	}
	
	private void collision()
	{
		for (int i = 0; i < handler.object.size(); i++)
		{
			GameObject tempObject = handler.object.get(i);
			
			if (tempObject.getID() == ID.BasicEnemy || tempObject.getID() == ID.FastEnemy
					|| tempObject.getID() == ID.SmartEnemy || tempObject.getID() == ID.EnemyBoss)
			{
				if (getBounds().intersects(tempObject.getBounds()))
				{
					// collision code
					HUD.HEALTH -= 2;
				}
			}
				
		}
	}

	public void render(Graphics g) 
	{
		g.setColor(Color.white);
		g.fillRect((int)x, (int)y,  32,  32);
	}


}

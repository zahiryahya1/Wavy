package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class FastEnemy extends GameObject
{

	private final int ENEMY_SIZE = 16;
	private Handler handler;
	
	public FastEnemy(int x, int y, ID id, Handler handler) 
	{
		super(x, y, id);
		this.handler = handler;
		
		velX = 6;
		velY = 9;
	}

	public Rectangle getBounds()
	{
		return new Rectangle((int)x, (int)y, 16, 16);
	}
	
	public void tick() 
	{
		x += velX;
		y += velY;
		
		if (y <= 0 || y >= Game.HEIGHT - 32)
			velY *= -1;
		
		if (x <= 0 || x >= Game.WIDTH - 16)
			velX *= -1;
		
		handler.addObject(new Trail((int)x, (int)y, ID.Trail, Color.CYAN, handler, ENEMY_SIZE, ENEMY_SIZE, 0.03f));
	}

	public void render(Graphics g) 
	{
		g.setColor(Color.CYAN);
		g.fillRect((int)x, (int)y, ENEMY_SIZE, ENEMY_SIZE);
	}
	
}

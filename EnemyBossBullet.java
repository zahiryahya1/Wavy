package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class EnemyBossBullet extends GameObject
{

	private final int ENEMY_SIZE = 16;
	private Handler handler;
	private Random r = new Random();
	
	public EnemyBossBullet(int x, int y, ID id, Handler handler) 
	{
		super(x, y, id);
		this.handler = handler;
		
		// sets random velocity for bullet
		velX = r.nextInt(20) - 10;
		velY = r.nextInt(5) + 5;
	}

	public Rectangle getBounds()
	{
		return new Rectangle((int)x, (int)y, 16, 16);
	}
	
	public void tick() 
	{
		x += velX;
		y += velY;
		
		/*
		 * sets velocity for basic enemy
		if (y <= 0 || y >= Game.HEIGHT - 32)
			velY *= -1;
		*/
		if (x <= 0 || x >= Game.WIDTH - 16)
			velX *= -1;
			
		if (y >= Game.HEIGHT) handler.removeObject(this);
		
        handler.addObject(new Trail((int)x,(int) y, ID.Trail, Color.magenta, handler, 16, 16, 0.05f));
	}

	public void render(Graphics g) 
	{
		g.setColor(Color.magenta);
		g.fillRect((int)x, (int)y, (int)ENEMY_SIZE, (int)ENEMY_SIZE);
	}
	
}

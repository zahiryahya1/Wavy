package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class EnemyBoss extends GameObject
{

	private final int ENEMY_SIZE = 64;
	private Handler handler;
	Random r = new Random();
	
	private int timer = 85;
	private int timer2 = 20;
	
	public EnemyBoss(int x, int y, ID id, Handler handler) 
	{
		super(x, y, id);
		this.handler = handler;
		
		velX = 0;
		velY = 3;
	}

	public Rectangle getBounds()
	{
		return new Rectangle((int)x, (int)y, ENEMY_SIZE, ENEMY_SIZE);
	}
	
	public void tick() 
	{
		x += velX;
		y += velY;
		
		if (timer <= 0)
			velY = 0;
		else 
			timer--;
		
		if (timer <= 0)
			timer2--;
		if (timer2 <= 0)
		{
			if (velX == 0)
				velX = 3;
			
			if (velX > 0)
				velX += .005f;
			else
				velX -= .005f;
			
			velX = Game.clamp(velX, -10, 10);
			
			int spawn = r.nextInt(10);
			if (spawn == 0)
				handler.addObject(new EnemyBossBullet((int)x + 36, (int)y + 36,
						ID.BasicEnemy, handler));
		}
		
		
		/* bounce around the window
		 * if (y <= 0 || y >= Game.HEIGHT - 32)
			velY *= -1;
			*/
		
		if (x <= 0 || x >= Game.WIDTH - 48)
			velX *= -1;
			
		//adds trail
        //handler.addObject(new Trail((int)x,(int) y, ID.Trail, Color.red, handler,
        //		ENEMY_SIZE, ENEMY_SIZE, 0.04f));
	}

	public void render(Graphics g) 
	{
		g.setColor(Color.magenta);
		g.fillRect((int)x, (int)y, (int)ENEMY_SIZE, (int)ENEMY_SIZE);
	}
	
}

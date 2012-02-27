package entity;
import java.util.ArrayList;
import java.util.Iterator;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
 
import entity.TopDownMovement;
import entity.ImageRenderComponent;
import entity.Entity;
 
public class Intermission1SlickBasicGame extends BasicGame{
 
    Entity player = null;
    Entity background = null;
    Entity enemy = null;
    
    ArrayList<Entity> enemies;
    Entity enemy2 = null;
    Entity enemy3 = null;
    int killIndex = 0;
   
    public Intermission1SlickBasicGame()
    {
        super("Son of Z");
    }
 
    @Override
    public void init(GameContainer gc)
			throws SlickException {
    	background = new Entity("background");
        background.AddComponent( new ImageRenderComponent("backgroundRender", new Image("data/backgroundtiled.png")) );
 
        player = new Entity("player");
        player.AddComponent( new ImageRenderComponent("playerFront", new Image("data/player/front.png")) );
//      player.AddComponent( new ImageRenderComponent("playerBack", new Image("data/player/back.png")) );
        player.AddComponent( new TopDownMovement("playerMovement") );
        player.setPosition(new Vector2f(20, 20));
        
        enemy = new Entity("enemy");
        enemy.AddComponent(new ImageRenderComponent("EnemyRender", new Image ("data/enemy.png")) );
        enemy.AddComponent( new Enemy("enemyMovement") );
        enemy.setPosition(new Vector2f(300, 300));
        
        enemy2 = new Entity("enemy");
        enemy2.AddComponent(new ImageRenderComponent("EnemyRender", new Image ("data/enemy.png")) );
        enemy2.AddComponent( new Enemy("enemyMovement") );
        enemy2.setPosition(new Vector2f(400, 300));
        
        enemy3 = new Entity("enemy");
        enemy3.AddComponent(new ImageRenderComponent("EnemyRender", new Image ("data/enemy.png")) );
        enemy3.AddComponent( new Enemy("enemyMovement") );
        enemy3.setPosition(new Vector2f(500, 300));
        
        enemies = new ArrayList<Entity>();
        enemies.add(enemy);
        enemies.add(enemy2);
        enemies.add(enemy3);
//        enemies.remove(0);
    }
 
    @Override
    public void update(GameContainer gc, int delta)
			throws SlickException
    {
    	boolean collision = false; // Karl
    	background.update(gc, null, delta);
    	player.update(gc, null, delta);
    	enemy.update(gc, null, delta);
 
    	for(Entity enemy: enemies)
    	{
    		collision = collision(player, enemy);
    		if(collision == true)
    		{
    			killIndex = enemies.indexOf(enemy);
    			
    			enemies.remove(killIndex);
    		}
    		enemy.update(gc, null, delta);
    	}
    	System.out.println(killIndex);
    }
    public void kill(int x)
    {
    	enemies.remove(x);
    }
    //KARL
    public boolean collision(Entity player, Entity enemy)
    {
    	boolean collide = false;
    	float x,y,x2,y2;
    	x = player.getPosition().x;
    	y = player.getPosition().y;
    	x2 = enemy.getPosition().x;
    	y2 = enemy.getPosition().y;
    	
    	if(x == x2 && y == y2)
    		collide = true;
    	
    	return collide;
    }
    //KARL
    public void render(GameContainer gc, Graphics gr)
			throws SlickException
    {
    	background.render(gc, null, gr);
    	player.render(gc, null, gr);
//    	enemy.render(gc, null, gr);
    	for(Entity enemy: enemies)
    	{
    		enemy.render(gc, null, gr);
    	}
    }
 
    public static void main(String[] args)
			throws SlickException
    {
         AppGameContainer app =
			new AppGameContainer( new Intermission1SlickBasicGame() );
 
         app.setDisplayMode(800, 600, false);
         app.start();
    }
}
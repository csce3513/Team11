package entity;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.newdawn.slick.Animation;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.tiled.TiledMap;
 
import entity.TopDownMovement;
import entity.ImageRenderComponent;
import entity.Entity;
 
public class Intermission1SlickBasicGame extends BasicGame{
 
    Entity player = null;
    Entity background = null;
    
    
    ArrayList<Entity> enemies;
    
    Entity enemy = null;
    Entity enemy2 = null;
    Entity enemy3 = null;
    Entity enemy4 = null;
    int killIndex = 0;
    Set<Entity> enemySet = new HashSet<Entity>();
    TiledMap bg;

    public Intermission1SlickBasicGame()
    {
        super("Son of Z");
    }
 
    @Override
    public void init(GameContainer gc)
			throws SlickException {
    	background = new Entity("background");
//        background.AddComponent( new ImageRenderComponent("backgroundRender", new Image("data/backgroundtiled.png")) );
    	bg = new TiledMap("data/grassmap1.tmx");
        player = new Entity("player");
//        player.AddComponent( new ImageRenderComponent("playerBack", new Image("data/player/back.png")) );
//        player.AddComponent( new ImageRenderComponent("playerFront", new Image("data/player/front.png")) );
        player.setImage(new Image("data/player/front.png"));
        player.AddComponent( new ImageRenderComponent("playerBack", player.getImage()) );
        player.AddComponent( new TopDownMovement("playerMovement") );
        player.setPosition(new Vector2f(20, 20));
        
        enemy = new Entity("enemy");
        enemy.AddComponent(new ImageRenderComponent("EnemyRender", new Image ("data/enemy.png")) );
        enemy.AddComponent( new Enemy("enemyMovement") );
        enemy.setPosition(new Vector2f(300, 400));
        
        enemy2 = new Entity("enemy2");
        enemy2.AddComponent(new ImageRenderComponent("EnemyRender", new Image ("data/enemy.png")) );
        enemy2.AddComponent( new Enemy("enemyMovement") );
        enemy2.setPosition(new Vector2f(400, 300));
        
        enemy3 = new Entity("enemy3");
        enemy3.AddComponent(new ImageRenderComponent("EnemyRender", new Image ("data/enemy.png")) );
        enemy3.AddComponent( new Enemy("enemyMovement") );
        enemy3.setPosition(new Vector2f(500, 200));
        
        enemy4 = new Entity("enemy4");
        enemy4.AddComponent(new ImageRenderComponent("EnemyRender", new Image ("data/enemy.png")) );
        enemy4.AddComponent( new Enemy("enemyMovement") );
        enemy4.setPosition(new Vector2f(600, 500));
        
//        enemies = new ArrayList<Entity>();
//        enemies.add(enemy);
//        enemies.add(enemy2);
//        enemies.add(enemy3);
//        enemies.add(enemy4);
        
        enemySet.add(enemy);
        enemySet.add(enemy2);
        enemySet.add(enemy3);
        enemySet.add(enemy4);
    }
 
    @Override
    public void update(GameContainer gc, int delta)
			throws SlickException
    {
    	Iterator<Entity> i = enemySet.iterator();
//    	background.update(gc, null, delta);
    	player.update(gc, null, delta);
    	
    	// Code using set:
    	while(i.hasNext())
    	{
    		Entity e = i.next();
    		e.update(gc,null,delta);
    	}
    	kill();
//    	for(Entity enemys: enemies)
//    	{
//    	enemys.update(gc, null, delta);
//    	}
//    	kill();
    	
    }
    public void kill()
    {
    	boolean collision = false; // Karl
    	Iterator<Entity> i = enemySet.iterator();
    	int count = 0;
    	while(i.hasNext())
    	{
    		Entity e = i.next();
    		collision = collision(player.getPosition(), e.getPosition());
    		
    		if(collision == true)
        		{
        			
        			enemySet.remove(e);
//        			System.out.print("REMOVED " + count+ "\n");
//        			System.out.print("Size: " + enemySet.size() +"\n");
        			i = enemySet.iterator();
        		}
    		
        	collision = false;
        	count++;
    	}
    	
//    	for(Entity enemy: enemies)
//    	{
//    		
//    		collision = collision(player.getPosition(), enemy.getPosition());
//    		
//    		if(collision == true)
//    		{
//    			killIndex = enemies.indexOf(enemy);
//    			enemies.remove(killIndex);
//    		}
//    		collision = false;
//    	}
    }
    //KARL
    public boolean collision(Vector2f player, Vector2f enemy)
    {
    	boolean collide = false;
    	float x,y,x2,y2;
    	x = player.x;
    	y = player.y;
    	x2 = enemy.x;
    	y2 = enemy.y;
    	
    	if((Math.abs(x-x2)<20) && (Math.abs(y-y2)<20))
    	{
    		collide = true;
    	}
    	return collide;
    }
    //KARL
    public void render(GameContainer gc, Graphics gr)
			throws SlickException
    {
//    	background.render(gc, null, gr);
    	bg.render(0, 0);
    	player.render(gc, null, gr);
    	Iterator<Entity> i = enemySet.iterator();
    	while(i.hasNext())
    	{
    		Entity e = i.next();
    		e.render(gc,null,gr);
    	}
    	
//    	for(Entity enemy: enemies)
//    	{
//    		enemy.render(gc, null, gr);
//    	}
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
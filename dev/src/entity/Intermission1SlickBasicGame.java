package entity;
import java.util.Random;
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
    Entity statusPanel = null;
  
    
    ArrayList<Entity> enemies;
   
    int killIndex = 0;
    Set<Entity> enemySet = new HashSet<Entity>();
    TiledMap bg;
    Random r = new Random();
    float randomx;
	float randomy = 0;
    
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
        statusPanel = new Entity("data/player/front.png");
//      player.AddComponent( new ImageRenderComponent("playerBack", new Image("data/player/back.png")) );
//      player.AddComponent( new ImageRenderComponent("playerFront", new Image("data/player/front.png")) );
        
        player.setImage(new Image("data/player/front.png"));
        player.AddComponent( new ImageRenderComponent("playerBack", player.getImage()) );
        player.AddComponent( new TopDownMovement("playerMovement") );
        player.setPosition(new Vector2f(32, 32));
          
        statusPanel.setImage(new Image("data/StatusPanel/StatusPanel.png"));
        statusPanel.AddComponent(new ImageRenderComponent("", statusPanel.getImage()));
        statusPanel.AddComponent(new TopDownMovement(""));
        statusPanel.setPosition(new Vector2f(-2, 600));
        randomx = (float) r.nextFloat() * (768-32) + 32;
        randomy = (float) r.nextFloat() * (568-32) + 32;
       
       
//        enemy = new Entity("enemy");
//        enemy.AddComponent(new ImageRenderComponent("EnemyRender", new Image ("data/enemy.png")) );
//        enemy.AddComponent( new Enemy("enemyMovement") );
//        enemy.setPosition(new Vector2f(randomx, randomy));
//
//        enemySet.add(enemy);
      createEnemy(500);

    }
    public void createEnemy(int numEnemies) throws SlickException
    {
    	int loop = 0;
    for(int i=0; i<numEnemies; i++)
    	{
    		 
    	randomx = (float) r.nextFloat() * (768-32) + 32;
        randomy = (float) r.nextFloat() * (568-32) + 32;
        System.out.println(randomx);
        System.out.println(randomy);

    	Entity enemy = new Entity("");
    	enemy.AddComponent(new ImageRenderComponent("EnemyRender", new Image ("data/enemy.png")) );
        enemy.AddComponent( new Enemy("enemyMovement") );
        enemy.setPosition(new Vector2f(randomx, randomy));
        enemySet.add(enemy);
        loop = loop + 1;
//        System.out.println("LOOP:" + loop);
    	}
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
    }
    
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
    	statusPanel.render(gc, null, gr);
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
 
         app.setDisplayMode(800, 700, false);
         app.start();
         
     
    }
}
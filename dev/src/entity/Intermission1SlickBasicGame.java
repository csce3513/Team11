package entity;
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
    Entity land = null;
    Entity enemy = null;
 
    public Intermission1SlickBasicGame()
    {
        super("Slick2D Path2Glory - SlickBasicGame");
    }
 
    @Override
    public void init(GameContainer gc)
			throws SlickException {
    	land = new Entity("land");
 
        land.AddComponent( new ImageRenderComponent("LandRender", new Image("data/backgroundtiled.png")) );
 
        player = new Entity("player");
        player.AddComponent( new ImageRenderComponent("playerRender", new Image("data/player/front.png")) );
        player.AddComponent( new TopDownMovement("playerMovement") );
        player.setPosition(new Vector2f(400, 300));
        
        enemy = new Entity("enemy");
        enemy.AddComponent(new ImageRenderComponent("EnemyRender", new Image ("data/enemy.png")) );
        enemy.setPosition(new Vector2f(300, 300));
    }
 
    @Override
    public void update(GameContainer gc, int delta)
			throws SlickException
    {
    	land.update(gc, null, delta);
    	player.update(gc, null, delta);
    	enemy.update(gc, null, delta);
    }
 
    public void render(GameContainer gc, Graphics gr)
			throws SlickException
    {
    	land.render(gc, null, gr);
    	player.render(gc, null, gr);
    	enemy.render(gc, null, gr);
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
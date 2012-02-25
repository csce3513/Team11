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
 
    Entity plane = null;
    Entity land = null;
    
 
    public Intermission1SlickBasicGame()
    {
        super("Slick2D Path2Glory - SlickBasicGame");
    }
 
    @Override
    public void init(GameContainer gc)
			throws SlickException {
    	land = new Entity("land");
 
        land.AddComponent( new ImageRenderComponent("LandRender", new Image("data/backgroundtiled.png")) );
 
        plane = new Entity("plane");
        plane.AddComponent( new ImageRenderComponent("PlaneRender", new Image("data/player.png")) );
        plane.AddComponent( new TopDownMovement("PlaneMovement") );
        plane.setPosition(new Vector2f(400, 300));
    }
 
    @Override
    public void update(GameContainer gc, int delta)
			throws SlickException
    {
    	land.update(gc, null, delta);
    	plane.update(gc, null, delta);
    }
 
    public void render(GameContainer gc, Graphics gr)
			throws SlickException
    {
    	land.render(gc, null, gr);
    	plane.render(gc, null, gr);
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
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
 
public class SimpleGame extends BasicGame{
	// background
	Image background = null;
	// player
	Image player = null;
	float x = 400;
	float y = 300;
	float scale = 1.0f;
	
    public SimpleGame()
    {
        super("Son Of Z");
    }
 
    @Override
    public void init(GameContainer gc) throws SlickException 
	{
    	background = new Image("data/background.png");
    	player = new Image("data/player.png");
    }
 
    @Override
    public void update(GameContainer gc, int delta) throws SlickException     
    {
    	Input input = gc.getInput();
    	 
        if(input.isKeyDown(Input.KEY_A))
        {
            player.rotate(-0.2f * delta);
        }
 
        if(input.isKeyDown(Input.KEY_D))
        {
            player.rotate(0.2f * delta);
        }
 
        if(input.isKeyDown(Input.KEY_W))
        {
            float hip = 0.4f * delta;
 
            float rotation = player.getRotation();
 
            x+= hip * Math.sin(Math.toRadians(rotation));
            y-= hip * Math.cos(Math.toRadians(rotation));
        }
 
        if(input.isKeyDown(Input.KEY_2))
        {
            scale += (scale >= 5.0f) ? 0 : 0.1f;
            player.setCenterOfRotation(player.getWidth()/2.0f*scale, player.getHeight()/2.0f*scale);
        }
        if(input.isKeyDown(Input.KEY_1))
        {
            scale -= (scale <= 1.0f) ? 0 : 0.1f;
            player.setCenterOfRotation(player.getWidth()/2.0f*scale, player.getHeight()/2.0f*scale);
        }
    }
 
    public void render(GameContainer gc, Graphics g) throws SlickException 
    {
    	background.draw(0,0);
    	player.draw(x,y,scale);
    }
 
    public static void main(String[] args) throws SlickException
    {
         AppGameContainer app = new AppGameContainer(new SimpleGame());
 
         app.setDisplayMode(800, 600, false);
         app.start();
    }
}
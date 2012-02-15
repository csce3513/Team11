import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
 
public class SimpleGame extends BasicGame{
 
    public SimpleGame()
    {
        super("Son Of Z");
    }
 
    @Override
    public void init(GameContainer gc) 
			throws SlickException 
	{
 
    }
 
    @Override
    public void update(GameContainer gc, int delta) 
			throws SlickException     
    {
 
    }
 
    public void render(GameContainer gc, Graphics g) 
			throws SlickException 
    {
 
    }
 
    public static void main(String[] args) 
			throws SlickException
    {
         AppGameContainer app = 
			new AppGameContainer(new SimpleGame());
 
         app.setDisplayMode(800, 600, false);
         app.start();
    }
}
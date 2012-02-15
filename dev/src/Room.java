
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.Image;

public class Room
{
	Image background = null;
	
	private static int roomX = 640;
	private static int roomY = 480;
	
    public void init() throws SlickException
    {
    	background = new Image("data/background.png");
    }
    public void render()
    {
    	background.draw(0,0);
    }
}
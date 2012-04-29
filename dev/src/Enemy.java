import org.newdawn.slick.Animation;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Polygon;

public class Enemy {
	
	private int x;
	private int y;
	private SpriteSheet sprite;
	private Polygon poly;
	/* I haven't implemented this player class into the Game class yet. This is the basic start of the class. I'm still thinking about 
	 * how we will utilize it so that I can add the correct methods.
	 * Please feel free to add or edit this class so we can be ready by Tuesdays final presentation.
	 */
	public Enemy()
	{
		x = 0;
		y = 0;	
		//sprite = new Image("data/")
	}
	public int getX()
	{
		return x;
	}
	public int getY()
	{
		return y;
	}
	public Image getImage()
	{
		return sprite;
	}
	public void setSpriteSheet(SpriteSheet s)
	
	{
		sprite = s;
	}
	public void setX(int xcoord)
	{
		x = xcoord;
	}
	public void setY(int ycoord)
	{
		y = ycoord;
	}
	

}
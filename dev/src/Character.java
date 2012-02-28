<<<<<<< HEAD
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


public class Character
{
	private Image img = null;
	private float x = 400f;
	private float y = 400f;
	private float scale = 0.0f;
	private float speed = 0.4f;
	private String location = "data/player.png";
	public Character(float xPos, float yPos, float Scale, float Speed) throws SlickException
=======
import org.newdawn.slick.Image;


public abstract class Character 
{
	private Image img = null;
	private double x = 400;
	private double y = 400;
	private double scale = 0;
	private double speed = 0.5;
	
	public Character(double xPos, double yPos, double Scale, double Speed)
>>>>>>> 2628ac4420c9d6108dc53f56c04e072844734ad8
	{
		x = xPos;
		y = yPos;
		scale = Scale;
		speed = Speed;
<<<<<<< HEAD
		img = new Image("data/character.png");
	}
	public void setX(float X)
	{
		x = X;
	}
	public float getX()
	{
		return x;
	}
	public void setY(float Y)
	{
		y = Y;
	}
	public float getY()
	{
		return y;
	}
	public void setScale(float Scale)
	{
		scale = Scale;
	}
	public float getScale()
	{
		return scale;
	}
	public void setSpeed(float Speed)
	{
		speed = Speed;
	}
	public float getSpeed()
	{
		return speed;
	}
	public void setLocation(String loc)
	{
		location = loc;
	}
	public Image getImage()
	{
		return img;
	}
=======
	}
	public void setX(double X)
	{
		x = X;
	}
	public double getX()
	{
		return x;
	}
	public void setY(double Y)
	{
		y = Y;
	}
	public double getY()
	{
		return y;
	}
	public void setScale(double Scale)
	{
		scale = Scale;
	}
	public double getScale()
	{
		return scale;
	}
	public void setSpeed(double Speed)
	{
		speed = Speed;
	}
	public double getSpeed()
	{
		return speed;
	}
>>>>>>> 2628ac4420c9d6108dc53f56c04e072844734ad8
}

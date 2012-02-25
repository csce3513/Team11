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
	{
		x = xPos;
		y = yPos;
		scale = Scale;
		speed = Speed;
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
}

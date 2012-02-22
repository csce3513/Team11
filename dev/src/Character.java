import org.newdawn.slick.Image;


public abstract class Character 
{
	private Image img = null;
	private double x = 400;
	private double y = 400;
	private double scale = 0;
	private double speed = 0.4;
	
	public Character(double xPos, double yPos, double Scale, double Speed)
	{
		x = xPos;
		y = yPos;
		scale = Scale;
		speed = Speed;
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
}

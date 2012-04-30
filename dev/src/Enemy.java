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
import java.util.*;

public class Enemy {
	
	private int x;
	private int y;
	private Animation animation;
	private float ENEMYSPEED = 90;
	private SpriteSheet sprite;
	private Polygon poly;

	/* I haven't implemented this player class into the Game class yet. This is the basic start of the class. I'm still thinking about 
	 * how we will utilize it so that I can add the correct methods.
	 * Please feel free to add or edit this class so we can be ready by Tuesdays final presentation.
	 */
	public Enemy(int x, int y)
	{
		this.x = x;
		this.y = y;	
		try {
			sprite = new SpriteSheet("data/enemies.png", 32, 32);
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //enemy reference
		animation = new Animation();
		animation.setSpeed(ENEMYSPEED);
		for (int frame = 0; frame < 2; frame++){
			animation.addFrame(sprite.getSprite(frame, 0), 755);
		}
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
	public Animation getAnimation()
	{
		return animation;
	}
	public Image getImage()
	{
		return sprite;
	}
	public void setSpriteSheet(SpriteSheet s)
	
	{
		sprite = s;
	}
	public void setX(int x)
	{
		this.x = x;
	}
	public void setY(int y)
	{
		this.y = y;
	}
	public void drawEnemy()
	{
		
	}

}

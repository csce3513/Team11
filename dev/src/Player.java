import org.newdawn.slick.Animation;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Polygon;
import java.util.ArrayList;
import org.newdawn.slick.tiled.TiledMap;
 
public class Player 
{
	int x;
	int y;
	Animation animation;
	Animation left; 
	Animation right;
	Animation up;
	Animation down;
	Animation attackLeft;
	Animation attackRight;
	Animation attackUp;
	Animation attackDown;
	
	//Polygon playerPoly;
	//SpriteSheet spriteSheet;
	
	public Player()
	{
		animation = new Animation();
		left = new Animation();
		right = new Animation();
		up = new Animation();
		down = new Animation();
		
		y = 0;
		x = 0;
	}
	public void setAnimation(Animation a)
	{
		animation = a;
	}
}

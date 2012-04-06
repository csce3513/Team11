package entity;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;
 
public class TopDownMovement extends Component {
 
	float direction;
	float speed = 1.0f;
	private Image sprite, up, down, left, right;
	
	//wizard
	 private float x = 94f, y = 84f;
	 TiledMap grassMap;
     /** The collision map indicating which tiles block movement - generated based on tile properties */
     private boolean[][] blocked;
     private static final int SIZE = 32;
	//wizard
	public TopDownMovement( String id )
	{
		this.id = id;
	}

	public float getSpeed()
	{
		return speed;
	}
 
	public float getDirection()
	{
		return direction;
	}
	public void init(GameContainer gc) throws SlickException // WIZARD MOVES
	{
		grassMap = new TiledMap("data/grassmap1.tmx");
         up = new Image("data/player/back.png");
         down = new Image("data/player/front.png");

         // Original orientation of the sprite. It will look right.
         sprite = right;
       //wizard
         // build a collision map based on tile properties in the TileD map
         blocked = new boolean[grassMap.getWidth()][grassMap.getHeight()];

         for (int xAxis=0;xAxis<grassMap.getWidth(); xAxis++)
         {
              for (int yAxis=0;yAxis<grassMap.getHeight(); yAxis++)
              {
                  int tileID = grassMap.getTileId(xAxis, yAxis, 0);
                  String value = grassMap.getTileProperty(tileID, "blocked", "false");
                  if ("true".equals(value))
                  {
                      blocked[xAxis][yAxis] = true;
                  }
              }
          }
	}
	public void render(GameContainer gc, Graphics gr) throws SlickException
    {
        sprite.draw((owner.getPosition().x), (owner.getPosition().y));
//        grassMap.render(0, 0);
//		sprite.draw(200,200);
    }
	public void update(GameContainer gc, StateBasedGame sb, int delta) 
	{
 
		float rotation = owner.getRotation();
		float scale = owner.getScale();
		Vector2f position = owner.getPosition();
		float fdelta = delta * 0.3f;
		Input input = gc.getInput();

        if((input.isKeyDown(Input.KEY_LEFT)) || (input.isKeyDown(Input.KEY_A)))
        {
        	
        	position.x -= fdelta;

        }
 
        if((input.isKeyDown(Input.KEY_RIGHT)) || (input.isKeyDown(Input.KEY_D)))
        {
        	
        	position.x += fdelta;

        }
 
        if(input.isKeyDown(Input.KEY_UP) || (input.isKeyDown(Input.KEY_W)))
        {
        	//owner.setImage(up);
        	sprite = up;
            position.y -= fdelta;
           
        }
        if(input.isKeyDown(Input.KEY_DOWN) || (input.isKeyDown(Input.KEY_S)))
        {
        	owner.setImage(down);
            position.y += fdelta;
           
        }
//		Input input = gc.getInput();
//	     if (input.isKeyDown(Input.KEY_UP))
//	     {
//	     if (!(isBlocked(x, y - fdelta) || isBlocked(x+SIZE-1, y - fdelta)))
//	     {
//
//	     // The lower the delta the slowest the sprite will animate.
//	     y -= fdelta;
//	     }
//	     }
//	     else if (input.isKeyDown(Input.KEY_DOWN))
//	     {
//	     if (!(isBlocked(x, y + SIZE + fdelta) || isBlocked(x+SIZE-1, y + SIZE + fdelta)))
//	     {
//	     y += fdelta;
//	     }
//	     }
//	     else if (input.isKeyDown(Input.KEY_LEFT))
//	     {
//	     if (!(isBlocked(x - fdelta, y) || isBlocked(x - fdelta, y+SIZE-1)))
//	     {
//	     x -= fdelta;
//	     }
//	     }
//	     else if (input.isKeyDown(Input.KEY_RIGHT))
//	     {
//	     if (!(isBlocked(x + SIZE + fdelta, y) || isBlocked(x + SIZE + fdelta, y+SIZE-1)))
//	     {
//	     x += fdelta;
//	     }
//	     }
//	    position.set(x, y);
		owner.setPosition(position);
//		owner.setRotation(rotation);
//		owner.setScale(scale);
	}
	private boolean isBlocked(float x, float y)
    {
        int xBlock = (int)x / SIZE;
        int yBlock = (int)y / SIZE;
        return blocked[xBlock][yBlock];
    }
}
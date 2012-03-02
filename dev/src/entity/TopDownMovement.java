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
		
         up = new Image("data/player/back.png");
         down = new Image("data/player/front.png");

         // Original orientation of the sprite. It will look right.
         sprite = right;

	}
	public void render(GameContainer gc, Graphics gr) throws SlickException
    {
        sprite.draw((owner.getPosition().x), (owner.getPosition().y));
//		sprite.draw(200,200);
    }
	public void update(GameContainer gc, StateBasedGame sb, int delta) 
	{
 
		float rotation = owner.getRotation();
		float scale = owner.getScale();
		Vector2f position = owner.getPosition();
		float fdelta = delta * 0.3f;
		Input input = gc.getInput();

        if(input.isKeyDown(Input.KEY_LEFT))
        {
        	
        	position.x -= fdelta;

        }
 
        if(input.isKeyDown(Input.KEY_RIGHT))
        {
        	
        	position.x += fdelta;

        }
 
        if(input.isKeyDown(Input.KEY_UP))
        {
        	owner.setImage(up);
            position.y -= fdelta;
           
        }
        if(input.isKeyDown(Input.KEY_DOWN))
        {
        	owner.setImage(down);
            position.y += fdelta;
           
        }
        
		owner.setPosition(position);
		owner.setRotation(rotation);
		owner.setScale(scale);
	}
 
}
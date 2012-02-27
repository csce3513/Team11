package entity;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;
 
public class TopDownMovement extends Component {
 
	float direction;
	float speed = 1.0f;
 
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
            position.y -= fdelta;
        }
        if(input.isKeyDown(Input.KEY_DOWN))
        {
            position.y += fdelta;
        }
 
		owner.setPosition(position);
		owner.setRotation(rotation);
		owner.setScale(scale);
	}
 
}
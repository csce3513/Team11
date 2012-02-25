package entity;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;
 
public class TopDownMovement extends Component {
 
	float direction;
	float speed;
 
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
 
	public void update(GameContainer gc, StateBasedGame sb, int delta) {
 
		float rotation = owner.getRotation();
		float scale = owner.getScale();
		Vector2f position = owner.getPosition();
 
		Input input = gc.getInput();
 
        if(input.isKeyDown(Input.KEY_LEFT))
        {
        	position.x -= 0.3f * delta;
        }
 
        if(input.isKeyDown(Input.KEY_RIGHT))
        {
        	position.x += 0.3f * delta;
        }
 
        if(input.isKeyDown(Input.KEY_UP))
        {
            position.y -= 0.3f * delta;
        }
        if(input.isKeyDown(Input.KEY_DOWN))
        {
            position.y += 0.3f * delta;
        }
 
		owner.setPosition(position);
		owner.setRotation(rotation);
		owner.setScale(scale);
	}
 
}
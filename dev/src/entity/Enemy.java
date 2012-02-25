package entity;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;
 
public class Enemy extends Component {
 
	float direction;
	float speed;
	
	
	public Enemy( String id )
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
		Vector2f position = owner.getPosition();
		Input input = gc.getInput();
		if(input.isKeyDown(Input.KEY_LEFT))
        {
        	position.x += 0.3f * delta;
        }
 
        if(input.isKeyDown(Input.KEY_RIGHT))
        {
        	position.x -= 0.3f * delta;
        }
 
        if(input.isKeyDown(Input.KEY_UP))
        {
            position.y += 0.3f * delta;
        }
        if(input.isKeyDown(Input.KEY_DOWN))
        {
            position.y -= 0.3f * delta;
        }
//		for(int i=1; i<15; i+=0.5)
//		{
//			position.x += 0.5f * delta;
//		}
		
		owner.setPosition(position);
//		owner.setRotation(rotation);
//		owner.setScale(scale);
		
	}
 
}
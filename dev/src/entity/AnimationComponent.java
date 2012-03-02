package entity;
import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;
 
public class AnimationComponent extends RenderComponent 
{
 
	Animation image;
 
	public AnimationComponent(String id, Animation image)
	{
		super(id);
		this.image = image;
	}
 
	@Override
	public void render(GameContainer gc, StateBasedGame sb, Graphics gr) 
	{
		Vector2f pos = owner.getPosition();
		float scale = owner.getScale();
 
		image.draw(pos.x, pos.y);

	}
	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta) 
	{
			
	}
 
}
package entity;
 
import java.util.ArrayList;
 
import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;
import entity.Component;
import entity.RenderComponent;
 
public class Entity {
 
    String id;
 
    Vector2f position;
    float scale;
    float rotation;
    Image img;
 
    RenderComponent renderComponent = null;
 
    ArrayList<Component> components = null;
 
    public Entity(String id)
    {
        this.id = id;
 
        components = new ArrayList<Component>();
 
        position = new Vector2f(0,0);
        scale = 1;
        rotation = 0;
    }
 
    public void AddComponent(Component component)
    {
        if(RenderComponent.class.isInstance(component))
            renderComponent = (RenderComponent)component;
 
        component.setOwnerEntity(this);
	components.add(component);
    }
 
    public Component getComponent(String id)
    {
        for(Component comp : components)
	{
	    if ( comp.getId().equalsIgnoreCase(id) )
	        return comp;
	}
 
	return null;
    }
 
    public Vector2f getPosition()
    {
	return position;
    }
 
    public float getScale()
    {
	return scale;
    }
 
    public float getRotation()
    {
	return rotation;
    }
 
    public String getId()
    {
    	return id;
    }
 
    public void setPosition(Vector2f position) {
	this.position = position;
    }
 
    public void setRotation(float rotate) {
        rotation = rotate;
    }
 
    public void setScale(float scale) {
	this.scale = scale;
    }
 
    public void update(GameContainer gc, StateBasedGame sb, int delta)
    {
        for(Component component : components)
        {
            component.update(gc, sb, delta);
        }
    }
 
    public void render(GameContainer gc, StateBasedGame sb, Graphics gr)
    {
        if(renderComponent != null)
            renderComponent.render(gc, sb, gr);
    }
    public void setImage(Image x)
    {
    	img = x;
    }
    public Image getImage()
    {
    	return img;
    }
}
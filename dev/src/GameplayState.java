

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
 
public class GameplayState extends BasicGameState {
 
    int stateID = 1;
    Image background = null;
   	// player
   	Image player = null;
   	float x = 400;
   	float y = 300;
   	float scale = 1.0f;
   	double playerspeed = 0.50;
   	
   	
   	
    GameplayState( int stateID ) 
    {
       this.stateID = stateID;
    }
 
    @Override
    public int getID() {
        return stateID;
    }
   
	
    public GameplayState()
    {
        
    }
 
    public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException 
	{
    	background = new Image("data/backgroundtiled.png");
    	player = new Image("data/player.png");
    }
 
    public void update(GameContainer gc, StateBasedGame sb, int delta) throws SlickException     
    {
    	Input input = gc.getInput();
    	 
        if(input.isKeyDown(Input.KEY_A))
        {
            player.draw(x-=playerspeed,y,scale);
        }
 
        if(input.isKeyDown(Input.KEY_D))
        {
        	player.draw(x+=playerspeed,y,scale);
        }
 
        if(input.isKeyDown(Input.KEY_W))
        {
        	player.draw(x,y-=playerspeed,scale);
        }
        if(input.isKeyDown(Input.KEY_S))
        {
        	player.draw(x,y+=playerspeed,scale);
        }
        if(input.isKeyDown(Input.KEY_2))
        {
            scale += (scale >= 5.0f) ? 0 : 0.1f;
            player.setCenterOfRotation(player.getWidth()/2.0f*scale, player.getHeight()/2.0f*scale);
        }
        if(input.isKeyDown(Input.KEY_1))
        {
            scale -= (scale <= 1.0f) ? 0 : 0.1f;
            player.setCenterOfRotation(player.getWidth()/2.0f*scale, player.getHeight()/2.0f*scale);
        }
    }
 
    public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2) throws SlickException 
    {
    	background.draw(0,0);
    	player.draw(x,y,scale);
    }
 
}
    
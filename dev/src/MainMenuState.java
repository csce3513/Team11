


import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
 
public class MainMenuState extends BasicGameState{
 
    Image background = null;
    Image startGameOption = null;
    Image exitOption = null;
 
    Sound menumusic = null;
    
    int stateID = 0;
 
 
    private static int menuX = 210;
    private static int menuY = 400;
 
    float startGameScale = 1;
    float exitScale = 1;
 
 
    public MainMenuState(int stateID )
    {
        this.stateID = stateID;
    }
 
    @Override
    public int getID() {
        return stateID;
    }
 
 
    public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
        background = new Image("data/titlescreen.png");
 
        // Load the menu images
        Image menuOptions = new Image("data/menuoptions2.png");
        menumusic = new Sound("data/menutheme.wav");
        
        menumusic.loop();
        startGameOption = menuOptions.getSubImage(0, 0, 377, 71);

        exitOption = menuOptions.getSubImage(0, 71, 377, 71);
 
    }
 
 
    public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2) throws SlickException {
        // render the background
        background.draw(0, 0);
 
        // Draw menu
        startGameOption.draw(menuX, menuY, startGameScale);
 
        exitOption.draw(menuX, menuY+80, exitScale);
 
    }
 
    float scaleStep = 0.0001f;
 
    public void update(GameContainer gc, StateBasedGame sb, int delta) throws SlickException 
    {
        Input input = gc.getInput();
 
        int mouseX = input.getMouseX();
        int mouseY = input.getMouseY();
        
        boolean insideStartGame = false;
        boolean insideExit = false;
 
        if( ( mouseX >= menuX && mouseX <= menuX + startGameOption.getWidth()) &&
            ( mouseY >= menuY && mouseY <= menuY + startGameOption.getHeight()) )
        {
            insideStartGame = true;
        }else if( ( mouseX >= menuX && mouseX <= menuX+ exitOption.getWidth()) &&
            ( mouseY >= menuY+80 && mouseY <= menuY+80 + exitOption.getHeight()) )
        {
            insideExit = true;
        }
 
        if(insideStartGame)
        {
        	
            if(startGameScale < 1.05f)
                startGameScale += scaleStep * delta;
 
            if ( input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) ){                
                sb.enterState(SlickBlocksGame.GAMEPLAYSTATE);
            }
        }else{
            if(startGameScale > 1.0f)
                startGameScale -= scaleStep * delta;
 
            if ( input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) )
                gc.exit();
        }
 
        if(insideExit)
        {
            if(exitScale < 1.05f)
                exitScale +=  scaleStep * delta;
        }else{
            if(exitScale > 1.0f)
                exitScale -= scaleStep * delta;
        }
    }
 
}
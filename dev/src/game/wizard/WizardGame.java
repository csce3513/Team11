package game.wizard;

import org.newdawn.slick.Animation; 
import org.newdawn.slick.AppGameContainer; 
import org.newdawn.slick.BasicGame; 
import org.newdawn.slick.GameContainer; 
import org.newdawn.slick.Graphics; 
import org.newdawn.slick.Image; 
import org.newdawn.slick.Input; 
import org.newdawn.slick.SlickException; 
import org.newdawn.slick.tiled.TiledMap;  

/**  
*  
* @author panos  
*/
public class WizardGame extends BasicGame
{
     private TiledMap grassMap;
     private Animation sprite, up, down, left, right;
     //private float x = 34f, y = 34f;
     private float x = 94f, y = 84f;

     /** The collision map indicating which tiles block movement - generated based on tile properties */
     private boolean[][] blocked;
     private static final int SIZE = 32;

     public WizardGame()
     {
         super("Wizard game");
     }

     public static void main(String [] arguments)
     {
         try
         {
             AppGameContainer app = new AppGameContainer(new WizardGame());
             app.setDisplayMode(500, 400, false);
             //app.setDisplayMode(800, 608, false);
             app.start();
         }
         catch (SlickException e)
         {
             e.printStackTrace();
         }
     }

     @Override
     public void init(GameContainer container) throws SlickException
     {
         Image [] movementUp = {new Image("data/wmg1_bk1.png"), new Image("data/wmg1_bk2.png")};
         Image [] movementDown = {new Image("data/wmg1_fr1.png"), new Image("data/wmg1_fr2.png")};
         Image [] movementLeft = {new Image("data/wmg1_lf1.png"), new Image("data/wmg1_lf2.png")};
         Image [] movementRight = {new Image("data/wmg1_rt1.png"), new Image("data/wmg1_rt2.png")};
         int [] duration = {300, 300};         grassMap = new TiledMap("data/grassmap.tmx");

          /*
          * false variable means do not auto update the animation.
          * By setting it to false animation will update only when
          * the user presses a key.
          */
         up = new Animation(movementUp, duration, false);
         down = new Animation(movementDown, duration, false);
         left = new Animation(movementLeft, duration, false);
         right = new Animation(movementRight, duration, false);

         // Original orientation of the sprite. It will look right.
         sprite = right;

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

     @Override
     public void update(GameContainer container, int delta) throws SlickException
     {
     Input input = container.getInput();
     float fdelta=delta*0.1f;
     if (input.isKeyDown(Input.KEY_UP))
     {
     sprite = up;
     if (!(isBlocked(x, y - fdelta) || isBlocked(x+SIZE-1, y - fdelta)))
     {
     sprite.update(delta);
     // The lower the delta the slowest the sprite will animate.
     y -= fdelta;
     }
     }
     else if (input.isKeyDown(Input.KEY_DOWN))
     {
     sprite = down;
     if (!(isBlocked(x, y + SIZE + fdelta) || isBlocked(x+SIZE-1, y + SIZE + fdelta)))
     {
     sprite.update(delta);
     y += fdelta;
     }
     }
     else if (input.isKeyDown(Input.KEY_LEFT))
     {
     sprite = left;
     if (!(isBlocked(x - fdelta, y) || isBlocked(x - fdelta, y+SIZE-1)))
     {
     sprite.update(delta);
     x -= fdelta;
     }
     }
     else if (input.isKeyDown(Input.KEY_RIGHT))
     {
     sprite = right;
     if (!(isBlocked(x + SIZE + fdelta, y) || isBlocked(x + SIZE + fdelta, y+SIZE-1)))
     {
     sprite.update(delta);
     x += fdelta;
     }
     }
     }

     public void render(GameContainer container, Graphics gr) throws SlickException
     {
         grassMap.render(0, 0);
         sprite.draw((int)x, (int)y);
     }

     private boolean isBlocked(float x, float y)
     {
         int xBlock = (int)x / SIZE;
         int yBlock = (int)y / SIZE;
         return blocked[xBlock][yBlock];
     }
 } 
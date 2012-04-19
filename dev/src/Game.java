import org.newdawn.slick.Animation;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Polygon;
 
public class Game extends BasicGame {
	SpriteSheet walk_L;
	SpriteSheet walk_R;
	SpriteSheet walk_U;
	SpriteSheet walk_D;
	SpriteSheet walk_Main;
	Image cross1, cross2, cross3, cross4, cross5;
    Image life1, life2, life3;
    Image StatusPanel;
    Image empty;
    Image menu;
    Image gameover;
    Image startButton;
    int menuState;
    String enemyRef = "data/enemies.png";
	int numCrosses;
	int numLives;
	float playerSpeed = 90;
	boolean enemyDead;
	//player start position
	private float playerX;
	private float playerY;
	int directionX;
	private float enemyX;
	private float enemyY;
	
	//map using Tiled
	//private TiledMap map;
	
	private Animation player;
	private Animation playerNoMove;
	private Animation playerLeft;
	private Animation playerRight;
	private Animation playerUp;
	private Animation playerDown;
	private Animation enemy;
	private Animation arrow;
	
	private Polygon playerPoly;
	private Polygon enemyPoly;
	
	public BlockMap map;
 
	public Game() {
		super("Son of Z"); //name in window
	}
	
	
	public void init(GameContainer container) throws SlickException {
		directionX = 1;
		enemyX = 32;
		enemyY = 32;
		enemyDead = false;
		playerX = 320;
		playerY = 240;
		menuState = 0;
		numCrosses = 5;
		numLives = 3;
		gameover = new Image("data/gameover.png");
		menu = new Image("data/titlescreen.png");
		
		//Instantiate objects for Health Status bar
		StatusPanel = new Image("data/StatusPanel.png");
		cross1 = new Image("data/RedCross.png");
    	cross2 = new Image("data/RedCross.png");
    	cross3 = new Image("data/RedCross.png");
    	cross4 = new Image("data/RedCross.png");
    	cross5 = new Image("data/RedCross.png");
    	life1 = new Image("data/front.png");
    	life2 = new Image("data/front.png");
    	life3 = new Image("data/front.png");
    	
		container.setVSync(true);  //display syncs with vertical refresh
		SpriteSheet walk_D = new SpriteSheet("data/Front/frontmove2.png", 48, 62); //player location
		SpriteSheet walk_L = new SpriteSheet("data/L_side/L_sidetexture.png", 48, 48); //player location
		SpriteSheet walk_R = new SpriteSheet("data/L_side/R_sidetexture.png", 48, 62);
		SpriteSheet walk_U = new SpriteSheet("data/Back/backmove.png", 48, 62);
		SpriteSheet enemySheet = new SpriteSheet(enemyRef, 32, 32); //enemy reference
		map = new BlockMap("data/map1.tmx"); //map location
		
		//Instantiate different Player animations (depending on what direction he's headed)
		player = new Animation();
		player.setSpeed(playerSpeed);
		playerNoMove = new Animation();
		playerNoMove.setSpeed(playerSpeed);
		playerLeft = new Animation();
		playerLeft.setSpeed(playerSpeed);
		playerRight = new Animation();
		playerRight.setSpeed(playerSpeed);
		playerUp = new Animation();
		playerUp.setSpeed(playerSpeed);
		playerDown = new Animation();
		playerDown.setSpeed(playerSpeed);
		enemy = new Animation();
		arrow = new Animation();
		walk_Main = walk_D;
		enemy.setAutoUpdate(true);
		
		//Fill each player instance with frames from their respective sprite sheets (still need additional sprite sheets)
		for (int frame = 0; frame < 1; frame++){
			player.addFrame(walk_Main.getSprite(frame, 1), 10000); // 150 time in ms
		}
		for (int frame = 0; frame < 1; frame++){
			playerNoMove.addFrame(walk_Main.getSprite(frame, 1), 10000); // 150 time in ms
		}
		for (int frame = 0; frame < 3; frame++){
			playerRight.addFrame(walk_R.getSprite(frame, 1), 10000); // 150 time in ms
		}
		for (int frame = 0; frame < 3; frame++){
			playerUp.addFrame(walk_U.getSprite(frame, 1), 10000); // 150 time in ms
		}
		for (int frame = 0; frame < 3; frame++){
			playerDown.addFrame(walk_D.getSprite(frame, 1), 10000); // 150 time in ms
		}
		for (int frame = 0; frame < 7; frame++){
			playerLeft.addFrame(walk_L.getSprite(frame, 0), 10000); // 150 time in ms
		}
		playerPoly = new Polygon(new float[]{
				 playerX, playerY,
				 playerX+32, playerY,
				 playerX+32, playerY+44,
				 playerX, playerY+44
		});	
		// ENEMIES
		for (int frame = 0; frame < 2; frame++){
			enemy.addFrame(enemySheet.getSprite(frame, 0), 150);
		}
		
		enemyPoly = new Polygon(new float[]{
				 enemyX, enemyY,
				 enemyX+32, enemyY,
				 enemyX+32, enemyY+32,
				 enemyX, enemyY+32
        });	
	}
	
	public void update(GameContainer container, int delta) 
	{ 	
		if(directionX < 1)
		enemyX--;
		else
		enemyX++;
		try {
			if (enemyCollisionWith()){
				if(directionX < 1)
					enemyX = enemyX + 2;
				else
					enemyX--;
				enemyPoly.setX(enemyX);
				directionX = directionX * -1;
			}
		} catch (SlickException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if(container.getInput().isKeyDown(Input.KEY_N))
		{
			menuState = 1;
		}
			
		if ((container.getInput().isKeyDown(Input.KEY_LEFT)) || (container.getInput().isKeyDown(Input.KEY_A))){
			    player = playerLeft;
				playerX--;
				playerPoly.setX(playerX);
		}
				try {
					if (entityCollisionWith()){
						playerX++;
           				playerPoly.setX(playerX);
					}
					if (enemyCollisionWith()){
						enemyX--;
						enemyPoly.setX(enemyX);
					}
					if (battle()){
						playerX += 20;
						playerPoly.setX(playerX + 20);
						enemyX--;
						enemyPoly.setX(enemyX);
						numCrosses--;
						enemyDead = true;
					}
					
				} catch (SlickException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
		if ((container.getInput().isKeyDown(Input.KEY_RIGHT)) || (container.getInput().isKeyDown(Input.KEY_D))) {
			player = playerRight;
			playerX++;
			playerPoly.setX(playerX);
			try {
				if (entityCollisionWith()){
					playerX--;
					playerPoly.setX(playerX);
				}
				if (enemyCollisionWith()){
					enemyX++;
					enemyPoly.setX(enemyX);
				}
				if (battle()){
					playerX-= 20;
					playerPoly.setX(playerX);
					enemyX++;
					enemyPoly.setX(enemyX);
					numCrosses--;
					enemyDead = true;
				}
			} catch (SlickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if ((container.getInput().isKeyDown(Input.KEY_UP)) || (container.getInput().isKeyDown(Input.KEY_W))){
			player = playerUp;
			playerY--;
			playerPoly.setY(playerY);
			try {
				if (entityCollisionWith()){
					playerY++;
					playerPoly.setY(playerY);
				}
				if (enemyCollisionWith()){
					enemyY--;
					enemyPoly.setY(enemyY);
				}
				if (battle()){
					playerY += 20;
					playerPoly.setY(playerY);
					enemyY--;
					enemyPoly.setY(enemyY);
					numCrosses--;
					enemyDead = true;
				}
			} catch (SlickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if ((container.getInput().isKeyDown(Input.KEY_DOWN)) || (container.getInput().isKeyDown(Input.KEY_S))){
			player = playerDown;
			playerY++;
			playerPoly.setY(playerY);
			try {
				if (entityCollisionWith()){
					playerY--;
					playerPoly.setY(playerY);
				}
				if (enemyCollisionWith()){
					enemyY++;
					enemyPoly.setY(enemyY);
				}
				if (battle()){
					playerY -= 20;
					playerPoly.setY(playerY);
					enemyY++;
					enemyPoly.setY(enemyY);
					numCrosses--;
					
				}
			} catch (SlickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		try {
			if (battle()){
				System.out.println("BATTLE");
				
			}
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		enemyX = enemyX++;
		enemyPoly.setX(enemyX);
	}
	
	public boolean battle() throws SlickException{
			if (enemyPoly.intersects(playerPoly)){
				return true;
		}
		return false;
	}
	
	public boolean enemyCollisionWith() throws SlickException{
		for (int i = 0; i < BlockMap.entities.size(); i++){
			Block entity1 = (Block) BlockMap.entities.get(i);
			if (enemyPoly.intersects(entity1.poly)){
				return true;
			}
					
		}
		return false;
	}
	
	   public boolean entityCollisionWith() throws SlickException
	   {
		   for (int i = 0; i < BlockMap.entities.size(); i++)
		   {
			   Block entity1 = (Block) BlockMap.entities.get(i);
			   if (playerPoly.intersects(entity1.poly)){
			   	return true;
		   }
					
		}
	   return false;
		
	   /*if((container.getInput().mousePressed()))
	   {
		           // on this one I was trying to set it up so that when you press the mouse button the player shoots an arrow.
	   }*/
	}
	
	public void render(GameContainer container, Graphics g)  {
		
	menu.draw();
	
	if(menuState == 1) // when 'n' is pressed menuState becomes = 1
	{
		BlockMap.tmap.render(0, 0);
		g.drawAnimation(player, playerX, playerY);
		//if(enemyDead == false)
		//{
			g.drawAnimation(enemy, enemyX, enemyY);
		//}
			
		//g.draw(enemyPoly);
		//g.draw(playerPoly);
		StatusPanel.draw(0, 480);
		
		//Draws the correct amount of red crosses after numCrosses is decremented when there is player enemy contact
		if(numCrosses == 5)
		{
		   cross1.draw(35, 520);
    	   cross2.draw(75, 520);
    	   cross3.draw(115, 520);
    	   cross4.draw(155, 520);
    	   cross5.draw(195, 520);
		}
		else if(numCrosses == 4)
		{
		   cross1.draw(35, 520);
	       cross2.draw(75, 520);
	       cross3.draw(115, 520);
	       cross4.draw(155, 520);
		}
		else if(numCrosses == 3)
		{
		   cross1.draw(35, 520);
	       cross2.draw(75, 520);
	       cross3.draw(115, 520);
		}
		else if(numCrosses == 2)
		{
		   cross1.draw(35, 520);
	       cross2.draw(75, 520);
		}
		else if(numCrosses == 1)
		{
		   cross1.draw(35, 520);
		}
		else if(numCrosses == 0)
		{
		   //"you died" object pops up on screen
			//numCrosses = 5;
			numLives--;
			numCrosses = 5;
		}
		else
			numCrosses = 5;
		
		//The # of life images drawn, depend on what numLives = (numLives is decremented when numCrosses hits 0)
		if( numLives == 3)
		{
			life1.draw(515, 520);
	    	life2.draw(555, 520);
	    	life3.draw(595, 520);
		}
		if( numLives == 2)
		{
			life1.draw(515, 520);
	    	life2.draw(555, 520);
		}
		if( numLives == 1)
		{
			life1.draw(515, 520);
		}
		if( numLives == 0)
		{
			gameover.draw(); //game over screen is displayed when numLives = 0
			
			/*game is reinitialized so that it's ready for a new game.
			try 
			{
				container.reinit();
			} 
			catch (SlickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
		}
	}
    
	}
	
	public static void main(String[] argv) throws SlickException {
		AppGameContainer container = new AppGameContainer(new Game(), 640, 580, false);
		container.start();
	}
}
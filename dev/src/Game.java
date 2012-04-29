import org.newdawn.slick.Animation;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Polygon;
import java.util.*;
public class Game extends BasicGame {
	Enemy enemy1, enemy2, enemy3, enemy4, enemy5, enemy6, enemy7, enemy8, enemy9, enemy10;
	SpriteSheet walk_L;
	SpriteSheet walk_R;
	SpriteSheet walk_U;
	SpriteSheet walk_D;
	SpriteSheet walk_Main;
	SpriteSheet stop_L;
	SpriteSheet stop_R;
	SpriteSheet stop_U;
	SpriteSheet stop_D;
	SpriteSheet attack_U;
	SpriteSheet attack_D;
	SpriteSheet attack_L;
	SpriteSheet attack_R;
	SpriteSheet enemySheet;
	Image cross1, cross2, cross3, cross4, cross5;
    Image life1, life2, life3;
    Image StatusPanel;
    Image empty;
    Image menu;
    Image gameover;
    Image startButton;
    Image dieScreen;
    Image tutorial;
    boolean restart;
    boolean quit;
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
	private Animation up, down, left, right;
	private Animation player;
	private Animation playerNoMove;
	private Animation playerLeft;
	private Animation playerRight;
	private Animation playerUp;
	private Animation playerDown;
	private Animation enemy;
//	private Animation boss;
//	private Animation nullAnimation = null;
	private Animation attackLeft;
	private Animation attackRight;
	private Animation attackUp;
	private Animation attackDown;
	
	private Polygon playerPoly;
	private Polygon enemyPoly;
	String direction = "null"; // Karl
	public BlockMap map;
	
	//experimenting with ArrayList of enemies
	private Enemy[] theEnemies = {enemy1, enemy2, enemy3, enemy4, enemy5, enemy6, enemy7, enemy8, enemy9, enemy10};
	private List<Enemy> enemyList;
 
	public Game() {
		super("Son of Z"); //name in window
	}
	
	
	public void init(GameContainer container) throws SlickException {
		enemyList = new ArrayList<Enemy>();
		//Adding enemies to ArrayList
    	enemyList.add(enemy1);
		enemyList.add(enemy2);
		enemyList.add(enemy3);
		enemyList.add(enemy4);
		enemyList.add(enemy5);
		enemyList.add(enemy6);
		enemyList.add(enemy7);
		enemyList.add(enemy8);
		enemyList.add(enemy9);
		enemyList.add(enemy10);
	 
		restart = false;
		quit = false;
		directionX = 1;
		enemyX = 32;
		enemyY = 32;
		enemyDead = false;
		playerX = 320;
		playerY = 240;
		menuState = 0;
		numCrosses = 5;
		numLives = 3;
		gameover = new Image("data/gameover_screen_white.png");
		menu = new Image("data/titlescreen.png");
		dieScreen = new Image("data/deadZ.png");
		tutorial = new Image("data/Tutorial.png");
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
    	// Karl
    	SpriteSheet stopD = new SpriteSheet("data/Standing/down.png", 47, 62);
    	SpriteSheet stopU = new SpriteSheet("data/Standing/up.png", 47, 62);
    	SpriteSheet stopL = new SpriteSheet("data/Standing/left.png", 47, 62);
    	SpriteSheet stopR = new SpriteSheet("data/Standing/right.png", 47, 62);
    	
    	SpriteSheet attack_D = new SpriteSheet("data/Attack/frontattack.png", 50, 63);
    	SpriteSheet attack_U = new SpriteSheet("data/Attack/backattack.png", 60, 63);
    	
    	SpriteSheet attack_R = new SpriteSheet("data/Attack/rightattack.png", 56, 63);
    	SpriteSheet attack_L = new SpriteSheet("data/Attack/leftattack.png", 54, 63);
    	// Karl
		container.setVSync(true);  //display syncs with vertical refresh
		walk_D = new SpriteSheet("data/Front/frontmove2.png", 47, 62); //player location
		walk_L = new SpriteSheet("data/L_side/L_sidetexture.png", 47, 48); //player location
		walk_R = new SpriteSheet("data/L_side/R_sidetexture.png", 47, 62);
		walk_U = new SpriteSheet("data/Back/backmove.png", 47, 62);
		enemySheet = new SpriteSheet(enemyRef, 32, 32); //enemy reference
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
		// Karl
		attackLeft = new Animation();
		attackLeft.setSpeed(playerSpeed);
		attackRight = new Animation();
		attackRight.setSpeed(playerSpeed);
		attackUp = new Animation();
		attackUp.setSpeed(playerSpeed);		
		attackDown = new Animation();
		attackDown.setSpeed(playerSpeed);
		
		up = new Animation();
		up.setSpeed(playerSpeed);
		down = new Animation();
		down.setSpeed(playerSpeed);
		left = new Animation();
		left.setSpeed(playerSpeed);
		right = new Animation();
		right.setSpeed(playerSpeed);
		
		// Karl
		enemy = new Animation();
		walk_Main = walk_D;
		enemy.setAutoUpdate(true);
		
		//Fill each player instance with frames from their respective sprite sheets (still need additional sprite sheets)
		for (int frame = 0; frame < 1; frame++){
			player.addFrame(walk_Main.getSprite(frame, 1), 12100); // 150 time in ms
		}
		for (int frame = 0; frame < 1; frame++){
			playerNoMove.addFrame(walk_Main.getSprite(frame, 1), 12100); // 150 time in ms
		}
		for (int frame = 0; frame < 1; frame++){
			up.addFrame(stopU.getSprite(frame, 1), 12100); // 150 time in ms
		}
		
		// ATTACK
		for (int frame = 0; frame < 4; frame++){
			attackLeft.addFrame(attack_L.getSprite(frame, 1), 12100); // 150 time in ms
		}
		for (int frame = 0; frame < 4; frame++){
			attackRight.addFrame(attack_R.getSprite(frame, 1), 12100); // 150 time in ms
		}
		for (int frame = 0; frame < 4; frame++){
			attackUp.addFrame(attack_U.getSprite(frame, 1), 12100); // 150 time in ms
		}
		for (int frame = 0; frame < 4; frame++){
			attackDown.addFrame(attack_D.getSprite(frame, 1), 12100); // 150 time in ms
		}
		// ATTACK
		
		for (int frame = 0; frame < 1; frame++){
			down.addFrame(stopD.getSprite(frame, 1), 12100); // 150 time in ms
		}
		for (int frame = 0; frame < 1; frame++){
			left.addFrame(stopL.getSprite(frame, 1), 12100); // 150 time in ms
		}
		for (int frame = 0; frame < 1; frame++){
			right.addFrame(stopR.getSprite(frame, 1), 12100); // 150 time in ms
		}
		for (int frame = 0; frame < 8; frame++){
			playerRight.addFrame(walk_R.getSprite(frame, 1), 11100); // 150 time in ms
		}
		for (int frame = 0; frame < 7; frame++){
			playerUp.addFrame(walk_U.getSprite(frame, 1), 11100); // 150 time in ms
		}
		for (int frame = 0; frame < 7; frame++){
			playerDown.addFrame(walk_D.getSprite(frame, 1), 11100); // 150 time in ms
		}
		for (int frame = 0; frame < 8; frame++){
			playerLeft.addFrame(walk_L.getSprite(frame, 0), 11100); // 150 time in ms
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
		if (container.getInput().isKeyPressed(Input.KEY_Q))
		{
			quit = true;
		}
		if (container.getInput().isKeyPressed(Input.KEY_T))
		{
			menuState = 3;
		}
		if (container.getInput().isKeyPressed(Input.KEY_ENTER))
		{
			restart = true;
		}
		if (container.getInput().isKeyPressed(Input.KEY_M))
		{
			menuState = 0;
		}
		if (container.getInput().isKeyPressed(Input.KEY_SPACE))
		{
			menuState = 1;
		}
		
		if ((container.getInput().isKeyDown(Input.KEY_LEFT)) || (container.getInput().isKeyDown(Input.KEY_A))){
			    player = playerLeft;
				playerX--;
				direction = "left";
				playerPoly.setX(playerX);
		}
		else
			player = playerNoMove;
		// Karl
		if(direction.equals("up"))
				playerNoMove = up;
		if(direction.equals("down"))
				playerNoMove = down;
		if(direction.equals("left"))
				playerNoMove = left;
		if(direction.equals("right"))
				playerNoMove = right;
		// Karl
		
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
						//enemyPoly.setClosed(true);
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
			direction = "right";
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
			direction = "up";
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
		if ((container.getInput().isKeyDown(Input.KEY_SPACE)))
		{
			
			if(direction.equals("down"))
			player = attackDown;
			if(direction.equals("up"))
			player = attackUp;
			if(direction.equals("left"))
			player = attackLeft;
			if(direction.equals("right"))
			player = attackRight;
		}
		if ((container.getInput().isKeyDown(Input.KEY_DOWN)) || (container.getInput().isKeyDown(Input.KEY_S)))
		{
			player = playerDown;
			playerY++;
			direction = "down";
			playerPoly.setY(playerY);
			try 
			{
				if (entityCollisionWith())
				{
					playerY--;
					playerPoly.setY(playerY);
				}
				if (enemyCollisionWith())
				{
					enemyY++;
					enemyPoly.setY(enemyY);
				}
				if (battle())
				{
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
		try 
		{
			if (battle())
			{
				
//				System.out.println("BATTLE");
				
			}
		} catch (SlickException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		System.out.println(direction); // KARL DEBUG
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
	if(quit == true)
	{
		container.exit();
	}
	if(restart == true)
	{
		//game is reinitialized so that it's ready for a new game.
		try 
		{
			container.reinit();
		} 
		catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		restart = false;
	}
	if(menuState == 3)
	{
		tutorial.draw();
	}
	if(menuState == 2)
	{
		dieScreen.draw();
		
	}
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
			menuState = 2;
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
		}
	}
    
	}
	
	public static void main(String[] argv) throws SlickException {
		AppGameContainer container = new AppGameContainer(new Game(), 640, 580, false);
		container.start();
	}
}
import org.newdawn.slick.Animation;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Polygon;
import java.util.*;

public class Game extends BasicGame {
	
	Sound whish;
	Sound painful;
	Music theme;
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
	private float playerX;
	private float playerY;
	int directionX;
	
	private Animation up, down, left, right;
	private Animation player;
	private Animation playerNoMove;
	private Animation playerLeft;
	private Animation playerRight;
	private Animation playerUp;
	private Animation playerDown;
	private Animation enemy;
//	private Animation boss;
	private Animation attackLeft;
	private Animation attackRight;
	private Animation attackUp;
	private Animation attackDown;
	
	private Polygon playerPoly;
	private Polygon playerAttackPoly;
	String direction = "null"; // Karl
	public BlockMap map;
	int index[];
	
	//experimenting with ArrayList of enemies
	Enemy enemy1, enemy2, enemy3, enemy4, enemy5, enemy6, enemy7, enemy8, enemy9, enemy10;
	private List<Enemy> enemyList;
 
	public Game() {
		super("Son of Z"); //name in window
	}
	
	
	public void init(GameContainer container) throws SlickException {
		
		
		//Initialize enemies at specific coords
		enemy1 = new Enemy(30, 30);
		enemy2 = new Enemy(30, 80);
		enemy3 = new Enemy(30, 130);
		enemy4 = new Enemy(30, 180);
		enemy5 = new Enemy(30, 230);
		enemy6 = new Enemy(30, 280);
		enemy7 = new Enemy(30, 330);
		enemy8 = new Enemy(30, 380);
		enemy9 = new Enemy(30, 430);
		enemy10 = new Enemy(30, 480);
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
    	stop_D = new SpriteSheet("data/Standing/down.png", 47, 62);
    	stop_U = new SpriteSheet("data/Standing/up.png", 47, 62);
    	stop_L = new SpriteSheet("data/Standing/left.png", 47, 62);
    	stop_R = new SpriteSheet("data/Standing/right.png", 47, 62);
    	attack_D = new SpriteSheet("data/Attack/frontattack.png", 50, 63);
    	attack_U = new SpriteSheet("data/Attack/backattack.png", 60, 63);
    	attack_R = new SpriteSheet("data/Attack/rightattack.png", 56, 63);
    	attack_L = new SpriteSheet("data/Attack/leftattack.png", 54, 63);
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
		
		//Fill each player instance with frames from their respective sprite sheets
		for (int frame = 0; frame < 1; frame++){
			player.addFrame(walk_Main.getSprite(frame, 1), 12100); //
		}
		for (int frame = 0; frame < 1; frame++){
			playerNoMove.addFrame(walk_Main.getSprite(frame, 1), 12100);
		}
		for (int frame = 0; frame < 1; frame++){
			up.addFrame(stop_U.getSprite(frame, 1), 12100);
		}
		
		// ATTACK
		for (int frame = 0; frame < 4; frame++){
			attackLeft.addFrame(attack_L.getSprite(frame, 1), 12100);
		}
		for (int frame = 0; frame < 4; frame++){
			attackRight.addFrame(attack_R.getSprite(frame, 1), 12100);
		}
		for (int frame = 0; frame < 4; frame++){
			attackUp.addFrame(attack_U.getSprite(frame, 1), 12100);
		}
		for (int frame = 0; frame < 4; frame++){
			attackDown.addFrame(attack_D.getSprite(frame, 1), 12100);
		}
		// ATTACK
		
		for (int frame = 0; frame < 1; frame++){
			down.addFrame(stop_D.getSprite(frame, 1), 12100); 
		}
		for (int frame = 0; frame < 1; frame++){
			left.addFrame(stop_L.getSprite(frame, 1), 12100); 
		}
		for (int frame = 0; frame < 1; frame++){
			right.addFrame(stop_R.getSprite(frame, 1), 12100); 
		}
		for (int frame = 0; frame < 8; frame++){
			playerRight.addFrame(walk_R.getSprite(frame, 1), 11100); 
		}
		for (int frame = 0; frame < 7; frame++){
			playerUp.addFrame(walk_U.getSprite(frame, 1), 11100); 
		}
		for (int frame = 0; frame < 7; frame++){
			playerDown.addFrame(walk_D.getSprite(frame, 1), 11100); 
		}
		for (int frame = 0; frame < 8; frame++){
			playerLeft.addFrame(walk_L.getSprite(frame, 0), 11100);
		}
		playerPoly = new Polygon(new float[]{
				 playerX + 5, playerY,
				 playerX + 41, playerY,
				 playerX + 41, playerY + 47,
				 playerX + 5, playerY + 47
		});	
		playerAttackPoly = new Polygon(new float[]{
				 playerX - 20, playerY - 20,
				 playerX + 60, playerY - 20,
				 playerX + 60, playerY + 60,
				 playerX - 20, playerY + 60
		});	
		// ENEMIES
		for (int frame = 0; frame < 2; frame++){
			enemy.addFrame(enemySheet.getSprite(frame, 0), 1000000000);
		}
		
		whish = new Sound("data/sounds/Whish.wav");
		painful = new Sound("data/sounds/painful.wav");
		//theme = new Music("");
	}
	
	public void update(GameContainer container, int delta) 
	{ 	
		
		Enemy s;
	    Iterator<Enemy> e = enemyList.iterator();
	    while (e.hasNext()) {
	      s = (Enemy) e.next();
	      if(s != null)
	      {
		      if(s.getDirectionX() < 1)
				{
					s.setX(s.getX() - 1);
				    s.setPolyX(s.getX());
				}
				else
				{
					s.setX(s.getX() + 1);
				    s.setPolyX(s.getX());
				}
					try 
					{
						if (enemyCollision(s)){
							if(s.getDirectionX() < 1)
							{
								s.setX(s.getX() - s.getDirectionX()*2);
								s.setPolyX(s.getX());
							    s.setDirectionX(s.getDirectionX() * -1);
							}
							else
							{
								s.setDirectionX(s.getDirectionX() * -1);
								s.setX(s.getX() + s.getDirectionX()*2);	
								s.setPolyX(s.getX());
							}
						}
					} 
					catch (SlickException e1) 
					{
						// TODO Auto-generated catch block
						e1.printStackTrace();
				    }
					try {
						if ((attack(s)) && (container.getInput().isKeyPressed(Input.KEY_SPACE))){
							if(s != null )
							enemyList.indexOf(s);
							enemyList.remove(s); 
							e = enemyList.iterator();
						}
					} catch (SlickException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
	      	}
	    }
			enemyList.remove(index);
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
				playerPoly = new Polygon(new float[]{
						 playerX + 5, playerY,
						 playerX + 41, playerY,
						 playerX + 41, playerY + 47,
						 playerX + 5, playerY + 47
				});	
				playerAttackPoly = new Polygon(new float[]{
						 playerX - 20, playerY - 20,
						 playerX + 60, playerY - 20,
						 playerX + 60, playerY + 60,
						 playerX - 20, playerY + 60
				});	
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
           				playerAttackPoly.setX(playerX);
					}
					e = enemyList.iterator();
				    while (e.hasNext()) {
				      s = (Enemy) e.next();
				      if(s != null)
				      {
				    	  if (battle(s)){
				    			
								playerX += 20;
								playerPoly.setX(playerX);
								s.setX(s.getX() - 1);
								s.setPolyX(s.getX());					
								numCrosses--;
				    	  }
				      } 
				    }
					
				} catch (SlickException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		
		if ((container.getInput().isKeyDown(Input.KEY_RIGHT)) || (container.getInput().isKeyDown(Input.KEY_D))) {
			player = playerRight;
			playerX++;
			direction = "right";
			playerPoly = new Polygon(new float[]{
					 playerX + 5, playerY,
					 playerX + 41, playerY,
					 playerX + 41, playerY + 47,
					 playerX + 5, playerY + 47
			});	
			playerAttackPoly = new Polygon(new float[]{
					 playerX - 20, playerY - 20,
					 playerX + 60, playerY - 20,
					 playerX + 60, playerY + 60,
					 playerX - 20, playerY + 60
			});	
		}
			try {
				if (entityCollisionWith()){
					playerX--;
					playerPoly.setX(playerX);
					playerAttackPoly.setX(playerX);
				}
				e = enemyList.iterator();
			    while (e.hasNext()) {
			      s = (Enemy) e.next();
			      if(s != null)
			      {
			    	  if (battle(s)){
			    			
							playerX -= 20;
							playerPoly.setX(playerX);
							s.setX(s.getX() + 1);
							s.setPolyX(s.getX());					
							numCrosses--;
			    	  }
			      } 
			    }
			} catch (SlickException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
		
		if ((container.getInput().isKeyDown(Input.KEY_UP)) || (container.getInput().isKeyDown(Input.KEY_W))){
			player = playerUp;
			playerY--;
			direction = "up";
			playerPoly = new Polygon(new float[]{
					 playerX + 5, playerY,
					 playerX + 41, playerY,
					 playerX + 41, playerY + 47,
					 playerX + 5, playerY + 47
			});	
			playerAttackPoly = new Polygon(new float[]{
					 playerX - 20, playerY - 20,
					 playerX + 60, playerY - 20,
					 playerX + 60, playerY + 60,
					 playerX - 20, playerY + 60
			});	
			
			try {
				if (entityCollisionWith()){
					playerY++;
					playerPoly.setY(playerY);
					playerAttackPoly.setY(playerY);
				}
				
				e = enemyList.iterator();
			    while (e.hasNext()) {
			      s = (Enemy) e.next();
			      if(s != null)
			      {
			    	  if (battle(s)){
			    			
							playerY += 20;
							playerPoly.setY(playerY);
							s.setY(s.getY() - 1);
							s.setPolyY(s.getY());					
							numCrosses--;
			    	  }
			      } 
			    }
			} catch (SlickException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		if ((container.getInput().isKeyDown(Input.KEY_DOWN)) || (container.getInput().isKeyDown(Input.KEY_S)))
		{
			player = playerDown;
			playerY++;
			direction = "down";
			playerPoly = new Polygon(new float[]{
					 playerX + 5, playerY,
					 playerX + 41, playerY,
					 playerX + 41, playerY + 47,
					 playerX + 5, playerY + 47
			});	
			playerAttackPoly = new Polygon(new float[]{
					 playerX - 20, playerY - 20,
					 playerX + 60, playerY - 20,
					 playerX + 60, playerY + 60,
					 playerX - 20, playerY + 60
			});	
			
			try 
			{
				if (entityCollisionWith())
				{
					playerY--;
					playerPoly.setY(playerY);
					playerAttackPoly.setY(playerY);
				}
				
				e = enemyList.iterator();
			    while (e.hasNext()) {
			      s = (Enemy) e.next();
			      if(s != null)
			      {
			    	  if (battle(s)){
			    			
							playerY -= 20;
							playerPoly.setY(playerY);
							s.setY(s.getY() + 1);
							s.setPolyY(s.getY());					
							numCrosses--;
			    	  }
			      } 
			    }
			} catch (SlickException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
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
	}
	

	public boolean attack(Enemy e) throws SlickException{
		if(e.getPoly().intersects(playerAttackPoly)){
			return true;
		}
		return false;
	}
	public boolean battle(Enemy e) throws SlickException{
			if (e.getPoly().intersects(playerPoly)){
				return true;
		}
		return false;
	}
	public boolean enemyCollision(Enemy e) throws SlickException{
		for (int i = 0; i < BlockMap.entities.size(); i++){
			Block entity1 = (Block) BlockMap.entities.get(i);
			if (e.getPoly().intersects(entity1.poly)){
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
	}
	
	public void render(GameContainer container, Graphics g)  {
		
	menu.draw();
	//theme.play();
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
		//g.draw(playerPoly);
		//g.draw(playerAttackPoly);
		
		Enemy s;
	    Iterator<Enemy> e = enemyList.iterator();
	    while (e.hasNext()) {
	      s = (Enemy) e.next();
	      if(s != null)
	      {
	         g.drawAnimation(s.getAnimation(), s.getX(), s.getY());
	         s.setPolyX(s.getX());
	         s.setPolyY(s.getY());
	      } 
	    }
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
			painful.play();
			numLives--;
			numCrosses = 5;
			menuState = 2;
		    player = playerLeft;
			playerX = 320;
			playerY = 240;
			direction = "left";
			playerPoly.setX(playerX);
			playerPoly.setY(playerY);
			playerAttackPoly.setX(playerX);
			playerAttackPoly.setY(playerY);
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
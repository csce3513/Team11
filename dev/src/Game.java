
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
import java.util.*;

public class Game extends BasicGame {
	
	Sound whish;
	Sound painful;
	Sound injured;
	Music theme;

	SpriteSheet enemySheet;
	Image cross1, cross2, cross3, cross4, cross5;
    Image life1, life2, life3;
    Image StatusPanel;
    Image empty;
    Image menu;
    Image gameover;
    Image startButton;
    Image dieScreen;
    Image youWinScreen;
    Image tutorial;
    boolean restart;
    boolean quit;
    int menuState;
    int gameState = 1;
    int numDeadEnemies;
    String enemyRef = "data/enemies.png";
	int numCrosses;
	int numLives;

	boolean enemyDead;

	int directionX;

//	private Animation boss;

	String direction = "null"; // Karl
	public BlockMap map1;
	public BlockMap map2;
	int index[];
	
	//experimenting with ArrayList of enemies
	Enemy enemy1, enemy2, enemy3, enemy4, enemy5, enemy6, enemy7, enemy8, enemy9, enemy10, enemy11, enemy12, enemy13, enemy14, enemy15;
	Player player1;
	Transporter door;
	private List<Enemy> enemyList;
	private List<Enemy> enemyList2;
 
	public Game() {
		super("Son of Z"); //name in window
	}
	
	
	public void init(GameContainer container) throws SlickException {
		

		
		whish = new Sound("data/sounds/Whish.wav");
		painful = new Sound("data/sounds/painful.wav");
		injured = new Sound("data/sounds/Shot in face.wav");
		//Initialize enemies at specific coords
		enemy1 = new Enemy(30, 30);
		enemy2 = new Enemy(90, 80);
		enemy3 = new Enemy(30, 130);
		enemy4 = new Enemy(74, 180);
		enemy5 = new Enemy(49, 230);
		enemy6 = new Enemy(400, 280);
		enemy7 = new Enemy(30, 330);
		enemy8 = new Enemy(30, 380);
		enemy9 = new Enemy(30, 430);
		enemy10 = new Enemy(30, 480);
		enemy11 = new Enemy(93, 230);
		enemy12 = new Enemy(150, 100);
		enemy13 = new Enemy(210, 330);
		enemy14 = new Enemy(490, 390);
		enemy15 = new Enemy(580, 270);
		enemyList = new ArrayList<Enemy>();
		enemyList2 = new ArrayList<Enemy>();
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
		
		enemyList2.add(enemy11);
		enemyList2.add(enemy12);
		enemyList2.add(enemy13);
		enemyList2.add(enemy14);
		enemyList2.add(enemy15);
		
		player1 = new Player(320, 140);
		
		
		
		door = new Transporter(636, 208);
		
		container.setVSync(true);  //display syncs with vertical refresh
	 
		restart = false;
		quit = false;
		directionX = 1;
		numDeadEnemies = 0;
		enemyDead = false;
		menuState = 0;
		numCrosses = 5;
		numLives = 3;
		gameover = new Image("data/gameover_screen_white.png");
		menu = new Image("data/titlescreen.png");
		dieScreen = new Image("data/deadZ.png");
		tutorial = new Image("data/Tutorial.png");
		youWinScreen = new Image("data/youWin_screen.png");
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

		enemySheet = new SpriteSheet(enemyRef, 32, 32); //enemy reference
		

			map1 = new BlockMap("data/map1.tmx"); //map location
			map2 = new BlockMap("data/map2.tmx"); //map location



	
		//theme = new Music("");
	}
	
	public void update(GameContainer container, int delta) 
	{ 	
		
		//System.out.println(gameState);
		
		
		if (gameState ==1){
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
							if (enemyCollision(s, map1)){
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
							if ((attack(s)) && (container.getInput().isKeyDown(Input.KEY_SPACE))){
								if(s != null )
								enemyList.indexOf(s);
								enemyList.remove(s); 
								//numDeadEnemies++;
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
				//blaine
				player1.setDirection("left");
				player1.setX(player1.getX()-1);

					try {
						if (entityCollisionWith(map1)){
							player1.setX(player1.getX()+1);
						}
						e = enemyList.iterator();
					    while (e.hasNext()) {
					      s = (Enemy) e.next();
					      if(s != null)
					      {
					    	  if (battle(s)){
					    		  player1.setX(player1.getX()+20);

									s.setX(s.getX() - 1);
									s.setPolyX(s.getX());					
									numCrosses--;
									injured.play();
					    	  }
					      } 
					    }
						
					} catch (SlickException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}
					
					else
						
						player1.stopMovement();
			

			if ((container.getInput().isKeyDown(Input.KEY_RIGHT)) || (container.getInput().isKeyDown(Input.KEY_D))) {
				//blaine
				player1.setDirection("right");
				player1.setX(player1.getX()+1);
				
				try {
					if (entityCollisionWith(map1)){
						player1.setX(player1.getX()-1);
					}
					e = enemyList.iterator();
				    while (e.hasNext()) {
				      s = (Enemy) e.next();
				      if(s != null)
				      {
				    	  if (battle(s)){
				    		  player1.setX(player1.getX()-20);

								s.setX(s.getX() + 1);
								s.setPolyX(s.getX());					
								numCrosses--;
								injured.play();
				    	  }
				      } 
				    }
				} catch (SlickException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
			
			if ((container.getInput().isKeyDown(Input.KEY_UP)) || (container.getInput().isKeyDown(Input.KEY_W))){
				//blaine
				player1.setDirection("up");
				player1.setY(player1.getY()-1);

				try {
					if (entityCollisionWith(map1)){
						player1.setY(player1.getY()+1);
					}
					
					e = enemyList.iterator();
				    while (e.hasNext()) {
				      s = (Enemy) e.next();
				      if(s != null)
				      {
				    	  if (battle(s)){
				    		  
				    		  player1.setY(player1.getY()+20);
								s.setY(s.getY() - 1);
								s.setPolyY(s.getY());					
								numCrosses--;
								injured.play();
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
				//blaine
				player1.setDirection("down");
				player1.setY(player1.getY()+1);
				//System.out.println(player1.getDirection());

				try 
				{
					if (entityCollisionWith(map1))
					{
						player1.setY(player1.getY()-1);
					}
					
					e = enemyList.iterator();
				    while (e.hasNext()) {
				      s = (Enemy) e.next();
				      if(s != null)
				      {
				    	  if (battle(s)){
				    		  
				    		  player1.setY(player1.getY()-20);

								s.setY(s.getY() + 1);
								s.setPolyY(s.getY());					
								numCrosses--;
								injured.play();
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
				//whish.play();
				player1.isAttacking(true);

			}
			try {
				if (doorCollisionWith(door, map1)){
					gameState = 2;
					player1.setX(18);
					player1.setY(208);
					enemyList.clear();
				}
			} catch (SlickException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		if (gameState ==2){

			Enemy s;
		    Iterator<Enemy> e = enemyList2.iterator();
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
							if (enemyCollision(s, map2)){
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
							if ((attack(s)) && (container.getInput().isKeyDown(Input.KEY_SPACE))){
								if(s != null )
								enemyList2.indexOf(s);
								enemyList2.remove(s); 
								numDeadEnemies++;
								e = enemyList2.iterator();
							}
						} catch (SlickException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
		      	}
		    }
				enemyList2.remove(index);
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
			if ((container.getInput().isKeyPressed(Input.KEY_SPACE)) && (menuState==0 || menuState==3 || menuState==2))
			{
				menuState = 1;
				gameState = 1;
			}
			
			if ((container.getInput().isKeyDown(Input.KEY_LEFT)) || (container.getInput().isKeyDown(Input.KEY_A))){
				//blaine
				player1.setDirection("left");
				player1.setX(player1.getX()-1);

					try {
						if (entityCollisionWith(map2)){
							player1.setX(player1.getX()+1);
						}
						e = enemyList2.iterator();
					    while (e.hasNext()) {
					      s = (Enemy) e.next();
					      if(s != null)
					      {
					    	  if (battle(s)){
					    		  player1.setX(player1.getX()+20);

									s.setX(s.getX() - 1);
									s.setPolyX(s.getX());					
									numCrosses--;
									injured.play();
					    	  }
					      } 
					    }
						
					} catch (SlickException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}
					
					else
						
						player1.stopMovement();
			

			if ((container.getInput().isKeyDown(Input.KEY_RIGHT)) || (container.getInput().isKeyDown(Input.KEY_D))) {
				//blaine
				player1.setDirection("right");
				player1.setX(player1.getX()+1);
				
				try {
					if (entityCollisionWith(map2)){
						player1.setX(player1.getX()-1);
					}
					e = enemyList2.iterator();
				    while (e.hasNext()) {
				      s = (Enemy) e.next();
				      if(s != null)
				      {
				    	  if (battle(s)){
				    		  player1.setX(player1.getX()-20);

								s.setX(s.getX() + 1);
								s.setPolyX(s.getX());					
								numCrosses--;
								injured.play();
				    	  }
				      } 
				    }
				} catch (SlickException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
			
			if ((container.getInput().isKeyDown(Input.KEY_UP)) || (container.getInput().isKeyDown(Input.KEY_W))){
				//blaine
				player1.setDirection("up");
				player1.setY(player1.getY()-1);

				try {
					if (entityCollisionWith(map2)){
						player1.setY(player1.getY()+1);
					}
					
					e = enemyList2.iterator();
				    while (e.hasNext()) {
				      s = (Enemy) e.next();
				      if(s != null)
				      {
				    	  if (battle(s)){
				    		  
				    		  player1.setY(player1.getY()+20);
								s.setY(s.getY() - 1);
								s.setPolyY(s.getY());					
								numCrosses--;
								injured.play();
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
				//blaine
				player1.setDirection("down");
				player1.setY(player1.getY()+1);
				//System.out.println(player1.getDirection());

				try 
				{
					if (entityCollisionWith(map2))
					{
						player1.setY(player1.getY()-1);
					}
					
					e = enemyList2.iterator();
				    while (e.hasNext()) {
				      s = (Enemy) e.next();
				      if(s != null)
				      {
				    	  if (battle(s)){
				    		  
				    		  player1.setY(player1.getY()-20);

								s.setY(s.getY() + 1);
								s.setPolyY(s.getY());					
								numCrosses--;
								injured.play();
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
				player1.isAttacking(true);

			}

		}
		
	}
	

	public boolean attack(Enemy e) throws SlickException{
		if(e.getPoly().intersects(player1.getAttackPoly())){
			return true;
		}
		return false;
	}
	public boolean battle(Enemy e) throws SlickException{
			if (e.getPoly().intersects(player1.getPoly())){
				return true;
		}
		return false;
	}
	public boolean enemyCollision(Enemy e, BlockMap m) throws SlickException{
		for (int i = 0; i < m.entities.size(); i++){
			Block entity1 = (Block) m.entities.get(i);
			if (e.getPoly().intersects(entity1.poly)){
				return true;
			}
					
		}
		return false;
	}
	
	   public boolean entityCollisionWith(BlockMap m) throws SlickException
	   {
		   for (int i = 0; i < m.entities.size(); i++)
		   {
			   Block entity1 = (Block) m.entities.get(i);
			   if (player1.getPoly().intersects(entity1.poly)){
			   	return true;
		   }
					
		}
	   return false;
	}
	   
	   public boolean doorCollisionWith(Transporter t, BlockMap m) throws SlickException{
		   for (int i = 0; i < m.entities.size(); i++){
			   if (player1.getPoly().intersects(t.getPoly())){
			   	return true;
			   }	
		   }
		   return false;
	   }
	   
	   

	public void render(GameContainer container, Graphics g)  {
		
	menu.draw();
	//theme.play();
	if(numDeadEnemies >= 5)
		menuState = 4;
	
	if(menuState == 4)
	{
		youWinScreen.draw();
	}
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
		if (gameState ==1){
			//System.out.println("###################");
			map1.tmap.render(0, 0);
			g.drawAnimation(player1.getAnimation(), player1.getX(), player1.getY());
			//g.draw(player1.getPoly());
			//g.draw(player1.getAttackPoly());
			//g.draw(playerAttackPoly);
			//g.draw(door.getPoly());
		
		
			Enemy s;
			Iterator<Enemy> e = enemyList.iterator();
			while (e.hasNext()) {
				s = (Enemy) e.next();
				if(s != null){
					g.drawAnimation(s.getAnimation(), s.getX(), s.getY());
					s.setPolyX(s.getX());
					s.setPolyY(s.getY());
				} 
			}
		}
		
		else if (gameState == 2){
			map2.tmap.render(0, 0);
			

			g.drawAnimation(player1.getAnimation(), player1.getX(), player1.getY());
			//g.draw(player1.getPoly());
			//g.draw(player1.getAttackPoly());
			//g.draw(playerAttackPoly);
			// g .draw(door.getPoly());
		
		
			Enemy s;
			Iterator<Enemy> e = enemyList2.iterator();
			while (e.hasNext()) {
				s = (Enemy) e.next();
				if(s != null){
					g.drawAnimation(s.getAnimation(), s.getX(), s.getY());
					s.setPolyX(s.getX());
					s.setPolyY(s.getY());
				} 
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
			player1.setX(320);
			player1.setY(240);

			direction = "left";
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
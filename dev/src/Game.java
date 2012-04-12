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
	
	Image cross1, cross2, cross3, cross4, cross5;
    Image life1, life2, life3;
    Image StatusPanel;
    Image empty;
    Image menu;
    Image gameover;
    Image startButton;
    int menuState = 0;
    String enemyRef = "data/enemy.png";
	int numCrosses = 5;
	int numLives = 3;
	float playerSpeed = 90;
	//player start position
	private float playerX = 320;
	private float playerY = 240;
	
	private float enemyX = 32;
	private float enemyY = 32;
	
	//map using Tiled
	//private TiledMap map;
	
	private Animation player;
	private Animation enemy;
	
	private Polygon playerPoly;
	private Polygon enemyPoly;
	
	public BlockMap map;
 
	public Game() {
		super("Son of Z"); //name in window
	}
	
	
	public void init(GameContainer container) throws SlickException {
		
		gameover = new Image("data/gameover.png");
		menu = new Image("data/titlescreen.png");
		StatusPanel = new Image("data/StatusPanel.png");
		cross1 = new Image("data/RedCross.png");
    	cross2 = new Image("data/RedCross.png");
    	cross3 = new Image("data/RedCross.png");
    	cross4 = new Image("data/RedCross.png");
    	cross5 = new Image("data/RedCross.png");
    	life1 = new Image("data/front.png");
    	life2 = new Image("data/front.png");
    	life3 = new Image("data/front.png");
    	empty = new Image("data/empty.png");
		container.setVSync(true);  //display syncs with vertical refresh
		SpriteSheet sheet = new SpriteSheet("data/front.png", 32, 44); //player location
		SpriteSheet enemySheet = new SpriteSheet(enemyRef, 32, 32); //enemy reference
		map = new BlockMap("data/map1.tmx"); //map location
		player = new Animation();
		//player.setAutoUpdate(true);
		player.setSpeed(playerSpeed);
		enemy = new Animation();
		//enemy.setAutoUpdate(true);
		
		
		//movement animation
		for (int frame = 0; frame < 1; frame++){
			player.addFrame(sheet.getSprite(frame, 0), 150);
		}
		
		playerPoly = new Polygon(new float[]{
											 playerX, playerY,
											 playerX+32, playerY,
											 playerX+32, playerY+44,
											 playerX, playerY+44
		});	
		
		for (int frame = 0; frame < 1; frame++){
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
		if(container.getInput().isKeyDown(Input.KEY_N))
		{
			menuState = 1;
		}
		if ((container.getInput().isKeyDown(Input.KEY_LEFT)) || (container.getInput().isKeyDown(Input.KEY_A))){
			playerX--;
			enemyX++;
			playerPoly.setX(playerX);
			enemyPoly.setX(enemyX);
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
				
				}
				
			} catch (SlickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if ((container.getInput().isKeyDown(Input.KEY_RIGHT)) || (container.getInput().isKeyDown(Input.KEY_D))) {
			playerX++;
			enemyX--;
			playerPoly.setX(playerX);
			enemyPoly.setX(enemyX);
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
				}
			} catch (SlickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if ((container.getInput().isKeyDown(Input.KEY_UP)) || (container.getInput().isKeyDown(Input.KEY_W))){
			playerY--;
			enemyY++;
			playerPoly.setY(playerY);
			enemyPoly.setY(enemyY);
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
				}
			} catch (SlickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if ((container.getInput().isKeyDown(Input.KEY_DOWN)) || (container.getInput().isKeyDown(Input.KEY_S))){
			playerY++;
			enemyY--;
			playerPoly.setY(playerY);
			enemyPoly.setY(enemyY);
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
				enemy.addFrame(empty, 150);

				
			}
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
	
	public boolean entityCollisionWith() throws SlickException{
		for (int i = 0; i < BlockMap.entities.size(); i++){
			Block entity1 = (Block) BlockMap.entities.get(i);
			if (playerPoly.intersects(entity1.poly)){
				return true;
			}
					
		}
		return false;
	}
	
	public void render(GameContainer container, Graphics g)  {
		
	menu.draw();
		
	if(menuState == 1)
	{
		BlockMap.tmap.render(0, 0);
		g.drawAnimation(player, playerX, playerY);
		g.drawAnimation(enemy, enemyX, enemyY);
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
			gameover.draw();
		}
	}
    
	}
	
	public static void main(String[] argv) throws SlickException {
		AppGameContainer container = new AppGameContainer(new Game(), 640, 580, false);
		container.start();
	}
}
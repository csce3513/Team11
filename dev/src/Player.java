import org.newdawn.slick.Animation;


import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Polygon;
 
public class Player 
{
	int x;
	int y;
	int health = 5;
	int numLives = 3;
	int playerSpeed = 90;
	Animation player;
	Animation animation;
	Animation left; 
	Animation right;
	Animation up;
	Animation down;
	Animation stopped;
	Animation walkUp;
	Animation walkDown;
	Animation walkLeft;
	Animation walkRight;
	Animation attackLeft;
	Animation attackRight;
	Animation attackUp;
	Animation attackDown;
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
	private Polygon poly;
	private Polygon attackPoly;
	private String direction = "down";
	public Player(int x, int y)
	{
		
		animation = new Animation();
		left = new Animation();
		right = new Animation();
		up = new Animation();
		down = new Animation();
		stopped = new Animation();
		
		this.y = y;
		this.x = x;
		
		poly = new Polygon(new float[]{
				 x   , y,
				 x + 41  , y,
				 x + 41  , y + 47,
				 x   , y + 47
      });	
		
		attackPoly = new Polygon(new float[]{
				 x , y-20,
				 x + 80, y-20,
				 x + 80, y + 60,
				 x, y +60
		});	
		
		try {
			stop_D = new SpriteSheet("data/Standing/down.png", 47, 62);
	    	stop_U = new SpriteSheet("data/Standing/up.png", 47, 62);
	    	stop_L = new SpriteSheet("data/Standing/left.png", 47, 62);
	    	stop_R = new SpriteSheet("data/Standing/right.png", 47, 62);
	    	attack_D = new SpriteSheet("data/Attack/frontattack.png", 50, 63);
	    	attack_U = new SpriteSheet("data/Attack/backattack.png", 60, 63);
	    	attack_R = new SpriteSheet("data/Attack/rightattack.png", 56, 63);
	    	attack_L = new SpriteSheet("data/Attack/leftattack.png", 54, 63);
	    	walk_D = new SpriteSheet("data/Front/frontmove2.png", 47, 62); //player location
			walk_L = new SpriteSheet("data/L_side/L_sidetexture.png", 47, 48); //player location
			walk_R = new SpriteSheet("data/L_side/R_sidetexture.png", 47, 62);
			walk_U = new SpriteSheet("data/Back/backmove.png", 47, 62);
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		player = new Animation();
		player.setSpeed(playerSpeed);
		stopped = new Animation();
		stopped.setSpeed(playerSpeed);
		walkLeft = new Animation();
		walkLeft.setSpeed(playerSpeed);
		walkRight = new Animation();
		walkRight.setSpeed(playerSpeed);
		walkUp = new Animation();
		walkUp.setSpeed(playerSpeed);
		walkDown = new Animation();
		walkDown.setSpeed(playerSpeed);
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
		
		walk_Main = walk_D;
		
		
		for (int frame = 0; frame < 1; frame++){
			player.addFrame(walk_Main.getSprite(frame, 1), 12100); //
		}
		for (int frame = 0; frame < 1; frame++){
			stopped.addFrame(walk_Main.getSprite(frame, 1), 12100);
		}
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
		for (int frame = 0; frame < 1; frame++){
			up.addFrame(stop_U.getSprite(frame, 1), 12100);
		}
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
			walkRight.addFrame(walk_R.getSprite(frame, 1), 11100); 
		}
		for (int frame = 0; frame < 7; frame++){
			walkUp.addFrame(walk_U.getSprite(frame, 1), 11100); 
		}
		for (int frame = 0; frame < 7; frame++){
			walkDown.addFrame(walk_D.getSprite(frame, 1), 11100); 
		}
		for (int frame = 0; frame < 8; frame++){
			walkLeft.addFrame(walk_L.getSprite(frame, 0), 11100);
		}


		
		
	}
	
	public void setDirection(String direction){
		this.direction = direction;
		//moving = true;
		if (direction.equals("up")){
			player = walkUp;
			stopped = up;
		}
		if (direction.equals("down")){
			player = walkDown;
			stopped = down;
		}
		if (direction.equals("left")){
			player = walkLeft;
			stopped = left;
		}
		if (direction == "right"){
			player = walkRight;
			stopped = right;
		}

		
	}
	
	public void stopMovement(){
		player = stopped;
	}
	
	public String getDirection(){
		return direction;
	}
	
	public void isAttacking(Boolean attack){
		if (direction.equals("down")){
			player = attackDown;
		}
		if (direction.equals("up")){
			player = attackUp;
		}
		if (direction.equals("left")){
			player = attackLeft;
		}
		if (direction.equals("right")){
			player = attackRight;
		}
	}
	
	
	public void setAnimation(Animation a)
	{
		animation = a;
	}
	public Polygon getPoly() {
		return poly;
	}
	public Polygon getAttackPoly(){
		return attackPoly;
	}
	public void setPoly(Polygon poly) {
		this.poly = poly;
	}
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public void setX(int x){
		this.x = x;
		poly.setX(x);
		attackPoly.setX(x-20);
	}
	public void setY(int y){
		this.y = y;
		poly.setY(y);
		attackPoly.setY(y-20);
	}
	public Animation getAnimation()
	{
		return player;
	}

}

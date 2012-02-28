package entity;
import java.util.ArrayList;
import java.util.Iterator; 
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import entity.TopDownMovement;
import entity.ImageRenderComponent;
import entity.Entity;
import static org.junit.Assert.*;
import org.junit.Test;


public class Intermission1SlickBasicGameTest 
{
	Intermission1SlickBasicGame test = new Intermission1SlickBasicGame();
	Vector2f playerposition = new Vector2f(250,250);
	Vector2f enemyposition = new Vector2f(250,250);
	Entity player = new Entity("player");
	Entity enemy = new Entity("enemy");
	player.setPosition(playerposition);
	boolean check = false;
	@Test
	public void testCollision() 
	{
		check = test.collision(player, enemy);
		assertTrue(check);
	}

}

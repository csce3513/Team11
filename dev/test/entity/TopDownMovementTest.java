package entity;

import static org.junit.Assert.*;

import org.junit.Test;

public class TopDownMovementTest {
	
	TopDownMovement test = new TopDownMovement(null);
	@Test
	public void testGetSpeed() 
	{
		assertEquals("Result", 1.0f, 0);
	}

}

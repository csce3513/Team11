import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class HealthTest {

	@Before
	public void setUp() throws Exception
	{
	   Health health = new Health();
	}
	@After
	public void tearDown() throws Exception {
	}
	@Test
	public void testHealth()
	{
	   Health myHealth = new Health();
	   assertEquals(100, myHealth.getHealth());
	}
	@Test
	public void testGetHealth() {
	   Health hisHealth = new Health();
	   assertEquals(100, hisHealth.getHealth());
	}
	@Test
	public void testSetCurrentHealth()
	{
	   Health yourHealth = new Health();
	   int num;
	   
	   yourHealth.setHealth(55);
	   num = yourHealth.getHealth();
	   assertEquals(55, num);
	}
}

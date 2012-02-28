package entity;

import static org.junit.Assert.*;

import org.junit.Test;

public class testTest {
	test test = new test();
	int a = 3;
	int b = 4;
	int c = 7;
	int total = 0;
	@Test
	public void testMaths() 
	{
		total = test.maths(a, b);
		assertEquals(total, c);
	}

}

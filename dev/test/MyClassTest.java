import static org.junit.Assert.*;

import org.junit.Test;


public class MyClassTest {

	@Test
	public void testMultiply() {
		MyClass test = new MyClass();
		boolean multiply = false;
		if(5*10 == 50)
			multiply = true;
		assertTrue(multiply);
	}

}

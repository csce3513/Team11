import static org.junit.Assert.*;

import org.junit.Test;


public class MyClassTest {

	@Test
	public void testMultiply() {
		MyClass test = new MyClass();
		assertEquals("Result", 50, test.multiply(10, 5));
	}

}

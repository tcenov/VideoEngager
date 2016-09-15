package android2;

import org.testng.annotations.Test;

public class TestCase3{

	@Test
	public void case1() {
		
		int a = 20/0;
		System.out.println("Krai na test1");
	}
	@Test
	public void case2() {

		System.out.println("Krai na test2");
	}
}
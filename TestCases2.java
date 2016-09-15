package android2;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestCases2 {// extends Base {

	public void setUp() {
		print("setUp");
	}

	@BeforeMethod
	void cleanBeforeEachTest() {
		print("@BeforeMethod");
	}

	@Test
	private void test1() {
		print("Krai na test 1");
	}

	@Test
	private void test2() {
		print("Krai na test 2");
	}

	void tearDown() {
		print("tearDown");
	}

	void print(String text) {
		System.out.println(text);
	}

}

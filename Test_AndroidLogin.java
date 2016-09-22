package android2.VideoEngager;

import java.awt.AWTException;
import java.io.IOException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Test_AndroidLogin {

	Android android = new Android();

	@BeforeClass
	void setUp() throws IOException, InterruptedException, AWTException {
		android.setUp();
	}

	@Test(priority = 1)
	public void login() throws InterruptedException, AWTException, IOException {
		android.login("tester2006@abv.bg", "Tarator1");
		android.pause(15);

	}

//	@AfterClass
//	void cleanUp() throws IOException {
//		android.cleanUpAndroid();
//	}
}

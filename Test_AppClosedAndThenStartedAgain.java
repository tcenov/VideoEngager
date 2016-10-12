package android2.VideoEngager;

import java.awt.AWTException;
import java.io.IOException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

public class Test_AppClosedAndThenStartedAgain {

	Android android = new Android();

	@Test(priority = 1)
	public void androidLogin() throws InterruptedException, AWTException, IOException {
		android.setUp();
//		android.login("tester2006@abv.bg", "Tarator1");
		android.print("--------------------------------------------------------------------------------");
	}

	@Test(priority = 3)
	public void test1() throws AWTException, IOException, InterruptedException {
		android.print("start new test ------------------------------------------------------------------");
		android.closeAllApps();
		android.pause(7);
		android.startApp();
		android.pause(7);
		android.closeAllApps();
		android.pause(7);
		android.startAgentAppMainActivity();
		android.pause(7);
		android.print("-end-of-test----------------------------------------------------------------------------");
	}

	@AfterClass
	void cleanUp() throws IOException {
		android.cleanUpAndroid();

	}
}

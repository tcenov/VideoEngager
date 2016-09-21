package android2.VideoEngager;

import java.awt.AWTException;
import java.io.IOException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

public class Tests_CallWhileIsLockedInBackground {

	Firefox firefox = new Firefox();
	Android android = new Android();

	@Test(priority = 1)
	public void androidLogin() throws InterruptedException, AWTException, IOException {
		android.setUp();
		android.login("tester2006@abv.bg", "Tarator1");
	}

	@Test(priority = 2)
	public void firefoxLogin() throws InterruptedException, AWTException, IOException {
		firefox.setUp();
		firefox.login();
	}

	@Test(priority = 14)
	public void androidAgentReceiveVideoCallWhileAppInBackground() throws InterruptedException {
		firefox.reloadAgentUrl();
		firefox.waitForPageLoad();
		android.startApp();
		android.pressHomeButton();
		firefox.callButtonFromHomeClick();
		android.pause(5);
		android.answerVideoCall();
		android.pause(5);
		android.stopOrRejectVideoCall();
		android.print("Test case - Call while android works in background");
	}
	
	@AfterClass
	void cleanUp() throws IOException {
		android.cleanUpAndroid();
		firefox.close();
	}
}

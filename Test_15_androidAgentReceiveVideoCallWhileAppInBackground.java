package android2.VideoEngager;

import java.awt.AWTException;
import java.io.IOException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

public class Test_15_androidAgentReceiveVideoCallWhileAppInBackground {

	Firefox firefox = new Firefox();
	static Android android = new Android();
	
	@Test(priority = 1)
	public void androidLogin() throws InterruptedException, AWTException, IOException {
		android.setUp();
		android.login("tester2006@abv.bg", "Tarator1");
		android.print("--------------------------------------------------------------------------------");
	}

	@Test(priority = 2)
	public void firefoxJoin() throws InterruptedException, AWTException, IOException {
		firefox.setUp();
		firefox.join();
		android.print("--------------------------------------------------------------------------------");
	}

	@Test(priority = 13)
	public void androidAgentReceiveVideoCallWhileAppInBackground() throws InterruptedException, AWTException, IOException {
		android.print("start new test ------------------------------------------------------------------");
		android.getAppBackInForeground();
//		firefox.close();
//		firefox.setUp();
//		firefox.waitForPageLoad();
		android.pressHomeButton();
		//android.pause(5);
		firefox.callButtonFromHomeClick();
		//android.pause(5);
		android.answerVideoCall();
		//ToDo verify video
		firefox.verifyOwnVideo();
		firefox.verifyVideoFromAgent();
		android.pause(5);
		android.stopOrRejectVideoCalling();
		android.print("Test case - Call while android works in background");
		android.print("--------------------------------------------------------------------------------");
	}
	
	@AfterClass
	void cleanUp() throws IOException {
		android.cleanUpAndroid();
		firefox.close();
	}
}

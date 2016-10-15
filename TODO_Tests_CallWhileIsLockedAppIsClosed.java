package android2.VideoEngager;

import java.awt.AWTException;
import java.io.IOException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

public class TODO_Tests_CallWhileIsLockedAppIsClosed {

	Firefox firefox = new Firefox();
	Android android = new Android();

	@Test(priority = 2)
	public void androidLogin() throws InterruptedException, AWTException, IOException {
		android.setUp();
		android.login("tester2006@abv.bg", "Tarator1");
	}

	@Test(priority = 1)
	public void firefoxJoin() throws InterruptedException, AWTException, IOException {
		firefox.setUp();
		firefox.join();
	}

	@Test(priority = 3)
	public void androidAgentReceiveCallWhileIsLockedAppIsClosed() throws IOException, InterruptedException {
		android.closeAllApps();
		android.lockScreen();
		firefox.callButtonFromHomeClick();
		android.pause(10);
		android.answerVideoCall();
//		firefox.verifyOwnVideo();
		android.pause(5);
		
		firefox.verifyVideoFromAgent();
		//ToDo verify video
		android.pause(5);
		android.stopOrRejectVideoCalling();
		android.print("Test case - Call when app is closed");
	}
 	
	@AfterClass
	void cleanUp() throws IOException {
		android.cleanUpAndroid();
		firefox.close();
	}
}

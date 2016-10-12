package android2.VideoEngager;

import java.awt.AWTException;
import java.io.IOException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

public class Test_14_androidAgentReceiveVideoCallWhileAppIsClosed {

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

	@Test(priority = 16)
	public void androidAgentReceiveVideoCallWhileAppIsClosed() throws InterruptedException, IOException {
		android.print("start new test ------------------------------------------------------------------");


		android.startApp();
		android.closeAllApps();
		firefox.callButtonFromHomeClick();
		firefox.verifyOwnVideo();
		android.pause(5);
		android.answerVideoCall();
		firefox.verifyVideoFromAgent();
		//ToDo verify video
		android.pause(5);
		android.stopOrRejectVideoCalling();
		android.print("Test case - Call when app is closed");
		android.print("--------------------------------------------------------------------------------");
	}

	@AfterClass
	void cleanUp() throws IOException {
		android.cleanUpAndroid();
		firefox.close();
	}
}

package android2.VideoEngager;

import java.awt.AWTException;
import java.io.IOException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

public class Test_05_prospectReceiveVideoCall {

	Firefox firefox = new Firefox();
	Android android = new Android();
	
	@Test(priority = 2)
	public void androidLogin() throws InterruptedException, AWTException, IOException {
		android.setUp();
		android.login("tester2006@abv.bg", "Tarator1");
		android.print("--------------------------------------------------------------------------------");
	}

	@Test(priority = 1)
	public void firefoxJoin() throws InterruptedException, AWTException, IOException {
		firefox.setUp();
		firefox.join();
		android.print("--------------------------------------------------------------------------------");
	}

	@Test(priority = 5)
	public void prospectReceiveVideoCall() throws InterruptedException, IOException {
		android.print("start new test ------------------------------------------------------------------");
		android.startConversation();
		android.startVideoCall();
		android.pause(5);
		firefox.answerVideoCall();
		firefox.verifyOwnVideo();
		firefox.verifyVideoFromAgent();
		//ToDo verify video
		android.pause(2);
	//	firefox.muteMicrophone();
		android.pause(2);
		android.stopOrRejectVideoCalling();
		android.closeConversation();
		android.print("Test case - prospect receive Video call, video ended from android");
		android.print("--------------------------------------------------------------------------------");
	}

	@AfterClass
	void cleanUp() throws IOException {
		android.cleanUpAndroid();
		firefox.close();
	}
}

package android2.VideoEngager;

import java.awt.AWTException;
import java.io.IOException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

public class Test_06_ProspectReceiveVideoCallEndFromProspect {

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

	@Test(priority = 5)
	public void prospectReceiveVideoCallEndFromProspect() throws InterruptedException, IOException {
		android.startConversation();
		android.startVideoCall();
		android.pause(3);
		firefox.answerVideoCall();
		firefox.verifyOwnVideo();
		firefox.verifyVideoFromAgent();
		android.pause(3);
		firefox.stopVideoCall();
		android.pause(3);
		//android.closeConversation();
		android.print("Test case - prospect receive Video call");
	}

	@AfterClass
	void cleanUp() throws IOException {
		android.cleanUpAndroid();
		firefox.close();
	}
}

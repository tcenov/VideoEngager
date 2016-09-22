package android2.VideoEngager;

import java.awt.AWTException;
import java.io.IOException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

public class Test_AndroidAgentReceiveVideoCallWhileInConversation {

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
		firefox.join();
	}
	
	@Test(priority = 8)
	public void androidAgentReceiveVideoCallWhileInConversation() throws InterruptedException {
		android.startConversation();
		android.pause(2);
		firefox.callButtonFromHomeClick();
		android.pause(2);
		android.answerVideoCall();
		android.pause(10);
 		android.stopOrRejectVideoCall();
		android.print("Test case - android receive video call while conversation is opened");
	}

	@AfterClass
	void cleanUp() throws IOException {
		android.cleanUpAndroid();
		firefox.close();
	}
}

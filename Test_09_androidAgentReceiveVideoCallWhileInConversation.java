package android2.VideoEngager;

import java.awt.AWTException;
import java.io.IOException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

public class Test_09_androidAgentReceiveVideoCallWhileInConversation {

	Firefox firefox = new Firefox();
	Android android = new Android();
	
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

	@Test(priority = 8)
	public void androidAgentReceiveVideoCallWhileInConversation() throws InterruptedException {
		android.print("start new test ------------------------------------------------------------------");
		android.startConversation();
		android.pause(2);
		firefox.callButtonFromHomeClick();
		//firefox.callButtonFromConversationClick();
		android.pause(2);
		android.answerVideoCall();
		//ToDo verify video
		android.pause(7);
 		android.stopOrRejectVideoCall();
		android.print("Test case - android receive video call while conversation is opened");
		android.print("--------------------------------------------------------------------------------");
	}

	@AfterClass
	void cleanUp() throws IOException {
		android.cleanUpAndroid();
		firefox.close();
	}
}

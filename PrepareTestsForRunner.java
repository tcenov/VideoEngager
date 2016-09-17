package android2.VideoEngager;

import java.awt.AWTException;
import java.io.IOException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

public class PrepareTestsForRunner {

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

	@Test(priority = 5)
	public void prospectReceiveVideoCall() throws InterruptedException {
		android.startConversation();
		android.startVideoCall();
		android.pause(10);
		firefox.answerVideoCall();
		android.pause(10);
		android.stopOrRejectVideoCall();
		android.closeConversation();
		android.print("Test case - prospect receive Video call");
	}
	
	@Test(priority = 6)
	public void androidAgentReceiveVideoCall() throws InterruptedException {
		
		android.pause(5);
		firefox.CallButtonClick();
		android.pause(2);
		android.answerVideoCall();
		android.pause(10);
		android.stopOrRejectVideoCall();
		android.pause(10);
		android.print("Test case - android agent receive video call,then android end video call ");
	}
	
	@AfterClass
	void cleanUp() throws IOException {
		android.cleanUpAndroid();
		firefox.close();
	}
}

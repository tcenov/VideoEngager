package android2.VideoEngager;

import java.awt.AWTException;
import java.io.IOException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

public class Test_08_androidAgentRejectVideoCall {

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

	@Test(priority = 7)
	public void androidAgentRejectVideoCall() throws InterruptedException {
		android.print("start new test ------------------------------------------------------------------");
		firefox.callButtonFromHomeClick();
		//firefox.callButtonFromConversationClick();
		android.pause(1);
		android.stopOrRejectVideoCall();
		android.pause(2);
		android.print("Test case - android agent rejected video call.");
		android.print("--------------------------------------------------------------------------------");
	}
		
	@AfterClass
	void cleanUp() throws IOException {
		android.cleanUpAndroid();
		firefox.close();
	}
}

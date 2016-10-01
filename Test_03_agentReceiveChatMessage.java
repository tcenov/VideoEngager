package android2.VideoEngager;

import java.awt.AWTException;
import java.io.IOException;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

public class Test_03_agentReceiveChatMessage {

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

	@Test(priority = 3)
	public void agentReceiveChatMessage() throws InterruptedException {
		android.print("start new test ------------------------------------------------------------------");
		// Prerequisites : login from android
		android.startConversation();		
		firefox.SendMessage("Sent from browser prospector");
		// firefox.resizeWindow(500, 20);
		
		android.verifyMessage("Sent from browser prospector");
		android.pause(5);
		android.closeConversation();
		android.pause(2);
		android.print("Test case - agent receive chat message from browser.");
		android.print("--------------------------------------------------------------------------------");
	}

	@AfterClass
	void cleanUp() throws IOException {
		android.cleanUpAndroid();
		firefox.close();
	}
}

package android2.VideoEngager;

import java.awt.AWTException;
import java.io.IOException;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

public class Test_04_prospectorReceiveChatMessage {

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

	@Test(priority = 4)
	public void prospectorReceiveChatMessage() throws InterruptedException {
		android.print("start new test ------------------------------------------------------------------");
		//ToDo - to remove next row after bug fix
		android.closeConversation();
		
		android.startConversation();
		android.sendMessage("Sent from android agent");
		firefox.verifyMessage("Sent from android agent");
		android.pause(5);
		android.closeConversation();
		android.pause(5);
		android.print("Test case - agent android send message");
		android.print("--------------------------------------------------------------------------------");
	}

	@AfterClass
	void cleanUp() throws IOException {
		android.cleanUpAndroid();
		firefox.close();
	}
}

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

	@Test(priority = 3)
	public void agentReceiveChatMessage() throws InterruptedException {
		// Prerequisites : login from android
		android.startConversation();
		
		firefox.SendMessage("Sent from browser prospector");
		// firefox.resizeWindow(500, 20);
		
		// ToDo check message received from android
		android.pause(20);
		android.closeConversation();
		android.print("Napisa li buga?");
		android.pause(15);
		android.print("Test case - agent receive chat message from browser.");
	}

	@Test(priority = 4)
	public void prospectorReceiveChatMessage() throws InterruptedException {

		android.startConversation();
		android.sendMessage("Sent from android agent");
		// ToDo check message received from firefox
		
		android.pause(10);
		android.closeConversation();
		android.pause(15);
		android.print("Test case - agent android send message");
	}

	@Test(priority = 5)
	public void prospectReceiveVideoCall() throws InterruptedException {
		android.startConversation();
		android.startVideoCall();
		android.pause(10);
		firefox.answerVideoCall();
		android.pause(10);
		android.stopVideoCall();
		android.closeConversation();
		android.print("Test case - prospect receive Video call");
	}

//	@Test(priority = 6)
//	public void androidAgentReceiveVideoCall() throws InterruptedException {
//		firefox.CallButtonClick();
//		android.startConversation();
//		android.stopVideoCall();
//		android.closeConversation();
//		android.print("Test case - android receive video call");
//		android.pause(120);
//	}

	@AfterClass
	void cleanUp() throws IOException {
		android.cleanUpAndroid();
		firefox.close();
	}
}

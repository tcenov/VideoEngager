package android2;

import java.awt.AWTException;
import java.io.IOException;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class FirefoxRunner {

	Firefox firefox = new Firefox();
	Android android = new Android();

	@BeforeClass
	void setUp() throws IOException, InterruptedException, AWTException {
		android.setUp();
		firefox.setUp();
	}

	@Test(priority = 1)
	public void test() throws InterruptedException, AWTException, IOException {

		android.login("tester2006@abv.bg", "Tarator1");
		firefox.login();
		firefox.SendMessage("Test1");
		firefox.resizeWindow(500, 20);
		android.print("Test case - login send message from android");
		firefox.pause(120);
	}

	@Test(priority = 2)
	public void agentRejectChat() throws InterruptedException {
		// Commented steps are executed in "Test case - login send message from
		// android"
		// android.login("tester2006@abv.bg", "Tarator1");
		// firefox.login();
		// firefox.SendMessage("Test1");

		android.startConversation();
		//android.pause(5);
		android.closeConversation();
		android.print("Test case - agent reject chat");
	}
//
//	@Test(priority=19)
//	public void agentReceiveCallThenSendMessage() {
//		firefox.CallButtonClick();
//		
//		android.startConversation();
//		
//		android.closeConversation();
//		android.print("Test case - agent receive call after that chat");		
//	}
	
	@Test(priority=21)
	public void prospectReceiveChatThenMakeCall() {
		android.startConversation();
		android.sendMessage("Send from android");
		android.startVideoCall();
		firefox.answerVideoCall();
		android.stopVideoCall();
		android.closeConversation();
		android.print("Test case - prospect receive chat after that call");		
	}
	
	@AfterClass
	void cleanUp() throws IOException {
		android.cleanUpAndroid();
		firefox.close();
	}
}

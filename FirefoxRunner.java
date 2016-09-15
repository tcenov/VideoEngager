package android2.VideoEngager;

import java.awt.AWTException;
import java.io.IOException;

import org.openqa.selenium.interactions.PauseAction;
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
	}

	@Test(priority = 2)
	public void agentReceiveChatMessage() throws InterruptedException {
		// Prerequisites : login from android

		firefox.SendMessage("Sent from browser prospector");
		// firefox.resizeWindow(500, 20);
		android.print("Test case - login send message from android");
		android.startConversation();
		// ToDo check message received from android
		android.closeConversation();
		android.print("Test case - agent receive chat message from browser.");
	}

	@Test(priority = 3)
	public void prospectorReceiveChatMessage() {

		android.startConversation();
		android.sendMessage("Sent from android agent");
		// ToDo check message received from firefox
		android.closeConversation();
		android.print("Test case - agent android send message");
	}

	@Test(priority = 4)
	public void prospectReceiveVideoCall() {
		android.startConversation();
		android.startVideoCall();
		firefox.answerVideoCall();
		android.stopVideoCall();
		android.closeConversation();
		android.print("Test case - prospect receive Video call");
	}

	@Test(priority = 5)
	public void androidAgentReceiveVideoCall() throws InterruptedException {
		firefox.CallButtonClick();
		android.startConversation();
		android.stopVideoCall();
		android.closeConversation();
		android.print("Test case - android receive video call");
		android.pause(120);
	}

	@AfterClass
	void cleanUp() throws IOException {
		android.cleanUpAndroid();
		firefox.close();
	}
}

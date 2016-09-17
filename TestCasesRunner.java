package android2.VideoEngager;

import java.awt.AWTException;
import java.io.IOException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

public class TestCasesRunner {

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
		android.pause(10);
		android.closeConversation();
		android.print("Napisa li buga?");
		android.pause(5);
		android.print("Test case - agent receive chat message from browser.");
	}

	@Test(priority = 4)
	public void prospectorReceiveChatMessage() throws InterruptedException {

		android.startConversation();
		android.sendMessage("Sent from android agent");
		// ToDo check message received from firefox
		
		android.pause(10);
		android.closeConversation();
		android.pause(5);
		android.print("Test case - agent android send message");
	}

	@Test(priority = 5)
	public void prospectReceiveVideoCall() throws InterruptedException {
		android.startConversation();
		android.startVideoCall();
		android.pause(5);
		firefox.answerVideoCall();
		android.pause(2);
	//	firefox.muteMicrophone();
		android.pause(2);
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
		android.pause(5);
		firefox.muteMicrophone();
		firefox.muteMicrophone();
		android.stopOrRejectVideoCall();
		android.pause(10);
		android.print("Test case - android agent receive video call,then android end video call ");
	}

	@Test(priority = 7)
	public void androidAgentRejectVideoCall() throws InterruptedException {
		firefox.CallButtonClick();
		android.pause(3);
		android.stopOrRejectVideoCall();
		android.pause(3);
		android.print("Test case - android agent rejected video call.");
	}
	
	@Test(priority = 8)
	public void androidAgentReceiveVideoCallWhileInConversation() throws InterruptedException {
		android.startConversation();
		android.pause(2);
		firefox.CallButtonClick();
		android.pause(2);
		android.answerVideoCall();
		android.pause(10);
 		android.stopOrRejectVideoCall();
		android.print("Test case - android receive video call while conversation is opened");
	}
	
	
	@Test(priority = 9)
	public void notificationsWhileRunInBackground() throws InterruptedException, IOException {
		//android.runAppInBackground(5);
				
		android.pressHomeButton();
		//android.clearNotifications();
		android.pause(2);
		firefox.SendMessage("Message while android works in background");
		android.pause(5);
		android.openNotifications();
		android.pause(3);
		android.clearNotifications();
		android.pause(1);
		firefox.SendMessage("Message while android works in background");
		android.pause(2);
		android.openNotifications();		
		android.pause(30);	
		android.print("Test case - call notifications while android run in background");
	}
	
	
	@AfterClass
	void cleanUp() throws IOException {
		android.cleanUpAndroid();
		firefox.close();
	}
}

package android2.VideoEngager;

import java.awt.AWTException;
import java.io.IOException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({ ScreenshotUtility.class })
public class TestCasesRunner {

	Firefox firefox = new Firefox();
	static Android android = new Android();
	
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
		
		android.verifyMessage("Sent from browser prospector");
		android.pause(5);
		android.closeConversation();
		android.pause(2);
		android.print("Test case - agent receive chat message from browser.");
	}

	@Test(priority = 4)
	public void prospectorReceiveChatMessage() throws InterruptedException {
		android.startConversation();
		android.sendMessage("Sent from android agent");
		firefox.verifyMessage("Sent from android agent");
		android.pause(5);
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
		firefox.callButtonFromConversationClick();
		android.pause(2);
		android.answerVideoCall();
		android.pause(5);
		firefox.muteMicrophone();
		android.stopOrRejectVideoCall();
		android.pause(10);
		android.print("Test case - android agent receive video call,then android end video call ");
	}

	@Test(priority = 7)
	public void androidAgentRejectVideoCall() throws InterruptedException {
		firefox.callButtonFromConversationClick();
		android.pause(3);
		android.stopOrRejectVideoCall();
		android.pause(3);
		android.print("Test case - android agent rejected video call.");
	}
	
	@Test(priority = 8)
	public void androidAgentReceiveVideoCallWhileInConversation() throws InterruptedException {
		android.startConversation();
		android.pause(2);
		firefox.callButtonFromConversationClick();
		android.pause(2);
		android.answerVideoCall();
		android.pause(10);
 		android.stopOrRejectVideoCall();
		android.print("Test case - android receive video call while conversation is opened");
	}
	
	@Test(priority = 9)
	public void notificationsWhileRunInBackground() throws InterruptedException, IOException {
		//android.runAppInBackground(5); - this is not a solution.
		android.pressHomeButton();
		android.clearNotifications();
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
		android.acceptRejectNotification("accept");
		android.closeConversation();
		firefox.reloadAgentUrl();
		firefox.waitForPageLoad();
		android.print("Test case - chat notifications while android run in background");
	}
	
	@Test(priority = 10)
	public void notificationsWhileAppIsKilled() throws InterruptedException, IOException {
		android.pressHomeButton();
		android.print("Android pressed Home button - app works in background");
		firefox.SendMessage("Message while android works in background");
		android.pause(5);
		android.adbExecuteComand("adb shell am force-stop com.leadsecure.agent");
		android.openNotifications();
		android.acceptRejectNotification("accept");
		android.closeConversation();
		firefox.reloadAgentUrl();
		firefox.waitForPageLoad();
		android.print("Test case - chat notifications while app is killed");
	}
	
	@Test(priority = 11)
	public void notificationsWhileAppIsClosed() throws InterruptedException {
		firefox.reloadAgentUrl();
		firefox.waitForPageLoad();
		android.startApp();
		android.pressHomeButton();
		firefox.SendMessage("Message while android works in background");
		android.pause(5);
		android.closeApp();
		android.openNotifications();
		android.acceptRejectNotification("accept");
		android.closeConversation();
		firefox.reloadAgentUrl();
		android.print("Test case - chat notifications while app is closed.");
	}
	
	@Test(priority = 12)
	public void notificationsWhileAppIsBehindAnotherApp() throws InterruptedException, IOException {
		firefox.reloadAgentUrl();
		firefox.waitForPageLoad();
		android.startApp();
		android.adbExecuteComand("adb shell am start -n com.android.calculator2/.Calculator");
		firefox.SendMessage("Message while android works in background");
		android.pause(5);
		android.openNotifications();
		android.acceptRejectNotification("accept");
		android.closeConversation();
		firefox.reloadAgentUrl();
		android.print("Test case - chat notifications while another app is on focus.");
	}
	
	@Test(priority = 13)
	public void notificationsWhilePlayGame() throws InterruptedException {	
		//ToDo
		android.print("Not implemented: Test case - chat notifications while play game.");
	}
 	
	@Test(priority = 14)
	public void androidAgentReceiveVideoCallWhileAppInBackground() throws InterruptedException {
		firefox.reloadAgentUrl();
		android.startApp();
		firefox.waitForPageLoad();
		android.startApp();
		android.pressHomeButton();
		android.pause(5);
		firefox.callButtonFromHomeClick();
		android.pause(5);
		android.answerVideoCall();
		android.pause(5);
		android.stopOrRejectVideoCall();
		android.print("Test case - Call while android works in background");
	}
	
	@Test(priority = 15)
	public void androidAgentReceiveVideoCallWhileAppBehindAnotherApp() throws InterruptedException, IOException {
		firefox.reloadAgentUrl();
		firefox.waitForPageLoad();
		android.startApp();
		android.adbExecuteComand("adb shell am start -n com.android.calculator2/.Calculator");
		firefox.callButtonFromHomeClick();
		android.pause(5);
		android.answerVideoCall();
		android.pause(5);
		android.stopOrRejectVideoCall();
		android.print("Test case - Call while android works in background");
	}

	@Test(priority = 16)
	public void androidAgentReceiveVideoCallWhileAppIsClosed() throws InterruptedException, IOException {
		firefox.reloadAgentUrl();
		firefox.waitForPageLoad();
		android.startApp();
		android.closeApp();
		firefox.callButtonFromHomeClick();
		android.pause(5);
		android.answerVideoCall();
		android.pause(5);
		android.stopOrRejectVideoCall();
		android.print("Test case - Call when app is closed");
	}
	
	@Test(priority = 16)
	public void androidAgentReceiveVideoCallWhenAppIsKilled() throws InterruptedException, IOException {
		//ToDo
		android.print("Not implemented - Test case - Call when app is killed");
	}
	
	@AfterClass
	void cleanUp() throws IOException {
		android.cleanUpAndroid();
		firefox.close();
	}
}

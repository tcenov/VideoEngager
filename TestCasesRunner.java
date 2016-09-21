package android2.VideoEngager;

import java.awt.AWTException;
import java.io.IOException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

//@Listeners({ ScreenshotUtility.class })
public class TestCasesRunner {

	Firefox firefox = new Firefox();
	static Android android = new Android();
	
	@Test(priority = 1)
	public void androidLogin() throws InterruptedException, AWTException, IOException {
		android.setUp();
		android.login("tester2006@abv.bg", "Tarator1");
		android.print("--------------------------------------------------------------------------------");
	}

	@Test(priority = 2)
	public void firefoxLogin() throws InterruptedException, AWTException, IOException {
		firefox.setUp();
		firefox.login();
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

	@Test(priority = 4)
	public void prospectorReceiveChatMessage() throws InterruptedException {
		android.print("start new test ------------------------------------------------------------------");
		android.startConversation();
		android.sendMessage("Sent from android agent");
		firefox.verifyMessage("Sent from android agent");
		android.pause(5);
		android.closeConversation();
		android.pause(5);
		android.print("Test case - agent android send message");
		android.print("--------------------------------------------------------------------------------");
	}

	@Test(priority = 5)
	public void prospectReceiveVideoCall() throws InterruptedException {
		android.print("start new test ------------------------------------------------------------------");
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
		android.print("--------------------------------------------------------------------------------");
	}
	
	@Test(priority = 6)
	public void androidAgentReceiveVideoCall() throws InterruptedException {
		android.print("start new test ------------------------------------------------------------------");
		android.pause(5);
		firefox.callButtonFromConversationClick();
		android.pause(2);
		android.answerVideoCall();
		android.pause(5);
		firefox.muteMicrophone();
		android.stopOrRejectVideoCall();
		android.pause(5);
		android.print("Test case - android agent receive video call,then android end video call ");
		android.print("--------------------------------------------------------------------------------");
	}

	@Test(priority = 7)
	public void androidAgentRejectVideoCall() throws InterruptedException {
		android.print("start new test ------------------------------------------------------------------");
		firefox.callButtonFromConversationClick();
		android.pause(3);
		android.stopOrRejectVideoCall();
		android.pause(3);
		android.print("Test case - android agent rejected video call.");
		android.print("--------------------------------------------------------------------------------");
	}
	
	@Test(priority = 8)
	public void androidAgentReceiveVideoCallWhileInConversation() throws InterruptedException {
		android.print("start new test ------------------------------------------------------------------");
		android.startConversation();
		android.pause(2);
		firefox.callButtonFromConversationClick();
		android.pause(2);
		android.answerVideoCall();
		android.pause(10);
 		android.stopOrRejectVideoCall();
		android.print("Test case - android receive video call while conversation is opened");
		android.print("--------------------------------------------------------------------------------");
	}
	
	@Test(priority = 9)
	public void notificationsWhileRunInBackground() throws InterruptedException, IOException {
		android.print("start new test ------------------------------------------------------------------");
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
		android.print("--------------------------------------------------------------------------------");
	}
	
	@Test(priority = 10)
	public void notificationsWhileAppIsKilled() throws InterruptedException, IOException {
		android.print("start new test ------------------------------------------------------------------");
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
		android.print("--------------------------------------------------------------------------------");
	}
	
	@Test(priority = 11)
	public void notificationsWhileAppIsClosed() throws InterruptedException {
		android.print("start new test ------------------------------------------------------------------");
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
		android.print("--------------------------------------------------------------------------------");
	}
	
	@Test(priority = 12)
	public void notificationsWhileAppIsBehindAnotherApp() throws InterruptedException, IOException {
		android.print("start new test ------------------------------------------------------------------");
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
		android.print("--------------------------------------------------------------------------------");
	}
	
	@Test(priority = 13)
	public void notificationsWhilePlayGame() throws InterruptedException {	
		android.print("start new test ------------------------------------------------------------------");
		//ToDo
		android.print("Not implemented: Test case - chat notifications while play game.");
		android.print("--------------------------------------------------------------------------------");
	}
 	
	@Test(priority = 14)
	public void androidAgentReceiveVideoCallWhileAppInBackground() throws InterruptedException {
		android.print("start new test ------------------------------------------------------------------");
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
		android.print("--------------------------------------------------------------------------------");
	}
	
	@Test(priority = 15)
	public void androidAgentReceiveVideoCallWhileAppBehindAnotherApp() throws InterruptedException, IOException {
		android.print("start new test ------------------------------------------------------------------");
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
		android.print("--------------------------------------------------------------------------------");
	}

	@Test(priority = 16)
	public void androidAgentReceiveVideoCallWhileAppIsClosed() throws InterruptedException, IOException {
		android.print("start new test ------------------------------------------------------------------");
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
		android.print("--------------------------------------------------------------------------------");
	}
	
	@Test(priority = 17)
	public void androidAgentReceiveVideoCallWhenAppIsKilled() throws InterruptedException, IOException {
		android.print("start new test ------------------------------------------------------------------");
		//ToDo
		android.print("Not implemented - Test case - Call when app is killed");
		android.print("--------------------------------------------------------------------------------");
	}
	
	@AfterClass
	void cleanUp() throws IOException {
		android.cleanUpAndroid();
		firefox.close();
	}
}

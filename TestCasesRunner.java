package android2.VideoEngager;

import java.awt.AWTException;
import java.io.IOException;

import org.testng.annotations.AfterClass;
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

	@Test(priority = 5)
	public void prospectReceiveVideoCall() throws InterruptedException {
		android.print("start new test ------------------------------------------------------------------");
		android.startConversation();
		android.startVideoCall();
		android.pause(5);
		firefox.answerVideoCall();
		//ToDo verify video
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
		android.pause(2);
		//firefox.callButtonFromHomeClick();
		firefox.callButtonFromConversationClick();
		android.pause(2);
		android.answerVideoCall();
		//ToDo verify video
		android.pause(5);
		firefox.muteMicrophone();
		android.stopOrRejectVideoCall();
		android.pause(2);
		android.print("Test case - android agent receive video call,then android end video call ");
		android.print("---------------------------------------------------------------------------");
	}

	@Test(priority = 7)
	public void androidAgentRejectVideoCall() throws InterruptedException {
		android.print("start new test ------------------------------------------------------------------");
		firefox.callButtonFromConversationClick();
		android.pause(1);
		android.stopOrRejectVideoCall();
		android.pause(2);
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
		//ToDo verify video
		android.pause(7);
 		android.stopOrRejectVideoCall();
		android.print("Test case - android receive video call while conversation is opened");
		android.print("--------------------------------------------------------------------------------");
	}
	
	@Test(priority = 9)
	public void notificationsWhileRunInBackground() throws InterruptedException, IOException, AWTException {
		android.print("start new test ------------------------------------------------------------------");
		//android.runAppInBackground(5); - this is not a solution.
		android.pressHomeButton();
		android.clearNotifications();
		firefox.close();
		firefox.setUp();
		firefox.waitForPageLoad();
		firefox.SendMessage("Message while android works in background");
		android.openNotifications();
		android.getAllNotifications();
		android.pause(2);
		android.acceptRejectNotification("accept");
		android.pause(4);
		android.verifyMessage("Message while android works in background");
		android.pause(2);
		android.closeConversation();
		android.print("Test case - chat notifications while android run in background");
		android.print("--------------------------------------------------------------------------------");
	}

	@Test(priority = 10)
	public void notificationsWhileAppIsKilled() throws InterruptedException, IOException {
		android.print("start new test ---- -while app is killed---------------------------------------");
		android.print("At the moment it failed intentionally");
		android.acceptRejectNotification("accept");
		
//		android.pressHomeButton();
//		android.adbExecuteComand("adb shell am force-stop com.leadsecure.agent");
//		firefox.SendMessage("Message while android works in background");
//		android.openNotifications();
//		android.pause(2);		
//		android.acceptRejectNotification("accept");
//		android.pause(4);
//		android.verifyMessage("Message while android works in background");
//		android.pause(2);
//		android.closeConversation();
		android.print("Test case - chat notifications while app is killed");
		android.print("--------------------------------------------------------------------------------");
	}

	@Test(priority = 11)
	public void notificationsWhileAppIsBehindAnotherAppAndDeviceLocked() throws InterruptedException, IOException, AWTException {
		android.print("start new test ------------------------------------------------------------------");
		firefox.close();
		firefox.setUp();
		firefox.waitForPageLoad();
		android.startCalculatorApp();
		android.lockScreen();
		android.pause(5);
		firefox.SendMessage("Message while android is locked and behind calculator");
		android.pause(5);
		//android.unlockScreenWithAppium();
		android.unlockScreen();
		android.startCalculatorApp();
		//android.getAppBackInForeground();
		android.openNotifications();
		android.openNotifications();
		android.getAllNotifications();
		android.pause(2);
		android.acceptRejectNotification("accept");
		android.pause(4);
		android.verifyMessage("Message while android is locked and behind calculator");
		android.closeConversation();
		android.print("Test case - chat notifications while device is locked.");
		android.print("--------------------------------------------------------------------------------");
	}
	
	@Test(priority = 12)
	public void notificationsWhileAppInBackgroundAndDeviceLocked() throws InterruptedException, IOException, AWTException {
		android.print("start new test ------------------------------------------------------------------");
		firefox.close();
		firefox.setUp();
		firefox.waitForPageLoad();
		android.pause(2);
		//android.unlockScreenWithAppium();
		android.getAppBackInForeground();
		android.pressHomeButton();
		android.pause(5);
		android.lockScreen();
		android.pause(2);
		firefox.SendMessage("Message while app is in background and device is locked.");
		android.pause(5);
		//android.unlockScreenWithAppium();
		android.unlockScreen();
		android.pause(5);
		android.openNotifications();
		android.pause(5);
		android.getAllNotifications();
		android.acceptRejectNotification("accept");
		android.pause(7);
		android.verifyMessage("Message while app is in background and device is locked.");
		android.closeConversation();
		android.print("Test case - chat notifications while deveice is locked.");
		android.print("--------------------------------------------------------------------------------");
	}
		
	@Test(priority = 17)
	public void notificationsWhileAppIsBehindAnotherApp() throws InterruptedException, IOException, AWTException {
		android.print("start new test ------------------------------------------------------------------");
		firefox.close();
		firefox.setUp();
		firefox.waitForPageLoad();
		android.startCalculatorApp();
		android.pause(5);
		firefox.SendMessage("Message while android is behind calculator");
		android.pause(5);
		android.openNotifications();
		android.acceptRejectNotification("accept");
		android.pause(4);
		android.verifyMessage("Message while android is behind calculator");
		android.closeConversation();
		android.print("Test case - chat notifications while another app is on focus.");
		android.print("--------------------------------------------------------------------------------");
	}
	
	@Test(priority = 18)
	public void notificationsWhilePlayGame() throws InterruptedException {	
		android.print("start new test ---notificationsWhilePlayGame-----------------------------");
		android.print("At the moment it failed intentionally");
		android.acceptRejectNotification("accept");		
		//ToDo
		android.print("Test case - chat notifications while play game.");
		android.print("--------------------------------------------------------------------------------");
	}

	@Test(priority = 13)
	public void androidAgentReceiveVideoCallWhileAppInBackground() throws InterruptedException, AWTException {
		android.print("start new test ------------------------------------------------------------------");
		android.getAppBackInForeground();
		firefox.close();
		firefox.setUp();
		firefox.waitForPageLoad();
		android.pressHomeButton();
		//android.pause(5);
		firefox.callButtonFromHomeClick();
		//android.pause(5);
		android.answerVideoCall();
		//ToDo verify video
		android.pause(5);
		android.stopOrRejectVideoCall();
		android.print("Test case - Call while android works in background");
		android.print("--------------------------------------------------------------------------------");
	}
	
	@Test(priority = 14)
	public void notificationsWhileAppIsClosed() throws InterruptedException, AWTException {
		android.print("start new test --------------------------------------------------------------------------------");
		android.clearNotifications();
		firefox.close();
		firefox.setUp();
		firefox.waitForPageLoad();
		android.closeApp();		
		firefox.SendMessage("Message while android app is closed");
		android.pause(2);
		android.openNotifications();
		android.pause(5);
		android.acceptRejectNotification("accept");
		android.pause(4);
		android.verifyMessage("Message while android app is closed");
		android.pause(5);
		android.closeConversation();
		android.print("Test case - chat notifications while app is closed.");
		android.print("--------------------------------------------------------------------------------");
	}
	
//	@Test(priority = 15)
//	public void androidAgentReceiveVideoCallWhileAppBehindAnotherApp() throws InterruptedException, IOException {
//		android.print("start new test ------------------------------------------------------------------");
//		firefox.reloadAgentUrl();
//		firefox.waitForPageLoad();
//		android.startApp();
//		android.adbExecuteComand("adb shell am start -n com.android.calculator2/.Calculator");
//		firefox.callButtonFromHomeClick();
//		android.pause(5);
//		android.answerVideoCall();
		//ToDo verify video
//		android.pause(5);
//		android.stopOrRejectVideoCall();
//		android.print("Test case - Call while android works in background");
//		android.print("--------------------------------------------------------------------------------");
//	}
//
//	@Test(priority = 16)
//	public void androidAgentReceiveVideoCallWhileAppIsClosed() throws InterruptedException, IOException {
//		android.print("start new test ------------------------------------------------------------------");
//		firefox.reloadAgentUrl();
//		firefox.waitForPageLoad();
//		android.startApp();
//		android.closeApp();
//		firefox.callButtonFromHomeClick();
//		android.pause(5);
//		android.answerVideoCall();
		//ToDo verify video
//		android.pause(5);
//		android.stopOrRejectVideoCall();
//		android.print("Test case - Call when app is closed");
//		android.print("--------------------------------------------------------------------------------");
//	}
//	
//	@Test(priority = 17)
//	public void androidAgentReceiveVideoCallWhenAppIsKilled() throws InterruptedException, IOException {
//		android.print("start new test ------------------------------------------------------------------");
//		//ToDo
//		android.print("Not implemented - Test case - Call when app is killed");
//		android.print("--------------------------------------------------------------------------------");
//	}
	
	@AfterClass
	void cleanUp() throws IOException {
		android.cleanUpAndroid();
		firefox.close();
	}
}

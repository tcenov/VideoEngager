package android2.VideoEngager;

import java.awt.AWTException;
import java.io.IOException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

public class TestCasesRunner2 {

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


	@Test(priority = 11)
	public void notificationsWhileAppIsBehindAnotherAppAndDeviceLocked() throws InterruptedException, IOException, AWTException {
		android.print("start new test ------------------------------------------------------------------");
//		firefox.close();
//		firefox.setUp();
//		firefox.waitForPageLoad();
		android.startCalculatorApp();
		android.pause(5);
		android.lockScreen();
		firefox.SendMessage("Message while android is locked and behind calculator");
		android.pause(5);
		//android.unlockScreenWithAppium();
		android.unlockScreen();
		android.startCalculatorApp();
		//android.getAppBackInForeground();
		android.openNotifications();
		android.openNotifications();
		android.getAllNotifications();
		android.pause(1);
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
	
	@Test(priority = 13)
	public void marker()  {
		android.print("start new test ------------------------------------------------------------------");
		android.print("marker finished");
		android.print("--------------------------------------------------------------------------------");
	}
	
	@Test(priority = 14)
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
	
	
//	@Test(priority = 14)
//	public void notificationsWhileAppIsClosed() throws InterruptedException, AWTException {
//		android.print("start new test --------------------------------------------------------------------------------");
//		android.clearNotifications();
//		firefox.close();
//		firefox.setUp();
//		firefox.waitForPageLoad();
//		android.closeApp();		
//		firefox.SendMessage("Message while android app is closed");
//		android.pause(2);
//		android.openNotifications();
//		android.pause(5);
//		android.acceptRejectNotification("accept");
//		android.pause(4);
//		android.verifyMessage("Message while android app is closed");
//		android.pause(5);
//		android.closeConversation();
//		android.print("Test case - chat notifications while app is closed.");
//		android.print("--------------------------------------------------------------------------------");
//	}
	
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

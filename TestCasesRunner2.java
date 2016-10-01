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
	
	

	
	@Test(priority = 14)
	public void androidAgentReceiveVideoCallWhileAppInBackground() throws InterruptedException, AWTException {
		android.print("start new test ------------------------------------------------------------------");
		android.getAppBackInForeground();
//		firefox.close();
//		firefox.setUp();
//		firefox.waitForPageLoad();
//		android.pressHomeButton();
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
	



	
	
	@Test(priority = 19)
	public void notificationsWhileAppIsClosed() throws InterruptedException, AWTException, IOException {
		android.print("start new test --------------------------------------------------------------------------------");
		android.clearNotifications();
		android.pause(6);
		firefox.close();
		firefox.setUp();
		firefox.waitForPageLoad();
		android.pause(2);
		android.startCalculatorApp();


		android.pause(10);
		android.closeApp();		
		android.pause(18);
		firefox.SendMessage("Message while android app is closed");
		android.pause(9);
		android.startApp();
		android.pressHomeButton();
		android.pause(12);
		android.openNotifications();
		android.pause(11);
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

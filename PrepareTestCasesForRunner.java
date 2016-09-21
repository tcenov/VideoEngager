package android2.VideoEngager;

import java.awt.AWTException;
import java.io.IOException;

import org.openqa.selenium.interactions.PauseAction;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

//@Listeners({ ScreenshotUtility.class })
public class PrepareTestCasesForRunner {

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

//	@Test(priority = 4)
//	public void prospectorReceiveChatMessage() throws InterruptedException {
//		android.startConversation();
//		android.sendMessage("Sent from android agent");
//		firefox.verifyMessage("Sent from android agent");
//		android.pause(5);
//		android.closeConversation();
//		android.pause(5);
//		android.print("Test case - agent android send message");
//	}

	@Test(priority = 6)
	public void androidAgentReceiveVideoCall() throws InterruptedException {
		android.pause(2);
		firefox.callButtonFromHomeClick();
		//firefox.callButtonFromConversationClick();
		android.pause(2);
		android.answerVideoCall();
		android.pause(5);
		firefox.muteMicrophone();
		android.stopOrRejectVideoCall();
		android.pause(5);
		android.print("Test case - android agent receive video call,then android end video call ");
		android.print("---------------------------------------------------------------------------");
	}

//	@Test(priority = 9)
//	public void notificationsWhileRunInBackground() throws InterruptedException, IOException {
//		//android.runAppInBackground(5); - this is not a solution.
//		android.pressHomeButton();
//		android.clearNotifications();
//		android.pause(1);
//		firefox.SendMessage("Message while android works in background");
//		android.openNotifications();
//		android.pause(2);		
//		android.acceptRejectNotification("accept");
//		android.pause(4);
//		android.verifyMessage("Message while android works in background");
//		android.pause(2);
//		android.closeConversation();
//		android.print("Test case - chat notifications while android run in background");
//		android.print("---------------------------------------------------------------------------");
//	}
	
	@Test(priority = 10)
	public void notificationsWhileAppIsKilled() throws InterruptedException, IOException {
		android.pressHomeButton();
		android.adbExecuteComand("adb shell am force-stop com.leadsecure.agent");
		firefox.SendMessage("Message while android works in background");
		android.openNotifications();
		android.pause(2);		
		android.acceptRejectNotification("accept");
		android.pause(4);
		android.verifyMessage("Message while android works in background");
		android.pause(2);
		android.closeConversation();
		android.print("Test case - chat notifications while app is killed");
	android.print("--------------------------------------------------------------------------------");
	}

	@Test(priority = 11)
	public void notificationsWhileAppIsClosed() throws InterruptedException {
		android.print("start new test --------------------------------------------------------------------------------");
		android.clearNotifications();
		android.pause(5);
		android.getAppBackInForeground();
		android.pause(14);
		android.closeApp();
		android.pause(13);
		firefox.SendMessage("Message while android works in background");
		android.pause(12);
		android.openNotifications();
		android.pause(11);
		android.acceptRejectNotification("accept");
		android.pause(5);
		android.closeConversation();
		android.print("Test case - chat notifications while app is closed.");
	android.print("--------------------------------------------------------------------------------");
	}
//	
	
//	@Test(priority = 12)
//	public void notificationsWhileAppIsBehindAnotherApp() throws InterruptedException, IOException {
//		firefox.reloadAgentUrl();
//		firefox.waitForPageLoad();
//		android.startApp();
//		android.adbExecuteComand("adb shell am start -n com.android.calculator2/.Calculator");
//		firefox.SendMessage("Message while android works in background");
//		android.pause(5);
//		android.openNotifications();
//		android.acceptRejectNotification("accept");
//		android.closeConversation();
//		firefox.reloadAgentUrl();
//		android.print("Test case - chat notifications while another app is on focus.");
	//android.print("--------------------------------------------------------------------------------");
//	}
//	
//	@Test(priority = 13)
//	public void notificationsWhilePlayGame() throws InterruptedException {	
//		//ToDo
//		android.print("Not implemented: Test case - chat notifications while play game.");
//	}
// 	
//	@Test(priority = 14)
//	public void androidAgentReceiveVideoCallWhileAppInBackground() throws InterruptedException {
//		firefox.reloadAgentUrl();
//		android.startApp();
//		firefox.waitForPageLoad();
//		android.startApp();
//		android.pressHomeButton();
//		android.pause(5);
//		firefox.callButtonFromHomeClick();
//		android.pause(5);
//		android.answerVideoCall();
//		android.pause(5);
//		android.stopOrRejectVideoCall();
//		android.print("Test case - Call while android works in background");
	//android.print("--------------------------------------------------------------------------------");
//	}
//	
//	@Test(priority = 15)
//	public void androidAgentReceiveVideoCallWhileAppBehindAnotherApp() throws InterruptedException, IOException {
//		firefox.reloadAgentUrl();
//		firefox.waitForPageLoad();
//		android.startApp();
//		android.adbExecuteComand("adb shell am start -n com.android.calculator2/.Calculator");
//		firefox.callButtonFromHomeClick();
//		android.pause(5);
//		android.answerVideoCall();
//		android.pause(5);
//		android.stopOrRejectVideoCall();
//		android.print("Test case - Call while android works in background");
	//android.print("--------------------------------------------------------------------------------");
//	}
//
//	@Test(priority = 16)
//	public void androidAgentReceiveVideoCallWhileAppIsClosed() throws InterruptedException, IOException {
//		firefox.reloadAgentUrl();
//		firefox.waitForPageLoad();
//		android.startApp();
//		android.closeApp();
//		firefox.callButtonFromHomeClick();
//		android.pause(5);
//		android.answerVideoCall();
//		android.pause(5);
//		android.stopOrRejectVideoCall();
//		android.print("Test case - Call when app is closed");
	//android.print("--------------------------------------------------------------------------------");
//	}
	
	@Test(priority = 16)
	public void androidAgentReceiveVideoCallWhenAppIsKilled() throws InterruptedException, IOException {
		//ToDo
		android.print("Not implemented - Test case - Call when app is killed");
		//android.print("--------------------------------------------------------------------------------");
	}
	
	@AfterClass
	void cleanUp() throws IOException {
		android.cleanUpAndroid();
		firefox.close();
	}
}

package android2.VideoEngager;

import java.awt.AWTException;
import java.io.IOException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({ GetAndroidLogs.class })

public class TestCasesRunner {

	Firefox firefox = new Firefox();
	static Android android = new Android();
	int i = 0;
	
	@BeforeMethod
	void printBeforeEachTest() {
		i++;
		System.out.println("---started-test-" + i + "---------------------------------------------------------------");
		
	}
	
	@AfterMethod
	void printAfterEachTest() {
		System.out.println("---end-of-test--" + i + "---------------------------------------------------------------");
	}
	
	@Test(priority = 2)
	public void androidLogin() throws InterruptedException, AWTException, IOException {
		android.setUp();
		android.login("tester2006@abv.bg", "Tarator1");
		android.print("--------------------------------------------------------------------------------");
	}

	@Test(priority = 1)
	public void firefoxJoin() throws InterruptedException, AWTException, IOException {
		firefox.setUp();
		firefox.join();
	}

	@Test(priority = 3, retryAnalyzer = Retry.class)
	public void agentReceiveChatMessage() throws InterruptedException {
		// Prerequisites : login from android
		android.startConversation();	
		android.pause(1);
		firefox.SendMessage("Sent from browser prospector");
		// firefox.resizeWindow(500, 20);
		android.pause(1);
		android.verifyMessage("Sent from browser prospector");
		android.pause(1);
		android.closeConversation();
		android.print("Test case - agent receive chat message from browser.");
	}

	@Test(priority = 4, retryAnalyzer = Retry.class)
	public void prospectorReceiveChatMessage() throws InterruptedException {
		//ToDo - to remove next row after bug fix
		android.closeConversation();
		
		android.startConversation();
		android.sendMessage("Sent from android agent");
		firefox.verifyMessage("Sent from android agent");
		android.pause(1);
		android.closeConversation();
		android.pause(1);
		android.print("Test case - agent android send message");
	}

	@Test(priority = 5, retryAnalyzer = Retry.class)
	public void prospectReceiveVideoCallEndFromAndroid() throws InterruptedException, IOException {
		android.startConversation();
		android.mute();
		android.startVideoCall();
		android.pause(1);
		firefox.answerVideoCall();
		
		firefox.verifyOwnVideo();
		firefox.verifyVideoFromAgent();
		//ToDo verify video
		android.pause(1);
//		firefox.muteMicrophone();
		android.pause(1);
		android.stopOrRejectVideoCalling();
		android.closeConversation();
		android.print("Test case - prospect receive Video call");
	}
	
	@Test(priority = 6, retryAnalyzer = Retry.class)
	public void prospectReceiveVideoCallEndFromProspect() throws InterruptedException, IOException {
		android.startConversation();
		android.startVideoCall();
		android.pause(1);
		firefox.answerVideoCall();
		android.pause(1);
		firefox.verifyOwnVideo();
		firefox.verifyOwnVideo();
		firefox.stopVideoCall();
		android.pause(1);
		//android.closeConversation();
		android.print("Test case - prospect receive Video call");
	}
	
	@Test(priority = 7, retryAnalyzer = Retry.class)
	public void androidAgentReceiveVideoCall() throws InterruptedException, IOException {
		android.pause(1);
		firefox.startVideoCall();
		//firefox.callButtonFromHomeClick();
//		firefox.callButtonFromConversationClick();
		android.pause(2);
		android.answerVideoCall();
		firefox.verifyOwnVideo();
		firefox.verifyVideoFromAgent();
		//ToDo verify video
		android.pause(2);
		firefox.muteMicrophone();
		android.stopOrRejectVideoCalling();
		android.print("Test case - android agent receive video call,then android end video call ");
	}

	@Test(priority = 8, retryAnalyzer = Retry.class)
	public void androidAgentRejectVideoCall() throws InterruptedException, IOException {
		firefox.startVideoCall();
//		firefox.callButtonFromConversationClick();
		android.pause(1);
		android.stopOrRejectVideoCalling();
		android.print("Test case - android agent rejected video call.");
	}
	
	@Test(priority = 9, retryAnalyzer = Retry.class)
	public void androidAgentReceiveVideoCallWhileInConversation() throws InterruptedException, IOException {
		android.startConversation();
		android.pause(2);
//		firefox.callButtonFromConversationClick();
		firefox.startVideoCall();
		android.pause(2);
		android.answerVideoCall();
		//ToDo verify video
		firefox.verifyOwnVideo();
		firefox.verifyVideoFromAgent();
		android.pause(2);
 		android.stopOrRejectVideoCalling();
		android.print("Test case - android receive video call while conversation is opened");
	}
	
	@Test(priority = 10, retryAnalyzer = Retry.class)
	public void notificationsWhileRunInBackground() throws InterruptedException, IOException, AWTException {
		//android.runAppInBackground(5); - this is not a solution.
		android.pressHomeButton();
		android.clearNotifications();
		firefox.close();
		firefox.setUp();
		firefox.waitForPageLoad();
		firefox.SendMessage("Message while android works in background");
		android.openNotifications();
		android.getAllNotifications();
		android.pause(1);
		android.acceptRejectNotification("accept");
		android.pause(2);
		android.verifyMessage("Message while android works in background");
		android.pause(2);
		android.closeConversation();
		android.print("Test case - chat notifications while android run in background");
	}

	@Test(priority = 11, retryAnalyzer = Retry.class)
	public void androidAgentChangeCameraPosition() throws InterruptedException, IOException {
//		firefox.callButtonFromHomeClick();
//		firefox.callButtonFromConversationClick();
		firefox.startVideoCall();
		android.pause(1);
		android.answerVideoCall();
		firefox.verifyVideoFromAgent();
		firefox.verifyOwnVideo();
		firefox.muteMicrophone();
		android.pause(1);
		android.inVideoCallCameraButtonClick();
		android.pause(1);
		android.changeCameraTo("no camera");
		android.pause(1);
		android.verifyStoppedOwnVideo();
		firefox.cameraButtonClick();
		android.pause(1);
		android.verifyStoppedVideoFromProspector();
		firefox.verifyStoppedOwnVideo();
		android.pause(1);
		android.inVideoCallCameraButtonClick();
		android.pause(1);
		android.changeCameraTo("front camera");
		android.pause(1);
		android.inVideoCallCameraButtonClick();
		android.pause(1);
		android.changeCameraTo("back camera");
		android.pause(2);
		android.stopOrRejectVideoCalling();
		android.pause(1);
		android.print("Test case - androidAgentChangeCameraPosition");
	}
	
	@Test(priority = 11, retryAnalyzer = Retry.class)
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
	}

	@Test(priority = 12, retryAnalyzer = Retry.class)
	public void notificationsWhileAppIsBehindAnotherAppAndDeviceLocked() throws InterruptedException, IOException, AWTException {
		firefox.close();
		firefox.setUp();
		firefox.waitForPageLoad();
		android.startCompassApp();
		android.lockScreen();
		android.pause(4);
		firefox.SendMessage("Message while android is locked and behind compass");
		android.pause(4);
		android.unlockScreen();
		android.openNotifications();
		android.getAllNotifications();
		android.pause(1);
		android.acceptRejectNotification("accept");
		android.pause(3);
		android.verifyMessage("Message while android is locked and behind compass");
		android.closeConversation();
		android.print("Test case - chat notifications while device is locked.");
	}
	
	@Test(priority = 13, retryAnalyzer = Retry.class)
	public void notificationsWhileAppInBackgroundAndDeviceLocked() throws InterruptedException, IOException, AWTException {
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
	}
	
	@Test(priority = 14)
	public void marker()  {
		android.print("marker finished");
	}
	
	@Test(priority = 15, retryAnalyzer = Retry.class)
	public void androidAgentReceiveVideoCallWhileAppInBackground() throws InterruptedException, AWTException, IOException {
		android.getAppBackInForeground();
		firefox.close();
		firefox.setUp();
		firefox.waitForPageLoad();
		android.pressHomeButton();
		android.pause(1);
//		firefox.startVideoCall();
		firefox.callButtonFromHomeClick();
		android.pause(1);
		android.answerVideoCall();
		firefox.verifyOwnVideo();
		firefox.verifyVideoFromAgent();
		//ToDo verify video
		android.pause(1);
		android.stopOrRejectVideoCalling();
		android.print("Test case - Call while android works in background");
	}
	
	@Test(priority = 16, retryAnalyzer = Retry.class)
	public void notificationsWhileAppIsBehindAnotherApp() throws InterruptedException, IOException, AWTException {
		firefox.close();
		firefox.setUp();
		firefox.waitForPageLoad();
		android.startCompassApp();
		android.pause(5);
		firefox.SendMessage("Message while android is behind calculator");
		android.pause(5);
		android.openNotifications();
		android.acceptRejectNotification("accept");
		android.pause(4);
		android.verifyMessage("Message while android is behind calculator");
		android.closeConversation();
		android.print("Test case - chat notifications while another app is on focus.");
	}
	
	@Test(priority = 17, retryAnalyzer = Retry.class)
	public void notificationsWhilePlayGame() throws InterruptedException {	
		android.print("start new test ---notificationsWhilePlayGame-----------------------------");
		android.print("At the moment it failed intentionally");
		android.acceptRejectNotification("accept");		
		//ToDo
		android.print("Test case - chat notifications while play game.");
	}
	
	
	@Test(priority = 18, retryAnalyzer = Retry.class)
	public void notificationsWhileAppIsClosed() throws InterruptedException, AWTException, IOException {
		android.clearNotifications();
		firefox.close();
		firefox.setUp();
		firefox.waitForPageLoad();
		android.closeAllApps();
		firefox.SendMessage("Message while android app is closed");
		android.pause(2);
		android.openNotifications();
		android.pause(5);
		android.acceptRejectNotification("accept");
		android.pause(6);
		android.verifyMessage("Message while android app is closed");
		android.pause(5);
		android.closeConversation();
		android.print("Test case - chat notifications while app is closed.");
	}
	
	@Test(priority = 19, retryAnalyzer = Retry.class)
		public void androidAgentReceiveVideoCallWhileAppBehindAnotherApp() throws InterruptedException, IOException, AWTException {
			firefox.close();
			firefox.setUp();
			firefox.waitForPageLoad();
			android.pause(2);
			android.startCompassApp();
			android.pause(2);
			firefox.startVideoCall();
//			firefox.callButtonFromHomeClick();
			android.pause(1);
			android.answerVideoCall();
			//ToDo verify video
			firefox.verifyOwnVideo();
			firefox.verifyVideoFromAgent();
			android.pause(5);
			android.stopOrRejectVideoCalling();
			android.print("Test case - Call while android works in background");
		}
	 
		
//	@Test(priority = 20, retryAnalyzer = Retry.class)
//	public void androidAgentReceiveVideoCallWhileAppIsClosed() throws InterruptedException, IOException {
//		firefox.reloadAgentUrl();
//		firefox.waitForPageLoad();
//		android.startApp();
//		android.closeAllApps();
//	firefox.startVideoCall();
//		android.pause(5);
//		android.answerVideoCall();
//	firefox.verifyOwnVideo();
//	firefox.verifyVideoFromAgent();
		//ToDo verify video
//		android.pause(5);
//		android.stopOrRejectVideoCall();
//		android.print("Test case - Call when app is closed");
//	}
//	
//	@Test(priority = 21, retryAnalyzer = Retry.class)
//	public void androidAgentReceiveVideoCallWhenAppIsKilled() throws InterruptedException, IOException {
//
//		//ToDo
//		android.print("Not implemented - Test case - Call when app is killed");
//	}
	
	@Test(priority = 22, retryAnalyzer = Retry.class)
	public void prospectFillRequestedForm() throws InterruptedException, IOException {
		android.startConversation();
		android.startVideoCall();
		android.pause(1);
		firefox.answerVideoCall();
		android.muteOrUnmuteMicrophone();
		firefox.verifyOwnVideo();
		firefox.verifyVideoFromAgent();
		android.pause(1);
		firefox.muteMicrophone();
		android.requestPhoneAndEmail();
		android.pause(1);
		firefox.fillRequestedForm("Test_Name", "Test_mail@mail.com", "1234567890");
//		
//		android.videoCallGetTextFromElements();
//		android.pause(15);
		
		// ToDo verify requested details
		android.verifyRequestedForm("Test_Name", "Test_mail@mail.com", "1234567890");
		
		android.stopOrRejectVideoCalling();
		android.closeConversation();
		android.print("Test case - prospect fill details in requested form from agent.");
	}

	@Test(priority = 23, retryAnalyzer = Retry.class)
	public void prospectMicrophoneTurnOnOffWhileInCall() throws InterruptedException, IOException {
//		firefox.callButtonFromHomeClick();
//		firefox.callButtonFromConversationClick();
		
		firefox.startVideoCall();
		android.answerVideoCall();
		android.pause(1);
		firefox.muteMicrophone();
		android.pause(1);
		firefox.unmuteMicrophone();
		android.pause(1);
		firefox.muteMicrophone();
		android.pause(1);
		firefox.unmuteMicrophone();
		
		android.stopOrRejectVideoCalling();
		android.pause(1);
		android.print("Test case - prospectMicrophoneTurnOnOffWhileInCall");
	}

	@Test(priority = 24, retryAnalyzer = Retry.class)
	public void agentStartCancelVideoCall() throws InterruptedException, AWTException, IOException {
		android.startConversation();
		android.startVideoCall();
		android.wait(1);
		android.stopOrRejectVideoCalling();
		android.wait(1);
		android.startVideoCall();
		android.wait(1);
		android.stopOrRejectVideoCalling();
		android.wait(1);
		android.startVideoCall();
		android.wait(1);
		firefox.answerVideoCall();
		firefox.verifyOwnVideo();
		firefox.verifyVideoFromAgent();
		android.stopOrRejectVideoCalling();
		android.closeConversation();
		android.print("Test case - agentStartCancelVideoCall");
	}
	
	@Test(priority = 25, retryAnalyzer = Retry.class)
	public void agentTurnMicrophoneOnOffWhileInCall() throws InterruptedException, IOException {
		android.startConversation();
		android.startVideoCall();
		firefox.answerVideoCall();
		firefox.verifyOwnVideo();
		firefox.verifyVideoFromAgent();
		android.wait(1);
		android.muteOrUnmuteMicrophone();
		android.wait(1);
		android.muteOrUnmuteMicrophone();
		android.wait(1);
		android.muteOrUnmuteMicrophone();
		android.stopOrRejectVideoCalling();
		android.closeConversation();
		android.print("Test case - agent Turn Microphone On / Off when in call");
	}
	
	@AfterClass
	void cleanUp() throws IOException {
		android.cleanUpAndroid();
		firefox.close();
	}
}

package android2.VideoEngager;

import java.awt.AWTException;
import java.io.IOException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

public class TestCasesVideo {

	Firefox firefox = new Firefox();
	Android android = new Android();
	Chrome chrome = new Chrome();

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
		android.print("--------------------------------------------------------------------------------");
	}

	@Test(priority = 3)
	public void chromeAgentLogin() throws InterruptedException, AWTException, IOException {
		chrome.setUp();
		chrome.login("tester2006@abv.bg", "Tarator1");
		chrome.print("--------------------------------------------------------------------------------");
	}

	@Test(priority = 3)
	public void agentStartCancelVideoCall() throws InterruptedException, AWTException, IOException {
		chrome.print("--------------------------------------------------------------------------------");
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
		android.print("---------------------------------------------------------------------------");
	}

	@Test(priority = 11)
	public void androidAgentChangeCameraPosition() throws InterruptedException, IOException {
		android.print("start new test ------------------------------------------------------------------");
		firefox.callButtonFromHomeClick();
		// firefox.callButtonFromConversationClick();
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
		android.print("---------------------------------------------------------------------------");
	}

	@Test(priority = 11)
	public void prospectMicrophoneTurnOnOffWhileInCall() throws InterruptedException {
		android.print("start new test ------------------------------------------------------------------");
		firefox.callButtonFromHomeClick();
		// firefox.callButtonFromConversationClick();

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
		android.print("---------------------------------------------------------------------------");
	}

	@Test(priority = 25)
	public void agentTurnMicrophoneOnOffWhileInCall() throws InterruptedException, IOException {
		android.print("--------------------------------------------------------------------------------");
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
		android.print("Test case - agentTurnMicrophoneOnOffWhileInCall");
		android.print("---------------------------------------------------------------------------");
	}

	@AfterClass
	void cleanUp() throws IOException {
		android.cleanUpAndroid();
		firefox.close();
	}
}

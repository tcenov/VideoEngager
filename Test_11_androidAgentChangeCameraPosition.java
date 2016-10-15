package android2.VideoEngager;

import java.awt.AWTException;
import java.io.IOException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

public class Test_11_androidAgentChangeCameraPosition {

	Firefox firefox = new Firefox();
	Android android = new Android();

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

	@AfterClass
	void cleanUp() throws IOException {
		android.cleanUpAndroid();
		firefox.close();
	}
}

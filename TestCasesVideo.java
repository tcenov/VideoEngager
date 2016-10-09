package android2.VideoEngager;

import java.awt.AWTException;
import java.io.IOException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

public class TestCasesVideo {

	Firefox firefox = new Firefox();
	Android android = new Android();

	@Test(priority = 1)
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

	@Test(priority = 7)
	public void androidAgentReceiveVideoCall() throws InterruptedException, IOException {
		android.print("start new test ------------------------------------------------------------------");

		firefox.callButtonFromHomeClick();
		// firefox.callButtonFromConversationClick();
		android.pause(1);
		android.answerVideoCall();
		firefox.verifyVideoFromAgent();
		firefox.verifyOwnVideo();
		// ToDo verify video
		android.pause(5);
		firefox.muteMicrophone();
		android.pause(2);
		android.inVideoCallCameraButtonClick();
		android.pause(3);
		android.changeCameraTo("no camera");

		android.pause(3);
		android.verifyStoppedOwnVideo();
		firefox.CameraButtonClick();
		android.pause(3);
		android.verifyStoppedVideoFromProspector();
		firefox.verifyStoppedOwnVideo();
		android.pause(3);
		android.requestPhoneAndEmail();
		android.pause(5);
		firefox.fillRequestedForm(firefox.generateName(), firefox.generateEmail(), "1234567890");

		// android.pause(8);
		// android.inVideoCallCameraButtonClick();
		// android.pause(8);
		// android.changeCameraTo("front camera");
		//
		// android.pause(11);
		// android.inVideoCallCameraButtonClick();
		// android.pause(8);
		// android.changeCameraTo("back camera");

		android.pause(20);
		android.stopOrRejectVideoCall();
		android.pause(2);
		android.print("Test case -  ");
		android.print("---------------------------------------------------------------------------");
	}

	// @Test(priority = 5)
	// public void prospectReceiveVideoCallEndFromAndroid() throws
	// InterruptedException {
	// android.print("start new test
	// ------------------------------------------------------------------");
	// android.startConversation();
	// android.startVideoCall();
	// android.pause(2);
	// firefox.answerVideoCall();
	// //ToDo verify video
	// android.pause(2);
	// // firefox.muteMicrophone();
	// android.pause(2);
	// android.stopOrRejectVideoCall();
	// android.closeConversation();
	// android.print("Test case - prospect receive Video call");
	// android.print("--------------------------------------------------------------------------------");
	// }

	@AfterClass
	void cleanUp() throws IOException {
		android.cleanUpAndroid();
		firefox.close();
	}
}

package android2.VideoEngager;

import java.awt.AWTException;
import java.io.IOException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import android.ScreenshotUtility_Toci;

@Listeners({ GetAndroidLogs.class })

public class DeleteMe {

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
	
	@Test(priority = 1)
	public void androidLogin() throws InterruptedException, AWTException, IOException {
		android.setUp();
		android.print("--------------------------------------------------------------------------------");
	}

	@Test(priority = 2)
	public void firefoxJoin() throws InterruptedException, AWTException, IOException {
		
	}

	@Test(priority = 3, retryAnalyzer = Retry.class)
	public void agentReceiveChatMessage() throws InterruptedException {
		android.pause(1);
		android.verifyMessage("Sent from browser prospector");
		android.pause(1);
		android.closeConversation();
	}

	@AfterClass
	void cleanUp() throws IOException {
		android.cleanUpAndroid();
	}
}

package android2.VideoEngager;

import java.awt.AWTException;
import java.io.IOException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Test_BackgroundTests {

	Firefox firefox = new Firefox();
	Android android = new Android();
	

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
	
	@Test(priority = 7)
	public void notificationsWhileRunInBackground() throws InterruptedException, IOException {
		//android.runAppInBackground(5);
				
		android.pressHomeButton();
		//android.clearNotifications();
		android.pause(2);
		firefox.SendMessage("Message while android works in background");
		android.pause(2);
		android.openNotifications();
		android.clearNotifications();
		
		//driver.findElement(By.xpath("//android.widget.TextView[@text='TitleText']"));
				
//		((AndroidDriver) driver).openNotifications();
//		sleep(1); //wait while notifications are playing animation to appear to avoid missed taps
//		nativeNotificationPage = new NativeNotificationPage(driver);
//		assertTrue("Native notification page is NOT loaded", nativeNotificationPage.isNativeNotificationPage());
//
//		int itemsListSize = nativeNotificationPage.getLastItemsContentSize();
//
//		String title, text;
//		int notificationItemNum = 0;
//		for (int i = 0; i <= itemsListSize; i++) {
//		    title = nativeNotificationPage.getItemTitle(i);
//		    text = nativeNotificationPage.getItemText(i);
//		    System.out.println("   notification title is: " + title);
//		    System.out.println("   notification text is: " + text);
//		    if (title.equals("SOME_TEXT")) {
//		        notificationItemNum = i;
//		        break;
//		    }
//		}
		
		
		android.pause(240);	
		android.print("Test case - call notifications while android run in background");
		
//		adb shell am start -n com.myApp.android/.Main
//		adb shell am start -n com.leadsecure.core/.ui.LoginActivity
//		adb shell input keyevent 26
//		adb shell am start -n com.android.browser/.BrowserActivity
		
		
	}

	@AfterClass
	void cleanUp() throws IOException {
		android.cleanUpAndroid();
		firefox.close();
	}
}

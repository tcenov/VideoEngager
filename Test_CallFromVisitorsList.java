package android2.VideoEngager;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.TouchAction;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

@SuppressWarnings("rawtypes")

public class Test_CallFromVisitorsList extends Android {

	AppiumDriver android;
	Dimension size;

	@Test
	public void test() throws InterruptedException {
		size = android.manage().window().getSize();
		print("Device screen size is " + size);

		login("tester2006@abv.bg", "Tarator1");

		// makeVideoCallFromVisitorList();
		WebDriverWait wait = new WebDriverWait(android, 15);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("ongoingEmailText")));
		WebElement visitor = android.findElement(By.id("ongoingEmailText"));
		print("Your first visitor is: " + visitor.getText());

		WebElement call = android.findElement(By.id("txtCall"));

		// Get visitor location to swipe to Call button
		Point point = call.getLocation();
		int xcoord = point.getX() - 96;
		int ycoord = point.getY();
		print("call button coordinates are " + xcoord + "   " + ycoord);
		TouchAction action = new TouchAction((MobileDriver) android);
		action.longPress(call).moveTo(xcoord, ycoord).perform().release();

		// logout();

		print("krai na testa");
		wait(8);

	}

	void makeVideoCallFromVisitorList() {
		WebDriverWait wait = new WebDriverWait(android, 20);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("ongoingCallContainerRecyclerView")));

		WebElement visitorsList = android.findElement(By.id("ongoingCallContainerRecyclerView"));
		List<WebElement> visitors = visitorsList.findElements(By.id("rowFront"));
		print("You have only " + visitors.size() + " visitors");

		// Get visitor location to swipe to Call button
		Point point = visitors.get(0).getLocation();
		int xcord = point.getX();
		int ycord = point.getY();

		TouchAction action = new TouchAction((MobileDriver) android);
		action.longPress(visitors.get(0)).moveTo(xcord - 50, ycord).perform();
	}
}
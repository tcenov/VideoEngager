package android2;

import org.openqa.selenium.WebElement;

public class IsPresent {
	public boolean isPresent(WebElement element) {
	    boolean flag = true;
	    try {
	        element.isDisplayed();
	        flag = true;
	    }
	    catch (Exception e) {
	        flag = false;
	    }
	    return flag;
	}	
}

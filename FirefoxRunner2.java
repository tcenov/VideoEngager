package android2.VideoEngager;

import java.awt.AWTException;
import java.io.File;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class FirefoxRunner2  {
	
	WebDriver firefox;
	
	@BeforeTest
	public void setUp() throws AWTException{
		System.setProperty("webdriver.gecko.driver", "D:\\geckodriver-v0.10.0-win64\\geckodriver.exe");
		ProfilesIni profile = new ProfilesIni();
		FirefoxProfile  firefoxProfile= profile.getProfile("default");
		 // this make firefoxProfile to block web page images
		firefoxProfile.setPreference("permissions.default.image", 2);
		firefox = new FirefoxDriver(firefoxProfile);		
		firefox.manage().window().maximize();
		new Minimize().minimize();
		firefox.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		firefox.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
		firefox.manage().timeouts().setScriptTimeout(100, TimeUnit.SECONDS);
		
		firefox.get("http://dir.bg");
		//firefox.get("https://videome.leadsecure.com/testtes");	
	}

	@Test
	public void test1() throws InterruptedException{

        System.out.println("test");      
    //    firefox.get("https://videome.leadsecure.com/testtes");	
        
        FirefoxProfile profile1= new FirefoxProfile(new File("C:\\Users\\toci\\AppData\\Roaming\\Mozilla\\Firefox\\Profiles\\zvbhnh6q.selenium")); 
    	WebDriver driver1= new FirefoxDriver(profile1);
    	driver1.get("https://videome.leadsecure.com/testtes");
    	Thread.sleep(120000);

	}
}

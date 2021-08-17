package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_00_Web_Element {
	WebDriver driver;
	String projectPath = System.getProperty ("user.dir");
	
	@BeforeClass
	public void beforeClass() {
		//FireFox
		System.out.println(projectPath);
		System.setProperty("webdriver.gecko.driver",projectPath +"\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		
		//Chrom
		//System.out.println(projectPath);
		//System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		//driver = new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://demo.guru99.com/v4/");
	}

	@Test
	public void TC_01_() {
	
	
	}

	@Test
	public void TC_02_() {
	
	}

	@Test
	public void TC_03_() {
	
	}
	@Test
	public void TC_04_() {
	
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	public void sleepInSecond(long time) {
		try {
			Thread.sleep(time * 1000);

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
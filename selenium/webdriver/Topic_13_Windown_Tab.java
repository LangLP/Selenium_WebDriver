package webdriver;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
//import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
//import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_13_Windown_Tab {
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
	}

	@Test
	public void TC_01_Github() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
        //get ra  window/tab id tại  tab đang đưuọc  active
		String  parentTabID = driver.getWindowHandle();
		
		//click to  google link ->Tab mới
		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
		Set<String> allTabIDs =driver.getWindowHandles();
		System.out.println(allTabIDs.size());
		//Switch to  Google tab
		for(String id:allTabIDs) {
			if(!id.equals(parentTabID)) {
				driver.switchTo().window(id);
			}
		}
	
		driver.findElement(By.xpath("//input[@name='q']")).sendKeys("Selenium");
		sleepInSecond(2);
		
		
	}

	@Test
	public void TC_02_Kyna() {
	
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
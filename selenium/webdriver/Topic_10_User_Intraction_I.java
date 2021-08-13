package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_10_User_Intraction_I {
	WebDriver driver;
	Actions action;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() {
		// FireFox
		System.out.println(projectPath);
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		action = new Actions(driver);

		// Chrom
		// System.out.println(projectPath);
		// System.setProperty("webdriver.chrome.driver", projectPath +
		// "\\browserDrivers\\chromedriver.exe");
		// driver = new ChromeDriver();

		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://demo.guru99.com/v4/");
	}

	//@Test
	public void TC_01_Hover_1() {
		driver.get("https://automationfc.github.io/jquery-tooltip/");
		action.moveToElement(driver.findElement(By.xpath("//input[@id='age']"))).perform();
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector(".ui-tooltip-content")).getText(),
				"We ask for your age only for statistical purposes.");

	}

	//@Test
	public void TC_01_Hover_2() {
		driver.get("http://www.myntra.com/");
		action.moveToElement(driver.findElement(By.xpath("//a[@class='desktop-main'and text()='Kids']"))).perform();
		sleepInSecond(3);

		driver.findElement(By.xpath("//a[@class='desktop-categoryName'and text()='Home & Bath']"));
		action.moveToElement(driver.findElement(By.xpath("//a[@class='desktop-categoryName'and text()='Home & Bath']")))
				.perform();

	}

	@Test
	public void TC_03_() {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		List<WebElement> rectangles = driver.findElements(By.cssSelector(".ui-selectee"));
		// nhan phim  Ctl  xuong (nhung chua nha  ra)
		if(osName.toLowerCase().contains("mac os"))
		{
			action.keyDown(Keys.COMMAND).perform();	
		}else {
			action.keyDown(Keys.CONTROL).perform();	

		}
		action.click(rectangles.get(0))
		.click(rectangles.get(2))
		.click(rectangles.get(5))
		.click(rectangles.get(10)).perform();
	
		//nha phim Ctl  ra
		
		if(osName.toLowerCase().contains("mac os"))
		{
			action.keyDown(Keys.COMMAND).perform();	
		}else {
			action.keyDown(Keys.CONTROL).perform();	

		}
		sleepInSecond(3);
Assert.assertEquals(driver.findElements(By.cssSelector(".ui-selectee.ui-selected")).size(), 4);

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
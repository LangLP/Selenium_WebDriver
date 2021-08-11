package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_09_Alerts {
	WebDriver driver;
	Alert alert;
	WebDriverWait expliciWait;
	By resultValue = By.xpath("//p[@id='result']");

	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		// FireFox
		System.out.println(projectPath);
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		expliciWait = new WebDriverWait(driver, 10);

		// Chrom
		// System.out.println(projectPath);
		// System.setProperty("webdriver.chrome.driver", projectPath +
		// "\\browserDrivers\\chromedriver.exe");
		// driver = new ChromeDriver();

		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	//@Test
	public void TC_01_Accept_Alerts() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();

		// vừa wait vừa Switch vao
		alert = expliciWait.until(ExpectedConditions.alertIsPresent());

        //get text đẻ verify
		Assert.assertEquals(alert.getText(), "I am a JS Alert");
		// Accept Alert
		alert.accept();
		sleepInSecond(3);

		Assert.assertEquals(driver.findElement(resultValue).getText(), "You clicked an alert successfully");

	}

	//@Test
	public void TC_02_Confirm_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();

		// vừa wait vừa Switch vao
		alert = expliciWait.until(ExpectedConditions.alertIsPresent());

        //get text đẻ verify
		Assert.assertEquals(alert.getText(), "I am a JS Confirm");
		// Cancel Alert
		alert.dismiss();
		sleepInSecond(3);

		Assert.assertEquals(driver.findElement(resultValue).getText(), "You clicked: Cancel");

	}

	//@Test
	public void TC_03_Prompt_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		String alertValue="Selenium Webdriver";
		driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();

		// vừa wait vừa Switch vao
		alert = expliciWait.until(ExpectedConditions.alertIsPresent());

        //get text đẻ verify
		Assert.assertEquals(alert.getText(), "I am a JS prompt");
		
		alert.sendKeys(alertValue);
		sleepInSecond(3);
	
		// Accept Alert
		alert.accept();
		sleepInSecond(3);

		Assert.assertEquals(driver.findElement(resultValue).getText(), "You entered: "+alertValue);
	}

	//@Test
	public void TC_04_Authentications() {
		
		driver.get("http://admin:admin@the-internet.herokuapp.com/basic_auth");
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).isDisplayed());

	}
	@Test
	public void TC_05_Authentications() {
		
		driver.get("http://the-internet.herokuapp.com");
		String basicAuthenUrl =driver.findElement(By.xpath("//a[text()='Basic Auth']")).getAttribute("href");
		driver.get(passInForToUrl(basicAuthenUrl));
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).isDisplayed());

	}
	
	public String passInForToUrl(String url)
	{
		String[] urlValue =url.split("//");
		url=urlValue[0]+"//"+"admin"+":"+"admin"+"@"+urlValue[1];
		return url;
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
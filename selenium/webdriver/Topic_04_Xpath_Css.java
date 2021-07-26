package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_04_Xpath_Css {
	WebDriver driver;
	// String projectPath = System.getProperty ("user.dir");

	@BeforeClass
	public void beforeClass() {
		// System.out.println(projectPath);
		// System.setProperty("webdriver.gecko.driver",projectPath
		// +"\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
	}

	@Test
	public void TC_01_Login_Emty_Email_And_Passwork() {
		// Nhập dữ liệu vào 1 text
		driver.findElement(By.id("email")).sendKeys("");
		driver.findElement(By.id("pass")).sendKeys("");
		// click vào Button
		driver.findElement(By.xpath("//button[@title='Login']")).click();
		// get error message text of Email Passwork
		Assert.assertEquals(driver.findElement(By.id("advice-required-entry-email")).getText(),
				"This is a required field.");
		Assert.assertEquals(driver.findElement(By.id("advice-required-entry-pass")).getText(),
				"This is a required field.");
	}

	@Test
	public void TC_02_Login_Invalid_Email() {
		// Refresh
		driver.navigate().refresh();
		// Nhập dữ liệu vào 1 text
		driver.findElement(By.id("email")).sendKeys("123@1234");
		driver.findElement(By.id("pass")).sendKeys("123456");
		// click vào Button
		driver.findElement(By.xpath("//button[@title='Login']")).click();
		// get error message text of Email
		Assert.assertEquals(driver.findElement(By.id("advice-validate-email-email")).getText(),
				"Please enter a valid email address. For example johndoe@domain.com.");
	}

	@Test
	public void TC_03_Login_Invalid_Passwork() {
		// Refresh
		driver.navigate().refresh();
		// Nhập dữ liệu vào 1 text
		driver.findElement(By.id("email")).sendKeys("automation@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("123");
		// click vào Button
		driver.findElement(By.xpath("//button[@title='Login']")).click();
		// get error message text of Passwork
		Assert.assertEquals(driver.findElement(By.id("advice-validate-password-pass")).getText(),
				"Please enter 6 or more characters without leading or trailing spaces.");
	}

	@Test
	public void TC_04_Login_Icorrect_Email() {
		// Refresh
		driver.navigate().refresh();
		// Nhập dữ liệu vào 1 text
		driver.findElement(By.id("email")).sendKeys("langlp@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("123456");
		// click vào Button
		driver.findElement(By.xpath("//button[@title='Login']")).click();
		// get error message text of Email Passwork
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='error-msg']//span")).getText(),
				"Invalid login or password.");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}

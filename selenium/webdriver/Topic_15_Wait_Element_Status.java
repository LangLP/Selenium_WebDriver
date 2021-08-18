package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_15_Wait_Element_Status {
	WebDriver driver;
	WebDriverWait explicitWait;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		// FireFox
		System.out.println(projectPath);
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();

		// Chrom
		// System.out.println(projectPath);
		// System.setProperty("webdriver.chrome.driver", projectPath +
		// "\\browserDrivers\\chromedriver.exe");
		// driver = new ChromeDriver();

		driver.manage().window().maximize();

		explicitWait = new WebDriverWait(driver,15);

	}

	//@Test
	public void TC_01_Visibale_Displayed() {
       //Hiển  thị(Visibale/displayed/visibility)-Hiển  thị trên UI  và có trong DOM
		driver.get("https://www.facebook.com/");
		// Wait cho 1 elemnet hiển thị trong khoản thời gian 1 s
		//có trên UI và  có trên DOM
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='email']")));
		

		// verify cho 1 elment hiển thị
		Assert.assertTrue(driver.findElement(By.xpath("//input[@id='email']")).isDisplayed());

	}

	//@Test
	public void TC_02_Invisible_Undisplayed() {//+bắt buôc(Ko  có trên UI)-có trong DOM  or Ko
		driver.get("https://www.facebook.com/"); 
		//wait  cho  button  tạo tài  khoản  có thể  được  click
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Tạo tài khoản mới']")));
	    //aciton
		driver.findElement(By.xpath("//a[text()='Tạo tài khoản mới']")).click();
		
		//ko  có trên UI vẫn có trong DOM
		
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//input[@name='reg_email__']")));
		
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Đăng ký']/parent::div/preceding-sibling::img")));
		driver.findElement(By.xpath("//div[text()='Đăng ký']/parent::div/preceding-sibling::img")).click();

	     // ko  có  trên  UI và k  có  trong DOM
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//form[@id='reg']")));

	}

	//@Test
	public void TC_03_Presence() {//bắt  buộc  có trong DOM - trên UI có or Ko
		driver.get("https://www.facebook.com/"); 

		//Hiển  thị  trên UI và có trên Dom=>pass
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='email']")));
		
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Tạo tài khoản mới']")));
		driver.findElement(By.xpath("//a[text()='Tạo tài khoản mới']")).click();

		//Hiển thị-K hiển  thị trên UI và vẫn có trong DOM=>Pass
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='reg_email__']")));
		
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Đăng ký']/parent::div/preceding-sibling::img")));
		driver.findElement(By.xpath("//div[text()='Đăng ký']/parent::div/preceding-sibling::img")).click();

		//k hiển  thị  trên UI và k  có trong DOM=>Fail
		
	}

	@Test
	public void TC_04_Staleness() {//bắt  buộc (k có trong DOM)
		driver.get("https://www.facebook.com/"); 
		
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Tạo tài khoản mới']")));
	    //aciton
		driver.findElement(By.xpath("//a[text()='Tạo tài khoản mới']")).click();
		
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//form[@id='reg']")));
		WebElement registerForm = driver.findElement(By.xpath("//form[@id='reg']"));

		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Đăng ký']/parent::div/preceding-sibling::img")));
		driver.findElement(By.xpath("//div[text()='Đăng ký']/parent::div/preceding-sibling::img")).click();

		
		//k có  trên UI và k  có trong DOM
		//wait register form Staleness
		explicitWait.until(ExpectedConditions.stalenessOf(registerForm));
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
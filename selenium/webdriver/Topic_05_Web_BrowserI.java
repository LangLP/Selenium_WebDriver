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

public class Topic_05_Web_BrowserI {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		// FireFox
		System.out.println(projectPath);
	     System.setProperty("webdriver.gecko.driver",projectPath
		 +"\\browserDrivers\\geckodriver.exe");
		 driver = new FirefoxDriver();

		// Chrom
		//System.out.println(projectPath);
		//System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		//driver = new ChromeDriver();

		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	@Test
	public void TC_01_Url() {
		//
		driver.get("https://vi-vn.facebook.com/");

		driver.findElement(By.xpath("//a[text()='Đăng ký']")).click();

		String registerPageUrl = driver.getCurrentUrl();
		Assert.assertEquals(registerPageUrl, "https://vi-vn.facebook.com/r.php");

		driver.findElement(By.xpath("//a[text()='Đăng nhập']")).click();

		String loginPageUrl = driver.getCurrentUrl();
		Assert.assertEquals(loginPageUrl, "https://vi-vn.facebook.com/login/");

	}

	@Test
	public void TC_02_Title() {
		//
		driver.get("https://vi-vn.facebook.com/");

		driver.findElement(By.xpath("//a[text()='Đăng ký']")).click();
		
		String registerPageTitle = driver.getTitle();
		Assert.assertEquals(registerPageTitle, "Đăng ký Facebook | Facebook");
		
		driver.findElement(By.xpath("//a[text()='Đăng nhập']")).click();
		String loginPageTitle = driver.getTitle();
		Assert.assertEquals(loginPageTitle, "Đăng nhập Facebook");
	}

	@Test
	public void TC_03_Navigation() {
		//
		driver.get("https://vi-vn.facebook.com/");
		driver.findElement(By.xpath("//a[text()='Đăng ký']")).click();
		driver.findElement(By.xpath("//a[text()='Đăng nhập']")).click();

		driver.navigate().back();
		String registerPageTitle = driver.getTitle();
		Assert.assertEquals(registerPageTitle, "Đăng ký Facebook | Facebook");
		
		driver.navigate().forward();
		String loginPageUrl = driver.getCurrentUrl();
		Assert.assertEquals(loginPageUrl, "https://vi-vn.facebook.com/login/");

	
	}
	@Test
	public void TC_04_Page_Sourse() {
		//
		driver.get("https://vi-vn.facebook.com/");
		driver.findElement(By.xpath("//a[text()='Đăng ký']")).click();

		driver.navigate().back();
		String registerPageSource = driver.getPageSource();
		Assert.assertTrue(registerPageSource.contains("Tạo tài khoản mới"));
	
		
		driver.findElement(By.xpath("//a[text()='Đăng nhập']")).click();
		driver.navigate().forward();
		String loginPageSource = driver.getPageSource();
		Assert.assertTrue(loginPageSource.contains("Đăng nhập Facebook"));

	
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
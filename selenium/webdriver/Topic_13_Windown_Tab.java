package webdriver;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
//import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_13_Windown_Tab {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		// FireFox
		System.out.println(projectPath);
		// System.setProperty("webdriver.gecko.driver", projectPath +
		// "\\browserDrivers\\geckodriver.exe");
		// driver = new FirefoxDriver();

		// Chrom
		// System.out.println(projectPath);
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	// @Test
	public void TC_01_Github() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		// get ra window/tab id tại tab đang đưuọc active
		String parentTabID = driver.getWindowHandle();

		// click to google link ->Tab mới
		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
		Set<String> allTabIDs = driver.getWindowHandles();
		System.out.println(allTabIDs.size());
		// Switch to Google tab
		for (String id : allTabIDs) {
			if (!id.equals(parentTabID)) {
				driver.switchTo().window(id);
			}
		}

		driver.findElement(By.xpath("//input[@name='q']")).sendKeys("Selenium");
		sleepInSecond(2);

	}

	//@Test
	public void TC_02_Github2() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		// get ra window/tab id tại tab đang được active
		String parentTabID = driver.getWindowHandle();
		System.out.println("Parent ID =" + parentTabID);

		// click to google link ->Tab mới
		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();

		// Switch to Google tab
		switchToWindowByID(parentTabID);
		sleepInSecond(5);

		String googleTabID = driver.getWindowHandle();
		driver.findElement(By.xpath("//input[@name='q']")).sendKeys("Selenium");
		sleepInSecond(5);
		// Switch to parent tab
		switchToWindowByID(googleTabID);
		sleepInSecond(2);

		// click to facebook link ->Tab mới
		driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();

		// Switch to facebook tab
		switchToWindowByTitle("Facebook - Đăng nhập hoặc đăng ký");

		// Switch to parent tab
		switchToWindowByTitle("SELENIUM WEBDRIVER FORM DEMO");

		// click to lazada link ->Tab mới
		driver.findElement(By.xpath("//a[text()='LAZADA']")).click();
		// Switch to lazada tab
		switchToWindowByTitle("Shopping online - Buy online on Lazada.vn");

		// Switch to parent tab
		switchToWindowByTitle("SELENIUM WEBDRIVER FORM DEMO");

		// click to tiki link ->Tab mới
		driver.findElement(By.xpath("//a[text()='TIKI']")).click();

		// Switch to tiki tab

		switchToWindowByTitle("Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");

		Assert.assertTrue(driver
				.findElement(
						By.cssSelector("input[placeholder='Tìm sản phẩm, danh mục hay thương hiệu mong muốn ...']"))
				.isDisplayed());

		driver.findElement(By.cssSelector("input[placeholder='Tìm sản phẩm, danh mục hay thương hiệu mong muốn ...']"))
				.sendKeys("iphone");

	}

	//@Test
	public void TC_03_Kyna() {
		driver.get("https://kyna.vn/");
		String parentID = driver.getWindowHandle();
        //click  to  Facebook link at Footer mới  
		driver.findElement(By.xpath("//div [@id='k-footer']//img[@alt='facebook']")).click();

		// Switch to facebook
		switchToWindowByID(parentID);
		sleepInSecond(2);

		// Switch to parent kyna
		switchToWindowByTitle("Kyna.vn - Học online cùng chuyên gia");

		// click to Thông tin hữu ích link at footer ->Tab mới
		driver.findElement(By.xpath("//div [@id='k-footer']//a[text()='Thông tin hữu ích']")).click();
		
		
		// Switch to Thông tin hữu ích kyna
		switchToWindowByTitle("Tổng hợp bài viết hay, thông tin hữu ích - Kyna.vn");
		
		// Switch to parent kyna
		switchToWindowByTitle("Kyna.vn - Học online cùng chuyên gia");
		
		 //click  to  Facebook link at Footer mới  
		driver.findElement(By.xpath("//div [@id='k-footer']//img[@alt='youtube']")).click();

		// Switch to youtube kyna
		switchToWindowByTitle("Kyna.vn - YouTube");
		
		driver.findElement(By.cssSelector("#search[placeholder='Search']")).sendKeys("Selenium");

		closeAllTabWithoutParent(parentID);

	}

	@Test
	public void TC_04_live_Guru() {

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public void closeAllTabWithoutParent(String parentID) {
		Set<String> allwindowIDs = driver.getWindowHandles();

		// duyêt qua các giá trị trong tab window
		for (String id : allwindowIDs) {
			// kiểm tra điều kiện nếu như khác với WindownID truyền vào thì sưitch
			if (!id.equals(parentID)) {
				driver.switchTo().window(id);
				driver.close();

			}
			driver.switchTo().window(parentID);
		}
	}

	public void switchToWindowByID(String windowID) {
		// get hết các id đang có
		Set<String> windowIDs = driver.getWindowHandles();
		//
		// duyêt qua các giá trị trong tab window
		for (String id : windowIDs) {
			// kiểm tra điều kiện nếu như khác với WindownID truyền vào thì sưitch
			if (!id.equals(windowID)) {
				driver.switchTo().window(id);
				// thoát khỏi vòng lặp
				break;

			}
		}
	}

	public void switchToWindowByTitle(String tabTitle) {
		// get het cac id dang co
		Set<String> windowIDs = driver.getWindowHandles();

		// Duyệt qua các giá trị trong all windows
		for (String id : windowIDs) {
			driver.switchTo().window(id);
			String actualTitle = driver.getTitle();
			if (actualTitle.equals(tabTitle)) {
				// thoát khỏi vòng lặp
				break;

			}
		}

	}

	public void sleepInSecond(long time) {
		try {
			Thread.sleep(time * 1000);

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
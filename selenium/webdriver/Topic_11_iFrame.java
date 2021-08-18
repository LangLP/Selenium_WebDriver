package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_11_iFrame {
	WebDriver driver;
	JavascriptExecutor jsExecuter;
	Actions action;

	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		// FireFox
		System.out.println(projectPath);
		//System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		//driver = new FirefoxDriver();

		// Chrom
		// System.out.println(projectPath);
		System.setProperty("webdriver.chrome.driver", projectPath+"\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		

		action = new Actions(driver);
		jsExecuter = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(defaultTimeout, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	// @Test
	public void TC_01_iFrame() {
		driver.get(
				"https://automationfc.com/2016/05/15/training-offline-khoa-hoc-automation-testing-voi-selenium-java/");

		// switch to facebook iframe
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@title='fb:page Facebook Social Plugin']")));

		// element of facebook iframe
		String likeNumber = driver
				.findElement(By.xpath("//a[@title='Automation FC']/parent::div/following-sibling::div")).getText();
		System.out.println(likeNumber);

		// switch to parent page
		driver.switchTo().defaultContent();

		// element of parent
		String postTitle = driver.findElement(By.xpath("//h1[@class='post-title']")).getText();
		System.out.println(postTitle);

		// switch to google dox iframe
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@src,'docs.google.com')]")));
		driver.findElement(By.xpath("//div[contains(@data-params,'HỌ TÊN')]//input")).sendKeys("LangLp");

	}

	@Test
	public void TC_02_Kyna() {
		driver.get("https://kyna.vn/");

		// By nameTextboxBy =By.xpath("//div[@class='floater_inner_seriously']
		// /label[@class='logged_in_name ng-binding']");
		// By phoneTextboxBy = By.cssSelector("div[class='floater_inner']
		// [class='logged_in_phone ng-binding']");
		// By messTextboxBy = By.cssSelector("[class='widget_widgets_TextArea']");

		By supportServicesBy = By.xpath("//select[@id='serviceSelect']");

		Select supportServices;
		// switch to facebook iframe
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@src,'false&height=350')]")));

		// element of facebook iframe
		String likeNumber = driver.findElement(By.xpath("//a[@title='Kyna.vn']/parent::div/following-sibling::div"))
				.getText();
		System.out.println(likeNumber);

		// switch to parent page
		driver.switchTo().defaultContent();
       
		// switch to chat iframe
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='cs_chat_iframe']")));
		sleepInSecond(5);
		//driver.switchTo().frame("cs_chat_iframe");
	//	sleepInSecond(5);
		// element of chat iframe '[class='widget_widgets_TextArea']'
		//driver.findElement(By.xpath("//div[contains(@class,'border_overlay meshim')]")).click();
		//driver.findElement(By.xpath("//div[@class='border_overlay meshim_widget_widgets_BorderOverlay']")).click();
		//action.click(driver.findElement(By.xpath("//div[@class='border_overlay meshim_widget_widgets_BorderOverlay']"))).perform();
		jsExecuter.executeScript("arguments[0].click();", driver.findElement(By.xpath("//div[@class='border_overlay meshim_widget_widgets_BorderOverlay']")));

	driver.findElement(By.xpath("//input[@ng-model='login.username']")).sendKeys("lang");
	driver.findElement(By.xpath("//input[@ng-model='login.phone']")).sendKeys("0987654321");



	
	supportServices=new Select(driver.findElement(supportServicesBy));
	supportServices.selectByVisibleText("TƯ VẤN TUYỂN SINH");
	sleepInSecond(2);
	
	supportServices=new Select(driver.findElement(supportServicesBy));
	Assert.assertEquals(supportServices.getFirstSelectedOption().getText(),"TƯ VẤN TUYỂN SINH");

	
	
	driver.findElement(By.xpath("//textarea[@ng-model='login.content']")).sendKeys("Java");
	sleepInSecond(2);
	driver.findElement(By.xpath("//input[@ng-click='sendOffline()']")).click();
	sleepInSecond(2);

	Assert.assertEquals(driver.findElement(By.xpath("//div[@class='floater']//label[@class='logged_in_name ng-binding']")).getText(),"lang");
	Assert.assertEquals(driver.findElement(By.xpath("//div[@class='floater']//label[@class='logged_in_phone ng-binding']")).getText(),"0987654321");
	Assert.assertEquals(driver.findElement(By.xpath("//textarea[@name='message']")).getText(),"Java");


	
	}

	public boolean isElementDisplayed(By By) {
		try {
			// no tim element trong vong 5
			// khi nao het 5 moi threw Exception

			return driver.findElement(By).isDisplayed();

		} catch (Exception e) {
			// catch bat Exception lai
			return false;
		}

	}

	public boolean isElementDisplayed2(By by) {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		List<WebElement> elements = driver.findElements(by);
		driver.manage().timeouts().implicitlyWait(defaultTimeout, TimeUnit.SECONDS);

		if (elements.size() == 0) {
			return false;
		} else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
			return false;
		} else {
			return true;

		}

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

	long defaultTimeout =30;
}
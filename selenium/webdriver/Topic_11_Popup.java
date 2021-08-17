package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_11_Popup {
	WebDriver driver;
	JavascriptExecutor jsExecuter;

	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		// FireFox
		System.out.println(projectPath);
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		 driver = new FirefoxDriver();
			jsExecuter = (JavascriptExecutor) driver;

		// Chrom
		// System.out.println(projectPath);
		//System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		//driver = new ChromeDriver();

		driver.manage().timeouts().implicitlyWait(defaultTimeout, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	// @Test
	public void TC_01_ZingPoll() {
		driver.get("https://www.zingpoll.com/");

		By signInPupup = By.cssSelector(".modal_dialog_custom");
		driver.findElement(By.xpath("//a[@id='Loginform']")).click();
		// verify signin popup is display

		Assert.assertTrue(driver.findElement(signInPupup).isDisplayed());
		driver.findElement(By.cssSelector("#loginEmail")).sendKeys("lang@gmail.com");
		driver.findElement(By.cssSelector("#loginPassword")).sendKeys("123456");
		driver.findElement(By.cssSelector(".modal_dialog_custom .close")).click();

		sleepInSecond(3);
		Assert.assertFalse(driver.findElement(signInPupup).isDisplayed());

	}

	// @Test
 	public void TC_02_Shopee() {
		driver.get("https://shopee.vn/");

		By homePupup = By.cssSelector("img[alt='home_popup_banner']");
		// verify home popup is display
		Assert.assertTrue(isElementDisplayed(homePupup));

		driver.findElement(By.cssSelector(".shopee-popup__close-btn")).click();
		sleepInSecond(3);

		// verify home popup is not display

		Assert.assertFalse(isElementDisplayed(homePupup));

	}

	 @Test
	public void TC_03_Random_Poupup_In_Dom() {
		driver.get("https://blog.testproject.io/");

		if (isElementDisplayed2(By.cssSelector(".mailch-wrap"))) {
			driver.findElement(By.cssSelector("#close-mailch")).click();
		}

		driver.findElement(By.cssSelector("section input[class='search-field']")).sendKeys("Selenium");
		//section//span[@class='glass']
		driver.findElement(By.xpath("//section//span[@class='glass']")).click();
		//jsExecuter.executeScript("arguments[0].click();", driver.findElement(By.cssSelector("section span[class='glass']")));


		sleepInSecond(3);

		List<WebElement> postArticles = driver.findElements(By.xpath("//h3[@class='post-title']/a"));

		for (WebElement article : postArticles) {
			

			System.out.println(postArticles);

			Assert.assertTrue(article.getText().contains("Selenium"));
		}
	}

	//@Test
	public void TC_04_() {
		driver.get("https://shopee.vn/");
         String SearchKeyword = "Macbook Pro" ;
		if (isElementDisplayed2(By.cssSelector("img[alt='home_popup_banner']"))) {
			driver.findElement(By.cssSelector(".shopee-popup__close-btn")).click();
		}

		driver.findElement(By.xpath("//input[@class='shopee-searchbar-input__input']")).sendKeys(SearchKeyword);

		driver.findElement(By.xpath("//button[@class='btn btn-solid-primary btn--s btn--inline']")).click();
		sleepInSecond(3);
		
		List<WebElement> products = driver.findElements(By.cssSelector(".shopee-search-item-result__item div[data-sqe='name'] .yQmmFK "));

		for (WebElement product : products) {
			String productName =product.getText().toLowerCase();
			System.out.println(productName);
			System.out.println(SearchKeyword.split(" ")[0].toLowerCase());
			System.out.println(SearchKeyword.split(" ")[1].toLowerCase());
			
			Assert.assertTrue(productName.contains(SearchKeyword.split(" ")[0].toLowerCase()) || productName.contains(SearchKeyword.split(" ")[1].toLowerCase()));
		}
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
		//driver.quit();
	}

	public void sleepInSecond(long time) {
		try {
			Thread.sleep(time * 1000);

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	long defaultTimeout =10;
}
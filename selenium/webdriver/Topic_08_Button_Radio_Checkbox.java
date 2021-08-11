package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_Button_Radio_Checkbox {
	WebDriver driver;
	JavascriptExecutor jsExecuter;
	By loginButton = By.cssSelector(".fhs-btn-login");
	By usernameTextbox = By.cssSelector("#login_username");
	By passwordTextbox = By.cssSelector("#login_password");

	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		// FireFox
		System.out.println(projectPath);
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();

		// Chrom
		// System.out.println(projectPath);
		/// System.setProperty("webdriver.chrome.driver", projectPath +
		// "\\browserDrivers\\chromedriver.exe");
		// driver = new ChromeDriver();

		jsExecuter = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	// @Test
	public void TC_01_Button() {
		driver.get("https://www.fahasa.com/customer/account/create");
		driver.findElement(By.cssSelector(".popup-login-tab-login")).click();
		// verify login button disable
		Assert.assertTrue(driver.findElement(loginButton).isDisplayed());
		driver.findElement(usernameTextbox).sendKeys("automation@gmail.com");
		driver.findElement(passwordTextbox).sendKeys("123456");
		sleepInSecond(2);

		// verify login button enable
		Assert.assertTrue(driver.findElement(loginButton).isEnabled());
		driver.navigate().refresh();
		driver.findElement(By.cssSelector(".popup-login-tab-login")).click();
		jsExecuter.executeScript("arguments[0].removeAttribute('disabled');", driver.findElement(loginButton));

		sleepInSecond(4);
		Assert.assertTrue(driver.findElement(loginButton).isEnabled());
		driver.findElement(loginButton).click();
		Assert.assertEquals(driver
				.findElement(By.xpath("//input[@id='login_username']/parent::div//following-sibling::div")).getText(),
				"Thông tin này không thể để trống");
		Assert.assertEquals(driver
				.findElement(By.xpath("//input[@id='login_password']/parent::div//following-sibling::div")).getText(),
				"Thông tin này không thể để trống");

	}

	// @Test
	public void TC_02_Hidden_Element() {
		driver.get("https://www.fahasa.com/tiep-thuc-sai-gon?fhs_campaign=top_banner");
		jsExecuter.executeScript("arguments[0].click();", driver.findElement(
				By.xpath("//div[contains(@class,'background-menu-none-homepage')]//span[text()='Sách Trong Nước']")));
		sleepInSecond(5);

	}

	// @Test
	public void TC_03_Default_Radio_Checkbox() {
		driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");

		driver.findElement(By.xpath("//label[text()='1.8 Petrol, 118kW']/preceding-sibling::input")).click();
		// sleepInSecond(3);

		Assert.assertTrue(driver.findElement(By.xpath("//label[text()='1.8 Petrol, 118kW']/preceding-sibling::input"))
				.isSelected());

		driver.findElement(By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input")).click();
		// sleepInSecond(3);

		Assert.assertTrue(driver.findElement(By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input"))
				.isSelected());
		Assert.assertFalse(driver.findElement(By.xpath("//label[text()='1.8 Petrol, 118kW']/preceding-sibling::input"))
				.isSelected());

		driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");

		driver.findElement(By.xpath("//label[text()='Rear side airbags']/preceding-sibling::input")).click();
		driver.findElement(By.xpath("//label[text()='Luggage compartment cover']/preceding-sibling::input")).click();

		Assert.assertTrue(
				driver.findElement(By.xpath("//label[text()='Luggage compartment cover']/preceding-sibling::input"))
						.isSelected());
		Assert.assertFalse(driver.findElement(By.xpath("//label[text()='Rear side airbags']/preceding-sibling::input"))
				.isSelected());

	}

	// @Test
	public void TC_04_All_Selected() {
		driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");

		List<WebElement> allCheckboxes = driver.findElements(By.xpath("//input[@type='checkbox'and not(@disabled)]"));
		// Select all checkbox
		for (WebElement checkbox : allCheckboxes) {
			if (!checkbox.isSelected()) {
				checkbox.click();
				sleepInSecond(1);
			}
			Assert.assertTrue(checkbox.isSelected());
		}

		// deselect all check box

		for (WebElement checkbox : allCheckboxes) {
			if (checkbox.isSelected()) {
				checkbox.click();
				sleepInSecond(1);
			}
			Assert.assertFalse(checkbox.isSelected());

		}

	}

	//@Test
	public void TC_05_Cuctom_Radio_Checkbox() {

		// input de click +input de verify
		driver.get("https://material.angular.io/components/radio/examples");
		By stringRadio = By.xpath("//span[contains(text(),'Spring')]/preceding-sibling::span/input");
		jsExecuter.executeScript("arguments[0].click();", driver.findElement(stringRadio));
		sleepInSecond(1);

		Assert.assertTrue(driver.findElement(stringRadio).isSelected());

	}

	@Test
	public void TC_06_Cuctom_Radio_Checkbox_Google_Form() {

		// input de click +input de verify
		driver.get(
				"https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");

		driver.findElement(By.xpath("//div[@aria-label='Cần Thơ']")).click();
		Assert.assertTrue(
				driver.findElement(By.xpath("//div[@aria-label='Cần Thơ' and @aria-checked='true']")).isDisplayed());
		
		// Select all checkbox start with text='Quang'
		List<WebElement> allCheckboxes = driver.findElements(
				By.xpath("//div[starts-with(@aria-label,'Quảng')]//div[contains(@class,'exportInnerBox')]"));
		for (int i = 0; i < allCheckboxes.size(); i++) {
			allCheckboxes.get(i).click();
			sleepInSecond(1);

		}
		// verify- true
		List<WebElement> allCheckboxesSelected = driver.findElements(
				By.xpath("//div[starts-with(@aria-label,'Quảng')and @role='checkbox' and @aria-cheked='true']"));
		for (WebElement checkbox : allCheckboxesSelected) {

			Assert.assertTrue(checkbox.isDisplayed());
		}
		
			// verify-equals
			allCheckboxesSelected = driver.findElements(By.xpath("//div[starts-with(@aria-label,'Quảng')and @role='checkbox']"));
			for (WebElement checkbox2 : allCheckboxesSelected)
			{
				
				Assert.assertEquals(checkbox2.getAttribute("aria-cheked"), "true");
			
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
}
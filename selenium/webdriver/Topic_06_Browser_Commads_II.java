package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_Browser_Commads_II {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	By emailTextboxBy = By.id("mail");
	By educationTextareBy = By.id("edu");
	By user5TextBy = By.xpath("//h5[text()='Name: User5']");

	By passwordTextboxBy = By.id("password");
	By slider2By = By.id("slider-2");

	By ageOver18RadioBy = By.id("over_18");
	By developmentcheckboxBy = By.id("development");
	By javacheckboxBy = By.id("java");

	@BeforeClass
	public void beforeClass() {
		// FireFox
		// System.out.println(projectPath);
		// System.setProperty("webdriver.gecko.driver",projectPath
		// +"\\browserDrivers\\geckodriver.exe");
		// driver = new FirefoxDriver();

		// Chrom
		System.out.println(projectPath);
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();

		driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	@Test
	public void TC_01_Is_Displayed() {

		driver.get("https://automationfc.github.io/basic-form/index.html");

		if (isElementDisplayed(educationTextareBy)) {
			senkeyToElement(educationTextareBy, "Automation Testing");
		}
		if (isElementDisplayed(emailTextboxBy)) {
			senkeyToElement(emailTextboxBy, "Automation Testing");
		}
		if (isElementDisplayed(ageOver18RadioBy)) {
			clickToElement(ageOver18RadioBy);
		}

		Assert.assertFalse(isElementDisplayed(user5TextBy));

	}

	@Test
	public void TC_02_Is_Enabled() {
		// Mong đợi 1 element enabled
		Assert.assertTrue(isElementEnabled(emailTextboxBy));
		Assert.assertTrue(isElementEnabled(ageOver18RadioBy));
		Assert.assertTrue(isElementEnabled(educationTextareBy));

		// Mong đợi 1 element disabled

		Assert.assertFalse(isElementEnabled(passwordTextboxBy));
		Assert.assertFalse(isElementEnabled(slider2By));

	}

	@Test
	public void TC_03_Is_Selected() {
		clickToElement(ageOver18RadioBy);
		clickToElement(developmentcheckboxBy);

		Assert.assertTrue(isElementSelected(ageOver18RadioBy));
		Assert.assertTrue(isElementSelected(developmentcheckboxBy));
		Assert.assertFalse(isElementSelected(javacheckboxBy));

		clickToElement(ageOver18RadioBy);
		clickToElement(developmentcheckboxBy);

		Assert.assertTrue(isElementSelected(ageOver18RadioBy));
		Assert.assertFalse(isElementSelected(developmentcheckboxBy));
		Assert.assertFalse(isElementSelected(javacheckboxBy));

	}

	public boolean isElementDisplayed(By by) {
		WebElement element = driver.findElement(by);
		if (element.isDisplayed()) {
			System.out.println("Element[" + by + "]is displayed");
			return true;
		} else {
			System.out.println("Element[" + by + "]is not displayed");
			return false;

		}
	}

	public boolean isElementEnabled(By by) {
		WebElement element = driver.findElement(by);
		if (element.isEnabled()) {
			System.out.println("Element[" + by + "]is enabled");
			return true;
		} else {
			System.out.println("Element[" + by + "]is disabled");
			return false;

		}
	}

	public boolean isElementSelected(By by) {
		WebElement element = driver.findElement(by);
		if (element.isSelected()) {
			System.out.println("Element[" + by + "]is selected");
			return true;
		} else {
			System.out.println("Element[" + by + "]is not selected");
			return false;

		}
	}

	public void senkeyToElement(By by, String value) {
		WebElement element = driver.findElement(by);
		element.clear();
		element.sendKeys(value);
	}

	public void clickToElement(By by) {
		WebElement element = driver.findElement(by);
		element.click();

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
//import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_Default_Dropdown {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	Select dayDropdown, monthDropdown, yearDropdown;
	String email, password, firstName, lastName,day ,month,year;

	By maleRadioBy = By.id("gender-male");
	By firstNameTextboxBy = By.id("FirstName");
	By lastNameTextboxBy = By.id("LastName");
	By emailTextboxBy = By.id("Email");
	By dayDropdownBy = By.name("DateOfBirthDay");
	By monthDropdownBy = By.name("DateOfBirthMonth");
	By yearDropdownBy = By.name("DateOfBirthYear");

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

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		firstName = "Automation";
		lastName = "FC";
		password = "123456";
		day= "31" ;
		month= "March";
		year= "1995";
		email = getRandomEmail();

	}

	@Test
	public void TC_01_Register() {
		driver.get("https://demo.nopcommerce.com/register?returnUrl=%2F");
		driver.findElement(maleRadioBy).click();
		driver.findElement(firstNameTextboxBy).sendKeys(firstName);;
		driver.findElement(lastNameTextboxBy).sendKeys(lastName);;
		driver.findElement(emailTextboxBy).sendKeys(email);
		driver.findElement(By.id("Password")).sendKeys(password);
		driver.findElement(By.id("ConfirmPassword")).sendKeys(password);
		
		dayDropdown=new Select(driver.findElement(dayDropdownBy));
		dayDropdown.selectByVisibleText(day);
		
		monthDropdown=new Select(driver.findElement(monthDropdownBy));
		monthDropdown.selectByVisibleText(month);
		
		yearDropdown=new Select(driver.findElement(yearDropdownBy));
		yearDropdown.selectByVisibleText(year);
		
		driver.findElement(By.id("register-button")).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='result']")).getText(),"Your registration completed");
		driver.findElement(By.cssSelector(".ico-account")).click();

		Assert.assertTrue(driver.findElement(maleRadioBy).isSelected());
		Assert.assertEquals(driver.findElement(firstNameTextboxBy).getAttribute("value"),firstName);
		Assert.assertEquals(driver.findElement(lastNameTextboxBy).getAttribute("value"),lastName);
		Assert.assertEquals(driver.findElement(emailTextboxBy).getAttribute("value"),email);
		
		dayDropdown=new Select(driver.findElement(dayDropdownBy));
		Assert.assertEquals(dayDropdown.getFirstSelectedOption().getText(),day);
		
		monthDropdown=new Select(driver.findElement(monthDropdownBy));
		Assert.assertEquals(monthDropdown.getFirstSelectedOption().getText(),month);
		
		yearDropdown=new Select(driver.findElement(yearDropdownBy));
		Assert.assertEquals(yearDropdown.getFirstSelectedOption().getText(),year);
		
		

		
	}

	@AfterClass
	public void afterClass() {
		// driver.quit();
	}

	public void sleepInSecond(long time) {
		try {
			Thread.sleep(time * 1000);

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public String getRandomEmail() {
		Random rand = new Random();
		return "Johndeep" + rand.nextInt(99999) + "@gmail.com";
	}

}
package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_Web_Element_Browser_Commands_III {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String firtName, lastName, fullName, email, password;

	@BeforeClass
	public void beforeClass() {
		
		//FireFox
		//System.out.println(projectPath);
		//System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		//driver = new FirefoxDriver();

		//Chrom
		System.out.println(projectPath);
	    System.setProperty("webdriver.chrome.driver", projectPath +"\\browserDrivers\\chromedriver.exe");
	    driver = new ChromeDriver();

		firtName = "John";
		lastName = "Wick";
		fullName = firtName + " " + lastName;
		email = "John" + getRandomNumber() + "@gmail.us";
		password = "123456";

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}
	@Test
	public void TC_01_Singup_Validate() {
		//
		driver.get("https://login.mailchimp.com/signup/");

		driver.findElement(By.cssSelector("#email")).sendKeys("automation@gmail.vn");
		driver.findElement(By.cssSelector("#new_username")).sendKeys("automationclub");

		By passwordTextbox = By.id("new_password");
		By signupButton = By.id("create-account");
		By newseletterCheckBox = By.id("marketing_newsletter");
		// Click Newseletter checkbox
		driver.findElement(newseletterCheckBox).click();
		

		// Lowercase
		driver.findElement(passwordTextbox).sendKeys("auto");

		Assert.assertTrue(driver
				.findElement(By.xpath("//li[@class='lowercase-char completed'and text()= 'One lowercase character']"))
				.isDisplayed());
		Assert.assertFalse(driver.findElement(signupButton).isEnabled());

		// Uppercase
		driver.findElement(passwordTextbox).clear();
		driver.findElement(passwordTextbox).sendKeys("AUTO");

		Assert.assertTrue(driver
				.findElement(By.xpath("//li[@class='uppercase-char completed'and text()='One uppercase character']"))
				.isDisplayed());
		Assert.assertFalse(driver.findElement(signupButton).isEnabled());

		// Number
		driver.findElement(passwordTextbox).clear();
		driver.findElement(passwordTextbox).sendKeys("123456");

		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char completed'and text()='One number']"))
				.isDisplayed());
		Assert.assertFalse(driver.findElement(signupButton).isEnabled());

		// Special
		driver.findElement(passwordTextbox).clear();
		driver.findElement(passwordTextbox).sendKeys("!");

		Assert.assertTrue(
				driver.findElement(By.xpath("//li[@class='special-char completed'and text()='One special character']"))
						.isDisplayed());
		Assert.assertFalse(driver.findElement(signupButton).isEnabled());

		// >-8
		driver.findElement(passwordTextbox).clear();
		driver.findElement(passwordTextbox).sendKeys("🤣🤣🤣🤣🤣🤣🤣🤣");

		Assert.assertTrue(
				driver.findElement(By.xpath("//li[@class='8-char completed'and text()='8 characters minimum']"))
						.isDisplayed());
		Assert.assertFalse(driver.findElement(signupButton).isEnabled());

		// fell Valid data
		driver.findElement(passwordTextbox).clear();
		driver.findElement(passwordTextbox).sendKeys("@Anh11111");
		
		Assert.assertFalse(driver
				.findElement(By.xpath("//li[@class='lowercase-char completed'and text()='One lowercase character']"))
				.isDisplayed());

		Assert.assertFalse(driver.findElement(By.xpath("//li[@class='number-char completed'and text()='One number']"))
				.isDisplayed());
		Assert.assertFalse(
				driver.findElement(By.xpath("//li[@class='special-char completed'and text()='One special character']"))
						.isDisplayed());

		Assert.assertFalse(driver
				.findElement(By.xpath("//li[@class='uppercase-char completed'and text()='One uppercase character']"))
				.isDisplayed());

		Assert.assertTrue(driver.findElement(signupButton).isEnabled());
		//Assert.assertTrue(driver.findElement(newseletterCheckBox).isSelected());

	}

@Test
	public void TC_02_LiveGuru_Resgister() {
		//
		driver.get("http://live.demoguru99.com/payment-gateway/index.php");

		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		driver.findElement(By.cssSelector("a[title='Create an Account']")).click();

		driver.findElement(By.id("firstname")).sendKeys(firtName);
		driver.findElement(By.id("lastname")).sendKeys(lastName);
		driver.findElement(By.id("email_address")).sendKeys(email);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.id("confirmation")).sendKeys(password);

		driver.findElement(By.xpath("//button[@title='Register']")).click();
		//Assert.assertTrue(driver.findElement(By.xpath("//li[@class='success-msg']//span[text()='Thank you for registering with Main Website Store.']")).isDisplayed());
		//Assert.assertTrue(driver.findElement(By.xpath("//h3[text()='Contact Information']/" + "parent::div/following-sibling::div[@class='box-content']/p[contains(string(),'"+ fullName +"')]")).isDisplayed());
		//Assert.assertTrue(driver.findElement(By.xpath("//h3[text()='Contact Information']/" + "parent::div/following-sibling::div[@class='box-content']/p[contains(string(),'"+ email +"')]")).isDisplayed());
		String contactInformation = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div[@class='box-content']/p")).getText();
		Assert.assertTrue(contactInformation.contains(fullName));
		Assert.assertTrue(contactInformation.contains(email));
		
		driver.findElement(By.xpath("//a/span[text()='Account']")).click();
		driver.findElement(By.xpath("//a[text()='Log Out']")).click();

	}

	@Test
	public void TC_03_LiveGuru_login() {
		//
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		driver.findElement(By.id("email")).sendKeys(email);
		driver.findElement(By.name("login[password]")).sendKeys(password);
		driver.findElement(By.id("send2")).click();

		Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='My Dashboard']")).isDisplayed());
		String contactInformation = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div[@class='box-content']/p")).getText();
		Assert.assertTrue(contactInformation.contains(fullName));
		Assert.assertTrue(contactInformation.contains(email));
		//Assert.assertTrue(driver.findElement(By.xpath("//form[@name='frmLogin']")).isDisplayed());
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(99999);
	}

}
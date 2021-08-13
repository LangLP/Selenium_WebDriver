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

public class Topic_07_Web_Handle_Textbox_I {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	String emailAddress, loginPageUrl, userID, password, gender;
	String name, gander, dateOfBirth, address, city, sate, pin, phone, customerID;
	String  editAddress, editCity, editSate, editPin, editPhone,editEmailAddress;

	By nameTextbox = By.name("name");
	By ganderRadio = By.xpath("//input[@value='m']");
	By dobTextbox = By.name("dob");
	By addressTextArea = By.name("addr");
	By citynameTextbox = By.name("city");
	By stateTextbox = By.name("state");
	By pinTextbox = By.name("pinno");
	By phoneTextbox = By.name("telephoneno");
	By emailTextbox = By.name("emailid");
	By passwordTextbox = By.name("password");

	@BeforeClass
	public void beforeClass() {
		// FireFox
		System.out.println(projectPath);
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();

		// Chrom
		//System.out.println(projectPath);
		//System.setProperty("webdriver.chrome.driver", projectPath +
		//"\\browserDrivers\\chromedriver.exe");
		// driver = new ChromeDriver();

		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://demo.guru99.com/v4/");

		emailAddress = getRandomEmail();
		name = "Jhon Deep";
		gender = "male";
		dateOfBirth = "1956-01-01";
		address = "77 PO Califoria";
		city = "Hanoi";
		sate = "Canada";
		pin = "123456";
		phone = "0976543211";
		
		editEmailAddress = getRandomEmail();
		
		editAddress = "76 PO Foria";
		editCity = "Ange";
		editSate = "Lao";
		editPin = "987654";
		editPhone = "0976555333";

	}

	@Test
	public void TC_01_Register() {
		loginPageUrl = driver.getCurrentUrl();

		driver.findElement(By.xpath("//a[text()='here']")).click();
		driver.findElement(By.name("emailid")).sendKeys(emailAddress);
		driver.findElement(By.name("btnLogin")).click();

		userID = driver.findElement(By.xpath("//td[text()='User ID :']/ following-sibling ::td ")).getText();
		password = driver.findElement(By.xpath("//td[text()='Password :']/ following-sibling ::td ")).getText();

	}

	@Test
	public void TC_02_Login() {
		driver.get(loginPageUrl);
		driver.findElement(By.name("uid")).sendKeys(userID);
		driver.findElement(By.name("password")).sendKeys(password);
		driver.findElement(By.name("btnLogin")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//marquee[@class='heading3']")).getText(),
				"Welcome To Manager's Page of Guru99 Bank");
	}

	@Test
	public void TC_03_New_Customer() {

		driver.findElement(By.xpath("//a[text()='New Customer']")).click();

		driver.findElement(nameTextbox).sendKeys(name);
		driver.findElement(ganderRadio).click();
		driver.findElement(dobTextbox).sendKeys(dateOfBirth);
		driver.findElement(addressTextArea).sendKeys(address);
		driver.findElement(citynameTextbox).sendKeys(city);
		driver.findElement(stateTextbox).sendKeys(sate);
		driver.findElement(pinTextbox).sendKeys(pin);
		driver.findElement(phoneTextbox).sendKeys(phone);
		driver.findElement(emailTextbox).sendKeys(emailAddress);
		driver.findElement(passwordTextbox).sendKeys(password);

		driver.findElement(By.name("sub")).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//p[@class='heading3']")).getText(),
				"Customer Registered Successfully!!!");
		Assert.assertEquals(
				driver.findElement(By.xpath("//td[text()='Customer Name']/ following-sibling ::td ")).getText(), name);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Gender']/ following-sibling ::td ")).getText(),
				gender);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/ following-sibling ::td ")).getText(),
				dateOfBirth);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/ following-sibling ::td ")).getText(),
				address);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/ following-sibling ::td ")).getText(),
				city);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/ following-sibling ::td ")).getText(),
				sate);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/ following-sibling ::td ")).getText(), pin);
		Assert.assertEquals(
				driver.findElement(By.xpath("//td[text()='Mobile No.']/ following-sibling ::td ")).getText(), phone);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/ following-sibling ::td ")).getText(),
				emailAddress);

		customerID = driver.findElement(By.xpath("//td[text()='Customer ID']/ following-sibling ::td ")).getText();

	}

	@Test
	public void TC_04_Edit_Customer() {

		driver.findElement(By.xpath("//a[text()='Edit Customer']")).click();

		driver.findElement(By.name("cusid")).sendKeys(customerID);
		driver.findElement(By.name("AccSubmit")).click();

		Assert.assertEquals(driver.findElement(By.xpath("//p[@class='heading3']")).getText(), "Edit Customer");
		Assert.assertEquals(driver.findElement(nameTextbox).getAttribute("value"), name);
		Assert.assertEquals(driver.findElement(By.xpath("//input[@name='gender']")).getAttribute("value"), gender);
		Assert.assertEquals(driver.findElement(dobTextbox).getAttribute("value"), dateOfBirth);
		Assert.assertEquals(driver.findElement(addressTextArea).getText(), address);
		Assert.assertEquals(driver.findElement(citynameTextbox).getAttribute("value"), city);
		Assert.assertEquals(driver.findElement(stateTextbox).getAttribute("value"), sate);
		Assert.assertEquals(driver.findElement(pinTextbox).getAttribute("value"), pin);
		Assert.assertEquals(driver.findElement(phoneTextbox).getAttribute("value"), phone);
		Assert.assertEquals(driver.findElement(emailTextbox).getAttribute("value"), emailAddress);

		

		driver.findElement(addressTextArea).clear();
		driver.findElement(addressTextArea).sendKeys(editAddress);
		driver.findElement(citynameTextbox).clear();
		driver.findElement(citynameTextbox).sendKeys(editCity);
		driver.findElement(stateTextbox).clear();
		driver.findElement(stateTextbox).sendKeys(editSate);
		driver.findElement(pinTextbox).clear();
		driver.findElement(pinTextbox).sendKeys(editPin);
		driver.findElement(phoneTextbox).clear();
		driver.findElement(phoneTextbox).sendKeys(editPhone);
		driver.findElement(emailTextbox).clear();
		driver.findElement(emailTextbox).sendKeys(editEmailAddress);
	
		driver.findElement(By.name("sub")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//p[@class='heading3']")).getText(),
				"Customer details updated Successfully!!!");
		Assert.assertEquals(
				driver.findElement(By.xpath("//td[text()='Customer Name']/ following-sibling ::td ")).getText(), name);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Gender']/ following-sibling ::td ")).getText(),
				gender);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/ following-sibling ::td ")).getText(),
				dateOfBirth);
		
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/ following-sibling ::td ")).getText(),
				editAddress);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/ following-sibling ::td ")).getText(),
				editCity);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/ following-sibling ::td ")).getText(),
				editSate);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/ following-sibling ::td ")).getText(), editPin);
		Assert.assertEquals(
				driver.findElement(By.xpath("//td[text()='Mobile No.']/ following-sibling ::td ")).getText(), editPhone);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/ following-sibling ::td ")).getText(),
				editEmailAddress);
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

	public String getRandomEmail() {
		Random rand = new Random();
		return "Johndeep" + rand.nextInt(99999) + "@gmail.com";
	}

}
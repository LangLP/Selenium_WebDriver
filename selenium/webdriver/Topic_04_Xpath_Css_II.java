package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Topic_04_Xpath_Css_II {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String name, emailAddress, password, phone;
//action
	By nameTextboxBy = By.id("txtFirstname");
	By emailTextboxBy = By.id("txtEmail");
	By confirmEmailTextboxBy = By.id("txtCEmail");
	By passwordTextboxBy = By.id("txtPassword");
	By confirpasswordTextboxBy = By.id("txtCPassword");
	By phoneTextboxBy = By.id("txtPhone");
	By registerButtonBy = By.xpath("//form[@id='frmLogin']//button");
//error

	By nameErrorMsBy = By.id("txtFirstname-error");
	By emailErrorMsBy = By.id("txtEmail-error");
	By confirmEmailErrorMsBy = By.id("txtCEmail-error");
	By passwordErrorMsBy = By.id("txtPassword-error");
	By confirpasswordErrorMsBy = By.id("txtCPassword-error");
	By phoneErrorMsBy = By.id("txtPhone-error");

	@BeforeClass
	public void beforeClass() {
		System.out.println(projectPath);
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		name = "John Wich";
		emailAddress = "automation@gmail.com";
		password = "123456";
		phone = "0987654321";

	}

	@BeforeMethod
	public void beforMethod() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
	}

	@Test
	public void TC_01_Emty() {
		driver.findElement(registerButtonBy).click();
		Assert.assertEquals(driver.findElement(nameErrorMsBy).getText(), "Vui lòng nhập họ tên");
		Assert.assertEquals(driver.findElement(emailErrorMsBy).getText(), "Vui lòng nhập email");
		Assert.assertEquals(driver.findElement(confirmEmailErrorMsBy).getText(), "Vui lòng nhập lại địa chỉ email");
		Assert.assertEquals(driver.findElement(passwordErrorMsBy).getText(), "Vui lòng nhập mật khẩu");
		Assert.assertEquals(driver.findElement(confirpasswordErrorMsBy).getText(), "Vui lòng nhập lại mật khẩu");
		Assert.assertEquals(driver.findElement(phoneErrorMsBy).getText(), "Vui lòng nhập số điện thoại.");

	}

	@Test
	public void TC_02_Login_Invalid_Email() {

		// Nhập sai định dạng mail
		driver.findElement(nameTextboxBy).sendKeys(name);
		driver.findElement(emailTextboxBy).sendKeys("123@123.234@");
		driver.findElement(confirmEmailTextboxBy).sendKeys("123@123.234@");
		driver.findElement(passwordTextboxBy).sendKeys(password);
		driver.findElement(confirpasswordTextboxBy).sendKeys(password);
		driver.findElement(phoneTextboxBy).sendKeys(phone);
		
		driver.findElement(registerButtonBy).click();
		Assert.assertEquals(driver.findElement(emailErrorMsBy).getText(), "Vui lòng nhập email hợp lệ");
		Assert.assertEquals(driver.findElement(confirmEmailErrorMsBy).getText(), "Email nhập lại không đúng");

	}

	@Test
	public void TC_03_Login_Confirm_Email() {
		// confirm email sai
		driver.findElement(nameTextboxBy).sendKeys(name);
		driver.findElement(emailTextboxBy).sendKeys(emailAddress);
		driver.findElement(confirmEmailTextboxBy).sendKeys("automation@gmail.vn");
		driver.findElement(passwordTextboxBy).sendKeys(password);
		driver.findElement(confirpasswordTextboxBy).sendKeys(password);
		driver.findElement(phoneTextboxBy).sendKeys(phone);
		driver.findElement(registerButtonBy).click();
		Assert.assertEquals(driver.findElement(confirmEmailErrorMsBy).getText(), "Email nhập lại không đúng");
	}

	@Test
	public void TC_04_Less_Than_6_Characters() {
		driver.findElement(nameTextboxBy).sendKeys(name);
		driver.findElement(emailTextboxBy).sendKeys(emailAddress);
		driver.findElement(confirmEmailTextboxBy).sendKeys(emailAddress);
		driver.findElement(passwordTextboxBy).sendKeys("123");
		driver.findElement(confirpasswordTextboxBy).sendKeys("123");
		driver.findElement(phoneTextboxBy).sendKeys(phone);
		driver.findElement(registerButtonBy).click();
		Assert.assertEquals(driver.findElement(passwordErrorMsBy).getText(), "Mật khẩu phải có ít nhất 6 ký tự");
		Assert.assertEquals(driver.findElement(confirpasswordErrorMsBy).getText(), "Mật khẩu phải có ít nhất 6 ký tự");
	}

	public void TC_05_Confirm_Password() {
		driver.findElement(nameTextboxBy).sendKeys(name);
		driver.findElement(emailTextboxBy).sendKeys(emailAddress);
		driver.findElement(confirmEmailTextboxBy).sendKeys(emailAddress);
		driver.findElement(passwordTextboxBy).sendKeys(password);
		driver.findElement(confirpasswordTextboxBy).sendKeys("123321");
		driver.findElement(phoneTextboxBy).sendKeys(phone);

		driver.findElement(registerButtonBy).click();
		Assert.assertEquals(driver.findElement(confirpasswordErrorMsBy).getText(), "Mật khẩu bạn nhập  không  khớp");
	}

	public void TC_06_Invalid_Phone() {
		driver.findElement(nameTextboxBy).sendKeys(name);
		driver.findElement(emailTextboxBy).sendKeys(emailAddress);
		driver.findElement(confirmEmailTextboxBy).sendKeys(emailAddress);
		driver.findElement(passwordTextboxBy).sendKeys(password);
		driver.findElement(confirpasswordTextboxBy).sendKeys(password);
		driver.findElement(phoneTextboxBy).sendKeys(emailAddress);
		driver.findElement(registerButtonBy).click();
		Assert.assertEquals(driver.findElement(phoneErrorMsBy).getText(), "Vui lòng nhập con số ");
		
		// clear dữ liệu đang nhập đi
		driver.findElement(phoneTextboxBy).clear();
		driver.findElement(phoneTextboxBy).sendKeys("0987654");
		driver.findElement(registerButtonBy).click();
		Assert.assertEquals(driver.findElement(phoneErrorMsBy).getText(), "Số điện thoại phải từ 10-11 số.");
		
		// clear dữ liệu đang nhập đi
		driver.findElement(phoneTextboxBy).clear();
		driver.findElement(phoneTextboxBy).sendKeys("098765432112365");
		driver.findElement(registerButtonBy).click();
		Assert.assertEquals(driver.findElement(phoneErrorMsBy).getText(), "Số điện thoại phải từ 10-11 số.");
		
		// clear dữ liệu đang nhập đi
		driver.findElement(phoneTextboxBy).clear();
		driver.findElement(phoneTextboxBy).sendKeys("12365432112365");
		driver.findElement(registerButtonBy).click();
		Assert.assertEquals(driver.findElement(phoneErrorMsBy).getText(),
				"Số điện thoại bắt đầu bằng: 09 - 03 - 012 - 016 - 018 - 019");

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}

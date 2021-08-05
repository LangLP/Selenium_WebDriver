package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
//import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_Custom_BrowsersII {
	WebDriver driver;
	WebDriverWait expliciWait;
	JavascriptExecutor jsExecuter;

	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.out.println(projectPath);
		// FireFox
		// System.setProperty("webdriver.gecko.driver",projectPath
		// +"\\browserDrivers\\geckodriver.exe");
		// driver = new FirefoxDriver();

		// Chrom
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();

		expliciWait = new WebDriverWait(driver, 15);
		jsExecuter = (JavascriptExecutor) driver;

		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	// @Test
	public void TC_01_JQuery() {
		//
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
		slectItemInCustomDropdown("//span[@id='number-button']", "//ul[@id='number-menu']//div", "8");
		sleepInSecond(2);
		Assert.assertTrue(
				driver.findElement(By.xpath("//span[@class='ui-selectmenu-text'and text()='8']")).isDisplayed());

		slectItemInCustomDropdown("//span[@id='number-button']", "//ul[@id='number-menu']//div", "16");
		sleepInSecond(2);
		Assert.assertTrue(
				driver.findElement(By.xpath("//span[@class='ui-selectmenu-text'and text()='16']")).isDisplayed());

		slectItemInCustomDropdown("//span[@id='number-button']", "//ul[@id='number-menu']//div", "3");
		sleepInSecond(2);
		Assert.assertTrue(
				driver.findElement(By.xpath("//span[@class='ui-selectmenu-text'and text()='3']")).isDisplayed());
	}

	// @Test
	public void TC_02_ReactJS() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");

		slectItemInCustomDropdown("//i[@class='dropdown icon']", "//div[@role='option']//span", "Jenny Hess");
		sleepInSecond(2);
		Assert.assertTrue(driver
				.findElement(
						By.xpath("//div[contains(@class,'select')]//div[@class='divider text'and text()='Jenny Hess']"))
				.isDisplayed());

		slectItemInCustomDropdown("//i[@class='dropdown icon']", "//div[@role='option']//span", "Elliot Fu");
		sleepInSecond(2);
		Assert.assertTrue(driver
				.findElement(
						By.xpath("//div[contains(@class,'select')]//div[@class='divider text'and text()='Elliot Fu']"))
				.isDisplayed());

		slectItemInCustomDropdown("//i[@class='dropdown icon']", "//div[@role='option']//span", "Matt");
		sleepInSecond(2);
		Assert.assertTrue(driver
				.findElement(By.xpath("//div[contains(@class,'select')]//div[@class='divider text'and text()='Matt']"))
				.isDisplayed());

	}

//Test
	public void TC_03_VueJS() {
		//
		driver.get("https://mikerodham.github.io/vue-dropdowns/");

		slectItemInCustomDropdown("//li[@class='dropdown-toggle']", "//ul[@class='dropdown-menu']//a", "Second Option");
		sleepInSecond(2);
		Assert.assertTrue(
				driver.findElement(By.xpath("//li[@class='dropdown-toggle' and contains(text(),'Second Option')]"))
						.isDisplayed());

		slectItemInCustomDropdown("//li[@class='dropdown-toggle']", "//ul[@class='dropdown-menu']//a", "Third Option");
		sleepInSecond(2);
		Assert.assertTrue(
				driver.findElement(By.xpath("//li[@class='dropdown-toggle' and contains(text(),'Third Option')]"))
						.isDisplayed());

		slectItemInCustomDropdown("//li[@class='dropdown-toggle']", "//ul[@class='dropdown-menu']//a", "First Option");
		sleepInSecond(2);
		Assert.assertTrue(
				driver.findElement(By.xpath("//li[@class='dropdown-toggle' and contains(text(),'First Option')]"))
						.isDisplayed());
	}

	// @Test
	public void TC_04_Default() {
		driver.get("https://demo.nopcommerce.com/register?returnUrl=%2F");

		slectItemInCustomDropdown("//select[@name='DateOfBirthDay']", "//select[@name='DateOfBirthDay']//option", "6");
		sleepInSecond(2);
		Assert.assertTrue(
				driver.findElement(By.xpath("//select[@name='DateOfBirthDay']//option[text()='6']")).isSelected());

		slectItemInCustomDropdown("//select[@name='DateOfBirthDay']", "//select[@name='DateOfBirthDay']//option", "20");
		sleepInSecond(2);
		Assert.assertTrue(
				driver.findElement(By.xpath("//select[@name='DateOfBirthDay']//option[text()='20']")).isSelected());

		slectItemInCustomDropdown("//select[@name='DateOfBirthDay']", "//select[@name='DateOfBirthDay']//option", "30");
		sleepInSecond(2);
		Assert.assertTrue(
				driver.findElement(By.xpath("//select[@name='DateOfBirthDay']//option[text()='30']")).isSelected());

	}

	// @Test
	public void TC_04_Angular() {
		driver.get(
				"https://ej2.syncfusion.com/angular/demos/?_ga=2.262049992.437420821.1575083417-524628264.1575083417#/material/drop-down-list/data-binding");

		slectItemInCustomDropdown("//ejs-dropdownlist[@id='games']//span[contains(@class,'e-search-icon')]",
				"//ul[@id='games_options']/li", "Basketball");
		sleepInSecond(2);
		Assert.assertEquals(
				driver.findElement(By.xpath("//ejs-dropdownlist[@id='games']//input")).getAttribute("aria-label"),
				"Basketball");

		slectItemInCustomDropdown("//ejs-dropdownlist[@id='games']//span[contains(@class,'e-search-icon')]",
				"//ul[@id='games_options']/li", "Badminton");
		sleepInSecond(2);
		Assert.assertEquals(
				driver.findElement(By.xpath("//ejs-dropdownlist[@id='games']//input")).getAttribute("aria-label"),
				"Badminton");

		slectItemInCustomDropdown("//ejs-dropdownlist[@id='games']//span[contains(@class,'e-search-icon')]",
				"//ul[@id='games_options']/li", "Golf");
		sleepInSecond(2);
		Assert.assertEquals(
				driver.findElement(By.xpath("//ejs-dropdownlist[@id='games']//input")).getAttribute("aria-label"),
				"Golf");

	}

	// @Test
	public void TC_06_Editable() {
		//
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
		slectItemInCustomDropdown("//i[@class='dropdown icon']", "//div[@role='option']/span", "Albania");
		sleepInSecond(2);
		Assert.assertTrue(driver
				.findElement(
						By.xpath("//div[contains(@class,'select')]//div[@class='divider text'and text()='Albania']"))
				.isDisplayed());

		slectItemInCustomDropdown("//i[@class='dropdown icon']", "//div[@role='option']/span", "Aruba");
		sleepInSecond(2);
		Assert.assertTrue(driver
				.findElement(By.xpath("//div[contains(@class,'select')]//div[@class='divider text'and text()='Aruba']"))
				.isDisplayed());

	}

	// @Test
	public void TC_07_tabEditable() {
		//
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");

		TabItemInCustomDropdown("//div[@role='combobox']/input", "Albania");
		sleepInSecond(2);
		Assert.assertTrue(driver
				.findElement(
						By.xpath("//div[contains(@class,'select')]//div[@class='divider text'and text()='Albania']"))
				.isDisplayed());

		TabItemInCustomDropdown("//div[@role='combobox']/input", "Aruba");
		sleepInSecond(2);
		Assert.assertTrue(driver
				.findElement(By.xpath("//div[contains(@class,'select')]//div[@class='divider text'and text()='Aruba']"))
				.isDisplayed());

	}
	@Test
	public void TC_08_Multiple_Select() {
		//
		driver.get("https://multiple-select.wenzhixin.net.cn/templates/template.html?v=189&url=basic.html");

		String[] lessMonth = { "April", "August", "September" };
		String[] moreMonth = { "April", "August", "September", "October", };
		mutilpleSlectItemInCustomDropdown("(//button[@class='ms-choice'])[1]",
				"(//button[@class='ms-choice'])[1]/following-sibling::div//span", lessMonth);
		sleepInSecond(1);
		Assert.assertTrue(areItemSelected(lessMonth));
		
		driver.navigate().refresh();
		mutilpleSlectItemInCustomDropdown("(//button[@class='ms-choice'])[1]",
				"(//button[@class='ms-choice'])[1]/following-sibling::div//span", moreMonth);
		sleepInSecond(1);
		Assert.assertTrue(areItemSelected(moreMonth));

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public void slectItemInCustomDropdown(String parentXpath, String ChildXpath, String expectedTextItem) {

		// click vào các thẻ cha cho nó xổ các item con bên trong
		driver.findElement(By.xpath(parentXpath)).click();
		sleepInSecond(1);

		// chờ cho tất cả các item được load ra hết (WEeddriverWait)
		List<WebElement> allItem = expliciWait
				.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(ChildXpath)));
		System.out.println("Item size =" + allItem.size());
		// nếu muốn chọn 1 item nào đó ->gettext của Item đó ra ss với text mình mong
		// muốn
		// Nếu như bằng với cái mình mm thì Scroll xuống và click vào ->thoát khỏi vòng
		// lặp
		// Nếu như chưa bằng thì duyệt cái Item tiếp theo

		for (WebElement item : allItem) {
			System.out.println(item.getText());
			if (item.getText().trim().equals(expectedTextItem)) {
				jsExecuter.executeScript("arguments[0].scrollIntoView(true);", item);
				sleepInSecond(1);
				item.click();
				break;
			}

		}

	}

	public void TabItemInCustomDropdown(String parentXpath, String expectedTextItem) {

		// click vào các thẻ cha cho nó xổ các item con bên trong
		driver.findElement(By.xpath(parentXpath)).click();
		sleepInSecond(1);

		driver.findElement(By.xpath(parentXpath)).sendKeys(expectedTextItem);
		sleepInSecond(1);

		driver.findElement(By.xpath(parentXpath)).sendKeys(Keys.TAB);

	}

	public void mutilpleSlectItemInCustomDropdown(String parentXpath, String ChildXpath, String[] expectedValueItem) {

		// click vào các thẻ cha cho nó xổ các item con bên trong
		driver.findElement(By.xpath(parentXpath)).click();
		sleepInSecond(1);

		// chờ cho tất cả các giá trị trong dropdown được load hết ra thành công
		List<WebElement> allItem = expliciWait
				.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(ChildXpath)));
		// duyệt qua hết tất cả các phần tử cho đến khi thỏa mãn điều kiện
		for (WebElement ChildElement : allItem) {
			// January,April,Jully
			for (String item : expectedValueItem) {
				if (ChildElement.getText().equals(item)) {
					// scroll đến item cần chọn nếu như item cần chọn có thể nhìn thấy thì k cần
					// scroll
					jsExecuter.executeScript("arguments[0].scrollIntoView(true);", ChildElement);
					sleepInSecond(1);
					// click vào item cần chọn
					ChildElement.click();
					sleepInSecond(1);

					List<WebElement> itemSelected = driver.findElements(By.xpath("//li[@class='selected']//input"));
					System.out.println("Item selected=" + itemSelected.size());
					if (expectedValueItem.length == itemSelected.size()) {
						break;
					}
				}
			}
		}

	}

	public boolean areItemSelected(String[] expectedValueItem) {
		List<WebElement> itemSelected = driver.findElements(By.xpath("//li[@class='selected']//input"));
		int numberItemSelected = itemSelected.size();
		String allItemSelected = driver.findElement(By.xpath("//button[@class='ms-choice']/span[1]")).getText();
		if (numberItemSelected <= 3 && numberItemSelected > 0) {
			boolean status = true;
			for (String item : expectedValueItem) {
				if (!allItemSelected.contains(item)) {
					status = false;
					return status;

				}
			}
			return status;
		} else if (numberItemSelected >= 12) {
			return driver.findElement(By.xpath("//button[@class='ms-choice']/span[text()='All selected']"))
					.isDisplayed();

		} else if (numberItemSelected > 3 && numberItemSelected < 12) {
			return driver
					.findElement(By.xpath(
							"//button[@class='ms-choice']/span[text()='" + numberItemSelected + " of 12 selected']"))
					.isDisplayed();
		} else {
			return false;
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
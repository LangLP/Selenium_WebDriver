package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_16_Wait_findElement {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		// FireFox
		System.out.println(projectPath);
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();

		// Chrom
		// System.out.println(projectPath);
		// System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		// driver = new ChromeDriver();

		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_FindElemnet() {
		driver.get("http://demo.guru99.com/v4/");
		// 1-Có duy nhất 1 element
		// -k cần chờ hết timeout của implicit
		// -tương tác lên element luôn

		// 2. -Ko có element nào hết
		// -chờ hết timeout của implicit
		// -trong thời gian chờ cứ mỗi 0,5s sẽ tìm lại 1 lần
		// -Khi nào chờ hết timeout của implicit thì đánh fail testcase và throw exception:NoSuchElementException

		// 3, có nhiều hơn 1 elemnet(>=2)
		// -Ko cần chờ hết timeout của implicit
		// -Nó sẽ lấy cái element đàu tiên để tương tác
		// - Ko quan tâm có bao nhiêu matching node
	}

	@Test
	public void TC_02_FindElements() {
		driver.navigate().refresh();

		// 1-Có duy nhất 1 element
		// -k cần chờ hết timeout của implicit
		// -tương tác lên element luôn

		// 2. -Ko có element nào hết-> Cần test 1 element k xuất hiện trên UI và k có trong DOM
		// -chờ hết timeout của implicit
		// -trong thời gian chờ cứ mỗi 0,5s sẽ tìm lại 1 lần
		// -Khi nào chờ hết timeout của implicit thì đánh fail testcase
		// -Trả về 1 list emty[rỗng/ ko có phần tử(web element)] nào hết
		// chuyển qua step tiếp theo

		// 3, có nhiều hơn 1 elemnet(>=2)
		// -Ko cần chờ hết timeout của implicit
		// -Lưu hết tất cả các element vào trong list
	}

	@Test
	public void TC_03_() {

	}

	@Test
	public void TC_04_() {

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
package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_14_Upload_Senkeys {
	WebDriver driver;
	String projectPath = System.getProperty ("user.dir");
	String googleFileName ="Google.png";
	String facebookFileName ="Facebook.png";
	String amazonFileName ="Amazon.png";
	String googleFilePath =projectPath +"\\uploadFile\\"+googleFileName;
	String facebookFilePath =projectPath +"\\uploadFile\\"+facebookFileName;
	String amazonFilePath =projectPath +"\\uploadFile\\"+amazonFileName;
	
	@BeforeClass
	public void beforeClass() {
		//FireFox
		System.out.println(projectPath);
		System.setProperty("webdriver.gecko.driver",projectPath +"\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		
		//Chrom
		//System.out.println(projectPath);
		//System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		//driver = new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_SenKeys_One_File() {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
        //Load file ko cần bật Open File Dialog
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(googleFilePath);
		//verify filr  loaded succes
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()= 'Google.png']")).isDisplayed());
		//Click Start upload button
		driver.findElement(By.cssSelector("table .start")).click();
		//verify file upload succes
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='Google.png']")).isDisplayed());
		
	
	}

	@Test
	public void TC_02_SenKeys_Multiple_File() {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
        //Load file ko cần bật Open File Dialog
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(googleFilePath +"\n"+facebookFilePath +"\n"+amazonFilePath );
		//verify filr  loaded succes
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()= '"+ googleFileName +"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()= '" + facebookFileName +"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()= '" + amazonFileName +"']")).isDisplayed());
		//Click Start upload button
		List<WebElement>StartUploadButtons=driver.findElements(By.cssSelector("table .start"));
		for (WebElement StartButtons:StartUploadButtons) {
			StartButtons.click();
			sleepInSecond(2);
		
		}
		
		//verify file upload succes
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + googleFileName + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + facebookFileName + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + amazonFileName + "']")).isDisplayed());
		
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
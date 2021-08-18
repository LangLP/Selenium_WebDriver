package webdriver;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_14_Upload_File_II {
	WebDriver driver;
	WebDriverWait explicitWait;
	
	String projectPath = System.getProperty ("user.dir");
	String googleFileName ="Google.png";
	String facebookFileName ="Facebook.png";
	String amazonFileName ="Amazon.png";
	String googleFilePath =projectPath +"\\uploadFile\\"+googleFileName;
	String facebookFilePath =projectPath +"\\uploadFile\\"+facebookFileName;
	String amazonFilePath =projectPath +"\\uploadFile\\"+amazonFileName;
	String firefoxOneTimeAutoIt =projectPath +"\\AutoIt\\firefoxUploadOneTime.exe";
	String firefoxMuntipleTimeAutoIt =projectPath +"\\AutoIt\\firefoxUploadMultiple.exe";
	String chromeMuntipleTimeAutoIt =projectPath +"\\AutoIt\\chromeUploadMultiple.exe";
	
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
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		explicitWait = new WebDriverWait(driver,10);
		driver.manage().window().maximize();
	}

	//@Test
	public void TC_01_One_File() throws IOException {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
      //bật open File Dialog
		driver.findElement(By.cssSelector(".btn-success")).click();
		Runtime.getRuntime().exec(new String[] {firefoxOneTimeAutoIt,googleFilePath});
		
		//verify filr  loaded succes
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()= '"+ googleFileName +"']")).isDisplayed());
		//Click Start upload button
		driver.findElement(By.cssSelector("table .start")).click();
		//verify file upload succes
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()= '"+ googleFileName +"']")).isDisplayed());
		
	
	}

	//@Test
	public void TC_02_Multiple_File() throws IOException {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		
		driver.findElement(By.cssSelector(".btn-success")).click();
		
		if(driver.toString().contains("chrome") ||driver.toString().contains("ege")) {
		Runtime.getRuntime().exec(new String[] {chromeMuntipleTimeAutoIt,googleFilePath,amazonFilePath,facebookFilePath});
		}else if(driver.toString().contains("firefox")) {
		Runtime.getRuntime().exec(new String[] {firefoxMuntipleTimeAutoIt,googleFilePath,amazonFilePath,facebookFilePath});

		}
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
	public void TC_03_Upload_Flow() {
		
		driver.get("https://gofile.io/?t=uploadFiles");

		String parentID = driver.getWindowHandle();
		
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(googleFilePath +"\n"+facebookFilePath +"\n"+amazonFilePath );
		//driver.findElement(By.xpath("//button[normalize-space()='Add files']")).click();
		
		
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#rowUploadSuccess-downloadPage")));
		Assert.assertTrue(driver.findElement(By.cssSelector("#rowUploadSuccess-downloadPage")).isDisplayed());
		driver.findElement(By.cssSelector("#rowUploadSuccess-downloadPage")).click();

		switchtoWindowByID(parentID);
		
		//explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Google.png']/parent::a/parent::div/following-sibling::div//div[text()='0 downloads']")));

		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='" + googleFileName + "']/parent::a/parent::div/following-sibling::div//div[text()='0 downloads']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='" + facebookFileName + "']/parent::a/parent::div/following-sibling::div//div[text()='0 downloads']")).isDisplayed());
		//Assert.assertTrue(driver.findElement(By.xpath("//span[text()='" + amazonFileName + "']/parent::a/parent::div/following-sibling::div//div[text()='0 downloads']")).isDisplayed());
		
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='" + googleFileName + "']/parent::a/parent::div/following-sibling::div//div[@class='col-sm-2 text-center text-sm-right']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='" + facebookFileName + "']/parent::a/parent::div/following-sibling::div//div[@class='col-sm-2 text-center text-sm-right']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='" + amazonFileName + "']/parent::a/parent::div/following-sibling::div//div[@class='col-sm-2 text-center text-sm-right']")).isDisplayed());
		
		
		
		
	}
	

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	public  void switchtoWindowByID(String windowID ) {
	//get  hết  các id đang có
		Set<String > allTabIDs =driver.getWindowHandles();
		//
		//duyêt qua các giá  trị  trong  tab window
		for(String id : allTabIDs) {
			//kiểm  tra điều  kiện  nếu như khác với  WindownID truyền vào thì sưitch
			if(!id.equals(windowID)) {
				driver.switchTo().window(id);
				//thoát  khỏi vòng lặp 
				break;
				
			}
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

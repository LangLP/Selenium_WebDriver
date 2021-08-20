package webdriver;

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

public class Topic_16_Wait_Explicit {
	WebDriver driver;
	WebDriverWait explicitWait;
	String projectPath = System.getProperty ("user.dir");
	By startButton = By.cssSelector("#start>button");
	By helloworldText = By.xpath("//h4[text()='Hello World!']");
	By loadingicon = By.cssSelector("div#loading");
	
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
		//System.setProperty("webdriver.gecko.driver",projectPath +"\\browserDrivers\\geckodriver.exe");
		//driver = new FirefoxDriver();
		
		//Chrom
		//System.out.println(projectPath);
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		explicitWait = new WebDriverWait(driver,30);
		
		driver.manage().window().maximize();
	}

	//@Test
	public void TC_01_Visible() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
        
		driver.findElement(startButton).click();
		
		
	//chờ  cho  hello word text  được  hiển thị
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(helloworldText));
		Assert.assertTrue(driver.findElement(helloworldText).isDisplayed());
	
	}

	//@Test
	public void TC_02_Invisible() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		driver.findElement(startButton).click();
		
		//chờ cho  loading icon  bị  mất đi
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(loadingicon));
		
		Assert.assertTrue(driver.findElement(helloworldText).isDisplayed());
	
	}

	//@Test
	public void TC_03_Ajax_Loading() {
		
		driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
		//Wait  chờ  cho  Date Picker hiển thị
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ctl00_ContentPlaceholder1_Panel1")));
		Assert.assertEquals(driver.findElement(By.id("ctl00_ContentPlaceholder1_ctl00_ContentPlaceholder1_Label1Panel")).getText(),"No Selected Dates to display.");
		
		//click  vào today
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//td/a[text()='25']")));
		driver.findElement(By.xpath("//td/a[text()='25']")).click();
		
		//Wait cho ajax loading biến  mất
		
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//body/div[@class='RadAjax RadAjax_Silk']")));
		//Verify today được selected
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//td[@class='rcSelected']/a[text()='25']")));
		Assert.assertTrue(driver.findElement(By.xpath("//td[@class='rcSelected']/a[text()='25']")).isDisplayed());
		

		//Verify today được update lên "selected date"
		Assert.assertEquals(driver.findElement(By.cssSelector("#ctl00_ContentPlaceholder1_ctl00_ContentPlaceholder1_Label1Panel")).getText(),"Wednesday, August 25, 2021");

		
	}
	@Test
	public void TC_04_Upload_file() {
		driver.get("https://gofile.io/?t=uploadFiles");
		//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		//String parentID = driver.getWindowHandle();
		//Wait choose  server  icon invisible 
		//explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("animation__wobble")));
		//explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("fas fa-spinner fa-spin fa-2x mr-1")));
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button.uploadButton")));
		
		//Assert.assertTrue(driver.findElement(By.cssSelector("mb-1 uploadButton")).isDisplayed());

        //Load file ko cần bật Open File Dialog
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(googleFilePath +"\n"+facebookFilePath );
		//driver.findElement(By.xpath("//button[normalize-space()='Add files']")).click();
		
		
		
		
		//wait  for  file  loaded success
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[(@class='col-md-6 text-truncate') and text()= '"+ googleFileName +"']")));
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[(@class='col-md-6 text-truncate')and text()= '"+ facebookFileName +"']")));

		//wait progress  bar icon  invisible
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.xpath("//div[@role='progressbar']"))));
		
		//wait profilepage invisible
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div/i[@class='fas fa-spinner fa-spin']")));

		//wait success  message displayed
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h5[text()='Your files have been successfully uploaded']")));
		Assert.assertTrue(driver.findElement(By.xpath("//h5[text()='Your files have been successfully uploaded']")).isDisplayed());
		
		//wait  show file button clickShowfile
		//explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button#rowUploadSuccess-showFiles")));
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@id='rowUploadSuccess-downloadPage']")));
		//Assert.assertTrue(driver.findElement(By.xpath("//a[@id='rowUploadSuccess-downloadPage']")).isDisplayed());

		driver.findElement(By.cssSelector("button#rowUploadSuccess-showFiles")).click();
		//switchtoWindowByID(parentID);
		
		//explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='contentName'] and text()= '"+ googleFileName +"']")));

		//verify file loaded succes
		Assert.assertTrue(driver.findElement(By.xpath("//span[@class='contentName' and text()= '"+ googleFileName +"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//span[@class='contentName' and text()= '" + facebookFileName +"']")).isDisplayed());
		
		//explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Google.png']/parent::a/parent::div/following-sibling::div//div[text()='0 downloads']")));

		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='" + googleFileName + "']/parent::a/parent::div/following-sibling::div//div[text()='0 downloads']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='" + facebookFileName + "']/parent::a/parent::div/following-sibling::div//div[text()='0 downloads']")).isDisplayed());
		
	
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	

}
package webdriver;

import java.time.Duration;
//import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_16_Wait_fulen {
	WebDriver driver;
	WebDriverWait explicitWait;
	JavascriptExecutor jsExecutor;
	FluentWait<WebElement> fluentElemnet ;
	FluentWait<WebDriver> wait ;

	String projectPath = System.getProperty ("user.dir");
	
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
		
		driver.manage().window().maximize();
	}

	//@Test
	public void TC_01_FindElemnet() {
		driver.get("https://automationfc.github.io/fluent-wait/");
		 WebElement coutdownTimer = driver.findElement(By.id("javascript_countdown_time"));
		 fluentElemnet =new FluentWait<WebElement>( coutdownTimer );
		 fluentElemnet.withTimeout(Duration.ofSeconds(15))
		 .pollingEvery(Duration.ofSeconds(1))
		 .ignoring(NoSuchElementException.class)
		 .until(new  Function<WebElement,Boolean>(){
			 @Override
			 public Boolean apply(WebElement coutdown)
			 {
				boolean status = coutdown.getText().endsWith("00");
				System.out.println("Text= "+coutdown.getText() +"_------"+ status);
			    return status ;
			 }
			 });
		 }
	  

	//@Test
	public void TC_02_FindElements() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		 ClickToElement(By.xpath("//div[@id='start']/button"));
	     Assert.assertTrue(isElementDisplayed(By.xpath("//div[@id='finish']/h4")));
	     Assert.assertEquals(getElement(By.xpath("//div[@id='finish']/h4")).getText(),"Hello World!");
	     
	 
	 
	}

	@Test
	public void TC_03_Icon_Loading_Success() {
		driver.get("https://opensource-demo.orangehrmlive.com/");
	    
		driver.findElement(By.cssSelector("input#txtUsername")).sendKeys("Admin");
		driver.findElement(By.cssSelector("input#txtPassword")).sendKeys("admin123");
	    driver.findElement(By.cssSelector("input#btnLogin")).click();
        
	    Assert.assertTrue(isJQueryLoadedSuccess(driver));
	    
	    Assert.assertEquals(driver.findElement(By.xpath("//div[@id='total']//span")).getText(),"3 month(s)");
	     
	    driver.findElement(By.xpath("//a[@id='menu_pim_viewPimModule']")).click();
	    
	    Assert.assertTrue(isJQueryLoadedSuccess(driver));

	    driver.findElement(By.cssSelector("input#empsearch_employee_name_empName")).sendKeys("Peter Mac");
	    driver.findElement(By.cssSelector("input#searchBtn")).click();
	   
	    Assert.assertTrue(isJQueryLoadedSuccess(driver));

	    Assert.assertEquals(driver.findElement(By.xpath("//div[@id='tableWrapper']//tr/td[3]")).getText(),"Peter Mac");

	}
	

	

	public WebElement getElement(By locator) {
		FluentWait<WebDriver> wait =new FluentWait<WebDriver>(driver)
		.withTimeout(Duration.ofSeconds(timeout))
		.pollingEvery(Duration.ofSeconds(polling))
		.ignoring(NoSuchElementException.class);
		
		WebElement element = wait.until(new  Function<WebDriver,WebElement>(){
			
			public WebElement apply(WebDriver driver)
			{
				
				return driver.findElement(locator) ;
			}
		});
		return element ;
	}
	public void ClickToElement(By locator) {
		FluentWait<WebDriver> wait =new FluentWait<WebDriver>(driver) 
		.withTimeout(Duration.ofSeconds(timeout))
		.pollingEvery(Duration.ofSeconds(polling))
		.ignoring(NoSuchElementException.class);
		WebElement element = wait.until(new  Function<WebDriver,WebElement>(){
			
			public WebElement apply(WebDriver driver)
			{
				
				return driver.findElement(locator) ;
			}
		});
		element.click();
	}
	

	public boolean isElementDisplayed (By locator) {
	FluentWait<WebDriver> wait =new FluentWait<WebDriver>(driver) 
		 .withTimeout(Duration.ofSeconds(timeout))
		 .pollingEvery(Duration.ofSeconds(polling))
		 .ignoring(NoSuchElementException.class);
		Boolean status = wait.until(new  Function<WebDriver,Boolean>(){
		
			 public Boolean apply(WebDriver driver)
			 {
				
			    return driver.findElement(locator).isDisplayed() ;
			 }
			 });
		 return status ;
		 }
	
   public  boolean isJQueryLoadedSuccess(WebDriver driver ) {
	   explicitWait = new WebDriverWait (driver ,timeout);
	   jsExecutor = (JavascriptExecutor) driver;
	   
	   ExpectedCondition<Boolean> jQueryLoad = new  ExpectedCondition<Boolean>(){   
		   @Override
			 public Boolean apply(WebDriver driver)
			 {
			 return (Boolean) jsExecutor.executeScript("return (window.jQuery != null)&& (jQuery.active ===0);");
			 }
	   };
		   return explicitWait.until(jQueryLoad);
			
	
   }
	@AfterClass
	public void afterClass() {
		driver.quit();	
		}
	long timeout =15;
	long polling =1;
	
}
package automationMobile;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class sortByPrice {

	public static WebDriverWait wait;
	public static WebDriver driver;

	@BeforeClass
	public static void setUp() 
	{
	System.setProperty("webdriver.chrome.driver","C:\\Users\\jucas.oliveira\\Documents\\chromedriver\\chromedriver.exe"); 
	driver = new ChromeDriver();            
	}
	
	@AfterClass
	public static void tearDown() 
	{
	driver.quit();
	}

	@Test
	public void testMethod() throws InterruptedException
	{
		try{
		//Put a Implicit wait, this means that any search for elements on the page could take the time the implicit wait is set for before throwing exception
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
 
        //Launching the Website
 
        driver.get("http://t.hostelworld.com");
 
        // Inputing city name
 
        driver.findElement(By.id("home-search-keywords")).sendKeys("Dublin, Ireland"); 
 
         
        // Find and select the name of the city on autocomplete
 
        driver.findElement(By.xpath("//*[contains(@class,'suggestion needsclick')]")).click();
      
        // Find the button search
 
        driver.findElement (By.xpath("//*[@id='sb-search']/div[3]/div/button")).click();
        
        
        // Find element sort to select by name
        
        driver.findElement (By.xpath("//*[@id='pagebody']/div[1]/div/div/div[12]/div[3]/div/div/div/div/dl[1]/dd[2]")).click();
        
        // Select price
        driver.findElement (By.xpath("//*[@id='sortByPrice']")).click();
        
        
        //Verify numeric order
        List<WebElement> allItems = driver.findElements(By.xpath("//*[@class='dormroom']"));
        List<String> money = new ArrayList<String>();
        List<String> newMoney = new ArrayList<String>();
        
       // List<WebElement> allItems = driver.findElements(By.xpath("//*[@class='pricing no-bullet']"));
        
        //Fill String Arrays to make numeric comparison 
        for(int i=0;i<allItems.size();i++){
        	money.add(allItems.get(i).getText());
        	newMoney.add(allItems.get(i).getText());
        }
        
        //Sorting first array to check if is sorted
        String compare = "True";
        Collections.sort(money);
        for (int i=0;i<money.size();i++) {
        	//System.out.println(money.get(i));
            System.out.println("Hostel price from input: " + money.get(i)  +  "--Hostel Price from sorted input: " + newMoney.get(i));
            if (!(money.get(i).equals(newMoney.get(i)))) {
                            System.out.println("Hostel Prices aren't sorted");
                            compare = "False";
                            break;
            }
        }
        
      	if(compare.equals("True")){
            System.out.println("Test completed succesfully with prices ordered correctly");
           /*
            for (int i=0;i<money.size();i++) {
    			System.out.println(money.get(i));
    			
            }
            */
      	}
        
        System.out.println("Test Complete");

		}catch (NoSuchElementException e) {
		
		}
	}     
}

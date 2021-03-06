package automationMobile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class sortByName {
	public static WebDriverWait wait;
	public static WebDriver driver;

	@BeforeClass
	public static void setUp() 
	{
	System.setProperty("webdriver.chrome.driver","C:\\Users\\jucas.oliveira\\Documents\\chromedriver\\chromedriver.exe"); 

	driver = new ChromeDriver();            
	}
	//Method created to eliminate case sensitive
	private static String normalize(String word) {
	    return word.toLowerCase().replaceAll("[^a-z0-9]", "");
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
        
        // Select name
        System.out.println("Sorting by name");
        driver.findElement (By.xpath("//*[@id='sortByName']")).click();
        
        //Verify alfabetic order
        //Webelement to retrieve all information
        List<WebElement> elements = driver.findElements(By.xpath("//*[@class='hwta-property-link']"));
        
        //Run the list and remove undesirable texts 
        for(int i=0;i<elements.size();i++){
        
	        //Remove undesirable strings
	        if(elements.get(i).getText().equals("Privates From")){
	        	elements.remove(i);
	        }
        }
        for(int i=0;i<elements.size();i++){
	        
	        //Remove undesirable strings
	        if(elements.get(i).getText().equals("Dorms From")){
	        	elements.remove(i);
	        }
        }
        
        
        //Create Array Strings to compare sorted list
        List<String> allItems = new ArrayList<String>();
        List<String> newItems = new ArrayList<String>();

        for(int i=0;i<elements.size();i++){
        	//Capture the hostel name values
        	if(!normalize(elements.get(i).getText()).equals("")){
        		//System.out.println(elements.get(i).getText());
        		allItems.add(normalize(elements.get(i).getText()));
        		newItems.add(normalize(elements.get(i).getText()));
        		
        	}
        }
        
        String compare = "True";
        Collections.sort(allItems);   
        for (int i=0;i<allItems.size();i++) {
           // System.out.println("Customer Name from input: " + newItems.get(i)  +  "--Customer Name from sorted input: " + allItems.get(i));
            if (!(allItems.get(i).equals(newItems.get(i)))) {
                            System.out.println("Hostel Names aren't sorted");
                            compare = "False";
                            break;
            }
        }
		//Print names
      	if(compare.equals("True")){
        System.out.println("Test completed succesfully with hostels ordered correctly: ");
        for (int i=0;i<elements.size();i++) {
			System.out.println(elements.get(i).getText());
			
		}

		tearDown();
		}
		
		}catch (NoSuchElementException e){
			
		}
		
	}
	
}

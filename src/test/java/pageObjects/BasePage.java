package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

//Creating a BaseClass for initializing constructor because this code will be same for all the POC.
public class BasePage {
	
	WebDriver driver;
	
	BasePage(WebDriver driver)
	{
		
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	

}

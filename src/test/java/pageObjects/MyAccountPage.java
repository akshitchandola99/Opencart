package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage {

	public MyAccountPage(WebDriver driver)
	{
		super(driver);
	}
	
	//element
	@FindBy(xpath="//h2[normalize-space()='My Account']")
	WebElement msgHeading;
	
	@FindBy(xpath="(//a[normalize-space()='Logout'])[1]")
	WebElement bthLogout;
	
	@FindBy(xpath="//a[@title='My Account']")
	WebElement myAccount;
	
	
	//Action methods
	public boolean isMyAccountPageExists()
	{
		try 
		{
			return msgHeading.isDisplayed();
		}
		catch(Exception e)
		{
			return false;
		}
	}
	
	public void clickLogout()
	{
		myAccount.click();
		bthLogout.click();
	}
	

}

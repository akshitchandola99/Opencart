package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

//POC for HomePage
public class HomePage extends BasePage {
	

	//constructor
	public HomePage(WebDriver driver)
	{
		super(driver);
	}
	
	
	//Locating Elements
	@FindBy(xpath="//a[@title='My Account']")
	WebElement myAccount;
	
	@FindBy(xpath="//a[normalize-space()='Register']")
	WebElement Register;
	
	@FindBy(xpath="//a[normalize-space()='Login']")
	WebElement Login;
	
	
	//Actions methods
	
	public void clickMyAccount()
	{
		myAccount.click();
	}
	
	
	public void clickRegister()
	{
		Register.click();
	}
	
	
	public void clickLogin()
	{
		Login.click();
	}
	
	

}

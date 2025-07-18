package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class RegisterPage extends BasePage {
	
	//need 2 POC - homepage,registrationpage
	
	//constructor
	
	public RegisterPage(WebDriver driver)
	{
		super(driver);
	}

	
	//Locating Elements
	
	@FindBy(xpath="//input[@id='input-firstname']")
	WebElement txtFirstname;
	
	@FindBy(xpath="//input[@id='input-lastname']")
	WebElement txtLastname;
	
	@FindBy(xpath="//input[@id='input-email']")
	WebElement txtEmail;
	
	@FindBy(xpath="//input[@id='input-telephone']")
	WebElement txtPhone;
	
	@FindBy(xpath="//input[@id='input-password']")
	WebElement txtPassword;
	
	@FindBy(xpath="//input[@id='input-confirm']")
	WebElement txtConfirmPassword;
	
	@FindBy(xpath="//input[@name='agree']")
	WebElement cbAgree;
	
	@FindBy(xpath="//input[@value='Continue']")
	WebElement btnContinue;
	
	@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement msgSuccess;
	
	
	//Action Methods
	
	
	public void setFirstName(String fname)
	{
		txtFirstname.sendKeys(fname);
	}
	
	public void setLastName(String lname)
	{
		txtLastname.sendKeys(lname);
	}
	
	public void setEmail(String email)
	{
		txtEmail.sendKeys(email);
	}
	

	public void setPhone(String phone)
	{
		txtPhone.sendKeys(phone);
	}
	public void setPassword(String pwd)
	{
		txtPassword.sendKeys(pwd);
	}
	
	public void confirmPassword(String conf_pwd)
	{
		txtConfirmPassword.sendKeys(conf_pwd);
	}
	
	public void checkAgree()
	{
		cbAgree.click();
	}
	
	public void clickContinue()
	{
		btnContinue.click();
	}
	
	public String getConfirmation()
	{
		try
		{
			return msgSuccess.getText();
		}
		catch(Exception e)
		{
			return e.getMessage();
		}
		
	}
	
	
}

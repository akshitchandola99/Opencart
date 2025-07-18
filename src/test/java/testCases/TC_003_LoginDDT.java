package testCases;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import utilities.DataProviders;

@Listeners(utilities.ExtentReportManager.class)
public class TC_003_LoginDDT extends BaseClass {
	
	@Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class, groups = "DataDriven")
	public void loginDDT(String email, String pass, String exp) throws InterruptedException
	{
		
		logger.info("****Starting TC003_LoginDDT****");
		System.out.println("starting login test");
		
		try
		{
		//homepage
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
		//login
		LoginPage lp = new LoginPage(driver);
		lp.setEmail(email);
		lp.setPassword(pass);
		lp.clicklogin();
		
		//myaccount
		MyAccountPage mp = new MyAccountPage(driver);	
		boolean targetPage = mp.isMyAccountPageExists();
		
		
		//validations
		if(exp.equalsIgnoreCase("Valid"))
		{
			if(targetPage==true)
			{
				mp.clickLogout();
				Assert.assertTrue(true);
			}
			else
			{
				Assert.assertTrue(false);
			}
			System.out.println(targetPage);
		}
		
		if(exp.equalsIgnoreCase("Invalid"))
		{
			if(targetPage==true)
			{
				mp.clickLogout();
				Assert.assertTrue(false);
			}
			else
			{
				Assert.assertTrue(true);
			}
		}
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		Thread.sleep(3000);
		
		logger.info("****Ending TC003_LoginDDT****");
				
		
	}



}

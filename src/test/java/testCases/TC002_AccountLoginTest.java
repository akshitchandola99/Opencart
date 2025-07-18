package testCases;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;

@Listeners(utilities.ExtentReportManager.class)
public class TC002_AccountLoginTest extends BaseClass {
	
	//TestCase class - only testing activities and methods
	@Test(groups={"Sanity","Master"})
	public void testLogin()
	{
		try
		{
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
		LoginPage lp = new LoginPage(driver);
		lp.setEmail(p.getProperty("email"));
		lp.setPassword(p.getProperty("password"));
		lp.clicklogin();
		
		MyAccountPage mp = new MyAccountPage(driver);	
		boolean targetPage = mp.isMyAccountPageExists();
		
		System.out.println("target page value is" + targetPage);
		
		Assert.assertEquals(targetPage,true);
		
		mp.clickLogout();
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Assert.fail();
		}
		
		

	}
	
	
}

package testCases;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.Assert;
import pageObjects.HomePage;
import pageObjects.RegisterPage;

//TestCase class - only testing activities and methods
@Listeners(utilities.ExtentReportManager.class)
public class TC001_AccountRegistrationTest extends BaseClass{
	

	@Test(groups={"Regression","Master"})
	public void testRegistration() throws InterruptedException
	{
		
		logger.info("********Staring TC001_AccountRegistrationTest********");
		
		//Creating object of HomePage & RegistrationPage class to access their methods
		try
		{
			
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		hp.clickRegister();

		logger.info("Clicked on Register");
		
		RegisterPage rp = new RegisterPage(driver);
		
		logger.info("Providing details");
		rp.setFirstName(randomString());
		rp.setLastName(randomString());
		rp.setEmail(randomAlphaNumber() + "@gmail.com");
		rp.setPhone(randomNumber());
		
		String pass = randomString();
		rp.setPassword(pass);
		rp.confirmPassword(pass);
		
		rp.checkAgree();
		
		rp.clickContinue();
		
		//validation
		logger.info("validation");
	    String confirmMessage = rp.getConfirmation(); 
	    
	    if(confirmMessage.equals("Your Account Has Been Created!"))
	    {
	    	Assert.assertTrue(true);
	    }
	    else
	    {
	    	logger.error("Testing error...");
			logger.debug("Testing debug...");
			Assert.assertTrue(false);
	    }
	    
	    logger.info("Test Passed");

		}
		catch(Exception e)
		{
			e.printStackTrace();
			Assert.fail();
		}
		
		logger.info("********Finished TC001_AccountRegistrationTest********");
	}
	
	
}

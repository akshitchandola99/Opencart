package testCases;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.text.RandomStringGenerator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;          
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//This is a base/Parent class for all other classes. Contains Resuable methods. Avoids Code Duplication......
public class BaseClass {
	
    public static WebDriver driver;
    public Logger logger;
    public Properties p;
	
	@BeforeClass(groups={"Sanity","Regression","Master"})
	@Parameters({"os","browser"})
	public void setup(String os,String br) throws InterruptedException, IOException
	{
		
		//loading properties file
		FileReader file = new FileReader("./src/test/resources/config.properties");
		p=new Properties();
		p.load(file);
		
		logger = LogManager.getLogger(this.getClass());
		
		//execution on local machine
		if(p.getProperty("execution_env").equalsIgnoreCase("local"))
		{
			switch(br.toLowerCase())
			{
			case "chrome"  : driver = new ChromeDriver();
			break;
			
			case "edge"    : driver = new EdgeDriver();
	        break;
	        
			case "firefox" : driver = new FirefoxDriver();
	        break;
	        
	        default : System.out.println("Invalid browser");
	        return;
			}
		}
		
		//execution on GRID
		if(p.getProperty("execution_env").equalsIgnoreCase("remote"))
		{
			DesiredCapabilities capabilities = new DesiredCapabilities();
			
			//os & browser coming from xml file, so launch that
			
			//setting OS
			if(os.equalsIgnoreCase("windows"))
				{
				capabilities.setPlatform(Platform.WIN11);
				}
		    else if(os.equalsIgnoreCase("mac"))
				{
				capabilities.setPlatform(Platform.MAC);
				}
			else if(os.equalsIgnoreCase("Linux"))
			    {
			     capabilities.setPlatform(Platform.LINUX);
			     System.out.println("Selecting Linux");
		    	}
			    else
			    {
				System.out.println("Invalid OS");
				return;
				}
			
			//setting browser
			switch(br.toLowerCase())
			{
			case "chrome" : capabilities.setBrowserName("chrome");
			                System.out.println("Selecting chrome");
							break;
			case "edge"   : capabilities.setBrowserName("MicrosoftEdge");
			                break;
			case "firefox": capabilities.setBrowserName("firefox");
			                System.out.println("Selecting Firefox");
                            break;
			default       : System.out.println("No matching browser");
							return;
			
			}
			//using RemoteWebDriver(parent of all webDriver classes) bcz we do not know which browser to launch 
			driver = new RemoteWebDriver(new URL("http://localhost:4444/"),capabilities);
			System.out.println("");
		}

		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		
		driver.get(p.getProperty("appurl1"));
		driver.manage().window().maximize();
		
	}
	
	@AfterClass(groups={"Sanity","Regression","Master"})
	public void teardown()
	{
		driver.quit();
	}
	
	//creating a method to generate random string
		public String randomString()
		{
			
			RandomStringGenerator generator = new RandomStringGenerator.Builder()
		            .withinRange('a', 'z')
		            .build();
			

		    String randomAlphabetic = generator.generate(5);
		    return randomAlphabetic;
		}

		//creating a method to generate random number
		public String randomNumber()
		{
			
			RandomStringGenerator generator = new RandomStringGenerator.Builder()
		            .withinRange('0', '9')
		            .build();
			

		    String randomNumber = generator.generate(6);
		    return randomNumber;
		}
		
		public String randomAlphaNumber()
		{
			RandomStringGenerator generatorString = new RandomStringGenerator.Builder()
		            .withinRange('a', 'z')
		            .build();
			
			RandomStringGenerator generatorNumber = new RandomStringGenerator.Builder()
		            .withinRange('0', '9')
		            .build();
			

		    String randomNumber = generatorString.generate(3);
		    String randomAlphabetic = generatorNumber.generate(3);
		    
		    return(randomAlphabetic + randomNumber);    
		}	
		
		public String captureScreen(String tname) throws IOException {
		    String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date(0));

		    TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		    File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

		    String targetFilePath = System.getProperty("user.dir") + "\\screenshots\\" + tname + " " + timeStamp + ".png";
		    File targetFile = new File(targetFilePath);

		    sourceFile.renameTo(targetFile);

		    return targetFilePath;
		}


}

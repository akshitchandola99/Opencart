package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testCases.BaseClass;

public class ExtentReportManager implements ITestListener {

    public ExtentSparkReporter sparkReporter;
    public ExtentReports extent;
    public ExtentTest test;
    String repName;

    public void onStart(ITestContext testContext) {
    	System.out.println("Starting ReportManager");
    	
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        repName = "Test-Report-" + timeStamp + ".html";

        sparkReporter = new ExtentSparkReporter("./reports/" + repName);
        sparkReporter.config().setDocumentTitle("opencart Automation Report");
        sparkReporter.config().setReportName("opencart Functional Testing");
        sparkReporter.config().setTheme(Theme.DARK);

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("Application", "opencart");
        extent.setSystemInfo("Module", "Admin");
        extent.setSystemInfo("Sub Module", "Customers");
    }

    public void onTestSuccess(ITestResult result) {
    	
    	System.out.println("Success ReportManager");
    	
        test = extent.createTest(result.getTestClass().getName());
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.PASS, result.getName() + " got successfully executed");
    }

    public void onTestFailure(ITestResult result) {
    	
    	System.out.println("Failed ReportManager");
    	
        test = extent.createTest(result.getTestClass().getName());
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.FAIL, result.getName() + " got failed");
        test.log(Status.INFO, result.getThrowable().getMessage());

        try {
            String imgPath = new BaseClass().captureScreen(result.getName());
            test.addScreenCaptureFromPath(imgPath);
        } catch (IOException e1) {
            e1.printStackTrace();
        } 
    }

    public void onTestSkipped(ITestResult result) {
    	
    	System.out.println("Skipped ReportManager");
    	
        test = extent.createTest(result.getTestClass().getName());
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.SKIP, result.getName() + " got skipped");
        test.log(Status.INFO, result.getThrowable().getMessage());
    }

    public void onFinish(ITestContext testContext) {
    	
    	System.out.println("finish ReportManager");
    	
        extent.flush();

        String pathOfExtentReport = System.getProperty("user.dir") + "/reports/" + repName;
        File extentReport = new File(pathOfExtentReport);

        try {
            Desktop.getDesktop().browse(extentReport.toURI());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Uncomment below if you want to send email with report
        /*
        try {
            // Create the URL for the report
        	File reportFile = new File(System.getProperty("user.dir") + File.separator + "reports" + File.separator + repName);
        	URL url = reportFile.toURI().toURL();


            // Create the email message
            ImageHtmlEmail email = new ImageHtmlEmail();
            email.setDataSourceResolver(new DataSourceUrlResolver(url));
            email.setHostName("smtp.googlemail.com"); // Gmail SMTP server
            email.setSmtpPort(465); // Secure port
            email.setAuthenticator(new DefaultAuthenticator("akshitchandola99@gmail.com", "Gmailz@4571")); // Update with real credentials
            email.setSSLOnConnect(true);
            
            email.setFrom("akshitchandola99@gmail.com"); // Sender email
            email.setSubject("Test Results");
            email.setMsg("Please find attached report.");
            email.addTo("akshitchandola99@gmail.com"); // Receiver email
            
            // Attach the extent report
            email.attach(url, "Extent Report", "Please check the report...");
            
            email.send(); // Send the email

        } catch (Exception e) {
            e.printStackTrace();
        }
        */

    }
}

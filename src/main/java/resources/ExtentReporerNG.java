package resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporerNG {

	public static ExtentReports getReportObject() {

		// Create object for two classes ExtentSparkReporter and ExtentReport
		
		String path = System.getProperty("user.dir") + "//reports" + "index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Automation Test Result");
		reporter.config().setDocumentTitle("Test Result");
		
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Prudhvi");
		// to crete entry for each test
		return extent;
		
		// directory where output is to be printed
//		ExtentSparkReporter spark = new ExtentSparkReporter("user/build/name/");
//		ExtentReports extent2 = new ExtentReports();
//		extent.attachReporter(spark);


	}
}

package core.report;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import core.driver.Mobile;

import java.io.File;
import java.text.SimpleDateFormat;

public class ExtentReport extends Mobile {

    public ExtentSparkReporter html = null;
    public ExtentReports reports = null;
    public ExtentTest test = null;

    String reportDirectoryPath = System.getProperty("user.dir") + File.separator + "output" + File.separator;

    public ExtentReport() {
        String date = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(new java.util.Date());
        html = new ExtentSparkReporter(reportDirectoryPath + "Report_"+date+".html");
        reports = new ExtentReports();
        reports.attachReporter(html);

        html.config().setDocumentTitle("Automation Report");
        html.config().setReportName("Test Report");
        html.config().setTheme(Theme.STANDARD);
        html.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
    }

    public void setTestName(String testName) {
        test = reports.createTest(testName);
    }

    public ExtentTest getTest() {
        return test;
    }

    public ExtentReports getReport() {
        return reports;
    }
    public void captureScreenShotInReport(){
        getTest().addScreenCaptureFromPath(takeScreenShot());

    }
}
package REST_API.Intermediate.reqresin.utilities;

import REST_API.Advanced.trello.reporting.ExtentManager;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import jdk.jfr.Description;
import jdk.jfr.Name;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.lang.reflect.Method;

public class TestBase {

    public ExtentReports extent;
    public ExtentTest test;


    @BeforeClass
    public void setup() {
        extent = ExtentManager.getInstance();
//        test = extent.createTest(getClass().getSimpleName());
    }

    @BeforeMethod
    public void beforeTests(Method method){
        test = extent.createTest(method.getName());

        Name testNameAnnotation = method.getAnnotation(Name.class);
        Description testDescriptionAnnotation = method.getAnnotation(Description.class);

        if (testNameAnnotation != null && testDescriptionAnnotation != null) {
            String testName = testNameAnnotation.value();
            String testDescription = testDescriptionAnnotation.value();
            test.info("Test Name: " + testName);
            test.info("Test Description: " + testDescription);
        }
    }

    @AfterClass
    public void tearDown() {
        ExtentManager.flushReport();
    }


}

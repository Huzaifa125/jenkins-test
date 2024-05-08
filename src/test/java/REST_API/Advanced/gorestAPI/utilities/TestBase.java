package REST_API.Advanced.gorestAPI.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import io.restassured.response.Response;
import jdk.jfr.Description;
import jdk.jfr.Name;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import REST_API.Advanced.trello.reporting.ExtentManager;
import org.testng.annotations.BeforeMethod;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;

import static REST_API.Advanced.gorestAPI.endpoints.ApiEndpoints.GET_ALL_USERS;

public class TestBase {


    public ExtentReports extent;
    public ExtentTest test;


    @BeforeClass
    public void setup() {
        extent = ExtentManager.getInstance();
//        test = extent.createTest(getClass().getSimpleName());
        test = extent.createTest(getClass().getSimpleName());


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

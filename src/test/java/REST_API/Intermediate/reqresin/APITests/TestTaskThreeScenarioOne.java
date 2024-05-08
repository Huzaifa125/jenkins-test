package REST_API.Intermediate.reqresin.APITests;

import REST_API.Intermediate.Reqresin.payloads.RequestPayloads;
import REST_API.Intermediate.Reqresin.utilities.RestUtils;
import REST_API.Intermediate.reqresin.utilities.Assertions;
import REST_API.Intermediate.reqresin.utilities.TestBase;
import REST_API.Intermediate.Reqresin.variables.TestVariables;
import com.aventstack.extentreports.Status;
import io.restassured.response.Response;
import jdk.jfr.Description;
import jdk.jfr.Name;
import org.testng.annotations.Test;

import static REST_API.Intermediate.Reqresin.endpoints.ApiEndpoints.*;
import static REST_API.Intermediate.Reqresin.utilities.HttpStatusCodes.*;

public class TestTaskThreeScenarioOne extends TestBase {


    @Test(priority = 1)
    @Name("Fetch User List.")
    @Description("Assert the Total Number of pages and Total records from the response.")
    public void testTotalPagesAndTotalRecords() {

        Response response = RestUtils.getRequest(FETCH_USER_LIST, test);
        int totalPages = response.jsonPath().getInt("total_pages");
        int totalRecords = response.jsonPath().getInt("total");


        if (totalPages == 2 && totalRecords == 12 && response.getStatusCode() == GET_OK) {

            Assertions.assertTotalPagesAndRecords(totalPages, totalRecords, response);

            test.log(Status.PASS, "Response status code is " + response.getStatusCode() + " -> Expected : " + GET_OK + "\n" +
                    "Total pages = " + totalPages + " -> Expected : 2" + "\n" +
                    "Total records = " + totalRecords + " -> Expected : 12");

        } else {
            test.log(Status.FAIL, "Response status code is " + response.getStatusCode() + " -> Expected :" + GET_OK + "\n" +
                    "Total pages = " + totalPages + " -> Expected : 2" + "\n" +
                    "Total records = " + totalRecords + " -> Expected : 12");

            Assertions.assertTotalPagesAndRecords(totalPages, totalRecords, response);
        }

    }

    @Test(priority = 2)
    @Name("Create new Employee.")
    @Description("Creating new Employee using payload.")
    public void testCreateEmployee() {

        String payload = RequestPayloads.createNewEmployee("Ali", "Developer");

        Response response = RestUtils.postRequestUsingPayload(CREATE_NEW_EMPLOYEE, test, payload);


        String responseBody = response.getBody().asString();
        String id = responseBody.split("\"id\":")[1].split(",")[0].trim();

        if (response.getStatusCode() == POST_CREATED) {

            Assertions.assertCreatedEmpId(id, response);

            test.log(Status.PASS, "Response status code is " + response.getStatusCode() + " -> Expected : " + POST_CREATED);

        } else {
            test.log(Status.FAIL, "Response status code is " + response.getStatusCode() + " -> Expected : " + POST_CREATED);

            Assertions.assertCreatedEmpId(id, response);

        }
    }

    @Test(priority = 3)
    @Name("Update the employee.")
    @Description("Updating Employee using PUT.")
    public void testUpdateEmployee() {

        TestVariables.empId = "2";

        String payload = RequestPayloads.updateEmployeePut("Khalid", "Software Engineer");

        Response response = RestUtils.putRequestUsingPayload(UPDATING_EMP_PUT, test, payload);

        String responseBody = response.getBody().asString();
        String retrievedName = responseBody.split("\"name\":\"")[1].split("\",")[0];
        String retrievedJob = responseBody.split("\"job\":\"")[1].split("\",")[0];


        if (response.getStatusCode() == PUT_OK) {

            Assertions.assertUpdatedEmpPut(retrievedName, retrievedJob, response);

            test.log(Status.PASS, "Response status code is " + response.getStatusCode() + " -> Expected : " + PUT_OK + "\n" +
                    "Total pages = " + retrievedName + " -> Expected : Khalid" + "\n" +
                    "Total records = " + retrievedJob + " -> Expected : Software Engineer");
        } else {

            test.log(Status.FAIL, "Response status code is " + response.getStatusCode() + " -> Expected : " + PUT_OK + "\n" +
                    "Total pages = " + retrievedName + " -> Expected : Khalid" + "\n" +
                    "Total records = " + retrievedJob + " -> Expected : Software Engineer");

            Assertions.assertUpdatedEmpPut(retrievedName, retrievedJob, response);

        }

    }

    @Test(priority = 4)
    @Name("Update the employee.")
    @Description("Updating Employee using PATCH.")
    public void testPatchEmployee() {


        String payload = RequestPayloads.updateEmployeePatch("Ahmed");

        Response response = RestUtils.patchRequestUsingPayload(UPDATING_EMP_PATCH, test, payload);

        String responseBody = response.getBody().asString();
        String retrievedName = responseBody.split("\"name\":\"")[1].split("\",")[0];


        if (response.getStatusCode() == PATCH_OK) {

            Assertions.assertUpdatedEmpPatch(retrievedName, response);

            test.log(Status.PASS, "Response status code is " + response.getStatusCode() + " -> Expected : " + PATCH_OK + "\n" +
                    "Total pages = " + retrievedName + " -> Expected : Ahmed");
        } else {

            test.log(Status.FAIL, "Response status code is " + response.getStatusCode() + " -> Expected : " + PATCH_OK + "\n" +
                    "Total pages = " + retrievedName + " -> Expected : Ahmed");

            Assertions.assertUpdatedEmpPatch(retrievedName, response);
        }

    }


    @Test(priority = 5)
    @Name("Delete the employee.")
    @Description("Deleting Employee.")
    public void testDeleteEmployee() {


        Response response = RestUtils.delRequest(DELETING_EMP, test);


        if (response.getStatusCode() == DELETE_NO_CONTENT) {

            Assertions.assertDeletedEmp(response);

            test.log(Status.PASS, "Response status code is " + response.getStatusCode() + " -> Expected : " + DELETE_NO_CONTENT);
        } else {

            test.log(Status.FAIL, "Response status code is " + response.getStatusCode() + " -> Expected : " + DELETE_NO_CONTENT);

            Assertions.assertDeletedEmp(response);

        }

    }


}

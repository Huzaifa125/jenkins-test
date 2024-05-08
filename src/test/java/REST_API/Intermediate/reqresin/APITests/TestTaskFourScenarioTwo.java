package REST_API.Intermediate.reqresin.APITests;

import REST_API.Intermediate.Reqresin.utilities.RestUtils;
import REST_API.Intermediate.Reqresin.variables.TestVariables;
import REST_API.Intermediate.reqresin.utilities.Assertions;
import REST_API.Intermediate.reqresin.utilities.TestBase;
import REST_API.Intermediate.reqresin.utilities.UserDataProvider;
import com.aventstack.extentreports.Status;
import io.restassured.response.Response;
import jdk.jfr.Description;
import jdk.jfr.Name;
import org.testng.Assert;
import org.testng.annotations.Test;

import static REST_API.Intermediate.Reqresin.endpoints.ApiEndpoints.*;
import static REST_API.Intermediate.Reqresin.utilities.HttpStatusCodes.*;

public class TestTaskFourScenarioTwo extends TestBase {

    @Test(dataProvider = "userIds", dataProviderClass = UserDataProvider.class)
    @Name("Get User Details")
    @Description("Getting user details.")
    public void testGetUserDetails(int userId) {


        Response response = RestUtils.getRequest(GET_SINGLE_USER + userId, test);

        String name = response.jsonPath().getString("data.first_name");

        Object id = response.jsonPath().get("data.id");

        String email = response.jsonPath().getString("data.email");

        if (response.getStatusCode() == GET_OK) {

            Assertions.assertUserList(name, id, email, response);

            test.log(Status.PASS, "Response status code is " + response.getStatusCode() + " -> Expected : " + GET_OK);

        } else {
            test.log(Status.FAIL, "Response status code is " + response.getStatusCode() + " -> Expected :" + GET_OK);

            Assertions.assertUserList(name, id, email, response);

        }


    }


    @Test(dataProvider = "userIdsTwo", dataProviderClass = UserDataProvider.class)
    @Name("Get User Details")
    @Description("Getting user details.")
    public void testGetUserDetailsAndDelete(int userId) {

        Response getResponse = RestUtils.getRequest(GET_SINGLE_USER + userId, test);


        Object id = getResponse.jsonPath().get("data.id");


        if (id.equals(3)) {

            Response deleteResponse = RestUtils.delRequest(DELETING_USER + userId , test);


            if (deleteResponse.getStatusCode() == DELETE_NO_CONTENT) {

                Assert.assertEquals(deleteResponse.getStatusCode(),DELETE_NO_CONTENT,"Its not Deleted.");

                test.log(Status.PASS, "Response status code is " + deleteResponse.getStatusCode() + " -> Expected : " + DELETE_NO_CONTENT);

            } else {
                test.log(Status.FAIL, "Response status code is " + deleteResponse.getStatusCode() + " -> Expected : " + DELETE_NO_CONTENT);

                Assert.assertEquals(deleteResponse.getStatusCode(),DELETE_NO_CONTENT,"Its not Deleted.");

            }
        }



//        if (getResponse.getStatusCode() == GET_OK) {
//            Object id = getResponse.jsonPath().get("data.id");
//
//            if (id.equals(3)) {
//                Response deleteResponse = RestUtils.delRequest(GET_SINGLE_USER + userId, test);
//
//                if (deleteResponse.getStatusCode() == DELETE_NO_CONTENT) {
//                    test.log(Status.PASS, "User with ID 3 deleted successfully.");
//                } else {
//                    test.log(Status.FAIL, "Failed to delete user with ID 3. Status code: " + deleteResponse.getStatusCode());
//                }
//            } else {
//                test.log(Status.INFO, "User with ID 3 not found. Skipping delete operation.");
//            }
//        } else {
//            test.log(Status.FAIL, "Failed to retrieve user details. Status code: " + getResponse.getStatusCode());
//        }

    }


}

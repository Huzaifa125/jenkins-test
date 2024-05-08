package REST_API.Intermediate.reqresin.utilities;

import com.aventstack.extentreports.Status;
import io.restassured.response.Response;
import net.bytebuddy.asm.Advice;
import org.testng.Assert;

import static REST_API.Intermediate.Reqresin.reporting.ExtentManager.test;
import static REST_API.Intermediate.Reqresin.utilities.HttpStatusCodes.*;

public class Assertions {

    public static void assertTotalPagesAndRecords(int totalPages, int totalRecords, Response response) {


        Assert.assertEquals(totalPages, 2, "Total pages mismatch");
        Assert.assertEquals(totalRecords, 12, "Total records mismatch");
        Assert.assertEquals(response.getStatusCode(), GET_OK);

    }


    public static void assertCreatedEmpId(String id, Response response) {

        Assert.assertNotNull(id, "ID is null");
        Assert.assertFalse(id.isEmpty(), "ID is empty");
        Assert.assertEquals(response.getStatusCode(), POST_CREATED, "Unexpected status code");


    }

    public static void assertUpdatedEmpPut(String retrievedName, String retrievedJob, Response response) {

        Assert.assertEquals(response.getStatusCode(), PUT_OK, "PUT request failed");
        Assert.assertEquals(retrievedName, "Khalid", "Name update failed");
        Assert.assertEquals(retrievedJob, "Software Engineer", "Job update failed");

    }

    public static void assertUpdatedEmpPatch(String retrievedName, Response response) {

        Assert.assertEquals(response.getStatusCode(), PATCH_OK, "PATCH request failed");
        Assert.assertEquals(retrievedName, "Ahmed", "Name update failed");

    }

    public static void assertDeletedEmp(Response response) {

        Assert.assertEquals(response.getStatusCode(), DELETE_NO_CONTENT, "DEL request failed");

    }

    public static void assertUserList(String name, Object id, String email, Response response) {

        String[] userList = {"Michael", "Lindsay", "Tobias", "Byron", "George", "Rachel"};

        for (String users : userList) {

            if (users.equals(name)) {

                Assert.assertEquals(response.getStatusCode(), GET_OK, "Status code is not 200");

                Assert.assertEquals(name, users, "First name is not as expected");

                Assert.assertTrue(id instanceof Integer, "ID should be an integer");

                Assert.assertTrue(email instanceof String, "Email should be a string");
            }


        }
    }


}

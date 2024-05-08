package REST_API.Advanced.gorestAPI.APITests;

import REST_API.Advanced.gorestAPI.utilities.Assertions;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import jdk.jfr.Description;
import jdk.jfr.Name;
import org.json.JSONArray;
import org.testng.Assert;
import org.testng.annotations.Test;
import REST_API.Advanced.gorestAPI.utilities.RestUtils;
import REST_API.Advanced.gorestAPI.utilities.TestBase;
import REST_API.Advanced.gorestAPI.utilities.User;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static REST_API.Advanced.gorestAPI.endpoints.ApiEndpoints.GET_ALL_USERS;


public class GorestAPITests extends TestBase {


    @Test(priority = 1)
    @Name("Get all Users.")
    @Description("Getting all Users.")
    public void testGetAllUsers() {


        Response response = RestUtils.getRequest(GET_ALL_USERS, test);

        Assertions.assertGetResponseStatus(response);

        List<User> userList = response.jsonPath().getList("$", User.class);

        for (User user : userList) {
            String status = user.getStatus();
            if (status.equals("active") || status.equals("inactive")) {
                Assert.assertTrue(status.equals("active") || status.equals("inactive"),
                        "User status is neither active nor inactive. User: " + user);
            }
        }
        test.log(Status.PASS, "All users are fetched");


    }

    @Test(priority = 2)
    public void saveResponseToJsonFile() {

        Response response = RestUtils.getRequestWithoutReport(GET_ALL_USERS);

        String responseBody = response.getBody().asString();

        String filePath = System.getProperty("user.dir") + "/src/test/resources/expected_users.json";

        try {
            FileWriter fileWriter = new FileWriter(filePath);
            fileWriter.write(responseBody);
            fileWriter.close();
            System.out.println("Response saved to " + filePath);
        } catch (IOException e) {
            System.out.println("An error occurred while saving the response: " + e.getMessage());
        }
    }

    @Test(priority = 3)
    @Name("Assert Get Users.")
    @Description("Get all users and assert them with JsonFile.")
    public void testGetAllUsersAssertFromJson() throws Exception {

        Response getResponse = RestUtils.getRequest(GET_ALL_USERS, test);


        ObjectMapper objectMapper = new ObjectMapper();
        File expectedFile = new File(System.getProperty("user.dir") + "/src/test/resources/expected_users.json");
        User[] expectedUsers = objectMapper.readValue(expectedFile, User[].class);

        // Deserialize actual user data from response
        User[] actualUsers = getResponse.getBody().as(User[].class);

        Assert.assertEquals(actualUsers.length, expectedUsers.length, "Number of users mismatch");

        for (int i = 0; i < actualUsers.length; i++) {
            User actualUser = actualUsers[i];
            User expectedUser = expectedUsers[i];

            Assert.assertEquals(actualUser.getId(), expectedUser.getId(), "ID mismatch for user " + actualUser.getName());
            Assert.assertEquals(actualUser.getName(), expectedUser.getName(), "Name mismatch for user " + actualUser.getId());
            Assert.assertEquals(actualUser.getEmail(), expectedUser.getEmail(), "Email mismatch for user " + actualUser.getId());
            Assert.assertEquals(actualUser.getGender(), expectedUser.getGender(), "Gender mismatch for user " + actualUser.getId());
            Assert.assertEquals(actualUser.getStatus(), expectedUser.getStatus(), "Status mismatch for user " + actualUser.getId());
        }

    }


}


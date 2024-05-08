package REST_API.Advanced.gorestAPI.scripts;

import REST_API.Advanced.gorestAPI.utilities.RestUtils;
import com.aventstack.extentreports.ExtentTest;
import io.restassured.response.Response;


import java.io.FileWriter;
import java.io.IOException;

import static REST_API.Advanced.gorestAPI.endpoints.ApiEndpoints.GET_ALL_USERS;

public class UpdateJsonFile  {

    public static ExtentTest test;

    public static void main(String[] args) {


        Response response = RestUtils.getRequest(GET_ALL_USERS,test);

        // Write response body to JSON file
        try (FileWriter file = new FileWriter(System.getProperty("user.dir") + "src/test/resources/expected_users.json" )) {
            file.write(response.getBody().asString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

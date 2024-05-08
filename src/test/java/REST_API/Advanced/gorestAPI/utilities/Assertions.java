package REST_API.Advanced.gorestAPI.utilities;

import io.restassured.response.Response;
import org.testng.Assert;

public class Assertions {

    public static void assertGetResponseStatus(Response response){

        Assert.assertEquals(response.getStatusCode(), 200, "Failed to retrieve user list");

    }
}

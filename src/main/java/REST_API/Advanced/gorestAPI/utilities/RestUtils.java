package REST_API.Advanced.gorestAPI.utilities;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static REST_API.Advanced.gorestAPI.variables.TestVariables.BASE_URI;


public class RestUtils {


    public static Response getRequest(String endpoint, ExtentTest test) {

        // Create request specification
        RequestSpecification requestSpec = RestAssured.given()
                .baseUri(BASE_URI)
                .log().all();

        // Log test execution
        test.log(Status.INFO, "Executing GET request to endpoint: " + endpoint);


        // Send GET request and retrieve response
        Response response = requestSpec
                .get(endpoint)
                .then().log().all()
                .extract().response();

        test.log(Status.INFO, "Received response: " + "<pre>" + response.prettyPrint() + "</pre>");
        return response;
    }

    public static Response getRequestWithoutReport(String endpoint){

        Response response = RestAssured.given()
                .baseUri(BASE_URI)
                .contentType(ContentType.JSON)
                .get(endpoint);

        return response;

    }

    public static Response postRequestUsingPayload(String endpoint, ExtentTest test, String payload) {
        // Make the POST request
        Response response = RestAssured.given()
                .baseUri(BASE_URI)
                .contentType(ContentType.JSON)
                .body(payload)
                .post(endpoint);

        test.log(Status.INFO, "Executing POST request to endpoint: " + endpoint);
        test.log(Status.INFO, "Request payload: " + payload);

        // Log the received response
        test.log(Status.INFO, "Received response: " + "<pre>" + response.prettyPrint() + "</pre>");

        return response;
    }


    public static Response putRequestUsingPayload(String endpoint, ExtentTest test, String payload) {
        // Make the PUT request
        Response response = RestAssured.given()
                .baseUri(BASE_URI)
                .contentType(ContentType.JSON)
                .body(payload)
                .put(endpoint);

        test.log(Status.INFO, "Executing PUT request to endpoint: " + endpoint);
        test.log(Status.INFO, "Request payload: " + payload);

        // Log the received response
        test.log(Status.INFO, "Received response: " + "<pre>" + response.prettyPrint() + "</pre>");

        return response;
    }

    public static Response delRequestUsingPayload(String endpoint, ExtentTest test, String payload) {
        // Make the PUT request
        Response response = RestAssured.given()
                .baseUri(BASE_URI)
                .contentType(ContentType.JSON)
                .body(payload)
                .delete(endpoint);

        test.log(Status.INFO, "Executing DELETE request to endpoint: " + endpoint);
        test.log(Status.INFO, "Request payload: " + payload);

        // Log the received response
        test.log(Status.INFO, "Received response: " + "<pre>" + response.prettyPrint() + "</pre>");

        return response;
    }




}

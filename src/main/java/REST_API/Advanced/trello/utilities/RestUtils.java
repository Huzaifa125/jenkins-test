package restAPI.trello.utilities;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import REST_API.Advanced.trello.variables.TestVariables;

import java.util.Map;

import static REST_API.Advanced.trello.endpoints.ApiEndpoints.*;
import static REST_API.Advanced.trello.variables.TestVariables.BASE_URI;


public class RestUtils {

    public static Response getRequestUsingMap(String endpoint, ExtentTest test, Map<String, String> payloadParams) {
        RequestSpecBuilder requestBuilder = new RequestSpecBuilder();
        requestBuilder.setBaseUri(BASE_URI);
        requestBuilder.addParams(payloadParams);

        RequestSpecification requestSpec = requestBuilder.build();

        Response response = RestAssured.given()
                .spec(requestSpec)
                .get(endpoint);

        test.log(Status.INFO, "Received response: " + "<pre>" + response.prettyPrint() + "</pre>");
        return response;
    }

    public static Response getRequestUsingPayload(String endpoint, ExtentTest test, String payload) {

        // Create request specification
        RequestSpecification requestSpec = RestAssured.given()
                .baseUri(BASE_URI)
                .queryParam("key", TestVariables.TRELLO_KEY)
                .queryParam("token", TestVariables.TRELLO_TOKEN)
                .log().all();



        // Send GET request and retrieve response
        Response response = requestSpec
                .get(endpoint)
                .then().log().all()
                .extract().response();

        test.log(Status.INFO, "Executing POST request to endpoint: " + endpoint);
        test.log(Status.INFO, "Request payload: " + payload);

        test.log(Status.INFO, "Received response: " + "<pre>" + response.prettyPrint() + "</pre>");

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
    public static Response getBoardById(String boardId, ExtentTest test) {
        // Get the Trello API key and token from TestVariables class
        String trelloKey = TestVariables.TRELLO_KEY;
        String trelloToken = TestVariables.TRELLO_TOKEN;

        // Make the GET request to retrieve a board by its ID
        String endpoint = GET_CREATED_BOARD + boardId;
        Response response = RestAssured.given()
                .baseUri(BASE_URI)
                .queryParam("key", trelloKey)
                .queryParam("token", trelloToken)
                .get(endpoint);

        test.log(Status.INFO, "Executing GET request to endpoint: " + endpoint);

        // Log the received response
        test.log(Status.INFO, "Received response: " + "<pre>" + response.prettyPrint() + "</pre>");

        return response;
    }




}

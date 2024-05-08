package REST_API.Intermediate.Reqresin.utilities;

import REST_API.Intermediate.Reqresin.variables.TestVariables;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

import static REST_API.Intermediate.Reqresin.variables.TestVariables.BASE_URI;


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

    public static Response getRequest(String endpoint, ExtentTest test){

        Response response = RestAssured.given()
                .baseUri(BASE_URI)
                .contentType(ContentType.JSON)
                .get(endpoint);

        test.log(Status.INFO, "Executing GET request to endpoint: " + endpoint);


        // Log the received response
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

    public static Response patchRequestUsingPayload(String endpoint, ExtentTest test, String payload) {
        // Make the PUT request
        Response response = RestAssured.given()
                .baseUri(BASE_URI)
                .contentType(ContentType.JSON)
                .body(payload)
                .patch(endpoint);

        test.log(Status.INFO, "Executing PUT request to endpoint: " + endpoint);
        test.log(Status.INFO, "Request payload: " + payload);

        // Log the received response
        test.log(Status.INFO, "Received response: " + "<pre>" + response.prettyPrint() + "</pre>");

        return response;
    }

    public static Response delRequest(String endpoint, ExtentTest test) {
        // Make the PUT request
        Response response = RestAssured.given()
                .baseUri(BASE_URI)
                .contentType(ContentType.JSON)
                .delete(endpoint);

        test.log(Status.INFO, "Executing DELETE request to endpoint: " + endpoint);

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

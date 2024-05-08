//#region Imports
package REST_API.Advanced.trello.APITests;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import jdk.jfr.Description;
import jdk.jfr.Name;
import org.testng.Assert;
import org.testng.annotations.Test;
import REST_API.Advanced.trello.payloads.RequestPayloads;
import restAPI.trello.utilities.RestUtils;
import REST_API.Advanced.trello.utilities.TestBase;
import REST_API.Advanced.trello.variables.TestVariables;

import java.util.HashMap;
import java.util.Map;

import static REST_API.Advanced.trello.endpoints.ApiEndpoints.*;
import static REST_API.Advanced.trello.variables.TestVariables.*;
//#endregion


public class PostmanAPITests extends TestBase {


    @Test
    @Name("Get all Boards")
    @Description("Getting all Boards on my trello")
    public void getAllBoards() {

        //This is commented because now we are getting info of test from @Name and @Description
//        ExtentTest test = extent.createTest("Get all Boards", "Getting all Boards on my trello");


        Map<String, String> payloadParams = new HashMap<>();
        payloadParams.put("key", TestVariables.TRELLO_KEY);
        payloadParams.put("token", TestVariables.TRELLO_TOKEN);

        Response response = RestUtils.getRequestUsingMap(GET_ALL_BOARDS, test, payloadParams);
        Assert.assertEquals(response.statusCode(), 200);

        if (response.getStatusCode() == 200) {
            test.log(Status.PASS, "Response status code is " + response.getStatusCode());
        } else {
            test.log(Status.FAIL, "Response status code is " + response.getStatusCode() + " -> Expected : 200");
        }
    }

    @Test
    @Name("Get all Boards using Payload")
    @Description("Getting all Boards on my trello using payload")
    public void getAllBoardsUsingPayload() {



        String payload = RequestPayloads.getAllBoardsPayload();

        Response response = RestUtils.getRequestUsingPayload(GET_ALL_BOARDS, test, payload);

        if (response.getStatusCode() == 200) {
            Assert.assertEquals(response.statusCode(), 200);
            test.log(Status.PASS, "Response status code is " + response.getStatusCode());
        } else {
            test.log(Status.FAIL, "Response status code is " + response.getStatusCode() + " -> Expected : 200");
            Assert.assertEquals(response.statusCode(), 200);
        }
    }

    @Test(priority = 1)
    @Name("Create a Board")
    @Description("Creating A Board")
    public void testCreateBoard() {


        String payload = RequestPayloads.createBoardsPayload();

        Response response = RestUtils.postRequestUsingPayload(CREATE_BOARD, test, payload);

        JsonPath jsonPath = response.jsonPath();
        boardID = jsonPath.getString("id");

        if (response.getStatusCode() == 200) {
            Assert.assertEquals(response.statusCode(), 200);
            test.log(Status.PASS, "Response status code is " + response.getStatusCode());
        } else {
            test.log(Status.FAIL, "Response status code is " + response.getStatusCode() + " -> Expected : 200");
            Assert.assertEquals(response.statusCode(), 200);
        }


    }


    @Test(priority = 2)
    @Name("Get Board by ID")
    @Description("Retrieve board details using ID")
    public void testGetCreatedBoard() {


        Response response = RestUtils.getBoardById(boardID, test);

        if (response.getStatusCode() == 200) {
            Assert.assertEquals(response.statusCode(), 200);
            test.log(Status.PASS, "Response status code is " + response.getStatusCode());
        } else {
            test.log(Status.FAIL, "Response status code is " + response.getStatusCode() + " -> Expected : 200");
            Assert.assertEquals(response.statusCode(), 200);
        }
    }

    @Test(priority = 3)
    @Name("Create TODO List")
    @Description("Creating TODO list using the new created board ID")
    public void testCreateTODOList() {


        String payload = RequestPayloads.createTodoList();

        Response response = RestUtils.postRequestUsingPayload(CREATE_TODO_LIST, test, payload);

        JsonPath jsonPath = response.jsonPath();
        todoListID = jsonPath.getString("id");


        if (response.getStatusCode() == 200) {
            Assert.assertEquals(response.statusCode(), 200);
            test.log(Status.PASS, "Response status code is " + response.getStatusCode());
        } else {
            test.log(Status.FAIL, "Response status code is " + response.getStatusCode() + " -> Expected : 200");
            Assert.assertEquals(response.statusCode(), 200);
        }

    }

    @Test(priority = 4)
    @Name("Create DONE List")
    @Description("Creating DONE list using the new created board ID")
    public void testCreateDONEList() {


        String payload = RequestPayloads.createDONEList();

        Response response = RestUtils.postRequestUsingPayload(CREATE_DONE_LIST, test, payload);

        JsonPath jsonPath = response.jsonPath();
        doneListID = jsonPath.getString("id");

        if (response.getStatusCode() == 200) {
            Assert.assertEquals(response.statusCode(), 200);
            test.log(Status.PASS, "Response status code is " + response.getStatusCode());
        } else {
            test.log(Status.FAIL, "Response status code is " + response.getStatusCode() + " -> Expected : 200");
            Assert.assertEquals(response.statusCode(), 200);
        }


    }


    @Test(priority = 5)
    @Name("Create Card in TODO List")
    @Description("Creating Card in TODO list in the new created board ID")
    public void testCreateCard() {


        String payload = RequestPayloads.createCard();

        Response response = RestUtils.postRequestUsingPayload(CREATE_CARD, test, payload);

        JsonPath jsonPath = response.jsonPath();
        TestVariables.cardID = jsonPath.getString("id");

        if (response.getStatusCode() == 200) {
            Assert.assertEquals(response.statusCode(), 200);
            test.log(Status.PASS, "Response status code is " + response.getStatusCode());
        } else {
            test.log(Status.FAIL, "Response status code is " + response.getStatusCode() + " -> Expected : 200");
            Assert.assertEquals(response.statusCode(), 200);
        }

    }

    @Test(priority = 6)
    @Name("Move Card From TODO List to DONE list")
    @Description("Moving Card from TODO list to DONE list")
    public void testMoveCard() {

        String payload = RequestPayloads.moveCard();

        Response response = RestUtils.putRequestUsingPayload(MOVE_CARD, test, payload);

        if (response.getStatusCode() == 200) {
            Assert.assertEquals(response.statusCode(), 200);
            test.log(Status.PASS, "Response status code is " + response.getStatusCode());
        } else {
            test.log(Status.FAIL, "Response status code is " + response.getStatusCode() + " -> Expected : 200");
            Assert.assertEquals(response.statusCode(), 200);
        }


    }

    @Test(priority = 7)
    @Name("Delete a new Created board")
    @Description("Deleting a new Created board")
    public void testDeleteBoard(){

        String payload = RequestPayloads.deleteBoard();

        Response response = RestUtils.delRequestUsingPayload(DELETE_BOARD,test,payload);

        if (response.getStatusCode() == 200) {
            Assert.assertEquals(response.statusCode(), 200);
            test.log(Status.PASS, "Response status code is " + response.getStatusCode());
        } else {
            test.log(Status.FAIL, "Response status code is " + response.getStatusCode() + " -> Expected : 200");
            Assert.assertEquals(response.statusCode(), 200);
        }


    }


    @Test(priority = 8)
    @Name("Get a Deleted Board")
    @Description("Get Request to check whether the board is deleted or not")
    public void testGetDeletedBoard(){

        String payload = RequestPayloads.deleteBoard();

        Response response = RestUtils.getRequestUsingPayload(DELETE_BOARD,test,payload);

        if (response.getStatusCode() == 404) {
            Assert.assertEquals(response.statusCode(), 404);
            test.log(Status.PASS, "Response status code is " + response.getStatusCode());
        } else {
            test.log(Status.FAIL, "Response status code is " + response.getStatusCode() + " -> Expected : 404");
            Assert.assertEquals(response.statusCode(), 404);
        }
    }


}

package REST_API.Advanced.trello.endpoints;

import REST_API.Advanced.trello.variables.TestVariables;

import static REST_API.Advanced.trello.variables.TestVariables.boardID;
import static REST_API.Advanced.trello.variables.TestVariables.cardID;

public class ApiEndpoints {

    public static final String GET_ALL_BOARDS = "/1/members/me/boards";
    public static final String CREATE_BOARD = "/1/boards";
    public static final String GET_CREATED_BOARD = "/1/boards/";
    public static final String CREATE_TODO_LIST = "/1/lists";
    public static final String CREATE_DONE_LIST = "/1/lists";
    public static final String CREATE_CARD = "/1/cards";
    public static final String MOVE_CARD = "/1/cards/" + TestVariables.cardID;
    public static final String DELETE_BOARD = "/1/boards/" + TestVariables.boardID;



    // Add more endpoints as needed

}

package REST_API.Advanced.trello.payloads;

import REST_API.Advanced.trello.variables.TestVariables;

import static REST_API.Advanced.trello.variables.TestVariables.*;

public class RequestPayloads {

    private static String trelloKey = TestVariables.TRELLO_KEY;
    private static String trelloToken = TestVariables.TRELLO_TOKEN;

    public static String getAllBoardsPayload() {

        return "{\"key\":\"" + trelloKey + "\",\"token\":\"" + trelloToken + "\"}";

    }

    public static String createBoardsPayload() {

        String boardName = "Huz RestAssured";

        return "{\"key\":\"" + trelloKey + "\",\"token\":\"" + trelloToken + "\",\"name\":\"" + boardName + "\"}";
    }

    public static String createTodoList() {
        String boardId = boardID;
        String listName = "TODO";
        return "{\"key\":\"" + trelloKey + "\",\"token\":\"" + trelloToken + "\",\"idBoard\":\"" + boardId + "\",\"name\":\"" + listName + "\"}";
    }

    public static String createDONEList() {
        String boardId = boardID;
        String listName = "DONE";
        return "{\"key\":\"" + trelloKey + "\",\"token\":\"" + trelloToken + "\",\"idBoard\":\"" + boardId + "\",\"name\":\"" + listName + "\"}";

    }

    public static String createCard() {
        String boardId = boardID;
        String listId = todoListID;
        String cardName = "Sign-up for Trello";
        return "{\"key\":\"" + trelloKey +
                "\",\"token\":\"" + trelloToken +
                "\",\"idBoard\":\"" + boardId +
                "\",\"idList\":\"" + listId +
                "\",\"name\":\"" + cardName + "\"}";

    }

    public static String moveCard(){
        String listId = doneListID;
        return "{\"key\":\"" + trelloKey +
                "\",\"token\":\"" + trelloToken +
                "\",\"idList\":\"" + listId + "\"}";
    }

    public static String deleteBoard(){
        return "{\"key\":\"" + trelloKey +
                "\",\"token\":\"" + trelloToken + "\"}";
    }



    // Add more payload methods as needed

}

package REST_API.Advanced.trello.variables;

import REST_API.Advanced.trello.utilities.ConfigReader;

public class TestVariables {


    public static final String BASE_URI = ConfigReader.getProperty("baseURI");
    public static final String TRELLO_KEY = ConfigReader.getProperty("trelloKey");
    public static final String TRELLO_TOKEN = ConfigReader.getProperty("trelloToken");
    public static String boardID;
    public static String todoListID;
    public static String doneListID;
    public static String cardID;
    // Add more variables as needed

}

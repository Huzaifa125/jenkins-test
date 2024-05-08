package REST_API.Intermediate.Reqresin.utilities;

public final class HttpStatusCodes {

    // PUT method status codes
    public static final int PUT_OK = 200;
    public static final int PUT_CREATED = 201;
    public static final int PUT_NO_CONTENT = 204;

    // POST method status codes
    public static final int POST_OK = 200;
    public static final int POST_CREATED = 201;
    public static final int POST_NO_CONTENT = 204;
    public static final int POST_BAD_REQUEST = 400;


    // DELETE method status codes
    public static final int DELETE_OK = 200;
    public static final int DELETE_NO_CONTENT = 204;


    // GET method status codes
    public static final int GET_OK = 200;
    public static final int GET_NOT_FOUND = 404;


    // PATCH method status codes
    public static final int PATCH_OK = 200;
    public static final int PATCH_NO_CONTENT = 204;
    public static final int PATCH_NOT_FOUND = 404;


    // Private constructor to prevent instantiation
    private HttpStatusCodes() {
        throw new AssertionError("HttpStatusCodes class cannot be instantiated");
    }
}

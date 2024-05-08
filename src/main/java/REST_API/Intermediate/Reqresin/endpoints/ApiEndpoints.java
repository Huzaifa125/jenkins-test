package REST_API.Intermediate.Reqresin.endpoints;

import REST_API.Intermediate.Reqresin.variables.TestVariables;


public class ApiEndpoints {

    public static final String FETCH_USER_LIST = "api/users?page=2";
    public static final String CREATE_NEW_EMPLOYEE = "api/users";
    public static final String UPDATING_EMP_PUT = "api/users/" + TestVariables.empId;
    public static final String UPDATING_EMP_PATCH = "api/users/" + TestVariables.empId;
    public static final String DELETING_EMP = "api/users/" + TestVariables.empId;
    public static final String GET_SINGLE_USER = "api/users/";
    public static final String DELETING_USER = "api/users/";



}

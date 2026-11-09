package api.endpoints;

/*
Base URL: http://petstore.swagger.io/v2
Create user: http://petstore.swagger.io/v2/user
Get user: http://petstore.swagger.io/v2/user/{username}
Update user: http://petstore.swagger.io/v2/user/{username}
Delete user: http://petstore.swagger.io/v2/user/{username}
*/

public class Routes {

    public static String base_url = "https://petstore.swagger.io/v2";

    //uer module
    public static String post_url = base_url+"/user";
    public static String get_url = base_url+"/user/{username}";
    public static String update_url = base_url+"/user/{username}";
    public static String Delete_url = base_url+"/user/{username}";

    //store module

    //pet module

}

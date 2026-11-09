package api.tests;

import api.endpoints.UserEndPoints;
import api.payloads.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
public class DataDrivenTests {

    Logger logger= LogManager.getLogger(this.getClass());

    @Test(priority = 1, dataProvider = "Data", dataProviderClass = DataProviders.class)
    public void testPostuser(String userID, String userName, String firstName, String lName, String email,  String pwd,  String phone){
        logger.info("**********Post Request ************");
        User userPayload = new User();
        userPayload.setId(Integer.parseInt(userID));
        userPayload.setUsername(userName);
        userPayload.setFirstName(firstName);
        userPayload.setLastName(lName);
        userPayload.setEmail(email);
        userPayload.setPhone(phone);
        userPayload.setPassword(pwd);

        Response response =  UserEndPoints.createUser(userPayload);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);

    }

    @Test(priority = 2, dataProvider = "UserNames", dataProviderClass = DataProviders.class)
    public void testDeleteUserByName(String userName){
        logger.info("********** Delete Request ************");
           Response response = UserEndPoints.deleteUser(userName);
            Assert.assertEquals(response.getStatusCode(),200);
    }

}

package api.tests;

import api.endpoints.UserEndPointsProp;
import api.payloads.User;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UserTestsProp {

    Faker faker;
    User userPayload;
    Logger logger = LogManager.getLogger(this.getClass());

    @BeforeClass
    public void setUp(){
        faker = new Faker();
        userPayload = new User();

        userPayload.setId(faker.idNumber().hashCode());
        userPayload.setUsername(faker.name().username());
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().safeEmailAddress());
        userPayload.setPassword(faker.internet().password(5, 10));
        userPayload.setPhone(faker.phoneNumber().cellPhone());

        logger = LogManager.getLogger(this.getClass());
    }

    @Test(priority = 1)
    public void testPostUser(){
        logger.info("**********Post Request ************");
        Response response =  UserEndPointsProp.createUser(userPayload);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);
    }


    @Test(priority = 2)
    public void testGetUser(){
        logger.info("**********Get Request ************");
        Response response=     UserEndPointsProp.readUser(this.userPayload.getUsername());
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);
    }

    @Test(priority = 3)
    public void updateUserByName(){
        logger.info("******* Update request **********");
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().safeEmailAddress());

        //update data using the payalod
        Response response= UserEndPointsProp.updateUser(this.userPayload.getUsername(), userPayload);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);

        //verying updated user
        Response responseAfterUpdate=     UserEndPointsProp.readUser(this.userPayload.getUsername());
        response.then().log().all();
        Assert.assertEquals(responseAfterUpdate.getStatusCode(),200);

    }

    @Test(priority = 4)
    public void  deleteUser(){
        logger.info("******* Delete request **********");
        Response response=   UserEndPointsProp.deleteUser(this.userPayload.getUsername());
        response.then().statusCode(200);
    }
}

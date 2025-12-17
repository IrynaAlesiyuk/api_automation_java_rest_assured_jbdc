package tests;

import api.client.UserApiClient;
import model.api.User;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static org.testng.Assert.assertEquals;

public class UserApiTest {


    @Test
    public void testGetUser() {
        //Arrange
        SoftAssert softAssert = new SoftAssert();
        UserApiClient client = new UserApiClient();

        //Act
        User user = client.getUser(1);

        //Assert
        softAssert.assertEquals(user.getId(), 1,
                "User ID should be 1");
        softAssert.assertEquals(user.getEmail(),
                "Sincere@april.biz", "User email should be 'Sincere@april.biz'");
    }
}

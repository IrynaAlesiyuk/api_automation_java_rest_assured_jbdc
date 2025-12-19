package tests;

import api.models.response.User;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;


public class UserApiTest extends BaseTest {


    @Test
    public void testGetUser() {
        //Act
        Response res = reqCreator.sendGet("/users/{id}", Map.of("id", 1));
        User user = parser.parse(res, User.class);

        //Assert
        Assert.assertNotNull(user, "User is null");
    }

    @Test
    public void testUserPartialComparison() {
        //Arrange
        User expectedUser = new User();
        expectedUser.setName("Leanne Graham");
        expectedUser.setUsername("Bret");
        User.Address expectedAddress = new User.Address();
        expectedAddress.setCity("Gwenborough");
        expectedUser.setAddress(expectedAddress);

        //Act
        Response res = reqCreator.sendGet("/users/{id}", Map.of("id", 1));
        User actualUser = parser.parse(res, User.class);

        //Assert
        softAssert.assertThat(actualUser)
                .usingRecursiveComparison()
                .comparingOnlyFields("name", "username", "address.city")
                .isEqualTo(expectedUser);

        softAssert.assertAll();
    }
}

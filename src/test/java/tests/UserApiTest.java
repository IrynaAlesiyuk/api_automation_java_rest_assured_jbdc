package tests;

import api.models.response.User;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;

import static common.constants.PathParams.ID;
import static common.constants.Suburls.USERS_BY_ID;


public class UserApiTest extends BaseTest {


    @Test
    public void testGetUser() {
        //Act
        Response res = reqCreator.sendGet(USERS_BY_ID, Map.of(ID, 1), null);
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
        Response res = reqCreator.sendGet(USERS_BY_ID, Map.of(ID, 1), null);
        User actualUser = parser.parse(res, User.class);

        //Assert
        softAssert.assertThat(actualUser)
                .usingRecursiveComparison()
                .comparingOnlyFields("name", "username", "address.city")
                .isEqualTo(expectedUser);

        softAssert.assertAll();
    }
}

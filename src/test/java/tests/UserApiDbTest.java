package tests;

import api.models.response.User;
import db.DataSourceFactory;
import db.DbClient;
import db.repository.UserRepository;
import db.model.UserDb;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Map;

import static common.constants.PathParams.ID;
import static common.constants.Suburls.USERS_BY_ID;

public class UserApiDbTest extends BaseTest {
    private UserRepository repository;

    @BeforeClass
    public void setup() {
        DbClient dbClient = new DbClient(
                DataSourceFactory.getDataSource()
        );
        repository = new UserRepository(dbClient);
    }

    @Test
    public void userFromApiShouldBeSavedToDb() {
        //Arrange
        Response res = reqCreator.sendGet(USERS_BY_ID, Map.of(ID, 2), null);
        User apiUser = parser.parse(res, User.class);
        int userId = apiUser.getId();

        //Act
        repository.save(userId, apiUser.getEmail());

        //Assert
        softAssert.assertThat(repository.exists(userId)).as("User should exist in DB after API call")
                .isTrue();
    }

    @Test
    public void userFromDbShouldMatchApi() {
        //Arrange
        Response res = reqCreator.sendGet(USERS_BY_ID, Map.of(ID, 1), null);
        User apiUser = parser.parse(res, User.class);
        int userId = apiUser.getId();
        repository.save(userId, apiUser.getEmail());

        //Act
        UserDb dbUser = repository.findById(userId);

        //Assert
        softAssert.assertThat(dbUser).as("User should exist in DB").isNotNull();
        softAssert.assertThat(dbUser.getId()).as("User ID should match API")
                .isEqualTo(apiUser.getId());
        softAssert.assertThat(dbUser.getEmail()).as("User email should match API")
                .isEqualTo(apiUser.getEmail());
    }
}

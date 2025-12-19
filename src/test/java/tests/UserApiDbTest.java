package tests;

import db.DataSourceFactory;
import db.DbClient;
import db.repository.UserRepository;
import db.model.UserDb;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class UserApiDbTest {
    //private UserApiClient api;
    private UserRepository repository;

    @BeforeClass
    public void setup() {
        //api = new UserApiClient();

        DbClient dbClient = new DbClient(
                DataSourceFactory.getDataSource()
        );
        repository = new UserRepository(dbClient);
    }

    @Test
    public void userFromApiShouldBeSavedToDb() {
        //Arrange
        //User user = api.getUser(2);
        //repository.save(user.getId(), user.getEmail());

        //Assert
        //Assert.assertTrue(repository.exists(user.getId()),
        //        "User should exist in DB after API call"
        //);
    }

    @Test
    public void userFromDbShouldMatchApi() {
        //Arrange
        SoftAssert softAssert = new SoftAssert();
        int userId = 1;
        //User apiUser = api.getUser(userId);
        //repository.save(apiUser.getId(), apiUser.getEmail());
        UserDb dbUser = repository.findById(userId);

        //Assert
        softAssert.assertNotNull(dbUser, "User should exist in DB");
        //softAssert.assertEquals(dbUser.getId(), apiUser.getId(), "User ID should match API");
        //softAssert.assertEquals(dbUser.getEmail(), apiUser.getEmail(), "User email should match API");
    }
}

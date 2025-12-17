package api.client;

import model.api.User;

import static io.restassured.RestAssured.given;

public class UserApiClient {

    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";

    public User getUser(int id) {
        return given()
                .baseUri(BASE_URL)
                .log().all() // log request
                .when()
                .get("/users/{id}", id)
                .then()
                .log().all() // log response
                .statusCode(200)
                .extract()
                .as(User.class);
    }
}

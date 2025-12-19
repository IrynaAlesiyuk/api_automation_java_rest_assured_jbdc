package api.requests;

import common.Config;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

public class RequestsCreator {

    private RequestSpecification baseSpec() {
        return RestAssured.given()
                .baseUri(Config.getBaseUrl())
                .contentType(ContentType.JSON)
                .log().all();
    }

    public <T> Response sendPost(String path, T body) {
        return baseSpec()
                .body(body)
                .post(path)
                .then()
                .log().all()
                .extract()
                .response();
    }

    public Response sendGet(String path, Map<String, ?> pathParams) {
        RequestSpecification spec = baseSpec();

        if (pathParams != null && !pathParams.isEmpty()) {
            spec.pathParams(pathParams);
        }
        return spec
                .get(path)
                .then()
                .log().all()
                .extract()
                .response();
    }

    public <T> Response sendPut(String path, T body) {
        return baseSpec()
                .body(body)
                .put(path)
                .then()
                .log().all()
                .extract()
                .response();
    }

    public Response sendDelete(String path) {
        return baseSpec()
                .delete(path)
                .then()
                .log().all()
                .extract()
                .response();
    }
}

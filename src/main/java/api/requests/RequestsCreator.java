package api.requests;

import common.Config;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.http.Method;


import java.util.Map;

import static io.restassured.http.Method.*;

public class RequestsCreator {

    private RequestSpecification baseSpec(Map<String, String> headers) {
        RequestSpecification spec = RestAssured.given()
                .baseUri(Config.getBaseUrl())
                .contentType(ContentType.JSON)
                .log().all();

        if (headers != null && !headers.isEmpty()) {
            spec.headers(headers);
        }

        return spec;
    }

    private <T> Response sendRequest(String path, Method method, T body, Map<String, ?> pathParams, Map<String, String> headers) {
        RequestSpecification spec = baseSpec(headers);

        if (pathParams != null && !pathParams.isEmpty()) {
            spec.pathParams(pathParams);
        }

        switch (method) {
            case GET:
                return spec.get(path).then().log().all().extract().response();
            case POST:
                return spec.body(body).post(path).then().log().all().extract().response();
            case PUT:
                return spec.body(body).put(path).then().log().all().extract().response();
            case DELETE:
                return spec.delete(path).then().log().all().extract().response();
            default:
                throw new IllegalArgumentException("Unsupported HTTP method: " + method);
        }
    }

    public Response sendGet(String path) {
        return sendRequest(path, GET, null, null, null);
    }

    public Response sendGet(String path, Map<String, ?> pathParams, Map<String, String> headers) {
        return sendRequest(path, GET, null, pathParams, headers);
    }

    public <T> Response sendPost(String path, T body) {
        return sendRequest(path, POST, body, null, null);
    }

    public <T> Response sendPost(String path, T body, Map<String, String> headers) {
        return sendRequest(path, POST, body, null, headers);
    }

    public <T> Response sendPost(String path, T body, Map<String, ?> pathParams, Map<String, String> headers) {
        return sendRequest(path, POST, body, pathParams, headers);
    }

    public <T> Response sendPut(String path, T body) {
        return sendRequest(path, PUT, body, null, null);
    }

    public <T> Response sendPut(String path, T body, Map<String, String> headers) {
        return sendRequest(path, PUT, body, null, headers);
    }

    public <T> Response sendPut(String path, T body, Map<String, ?> pathParams, Map<String, String> headers) {
        return sendRequest(path, PUT, body, pathParams, headers);
    }

    public Response sendDelete(String path) {
        return sendRequest(path, DELETE, null, null, null);
    }

    public Response sendDelete(String path, Map<String, String> headers) {
        return sendRequest(path, DELETE, null, null, headers);
    }

    public Response sendDelete(String path, Map<String, ?> pathParams, Map<String, String> headers) {
        return sendRequest(path, DELETE, null, pathParams, headers);
    }
}

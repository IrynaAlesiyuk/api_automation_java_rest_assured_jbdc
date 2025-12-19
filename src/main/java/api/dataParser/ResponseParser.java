package api.dataParser;

import io.restassured.response.Response;

public class ResponseParser {

    public <T> T parse(Response response, Class<T> clazz) {
        return response.as(clazz);
    }

}

package tests;

import api.dataParser.ResponseParser;
import api.requests.RequestsCreator;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    RequestsCreator reqCreator;
    ResponseParser parser;
    SoftAssertions softAssert;

    @BeforeMethod
    public void setUp(){
        reqCreator = new RequestsCreator();
        softAssert =  new SoftAssertions();
        parser = new ResponseParser();
    }
}

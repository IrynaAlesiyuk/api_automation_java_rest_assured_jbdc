package tests;

import api.models.request.PostRequest;
import api.models.response.PostResponse;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;

import static common.constants.PathParams.ID;
import static common.constants.Suburls.POSTS;
import static common.constants.Suburls.POSTS_BY_ID;

public class PostApiTest extends BaseTest{


    @Test
    public void testPost() {
        //Arrange
        PostRequest expectedPost = new PostRequest();
        expectedPost.setUserId(1);
        expectedPost.setTitle("example title");
        expectedPost.setBody("example body");

        //Act
        Response postReq = reqCreator.sendPost(POSTS, expectedPost);
        PostResponse post = parser.parse(postReq, PostResponse.class);
        Response getRes = reqCreator.sendGet(POSTS_BY_ID, Map.of(ID, post.getUserId()), null);
        PostResponse postRes = parser.parse(getRes, PostResponse.class);

        //Assert
        Assert.assertEquals(postRes.getUserId(), 1,"Data doesn't match");
    }
}

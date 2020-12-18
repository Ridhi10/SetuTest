import io.restassured.response.Response;

import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;


import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.given;


public class TC004_DELETE_Request extends TestBase {

    Response response;

    @Test
    public void checkResponseBody() throws ParseException, IOException {

        {
            //Specify Base URL
            basePath = "https://garima-test.atlassian.net/rest/api/3/issue";


            //Request Object
            Response response = (Response) given()
                    .port(443)
                    .contentType("application/json")
                    .auth()
                    .basic("ggunjan2103@gmail.com", "9MG5d8zSLmbcW3wLI6JwFF5F")
                    .log().all().
                            header("Authorization", "Basic Z2d1bmphbjIxMDNAZ21haWwuY29tOjlNRzVkOHpTTG1iY1czd0xJNkp3RkY1Rg==")
                    .when()
                    .delete(basePath + testId )
                    .then()
                    .extract()
                    .response();


            //Status code validation
            Assert.assertEquals(response.getStatusCode(), 204);






        }


    }
}

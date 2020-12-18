import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

import static io.restassured.RestAssured.*;


public class TC001_GET_Request extends TestBase {

    Response response;

    @Test
    public void getData() throws ParseException, IOException, InterruptedException {


        //Specify Base URL
        basePath = "https://garima-test.atlassian.net/rest/api/3/issue";


        //Request Object
        Response response = (Response) given()
                .port(443)
                .auth()                                   //Basic Authentication
                .basic("ggunjan2103@gmail.com", "9MG5d8zSLmbcW3wLI6JwFF5F")
                .log().all().
                        header("Authorization", "Basic Z2d1bmphbjIxMDNAZ21haWwuY29tOjlNRzVkOHpTTG1iY1czd0xJNkp3RkY1Rg==")
                .when()
                .get(basePath + testId)       //GET Request
                .then()
                .extract()                               // Response extracted
                .response();

        Thread.sleep(3000);


        //Parse Json Data from Response Body
        String jsonString = response.getBody().asString();
        JSONParser jsonparser = new JSONParser();
        var obj = jsonparser.parse(jsonString);
        JSONObject ticketInfo = (JSONObject) obj;
        String keyName = (String) ticketInfo.get("key");
        //Validate Ticket Number Created
        System.out.println(new StringBuilder().append("The Ticket is:").append(keyName).toString());
        Assert.assertEquals(jsonString.contains(keyName), true);


        //Print and Validate JSON response in console window
        System.out.println("Response Body is :" + jsonString);
        Assert.assertEquals(jsonString.contains(testId), true);


        //Status Code Validation
        Assert.assertEquals(response.getStatusCode(), 200);


        //Status Line Validation
        Assert.assertEquals(response.getStatusLine(), "HTTP/1.1 200 OK");


        //Printing all headers
        Headers allHeaders = response.headers();          //capture all headers from response
        for (Header header : allHeaders) {
            System.out.println(header.getName() + "     " + header.getValue());
        }


        //Headers Validation
        Assert.assertEquals(response.getContentType(), "application/json;charset=UTF-8");
        Assert.assertEquals(response.header("content-encoding"), "gzip");
        Assert.assertEquals(response.header("Server"), "AtlassianProxy/1.15.8.1");
    }


}



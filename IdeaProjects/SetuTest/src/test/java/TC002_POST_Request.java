import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.IOException;
import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.given;


public class TC002_POST_Request extends TestBase {

    Response response;

    @Test()
    public void checkResponseBody() throws ParseException, IOException {

        {
            //Specify Base URL
            basePath = "https://garima-test.atlassian.net/rest/api/3/issue";


            //String Request Body
            String data = "{\n" +
                    "\"update\": {},\n" +
                    "\"fields\": {\n" +
                    "\"summary\": \"Main order flow broken\",\n" +
                    "\"parent\": null,\n" +
                    "\"issuetype\": {\n" +
                    "\"id\": \"10000\"\n" +
                    "},\n" +
                    "\"components\": [\n" +
                    "{\n" +
                    "\"id\": \"10000\"\n" +
                    "}\n" +
                    "],\n" +
                    "\"project\": {\n" +
                    "\"id\": \"10002\"\n" +
                    "},\n" +
                    "\"customfield_10011\": \"ST-1\",\n" +
                    "\"description\": {\n" +
                    "\"type\": \"doc\",\n" +
                    "\"version\": 1,\n" +
                    "\"content\": [\n" +
                    "{\n" +
                    "\"type\": \"paragraph\",\n" +
                    "\"content\": [\n" +
                    "{\n" +
                    "\"text\": \"Order entry fails when selecting supplier.\",\n" +
                    "\"type\": \"text\"\n" +
                    "}\n" +
                    "]\n" +
                    "}\n" +
                    "]\n" +
                    "},\n" +
                    "\"reporter\": {\n" +
                    "\"id\": \"5eb97ba3c5c6230baa5d00fe\"\n" +
                    "},\n" +
                    "\"fixVersions\": null,\n" +
                    "\"priority\": {\n" +
                    "\"id\": \"3\"\n" +
                    "},\n" +
                    "\"labels\": [\n" +
                    "\"bugfix\",\n" +
                    "\"blitz_test\"\n" +
                    "],\n" +
                    "\"assignee\": {\n" +
                    "\"id\": null\n" +
                    "}\n" +
                    "}\n" +
                    "}";


            //Request Object
            Response response = (Response) given()
                    .port(443)
                    .contentType("application/json")
                    .auth()
                    .basic("ggunjan2103@gmail.com", "9MG5d8zSLmbcW3wLI6JwFF5F")
                    .body(data)
                    .log().all().
                            header("Authorization", "Basic Z2d1bmphbjIxMDNAZ21haWwuY29tOjlNRzVkOHpTTG1iY1czd0xJNkp3RkY1Rg==")
                    .when()
                    .post(basePath )
                    .then()
                    .extract()
                    .response();


            //Parse Json Data from Response Body
            String jsonString = response.getBody().asString();
            JSONParser jsonparser = new JSONParser();
            var obj = jsonparser.parse(jsonString);
            JSONObject ticketInfo = (JSONObject)obj;
            String keyName = (String) ticketInfo.get("key");


            // Ticket Number Created
            System.out.println(new StringBuilder().append("Key is:").append(keyName).toString());


            //Status Code and Header Validation
            Assert.assertEquals(response.getStatusCode(), 201);
            Assert.assertEquals(response.getContentType(), "application/json;charset=UTF-8");
        }


    }
}

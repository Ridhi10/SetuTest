import io.restassured.response.Response;
import org.apache.log4j.Priority;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.IOException;
import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.given;


public class TC003_PUT_Request extends TestBase {

    Response response;

    @Test
    public void checkResponseBody() throws ParseException, IOException {

        {
            //Specify Base URL
            basePath = "https://garima-test.atlassian.net/rest/api/3/issue";


            //String request Body to update Priority of Ticket
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
                    "\"id\": \"4\"\n" +
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
                    .put(basePath + testId)
                    .then()
                    .extract()
                    .response();


            //Status Code and Header Validation
            Assert.assertEquals(response.getStatusCode(), 204);

//            //Print New Priority
//            //Parse Json Data from Response Body
//            String jsonString = response.getBody().asString();
//            JSONParser jsonparser = new JSONParser();
//            var obj = jsonparser.parse(jsonString);
//            JSONObject ticketInfo = (JSONObject) obj;
//            JSONArray keyName = (JSONArray) ticketInfo.get("fields");
//            //Validate Ticket Number Created
//            System.out.println(new StringBuilder().append("The Ticket is:").append(keyName).toString());
//            //Assert.assertEquals(jsonString.contains(keyName), true);


        }


    }
}

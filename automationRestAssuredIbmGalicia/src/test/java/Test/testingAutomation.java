package Test;
import Util.Json;
import Util.Url;
import io.restassured.builder.RequestSpecBuilder;
import org.json.simple.JSONObject;
import io.restassured.response.Response;
import org.json.simple.parser.JSONParser;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.baseURI;
import static org.hamcrest.Matchers.equalTo;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalToObject;
import static org.junit.Assert.assertEquals;
import java.io.File;
import java.io.FileReader;
import io.restassured.specification.RequestSpecification;
import io.restassured.module.jsv.JsonSchemaValidator;
import static org.junit.Assert.assertEquals;
import static io.restassured.RestAssured.given;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static org.junit.Assert.assertEquals;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;

public class testingAutomation {
    RequestSpecification requestSpecification;
    Response response;
    @Test
    public void test1(){
    requestSpecification = new RequestSpecBuilder().
            setBaseUri(Url.API_PUBLIC_TEST).
            setContentType(ContentType.JSON).
            build();

        response =
                given().
                        spec(requestSpecification).
                        when().
                        get("/users").
                        then().
                        extract().response();

        assertEquals("El Status Code es incorrecto: " + response.getStatusCode(), 200, response.statusCode());
        
    }

    @Test
    public void test2POst (){
        requestSpecification = new RequestSpecBuilder().
                setBaseUri(Url.API_PUBLIC_TEST).
                setContentType(ContentType.JSON).
                build();

        Json json = new Json();
        JSONObject jsonObject = json.jsontest();

         String requestbody  = given().relaxedHTTPSValidation()
                .body(jsonObject.toString())
                .when()
                .post("/users")
                .then()
                .body(matchesJsonSchemaInClasspath(" schemas/schemaTest.json")).toString();
         System.out.println(requestbody);
        }

}

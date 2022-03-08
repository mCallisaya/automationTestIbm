package Test;

import org.json.simple.JSONObject;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static org.hamcrest.Matchers.equalTo;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalToObject;
import java.io.File;

import io.restassured.module.jsv.JsonSchemaValidator;

public class testingAutomation {
    @Test
    public void test1() {
        baseURI= "https://reqres.in/api";
        String body = given()
                .when()
                .get("/users")
                .then()
                .body("data[3].first_name",equalTo("Eve"))
                .statusCode(200)
                .extract().body().asString();
        System.out.println(body);

    }
    @Test
    public void test2POst (){
        baseURI= "https://reqres.in/api";
        //  Map<String, Object>  map = new HashMap<String,Object>();
        // map.put("name","morpheus");
        //    map.put("jod","leader");
        JSONObject requestBody = new JSONObject();
        requestBody.put("name","morpheus");
        requestBody.put("jod","leader");
        String body = given()
                .body(requestBody.toString())
                .log().all()
                .when()
                .post("/users")
                .then()
                .assertThat().body(JsonSchemaValidator.matchesJsonSchema(new File("")))
                .statusCode(201)
                .extract().body().asString();
                System.out.println(body);
        //comentario
        //comentario2
    }


}

package back.step;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import static io.restassured.RestAssured.given;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class testPruebaEze {
    
    RequestSpecification requestSpecification;
    Response response;

    @Test
    public void testGET() {

        requestSpecification = new RequestSpecBuilder().
                setBaseUri("https://reqres.in/api").
                setContentType(ContentType.JSON).
                build();

        response = 
            given().
                spec(requestSpecification).
            when().
                get("/users").
            then().
                log().all().
                extract().response();

        assertEquals("El Status Code es incorrecto: " + response.getStatusCode(), 200, response.statusCode());

    }
}

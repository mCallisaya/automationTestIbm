package back.step;

import static org.junit.Assert.assertEquals;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;

import static io.restassured.RestAssured.given;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import mapeo.Usuario;
import util.Url;

public class testPruebaEze {
    
    RequestSpecification requestSpecification;
    Response response;

    @Test
    public void testGET() {

        requestSpecification = new RequestSpecBuilder().
                setBaseUri(Url.URL_API).
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

    @Test
    public void testPost(){
        
        String name = "Lautaro";
        String job = "Programador";

        requestSpecification = new RequestSpecBuilder().
                setBaseUri(Url.URL_API).
                setContentType(ContentType.JSON).
                build();

        Usuario requestBody = (new Usuario(name, job));

        response = 
            given().
                spec(requestSpecification).
                body(requestBody).
            when().
                post("/users").
            then().
                log().all().
                body(matchesJsonSchemaInClasspath("schemas/schemaPost.json")).
                body("name", equalTo(name)).
                body("job", equalTo(job)).
                extract().response();

        assertEquals("El Status Code es incorrecto: " + response.getStatusCode(), 201, response.statusCode());

    }
}

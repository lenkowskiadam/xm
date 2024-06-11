package tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class APITest {

    private static final String BASE_URI = "https://swapi.dev/api";

    @Test
    public void testSearchForPersonWithNameVader() {

        //GIVEN user search for a person with the name Vader
        RestAssured.baseURI = BASE_URI;
        Response response = given()
                .queryParam("search", "Vader")
                .when()
                .get("/people");
        response.prettyPrint();
        Assert.assertEquals("Status code should be 200", 200, response.statusCode());
        String name = response.jsonPath().getString("results[0].name");
        Assert.assertEquals("Name should be Darth Vader", "Darth Vader", name);
    }
}

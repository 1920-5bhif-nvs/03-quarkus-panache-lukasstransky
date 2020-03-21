import at.htl.leonding.model.AnimalShelter;
import at.htl.leonding.model.Cage;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.assertj.core.api.ListAssert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import static org.assertj.core.api.Assertions.*;

@QuarkusTest
public class RestAssuredTests {

    private static RequestSpecification spec;

    @BeforeAll
    public static void setup(){
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8080;

        spec = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBaseUri("http://localhost:8080/")
                .addFilter(new ResponseLoggingFilter())//log request and response for better debugging. You can also only log if a requests fails.
                .addFilter(new RequestLoggingFilter())
                .build();
    }

    @Test
    public void test01_get(){
        when().get("/shelter/panache").then().statusCode(200);

        AnimalShelter animalShelter =
                when().get("/shelter/panache/pet?id=1")
                .then().statusCode(200).extract().as(AnimalShelter.class);

        List<AnimalShelter> animalShelterList =
                when().get("/shelter/panache")
                .then().statusCode(200)
                .extract().as(List.class);

        /*AnimalShelter animalShelter3 = given().queryParam("id", "1")
                .when().get("/shelter/panache").then().extract().as(AnimalShelter.class);;*/
                //.then().statusCode(200);
    }

    @Test
    public void test02_post(){
        AnimalShelter animalShelter1 = new AnimalShelter();
        animalShelter1.setPost_code(1234);
        animalShelter1.setStreet("blabla");
        animalShelter1.setTown("jojo");
        given().contentType(ContentType.JSON)
                .body(animalShelter1)
                .post("/shelter/panache")
                .then().statusCode(200);
    }

    @Test
    public void test03_delete(){
        given().queryParam("id", "5")
                .when().delete("/shelter/panache");
    }

    @Test
    public void test04_jsonpath(){
        //JsonPath retrievedCages = when().get("/cage/panache/").then().statusCode(200).extract().jsonPath();
        //assertThat(retrievedCages.getList("cage")).isNotEmpty();
        //assertThat(retrievedCages.getInt("count")).isGreaterThan(2);

        JsonPath jsonPath = new JsonPath("{\"blogs\":[\"posts\":[{\"author\":{\"name\":\"Paul\"}}]]}");
        JsonPath retrievedBlogs = given()
                .spec(spec)
                .when()
                .get("blogs")
                .then()
                .statusCode(200)
                .extract().jsonPath();
        assertThat(retrievedBlogs.getInt("count")).isGreaterThan(7);
        assertThat(retrievedBlogs.getList("blogs")).isNotEmpty();
        int x = 1;
    }

    @Test
    public void testjsonpath(){
        JsonPath jsonPath = when().get("/shelter/panache")
                .then().statusCode(200).extract().jsonPath();
        int x = 1;
    }
}

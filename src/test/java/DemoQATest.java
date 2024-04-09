import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.HashMap;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;


public class DemoQATest {
    HashMap<Integer, String> metin = new HashMap<>();


    @BeforeClass
    public void data(){
        baseURI = "https://reqres.in/api/users/2";

    }

    @Test
    public void firstBddTestScenario() {
        given()
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .statusCode(200)
                .log().body();
    }

    @Test
    public void singleUserBddTest() {
        baseURI = "https://reqres.in/api/users/2";

        given()
                .contentType("application/json");
        when()
              .get(baseURI)
              .then()
              .statusCode(200)
              .body("data.email",equalTo("janet.weaver@reqres.in"))
              .time(lessThan(2000L));
    }

@Test
    public void userControlTest() {
    given()
            .contentType("application/json");
    when()
            .get("https://reqres.in/api/users/2")
            .then()
            .statusCode(200)
            .body("data.first_name",equalTo("Janet"))
            .body("data.last_name",equalTo("Weaver"))
            .time(lessThan(2000L))
            .log().body();
}
    @Test
    public void createUserTest2() {
        baseURI = "https://reqres.in/api";
        HashMap<String, Object> data = new HashMap<>();
        data.put("name", "Murat");
        data.put("job", "Tester");

        given()
                .body(data)
                .when()
                .post("/users")
                .then()
                .statusCode(201);

    }

    @Test
    public void updateUserTest() {
        baseURI = "https://reqres.in/api";
        HashMap<String, Object> data = new HashMap<>();
        data.put("name", "Murat");
        data.put("job", "Tester");

        given()
                .contentType("application/json")
                .body(data)
                .when()
                .put("/users/2")
                .then()
                .statusCode(200)
                .log().body();
    }


    }


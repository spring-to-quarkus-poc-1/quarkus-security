package br.com.company.quarkus.security.it;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class QuarkusSecurityResourceTest {

    @Test
    public void testHelloEndpoint() {
        given()
                .when().get("/quarkus-security")
                .then()
                .statusCode(200)
                .body(is("Hello quarkus-security"));
    }
}

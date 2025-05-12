package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

public class LoginSteps {

    Response response;

    @Given("the service is available")

    public void the_services_is_available(){
       baseURI= "http://localhost:8080/api";
    }

    @When("the client sends username {string} and password {string}")
    public void the_cliente_sends_username_and_password(String username,String password){

        Map<String, String> credentials = new HashMap<>();
        credentials.put("username", username);
        credentials.put("password", password);

        response = given()
                .contentType("application/json")
                .body(credentials)
                .when()
                .post("/login");

    }

    @Then("the response should have status code {int}")
    public void the_response_should_have_status_code(int statusCode){
        response.then().statusCode(statusCode);
    }

    @And("the body should contain {string}")
    public void the_body_should_contain(String expectedTest){
        assertTrue(response.getBody().jsonPath().get(expectedTest) != null);

    }

    @And("the response body should contain key {string}")
    public void the_response_body_should_contain_key(String expectedKey){
        assertTrue(response.getBody().jsonPath().get(expectedKey) != null);
    }

    @And("the response body's {string} should be {string}")
    public void verifyBodyValue(String key, String expectedValue) {
        assertEquals(expectedValue, response.getBody().jsonPath().getString(key));
    }

    @And("the response body's title {string} should be {string}")
    public void the_response_body_should_be_null(String key,String expectedValue) {
        assertEquals(expectedValue,response.getBody().jsonPath().get(key));
    }

}

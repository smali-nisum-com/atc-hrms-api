package atc.hrms.api.steps;


import atc.hrms.api.steps.serenity.ProductDetailsSteps;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import net.thucydides.core.annotations.Steps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import static atc.hrms.api.constant.TestConstant.BASEPATH;
import static io.restassured.RestAssured.basePath;
import static org.junit.Assert.assertEquals;

/**
 * Created by smali on 14/12/2017.
 */
public class ProductDetailsStepDefinition {
    private static final Logger log = LoggerFactory.getLogger(ProductDetailsStepDefinition.class);

    @Steps
    ProductDetailsSteps productDetailsSteps;

    private String url;
    private Response authToken;
    private String accessToken;
    private Response response;

    @Before
    public void beforeScenarios() throws IOException {
        url = BASEPATH + "/auth/login";
        authToken = productDetailsSteps.authApi(url);
        assertEquals(200, authToken.getStatusCode());
        accessToken = authToken.getBody().path("access_token").toString();

    }

    @Given("^url is \"([^\"]*)\"$")
    public void urlIs(String endpoint) {
        url = BASEPATH + endpoint;
    }

    @When("^get request is made$")
    public void get_request_is_made() {
        response = productDetailsSteps.productApi(url, accessToken);
    }

    @Then("^statuscode should be (\\d+)$")
    public void statuscode_should_be(int statuscode) {
        assertEquals(statuscode, response.getStatusCode());
    }

    @When("^post request is made$")
    public void post_request_is_made() throws IOException {
        response = productDetailsSteps.authApi(url);
    }

}

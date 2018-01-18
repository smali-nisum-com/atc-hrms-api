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

import static org.junit.Assert.assertEquals;

/**
 * Created by smali on 14/12/2017.
 */
public class ProductDetailsStepDefinition {
    private static final Logger log = LoggerFactory.getLogger(ProductDetailsStepDefinition.class);

    @Steps
    ProductDetailsSteps productDetailsSteps;

    private String url;
    private String basePath;
    private Response authToken;
    private String accessToken;
    private Response response;

    @Before
    public void beforeScenarios() {
        RestAssured.useRelaxedHTTPSValidation();
        basePath = "http://10.4.10.108:8080";
        url = basePath + "/auth/login";
        log.info("url" + url);
        authToken = productDetailsSteps.authApi(url);
        assertEquals(200, authToken.getStatusCode());
        accessToken = authToken.getBody().path("access_token").toString();

    }

    @Given("^url is \"([^\"]*)\"$")
    public void urlIs(String endpoint) {

        url = basePath + endpoint;
        log.info("endpoint" + url);
    }

    @When("^get request is made$")
    public void get_request_is_made() {
        log.info("access token when" + accessToken + url);

        response = productDetailsSteps.productApi(url, accessToken);
        log.info("response of api" + response.asString());
    }

    @Then("^statuscode should be (\\d+)$")
    public void statuscode_should_be(int statuscode) {

        assertEquals(statuscode, response.getStatusCode());

    }

    @When("^post request is made$")
    public void post_request_is_made() {

        response = productDetailsSteps.authApi(url);

    }

}

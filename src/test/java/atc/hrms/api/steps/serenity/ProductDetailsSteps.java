package atc.hrms.api.steps.serenity;


import io.restassured.RestAssured;
import io.restassured.response.Response;
import net.thucydides.core.annotations.Step;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static net.serenitybdd.rest.SerenityRest.rest;

/**
 * Created by smali on 14/12/2017.
 */
public class ProductDetailsSteps {

    private static final Logger logger = LoggerFactory.getLogger(ProductDetailsSteps.class);

    @Step
    public Response productApi(String endpoint, String token) {

        DateTime dtBeforeAPIRequest = new DateTime();
        long current = dtBeforeAPIRequest.getMillis();

        RestAssured.useRelaxedHTTPSValidation();

        Response responseProductInformation = rest().
                given().
                auth()
                .oauth2(token).
                contentType("application/json").
                when().
                get(endpoint);

        DateTime dtAfterResponse = new DateTime();
        logger.info("Response Time - api Information: "
                + Long.toString(dtAfterResponse.getMillis() - current) + " ms");

        return responseProductInformation;
    }

    @Step
    public Response authApi(String apiEndpoint) {

        DateTime dtBeforeAPIRequest = new DateTime();
        long current = dtBeforeAPIRequest.getMillis();

        Response responseProductInformation = rest().
                given().
                contentType("application/json").
                when().
                body("{\"username\":\"shahmed@nisum.com\",\"password\":\"sameer\"}").
                post(apiEndpoint);

        DateTime dtAfterResponse = new DateTime();
        logger.info("Response Time Authetication - api Information: "
                + Long.toString(dtAfterResponse.getMillis() - current) + " ms");

        return responseProductInformation;
    }
}

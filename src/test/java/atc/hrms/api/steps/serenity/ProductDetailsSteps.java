package atc.hrms.api.steps.serenity;


import connection.JdbcCon;
import io.restassured.response.Response;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;
import org.apache.commons.io.IOUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.Charset;

import static net.serenitybdd.rest.SerenityRest.rest;

/**
 * Created by smali on 14/12/2017.
 */
public class ProductDetailsSteps {

    private static final Logger logger = LoggerFactory.getLogger(ProductDetailsSteps.class);

    @Steps
    JdbcCon jdbcCon;

    String loadFile(String path) throws IOException {
        return IOUtils.toString(ProductDetailsSteps.class.getClassLoader().getResourceAsStream(path), Charset.forName("UTF-8"));
    }


    @Step
    public Response productApi(String endpoint, String token) {

        DateTime dtBeforeAPIRequest = new DateTime();
        long current = dtBeforeAPIRequest.getMillis();

        // Avoid SSL validation if persist
      //  RestAssured.useRelaxedHTTPSValidation();

        Response responseProductInformation = rest().
                given().
                contentType("application/json").
                when().
                get(endpoint);

        DateTime dtAfterResponse = new DateTime();
        logger.info("Response Time - api Information: "
                + Long.toString(dtAfterResponse.getMillis() - current) + " ms");

        return responseProductInformation;
    }

    @Step
    public Response authApi(String apiEndpoint) throws IOException {

        DateTime dtBeforeAPIRequest = new DateTime();
        long current = dtBeforeAPIRequest.getMillis();

        String json = loadFile("json/authtoken.json");

        Response responseProductInformation = rest().
                given().
                contentType("application/json").
                when().
                body(json).
                post(apiEndpoint);

        DateTime dtAfterResponse = new DateTime();
        logger.info("Response Time Authetication - api Information: "
                + Long.toString(dtAfterResponse.getMillis() - current) + " ms");

        return responseProductInformation;
    }


}

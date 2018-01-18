package atc.hrms.api;

import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

/**
 * Created by smali on 14/12/2017.
 */
@RunWith(CucumberWithSerenity.class)
@CucumberOptions(features="src/test/resources/features")
public class ProductDetailsRunner {
}

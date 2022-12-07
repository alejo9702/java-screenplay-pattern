package runners;


import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import static net.serenitybdd.core.Serenity.getDriver;


@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "classpath:features/test.feature",
        glue = {"stepdefinitions"},
        snippets = CucumberOptions.SnippetType.CAMELCASE
)
public class RunnerTest {
    private RunnerTest() {
        super();
    }

    @AfterClass
    public static void afterClass() {
        getDriver().close();
        getDriver().quit();
    }
}

package stepdefinitions;


import exceptions.Exception;
import interactions.GoToInsights;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.Blog;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;
import questions.Validation;
import tasks.SubscribeToNewsLetter;
import tasks.SearchTheBlog;
import userinterface.BlogsBlankFactor;
import utils.RandomData;

import java.time.Duration;
import java.util.List;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;
import static net.serenitybdd.screenplay.questions.WebElementQuestion.the;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.containsOnlyText;
import static userinterface.BlogsBlankFactor.*;


public class TestStepDefinitions {
    private static Blog blog;

    @Given("^the user (.*) go to blankFactor page and go to blogs section$")
    public void theUserGoToBlankFactorPageAndGoToBlogsSection(String userName) {
        theActorCalled(userName).attemptsTo(
            GoToInsights.openPage()
        );

        theActorCalled(userName).attemptsTo(
            GoToInsights.acceptCookies(),
            GoToInsights.intoBlogSection()
        );
    }

    @When("^try to search the blog (.*) By (.*)$")
    public void tryToSearchTheBlogBlogNameByAuthor(String blogTitle, String blogAuthor) {
        blog = new Blog(blogTitle, blogAuthor);
        theActorInTheSpotlight().attemptsTo(
                SearchTheBlog.withNameAndAuthor( blog.getBlogName(), blog.getBlogAuthor() )
        );
    }


    @Then("should be in the correct URL and have main title equal to the blog name$")
    public void shouldBeInTheCorrectURLAndHaveMainTitleEqualToTheBlogName() {
        theActorInTheSpotlight().should(seeThat(Validation.blogResume(blog.getBlogName()))
                .orComplainWith(Exception.class, Exception.ERROR_BLOG_PAGE));
        blog.setBlogTopic(BLOG_CATEGORY.resolveFor(theActorInTheSpotlight()).getText());
    }

    @And("Subscribe to the newsletter using the subscribe form with the email$")
    public void subscribeToTheNewsletterUsingTheSubscribeFormWithTheEmail() {
        RandomData randoEmail = new RandomData();
        theActorInTheSpotlight().attemptsTo(
            SubscribeToNewsLetter.withTheEmail(randoEmail.generateRandomEmail()),
            WaitUntil.the( SUBSCRIBE_RESULT_MESSAGE, isVisible() )
        );
        final String SUBSCRIBE_MESSAGE = "Thank you for subscribing! Stay tuned.";
        theActorInTheSpotlight().should(
            seeThat( the( BlogsBlankFactor.SUBSCRIBE_RESULT_MESSAGE ), containsOnlyText( SUBSCRIBE_MESSAGE ) )
        );
    }

    @Then("Go back to the Blog section and print a list of the all posts titles with related links.")
    public void goBackToTheBlogSectionAndPrintAListOfTheAllPostsTitlesWithRelatedLinks() {
        theActorInTheSpotlight().attemptsTo(
                GoToInsights.intoBlogSection(),
                Click.on(BLOG_LIST_TOPICS.of(blog.getBlogTopic()).waitingForNoMoreThan(Duration.ofSeconds(10)))
        );

        List<WebElementFacade> postList = BlogsBlankFactor.BLOG_TITLE_WITH_TOPIC.resolveAllFor(theActorInTheSpotlight());
        StringBuilder result = new StringBuilder();
        for (WebElementFacade element : postList) {
            result.append(element.getText()).append(" ----------------------> ").append(element.getAttribute("href")).append("\n");
        }
        System.out.println(result);
    }


}


package interactions;


import net.serenitybdd.core.exceptions.NoSuchElementException;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.HoverOverTarget;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import userinterface.BlankFactorPage;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.*;
import static userinterface.BlankFactorPage.*;

public class GoToInsights implements net.serenitybdd.screenplay.Interaction {
    private enum Action {
        OPEN, BLOG, COOKIES;
    }
    private Action action;
    private GoToInsights(Action action ) {
        this.action = action;
    }

    @Override
    public <T extends Actor> void performAs( T actor ) {
        final String BLOG_SECTION = "Blog";

        if ( action == Action.OPEN ) {
            actor.wasAbleTo(
                Open.browserOn().the(BlankFactorPage.class)
            );
        }

        if ( action == Action.BLOG ) {
            actor.attemptsTo(
                WaitUntil.the( INSIGHTS_TAB, isClickable() ),
                HoverOverTarget.over( INSIGHTS_TAB ),
                Click.on( BlankFactorPage.BLOG_SECTION.of( BLOG_SECTION ) ).afterWaitingUntilEnabled()
            );
        }

        if ( action == Action.COOKIES ) {
            try {
                actor.attemptsTo(
                    WaitUntil.the(ACCEPT_COOKIES_BUTTON, WebElementStateMatchers.isPresent()),
                    WaitUntil.the(ACCEPT_COOKIES_BUTTON, isClickable()),
                    Click.on(ACCEPT_COOKIES_BUTTON),
                    WaitUntil.the(ACCEPT_COOKIES_BUTTON, WebElementStateMatchers.isNotCurrentlyVisible())
                );
            } catch (TimeoutException | NoSuchElementException | StaleElementReferenceException | AssertionError ignored ) {
                // Do nothing
            }
        }
    }
    /**
     * goes to an specific section
     * @return an interaction
     */
    public static GoToInsights acceptCookies() {
        return new GoToInsights( Action.COOKIES);
    }
    public static GoToInsights intoBlogSection() {
        return new GoToInsights( Action.BLOG );
    }

    public static GoToInsights openPage() {
        return new GoToInsights( Action.OPEN );
    }
}

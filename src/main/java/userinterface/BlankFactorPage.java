package userinterface;


import net.serenitybdd.screenplay.targets.Target;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;


@DefaultUrl("https://blankfactor.com")
public class BlankFactorPage extends PageObject {
    public static final Target ACCEPT_COOKIES_BUTTON = Target.the("accept cookies button").locatedBy("//a[@id='hs-eu-confirmation-button']");
    public static final Target INSIGHTS_TAB = Target.the("insights tab section").locatedBy("//li[@id='menu-item-4436']//span[contains(text(),'Insights')]");
    public static final Target BLOG_SECTION = Target.the("Blog section").locatedBy("//h4[contains(text(),'Insights')]//following::p[text()=' {0}']");
}

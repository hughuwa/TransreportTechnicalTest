package stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import pages.SaucedemoHomepage;
import pages.SaucedemoProductPage;
import pages.SaucedemoShoppingCartPage;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class SaucedemoStepdef {

    WebDriver driver;

    @Before
    public void Initialize() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        DesiredCapabilities dp = new DesiredCapabilities();
        dp.setCapability(ChromeOptions.CAPABILITY, options);
        driver = new ChromeDriver(options);

        driver.manage().window().maximize();


    }

    @After
    public void Teardown() {
        driver.close();
        driver.quit();
    }

    @Given("I am on the saucedemo home page {string}")
    public void i_am_on_the_saucedemo_home_page(String url) {
        driver.navigate().to(url);
    }

    @When("I login with the following details")
    public void i_login_with_the_following_details(List<List<String>> dataTable) {
        // Write code here that turns the phrase above into concrete actions
        // For automatic transformation, change DataTable to one of
        // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
        // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
        // Double, Byte, Short, Long, BigInteger or BigDecimal.
        //
        // For other transformations you can register a DataTableType.
        SaucedemoHomepage saucedemoHomepage = new SaucedemoHomepage(driver);
        saucedemoHomepage.enterUsername(dataTable.get(1).get(0));
        saucedemoHomepage.enterPassword(dataTable.get(1).get(1));
    }

    @When("I click on the login button")
    public void i_click_on_the_login_button() {
        // Write code here that turns the phrase above into concrete actions
        SaucedemoHomepage saucedemoHomepage = new SaucedemoHomepage(driver);
        saucedemoHomepage.clickLoginButton();
    }

    @Then("{string} page is displayed")
    public void page_is_displayed(String pageName) {
        SaucedemoProductPage saucedemoProductPage = new SaucedemoProductPage(driver);
        assertThat(saucedemoProductPage.getPageTitleName(), equalTo(pageName));
    }

    @Then("{string} error message is displayed")
    public void errorMessageIsDisplayed(String errorMessage) {
        SaucedemoHomepage saucedemoHomepage = new SaucedemoHomepage(driver);
        assertThat(saucedemoHomepage.getErrroMessage().contains(errorMessage), equalTo(true));
    }

    @Then("the number of items displayed on the Products page is {int}")
    public void the_number_of_items_displayed_on_the_products_page_is(Integer total) {
        SaucedemoProductPage saucedemoProductPage = new SaucedemoProductPage(driver);
        assertThat(saucedemoProductPage.getNumberOfItems(), equalTo(total));
    }

    @When("I filter by low to high price")
    public void i_filter_by_low_to_high_price() {
        SaucedemoProductPage saucedemoProductPage = new SaucedemoProductPage(driver);
        saucedemoProductPage.selectFilterLowToHigh();
    }

    @Then("The first item has lowest price and the last item has highest price")
    public void the_first_item_has_lowest_price_and_the_last_item_has_highest_price() {
        SaucedemoProductPage saucedemoProductPage = new SaucedemoProductPage(driver);
        assertThat(saucedemoProductPage.getFirstItemsPrice().contains("7.99"), equalTo(true));
        assertThat(saucedemoProductPage.getLastItemsPrice().contains("49.99"), equalTo(true));
    }


    @When("I add three items to basket")
    public void i_add_three_items_to_basket() {
        SaucedemoProductPage saucedemoProductPage = new SaucedemoProductPage(driver);
        saucedemoProductPage.addBackpackToCart();
        saucedemoProductPage.addTShirtToCart();
        saucedemoProductPage.addOnesieToCart();
    }

    @Then("{int} items are shown in the basket")
    public void items_are_shown_in_the_basket(Integer int1) {
        SaucedemoProductPage saucedemoProductPage = new SaucedemoProductPage(driver);
        saucedemoProductPage.clickOnShoppingCart();
        SaucedemoShoppingCartPage saucedemoShoppingCartPage = new SaucedemoShoppingCartPage(driver);
        assertThat(saucedemoShoppingCartPage.getTotalItemsInShoppingCart(), equalTo(int1));
    }

    @When("I remove one item from the basket")
    public void i_remove_one_item_from_the_basket() {
        SaucedemoShoppingCartPage saucedemoShoppingCartPage = new SaucedemoShoppingCartPage(driver);
        saucedemoShoppingCartPage.removeAnItemFromShoppingCart();
    }

    @Then("number of items in the basket reduces to {int}")
    public void number_of_items_in_the_basket_reduces_to(Integer int1) {
        SaucedemoShoppingCartPage saucedemoShoppingCartPage = new SaucedemoShoppingCartPage(driver);
        assertThat(saucedemoShoppingCartPage.getTotalItemsInShoppingCart(), equalTo(int1));
    }

}

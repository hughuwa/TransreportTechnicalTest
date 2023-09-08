package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SaucedemoShoppingCartPage {

    WebDriver driver;

    By cartItems = By.cssSelector(".cart_item_label");
    By removeButtons = By.cssSelector(".btn.btn_secondary.btn_small.cart_button");



    public SaucedemoShoppingCartPage(WebDriver driver ) {this.driver = driver; }

    public int getTotalItemsInShoppingCart(){
        return driver.findElements(cartItems).size();
    }

    public void removeAnItemFromShoppingCart(){
        List<WebElement> ele = driver.findElements(removeButtons);
        for (WebElement el:ele
             ) {el.click();
            break;

        }
    }
}

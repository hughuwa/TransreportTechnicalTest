package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class SaucedemoProductPage {

    WebDriver driver;

    By title = By.cssSelector("span.title");
    By inventory = By.cssSelector(".inventory_item_description");
    By filter = By.cssSelector(".product_sort_container");
    By inventoryItemPrice = By.cssSelector(".inventory_item_price");
    By addToCartButton_Backpack = By.cssSelector("#add-to-cart-sauce-labs-backpack");
    By addToCartButton_TShirt = By.cssSelector("#add-to-cart-sauce-labs-bolt-t-shirt");
    By addToCartButton_Onesie = By.cssSelector("#add-to-cart-sauce-labs-onesie");
    By shoppingCartLink = By.cssSelector(".shopping_cart_link");


    public SaucedemoProductPage(WebDriver driver ) {this.driver = driver; }


    public String getPageTitleName(){
        return driver.findElement(title).getText();
    }

    public int getNumberOfItems(){
        return driver.findElements(inventory).size();
    }
    public void selectFilterLowToHigh(){
        Select select = new Select(driver.findElement(filter));
        select.selectByValue("lohi");
    }
    public String getFirstItemsPrice(){
        return driver.findElements(inventoryItemPrice).get(0).getText();
    }
    public String getLastItemsPrice(){
        return driver.findElements(inventoryItemPrice).get(5).getText();
    }
    public void addBackpackToCart(){
        driver.findElement(addToCartButton_Backpack).click();
    }
    public void addTShirtToCart(){
        driver.findElement(addToCartButton_TShirt).click();
    }
    public void addOnesieToCart(){
        driver.findElement(addToCartButton_Onesie).click();
    }
    public void clickOnShoppingCart(){
        driver.findElement(shoppingCartLink).click();
    }
}

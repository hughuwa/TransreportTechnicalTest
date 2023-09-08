package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SaucedemoHomepage {

    WebDriver driver;

    By userName = By.id("user-name");
    By passWord = By.id("password");
    By loginButton = By.id("login-button");
    By errorMessage = By.xpath("//h3[contains(@data-test,'error')]");


    public SaucedemoHomepage(WebDriver driver ) {this.driver = driver; }

    public void enterUsername(String uName){
        driver.findElement(userName).sendKeys(uName);
    }

    public void enterPassword(String pWord){
        driver.findElement(passWord).sendKeys(pWord);
    }

    public void clickLoginButton (){
       driver.findElement(loginButton).click();
    }
    public String getErrroMessage(){
        return driver.findElement(errorMessage).getText();
    }
}

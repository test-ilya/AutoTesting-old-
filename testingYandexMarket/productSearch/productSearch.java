import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static java.lang.Thread.sleep;

public class productSearch  {
    //под куки для быстрой авторизации (заполнить!)
    private String nameCookie = "";
    private String valueCookie = "";
    //======
    private String url = "https://market.yandex.ru/?loggedin=1";
    private By searchString = By.xpath("//input[@id='header-search']");
    private By searchBtn = By.xpath("//button[@class='V9Xu6 button-focus-ring _3RXxZ _1LG7Q _3iB1w mini-suggest__button']");
    private String productName1 = "домик для кошки";
    private String productName2 = "прикормка для сусликов";
    private String productName3 = "73213213131131";

    @Test
    public void productSearch() throws InterruptedException{

        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        driver.get(url);
        Cookie getCookie = new Cookie(nameCookie, valueCookie);
        driver.manage().addCookie(getCookie);
        driver.navigate().refresh();

        driver.findElement(searchString).sendKeys(productName1);
        driver.findElement(searchBtn).click();
        try {
            Boolean text = driver.findElement(
                            By.xpath("//span[contains(text(),'кошк')]"))
                    .isDisplayed();
            System.out.println("Test 1:\n" + productName1 + " found\n");
        } catch (NoSuchElementException e) {
            System.out.println("Test 1:\n" + productName1 + " not found\n");
        }
        sleep(1000);

        driver.get(url);
        driver.findElement(searchString).sendKeys(productName2);
        driver.findElement(searchBtn).click();
        try {
            Boolean text = driver.findElement(
                    By.xpath("//span[contains(text(),'сусл')]"))
                    .isDisplayed();
            System.out.println("Test 2:\n" +productName2 + " found\n");
        } catch (NoSuchElementException e) {
            System.out.println("Test 2:\n" + productName2 + " not found\n");
        }
        sleep(1000);

        driver.get(url);
        driver.findElement(searchString).sendKeys(productName3);
        driver.findElement(searchBtn).click();
        try {
            Boolean text = driver.findElement(
                            By.xpath("//span[contains(text(),'732')]"))
                    .isDisplayed();
            System.out.println("Test 3:\n" + productName3 + " found\n");
        } catch (NoSuchElementException e) {
            System.out.println("Test 3:\n" + productName3 + " not found\n");
        }
        
    }
}

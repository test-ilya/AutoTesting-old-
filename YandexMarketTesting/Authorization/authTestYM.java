import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static java.lang.Thread.sleep;

public class authTestYM {
    private String login = "Testovich";
    private String password = "Kukish";
    //===========================
    private String url = "https://market.yandex.ru/";
    private By btnCloseAdv = By.xpath("//div[@class='_2Mdur']" +
            "//div[@class='cia-cs']" +
            "//button[@class='_1KpjX _3lvec _38a54']");
    private By btnEnter = By.xpath("//div[@id='USER_MENU_ANCHOR']" +
            "//div[@class='cia-vs cia-cs']" +
                    "//descendant::a");
    private By inputLogin = By.xpath("//div[@class='Field-inputWrapper']" +
            "//input[@id='passp-field-login']");
    private By btnEnterYaId = By.xpath("//div[@class='passp-button passp-sign-in-button']" +
            "//button[@id='passp:sign-in']");
    private By inputPass = By.xpath(
            "//div[@class='Field-inputWrapper']" +
            "//input[@id='passp-field-passwd']");

    @Test
    public void authTestYM() throws InterruptedException {

        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(url);
        driver.findElement(btnCloseAdv).click();
        driver.findElement(btnEnter).click();
        driver.findElement(btnEnterYaId).click();
        sleep(2000);
        driver.findElement(inputLogin).sendKeys(login);
        sleep(1000);
        driver.findElement(btnEnterYaId).click();
        driver.findElement(inputPass).click();
        driver.findElement(inputPass).sendKeys(password);
        sleep(1000);
        driver.findElement(btnEnterYaId).click();
    }


}

import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import java.time.Duration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Thread.sleep;

public class priceFilter {
    //под куки для быстрой авторизации (заполнить!)
    private String nameCookie = "";
    private String valueCookie = "";
    private String url = "https://market.yandex.ru/catalog--literatura-na-kitaiskom-iazyke/20791430/list?hid=18540910&rs=eJwdkC8LwlAUxd9YEQ0aVFhbEoNFo39gcRZR8AMs-Q2GwfIQg8FmMFiWtA2LMJniDBbBYBFNDgTNfgDRd045XH73vnMOrzLU29pRE3GvoVTMakrNd12ppW-VyjyINQGJMlCnH4KMwGVAMoUKE_fCxVY-QGTIyzVvStg6K5Dogtm0STrIlQXe8K04Y45DOqQDpZ5gNx_EvCI3HuwxJzd41QD3unTw2dnGfaxDrRT9JR1abPhjW4NNxnCLXGZ9mct0eeB2Tud7FVlL-t_4Mx92ePFPiuTGDqTMt2zrJLhdMCvH-Yl0T9KhySYnpDjZ2h_idoTN&allowCollapsing=1&local-offers-first=0";
    private By inputFrom = By.xpath("//input[@class='_3qxDp _37_h4']");
    private By inputUpto = By.xpath("//input[@class='_3qxDp _1R_cW']");
    private By btnFilterClose = By.xpath("//button[@class='_2AMPZ _2reIv _2PrFr _1E9UV _5QS0d']");
    private By btnFilterCloseAll = By.xpath("//button[@class='_2AMPZ _1N_0H _3_b2k']");
    private By priceProduct = By.xpath("//span[@class='_8-sD9']");
    private By priceUpto = By.xpath("//label[@class='bUKrx _1R_cW']");
    private By priceFrom = By.xpath("//label[@class='bUKrx _37_h4']");
    private int price;
    private int priceInput;
    private String priceT3 = "";
    private String priceT4 = "";

    @Test
    public void priceFilter() throws InterruptedException {

        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        driver.get(url);
        Cookie getCookie = new Cookie(nameCookie, valueCookie);
        driver.manage().addCookie(getCookie);
        driver.navigate().refresh();

        //=========Test1
        driver.findElement(inputFrom).sendKeys("300");
        driver.findElement(inputUpto).sendKeys("500");
        sleep(3000);
        String getPriceProduct1 = driver.findElement(priceProduct).getText();
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(getPriceProduct1);
        while (matcher.find()) {
            price = Integer.parseInt(matcher.group());
//            System.out.println(price);
        }
        if(price > 300 && price < 500){
            System.out.println("Test 1 completed");
        } else{
            System.out.println("Test 1 failed");
        }
        driver.findElement(btnFilterClose).click();
        sleep(3000);
//
//        //=========Test2
        driver.findElement(inputUpto).sendKeys("0");
        sleep(3000);
        try {
            boolean text = driver.findElement(By.xpath("//*[contains(text(),'Нет подходящих товаров')]"))
                    .isDisplayed();
            System.out.println("Test 2 completed");
        } catch (NoSuchElementException e){
            System.out.println("Test 2 failed");
        }
        driver.findElement(btnFilterClose).click();
        sleep(3000);

        //=========Test3
        String priceUpTo3 = driver.findElement(priceUpto).getText();
        driver.findElement(inputFrom).sendKeys(priceUpTo3);
        sleep(3000);
        String p3 = driver.findElement(By.xpath("//input[@class='_3qxDp _37_h4']"))
                .getAttribute("value");
//        System.out.println(p3);
        String getPriceProduct3 = driver.findElement(priceProduct).getText();
        Pattern pattern3 = Pattern.compile("\\d+");
        Matcher matcher3 = pattern3.matcher(getPriceProduct3);
        while (matcher3.find()) {
            price = Integer.parseInt(matcher3.group());
            priceT3 += price;
        }
//        System.out.println(pp);
        if(Integer.parseInt(p3) == Integer.parseInt(priceT3)){
            System.out.println("Test 3 completed");
        } else{
            System.out.println("Test 3 failed");
        }
        driver.findElement(btnFilterClose).click();
        sleep(3000);

//        //=========Test4
        String priceUpTo4 = driver.findElement(priceFrom).getText();
        driver.findElement(inputUpto).sendKeys(priceUpTo4);
        sleep(3000);
        String p4 = driver.findElement(By.xpath("//input[@class='_3qxDp _1R_cW']"))
                .getAttribute("value");
//        System.out.println(p4);
        String getPriceProduct4 = driver.findElement(priceProduct).getText();
        Pattern pattern4 = Pattern.compile("\\d+");
        Matcher matcher4 = pattern4.matcher(getPriceProduct4);
        while (matcher4.find()) {
            price = Integer.parseInt(matcher4.group());
            priceT4 += price;
        }
//        System.out.println(priceT4);
        if(Integer.parseInt(p4) == Integer.parseInt(priceT4)){
            System.out.println("Test 4 completed");
        } else{
            System.out.println("Test 4 failed");
        }
        driver.findElement(btnFilterClose).click();
        sleep(3000);
//
//        //=========Test5
        String priceUpTo5 = driver.findElement(priceUpto).getText();
        String priceUpTo5Repl = priceUpTo5.replaceAll(" ", "");
        priceInput = Integer.parseInt(priceUpTo5Repl) + 1;
        driver.findElement(inputFrom).sendKeys(String.valueOf(priceInput));
        sleep(3000);
        try {
            boolean text = driver.findElement(By.xpath("//*[contains(text(),'Нет подходящих товаров')]"))
                    .isDisplayed();
            System.out.println("Test 5 completed");
            driver.findElement(btnFilterCloseAll).click();
        } catch (NoSuchElementException e){
            System.out.println("Test 5 failed");
            driver.findElement(btnFilterClose).click();
        }
        sleep(3000);
//
//        //=========Test6
        String priceUpTo6 = driver.findElement(priceFrom).getText();
        String priceUpTo6Repl = priceUpTo6.replaceAll(" ", "");
        priceInput = Integer.parseInt(priceUpTo6Repl) - 1;
        driver.findElement(inputUpto).sendKeys(String.valueOf(priceInput));
        sleep(3000);
        try {
            boolean text = driver.findElement(By.xpath("//*[contains(text(),'Нет подходящих товаров')]"))
                    .isDisplayed();
            System.out.println("Test 6 completed");
            driver.findElement(btnFilterCloseAll).click();
        } catch (NoSuchElementException e){
            System.out.println("Test 6 failed");
            driver.findElement(btnFilterClose).click();
        }
        sleep(3000);
//
//        //=========Test7
        driver.findElement(inputUpto).sendKeys("-1");
        sleep(3000);
        try {
            boolean text = driver.findElement(By.xpath("button[@class='_2AMPZ _2reIv _2PrFr eWweo _1aA_N _3FGRg']"))
                    .isDisplayed();
            if(!text){
                System.out.println("Test 7 completed");
            }
            driver.findElement(btnFilterClose).click();
        } catch (NoSuchElementException e){
            System.out.println("Test 7 failed");
        }
        sleep(3000);

    }
}

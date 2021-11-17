import static org.junit.jupiter.api.Assertions.*;


import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;


public class TelusUserLoginTest {
    private static final String WEBDRIVER_PATH = "C:\\Users\\posya\\OneDrive\\Documents\\GitHub\\telus_automation_qa\\libs\\chromedriver_win32\\chromedriver.exe";
    private WebDriver driver;

    @BeforeEach
    public void beforeEachTest(){
        System.setProperty("webdriver.chrome.driver", WEBDRIVER_PATH);
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.telus.com/my-account");
    }

    @AfterEach
    public void afterEachTest(){
        driver.close();
    }

    @Test
    public void shouldLoginAndUpgrade(){
        try {
            WebElement userName = driver.findElement(By.id("idtoken1"));
            userName.sendKeys("tdvancouver.devicelab1@telusinternal.com");

            WebElement password = driver.findElement(By.id("idtoken2"));
            password.sendKeys("u7xPU%49");

            WebElement loginButton = driver.findElement(By.id("login-btn"));
            loginButton.click();

            Thread.sleep(15_000);
            //new WebDriverWait(driver,60).until(
            //        webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
            String url = "/my-telus/internet?linktype=topNavLnkInternet";
            WebElement manageInternet = driver.findElement(By.xpath("//a[@href='" + url + "']"));
            manageInternet.click();

            Thread.sleep(5_000);
            WebElement changeInternet = driver.findElement(By.xpath("//span[text()='Change internet plan']"));
            changeInternet.click();

            Thread.sleep(20_000);
            WebElement pickPlan = driver.findElement(By.cssSelector("button[data-qa*='exe-irpc-card-cta-internet-150-30']"));
            pickPlan.click();


            WebElement addAddon = driver.findElement(By.cssSelector("button[data-qa*='exe-irpc-card-cta-telus-boost-wi-fi-expansion-pack-easy-payment']"));
            addAddon.click();
                                                              
            WebElement continueButton = driver.findElement(By.cssSelector("button[data-qa*='exe-irpc-continue-btn']"));
            continueButton.click();
        } catch (Exception e) {
            fail("Test case failed:  " + e);
        }
    }
}


/*<button data-qa="exe-irpc-card-cta-internet-150-30" type="button" class="Button__StyledButton-sa7xo3-0 jMqVeU" data-di-id="di-id-e3f0971a-dac75a97"><span class="Button__ButtonTextWrapper-sa7xo3-1 gTQbTh">Select This Plan</span></button>*/

//Wait for element to load
//https://stackoverflow.com/questions/11736027/webdriver-wait-for-element-using-java
                                                              

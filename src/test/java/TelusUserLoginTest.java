import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class TelusUserLoginTest {
    private static final String WEBDRIVER_PATH = "C:\\Users\\posya\\OneDrive\\Documents\\GitHub\\telus_automation_qa\\libs\\chromedriver_win32\\chromedriver.exe";
    public WebDriver driver;

    @BeforeEach
    public void beforeEachTest(){
        System.setProperty("webdriver.chrome.driver", WEBDRIVER_PATH);
        driver = new ChromeDriver();
        driver.get("https://www.telus.com/my-account");
    }

    @AfterEach
    public void afterEachTest(){
        driver.close();
    }

    @Test
    public void shouldLoginAndUpgrade(){
        try {
            //Username and password input for login
            WebElement userName = driver.findElement(By.id("idtoken1"));
            userName.sendKeys("tdvancouver.devicelab1@telusinternal.com");
            WebElement password = driver.findElement(By.id("idtoken2"));
            password.sendKeys("u7xPU%49");
            WebElement loginButton = driver.findElement(By.id("login-btn"));
            loginButton.click();

            //Navigate to Internet webpage
            String url = "/my-telus/internet?linktype=topNavLnkInternet";
            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='" + url + "']")));
            WebElement manageInternet = driver.findElement(By.xpath("//a[@href='" + url + "']"));
            manageInternet.click();

            //Navigate to change Internet option
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Change internet plan']")));
            WebElement changeInternet = driver.findElement(By.xpath("//span[text()='Change internet plan']"));
            changeInternet.click();

            //Select plan, add addon, and continue
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[data-qa*='exe-irpc-card-cta-internet-150-30']")));
            WebElement pickPlan = driver.findElement(By.cssSelector("button[data-qa*='exe-irpc-card-cta-internet-150-30']"));
            pickPlan.click();
            WebElement addAddon = driver.findElement(By.cssSelector("button[data-qa*='exe-irpc-card-cta-telus-boost-wi-fi-expansion-pack-easy-payment']"));
            addAddon.click();
            WebElement continueButton = driver.findElement(By.cssSelector("button[data-qa*='exe-irpc-continue-btn']"));
            continueButton.click();

            verifyCart();
        } catch (Exception e) {
            fail("Test case failed:  " + e);
        }
    }

    private void verifyCart(){

    }
}
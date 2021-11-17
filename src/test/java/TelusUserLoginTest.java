import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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
            userLogin();
            navigateToInternet();
            navigateToChangeInternet();
            selectPlanAndAddOn();
            verifyCart();
        } catch (Exception e) {
            fail("Test case failed:  " + e);
        }
    }

    /**
    Username and password input for login
     **/
    private void userLogin() throws Exception {
        WebElement userName = driver.findElement(By.id("idtoken1"));
        userName.sendKeys("tdvancouver.devicelab1@telusinternal.com");
        WebElement password = driver.findElement(By.id("idtoken2"));
        password.sendKeys("u7xPU%49");
        WebElement loginButton = driver.findElement(By.id("login-btn"));
        loginButton.click();

        //Verify login
        assertWithRetries(() -> {
            assertEquals("Account overview | My TELUS | TELUS.com", driver.getTitle()); //able to access account
        }, 1_000, 20);
    }

    /**
     Navigate to Internet webpage
     **/
    private void navigateToInternet() throws Exception {
        String url = "/my-telus/internet?linktype=topNavLnkInternet";
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='" + url + "']")));
        WebElement manageInternet = driver.findElement(By.xpath("//a[@href='" + url + "']"));
        manageInternet.click();

        //Verify destination page
        assertWithRetries(() -> {
            assertEquals("My TELUS Internet", driver.getTitle());
            assertEquals("https://www.telus.com/my-telus/internet?linktype=topNavLnkInternet", driver.getCurrentUrl());
        }, 1_000, 20);
    }

    /**
     * Navigate to change Internet option
     */
    private void navigateToChangeInternet() throws Exception {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Change internet plan']")));
        WebElement changeInternet = driver.findElement(By.xpath("//span[text()='Change internet plan']"));
        changeInternet.click();

        //Verify destination page
        assertWithRetries(() -> {
            assertEquals("Choose an Internet Plan - Manage Internet Services | TELUS", driver.getTitle());
        }, 1_000, 20);
    }

    /**
     * Select plan, add addon, and continue
     */
    private void selectPlanAndAddOn() throws Exception {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[data-qa*='exe-irpc-card-cta-internet-150-30']")));

        WebElement pickPlan = driver.findElement(By.cssSelector("button[data-qa*='exe-irpc-card-cta-internet-150-30']"));
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].click()", pickPlan);

        WebElement addAddon = driver.findElement(By.cssSelector("button[data-qa*='exe-irpc-card-cta-telus-boost-wi-fi-expansion-pack-easy-payment']"));
        js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].click()", addAddon);

        WebElement continueButton = driver.findElement(By.cssSelector("button[data-qa*='exe-irpc-continue-btn']"));
        js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].click()", continueButton);

        assertWithRetries(() -> {
            assertEquals("Secure Cart - TELUS", driver.getTitle());
        }, 1_000, 20);
    }

    /**
     * Check the items in the cart are correct
     */
    private void verifyCart() throws Exception {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[(text()='Internet 150')]")));
        WebElement internetPlan = driver.findElement(By.xpath("//h2[(text()='Internet 150')]"));
        assertWithRetries(() -> {
            assertTrue(internetPlan.isDisplayed());
        }, 1_000, 20);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[(text()='TELUS Boost Wi-Fi Expansion Pack')]")));
        WebElement addon = driver.findElement(By.xpath("//li[(text()='TELUS Boost Wi-Fi Expansion Pack')]"));
        assertWithRetries(() -> {
            assertTrue(addon.isDisplayed());
        }, 1_000, 20);
    }

    /**
    Retry assertions with a given interval and retries
     **/
    private void assertWithRetries(TestBlock test, long interval, int retries) throws Exception {
        while(true) {
            try {
                test.run();
                break;
            } catch (AssertionError | Exception e) {
                if (--retries == 0) {
                    throw e;
                }
                Thread.sleep(interval);
            }
        }
    }
}
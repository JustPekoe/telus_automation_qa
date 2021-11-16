import static org.junit.jupiter.api.Assertions.*;


import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;


public class TelusUserLoginTest {
    private static final String WEBDRIVER_PATH = "C:\\Users\\posya\\OneDrive\\Documents\\GitHub\\telus_automation_qa\\libs\\chromedriver_win32\\chromedriver.exe";

    @Test
    public void shouldLoginAndUpgrade(){
        System.setProperty("webdriver.chrome.driver", WEBDRIVER_PATH);
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.telus.com/my-account");

        try {
            WebElement userName = driver.findElement(By.id("idtoken1"));
            userName.sendKeys(" tdvancouver.devicelab1@telusinternal.com");

            WebElement password = driver.findElement(By.id("idtoken2"));
            password.sendKeys("u7xPU%49");

            WebElement loginButton = driver.findElement(By.id("login-btn"));
            loginButton.click();

            Thread.sleep(15_000);
            //new WebDriverWait(driver,60).until(
            //        webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));

            String url = "/my-telus/session/internet/account?account=eAxeDDo7jcpzSwOc-JifqA&serviceId=614341334&rd=/my-telus/internet";
            WebElement manageInternet = driver.findElement(By.xpath("//a[@href='" + url + "']"));
            manageInternet.click();

            Thread.sleep(5_000);
            WebElement changeInternet = driver.findElement(By.xpath("//span[text()='Change internet plan']"));
            changeInternet.click();

            Thread.sleep(20_000);
            WebElement pickPlan = driver.findElement(By.xpath("//button[text()='exe-irpc-card-cta-internet-150-30']"));
            pickPlan.click();

        } catch (Exception e) {
            driver.close();
            fail("Test case failed:  " + e);
        }
    }
}


/*<button data-qa="exe-irpc-card-cta-internet-150-30" type="button" class="Button__StyledButton-sa7xo3-0 jMqVeU" data-di-id="di-id-e3f0971a-dac75a97"><span class="Button__ButtonTextWrapper-sa7xo3-1 gTQbTh">Select This Plan</span></button>*/
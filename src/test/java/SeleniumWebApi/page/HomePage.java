package SeleniumWebApi.page;

import SeleniumWebApi.infrastructure.SeleniumTestPageBase;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;


public class HomePage extends SeleniumTestPageBase {

    private WebElement menu = null;
    private WebElement subMenu = null;

    private String currentUrl;
    private String pageSource;

    private String title;

    private final int timeout = 10;

    public HomePage(final String baseUrl, final WebDriver driver) {
        super(baseUrl, driver);
    }

    public final void clickMenuOption(String menuOptionByXpath, String subMenuOptionByXpath) {

        driver.get(baseUrl);

        menu = driver.findElement(By.xpath(menuOptionByXpath));
        subMenu = driver.findElement(By.xpath(subMenuOptionByXpath));

        Actions action = new Actions(driver);
        action.moveToElement(menu).moveToElement(subMenu).click().build().perform();

        if (driver instanceof JavascriptExecutor) {

            for (int i = 0; ; i++) {

                if (i > timeout) {
                    Assert.fail("Page Load failed: Timeout: " + timeout);
                }

                try {
                    if (!driver.getCurrentUrl().equals("https://www.s3group.com/") &&
                            ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete")) {
                        break;
                    }
                } catch (Exception ex) {
                }

                SelThreadSleep(500);
            }
        } else {
            throw new IllegalStateException("Error when creating JavascriptExecutor");
        }
    }

    public final String returnPageUrl() {
        currentUrl = null;
        currentUrl = driver.getCurrentUrl();

        pageSource = null;
        pageSource = driver.getPageSource();

        if (pageSource.equals(null) || currentUrl.equals(null)) {
            Assert.fail("Page load failed");
        }
        return currentUrl;
    }

    public final String returnTitle() {
        title = null;
        title = driver.getTitle();
        if (title.equals(null)) {
            Assert.fail("Title read failed ");
        }
        return title;
    }
}




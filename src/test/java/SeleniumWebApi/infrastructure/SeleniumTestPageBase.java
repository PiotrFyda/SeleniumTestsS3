package SeleniumWebApi.infrastructure;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

public abstract class SeleniumTestPageBase {

    private static final String EXCEPTION_WHILE_CLICKING_ELEMENT_BY_LINK_TEXT =
            "Exception while clicking element By linkText :\n ";

    private static final String EXCEPTION_WHILE_CLICKING_ELEMENT_BY_XPATH =
            "Exception while clicking element By Xpath :\n ";

    private static final String EXCEPTION_WHILE_CLICKING_ELEMENT = "Exception while clicking element:\n ";

    protected final WebDriver driver;
    protected final String baseUrl;

    public SeleniumTestPageBase(final String baseUrl, final WebDriver driver) {
        this.baseUrl = baseUrl;
        this.driver = driver;
    }

    public SeleniumTestPageBase fillById(final String inputId, final String value) {
        final WebElement element = driver.findElement(By.id(inputId));
        if (value != null) {
            element.clear();
            element.sendKeys(value);
        }
        return this;
    }

    public void selectDropOptionById(final String inputId, final String value) {
        if (value != null) {
            new Select(driver.findElement(By.id(inputId))).selectByVisibleText(value);
        }
    }

    public SeleniumTestPageBase fillByName(final String name, final String value) {
        SelThreadSleep(1000);
        final WebElement element = driver.findElement(By.name(name));
        element.clear();
        element.sendKeys(value);
        return this;
    }

    public void SelThreadSleep(int millisTimeout) {
        try {
            Thread.sleep(millisTimeout);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

    public SeleniumTestPageBase clickById(String id) {
        try {
            driver.findElement(By.id(id)).click();
            return this;
        } catch (final NoSuchElementException e) {
            throw new RuntimeException(EXCEPTION_WHILE_CLICKING_ELEMENT + driver.getPageSource(), e);
        } catch (final StaleElementReferenceException e) {
            throw new RuntimeException(EXCEPTION_WHILE_CLICKING_ELEMENT + driver.getPageSource(), e);
        }
    }

    public SeleniumTestPageBase clickByXpath(String xpath) {
        SelThreadSleep(1000);
        try {
            driver.findElement(By.xpath(xpath)).click();
            return this;
        } catch (final NoSuchElementException e) {
            throw new RuntimeException(EXCEPTION_WHILE_CLICKING_ELEMENT_BY_XPATH + xpath + driver.getPageSource(), e);
        } catch (final StaleElementReferenceException e) {
            throw new RuntimeException(EXCEPTION_WHILE_CLICKING_ELEMENT_BY_XPATH + xpath + driver.getPageSource(), e);
        }
    }

    public SeleniumTestPageBase clickBylinkText(String linkText) {
        try {
            driver.findElement(By.linkText(linkText)).click();
            return this;
        } catch (final NoSuchElementException e) {
            throw new RuntimeException(EXCEPTION_WHILE_CLICKING_ELEMENT_BY_LINK_TEXT + linkText
                    + driver.getPageSource(), e);
        } catch (final StaleElementReferenceException e) {
            throw new RuntimeException(EXCEPTION_WHILE_CLICKING_ELEMENT_BY_LINK_TEXT + linkText
                    + driver.getPageSource(), e);
        }
    }
}
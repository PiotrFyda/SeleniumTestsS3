package SeleniumWebApi.infrastructure;

import SeleniumWebApi.page.HomePage;
import SeleniumWebApi.page.SignUpPage;
import SeleniumWebApi.page.VacanciesPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import java.util.concurrent.TimeUnit;

public class SeleniumTestCaseBaseWebSession {

    private final WebDriver driver;
    private final String baseUrl;
    private final SignUpPage signUpPage;
    private final VacanciesPage vacanciesPage;
    private final HomePage homePage;

    public SeleniumTestCaseBaseWebSession() {
        baseUrl = "http://s3group.com";
        driver = setupWebDriver();
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
        signUpPage = new SignUpPage(baseUrl, driver);
        vacanciesPage = new VacanciesPage(baseUrl, driver);
        homePage = new HomePage(baseUrl, driver);
    }

    public SignUpPage signUpPage() {
        return signUpPage;
    }

    public VacanciesPage vacanciesPage() {
        return vacanciesPage;
    }

    public HomePage homePage() {
        return homePage;
    }

    public void close() {
        this.driver.close();
    }

    private WebDriver setupWebDriver() {
        System.setProperty("webdriver.gecko.driver", "src/test/java/SeleniumWebApi/components/geckodriver/geckodriver.exe");
        FirefoxProfile fp = new FirefoxProfile();
        return new FirefoxDriver(fp);
    }
}
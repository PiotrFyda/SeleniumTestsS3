package SeleniumWebApi.page;

import SeleniumWebApi.infrastructure.SeleniumTestPageBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class VacanciesPage extends SeleniumTestPageBase {

    private final String vacanciesUrl = baseUrl + "/careers/vacancies/";
    private final String searchFieldByName = "jst";
    private final String searchButtonByXpath = "//*[@id=\"middle\"]/div/div[2]/div/div/div[1]/div/div/div/div/div/div/div[1]/form/div/div/span/button";
    private final String searchResultByXpath = ".//*[@id='middle']/div/div[2]/div/div/div[1]/div/div/div/div/div/div/div[3]/table/tbody/tr";

    private List<WebElement> jobList;
    private List<WebElement> finalJobList;

    public VacanciesPage(final String baseUrl, final WebDriver driver) {
        super(baseUrl, driver);
    }

    public List<WebElement> getJobList() {
        return this.finalJobList;
    }

    public VacanciesPage searchJob(String jobName) {

        driver.get(vacanciesUrl);

        fillByName(searchFieldByName, jobName);
        clickByXpath(searchButtonByXpath);

        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(searchResultByXpath)));

        jobList = driver.findElements(By.xpath(searchResultByXpath));

        this.finalJobList = jobList;
        return this;
    }
}
package tests;

import SeleniumWebApi.infrastructure.SeleniumTestCaseBaseWebSession;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class VacationsTests {

    private final String jobName = "Tests Engineer";
    private final int actualNumberJobsOnWebsite = 3;

    private List<WebElement> searchResultJobList;
    private List<String> actualJobsOnWebsite;
    private int theSameJobs;
    private WebElement job;
    private String jobTxt;

    @Before
    public void prepareData() {
        theSameJobs = 0;

        actualJobsOnWebsite = new ArrayList<String>();
        actualJobsOnWebsite.add("Senior Automation Tests Engineer Connected Health Wroclaw");
        actualJobsOnWebsite.add("Senior Product / Test Engineer Semiconductor Solutions Cork Dublin");
        actualJobsOnWebsite.add("Senior Test Automation Engineer Connected Health Dublin");
    }

    @Test
    public void searchJobsResultVerification() {

        SeleniumTestCaseBaseWebSession web = new SeleniumTestCaseBaseWebSession();
        web.vacanciesPage().searchJob(jobName);

        searchResultJobList = web.vacanciesPage().getJobList();
        Assert.assertEquals("The number of results is incorrect", searchResultJobList.size(), actualNumberJobsOnWebsite);

        Iterator<WebElement> jobIterator = searchResultJobList.iterator();

        while (jobIterator.hasNext()) {
            job = jobIterator.next();
            jobTxt = job.getText();

            for (int i = 0; i <= actualNumberJobsOnWebsite - 1; i++) {
                if (jobTxt.equals(actualJobsOnWebsite.get(i))) {
                    theSameJobs++;
                }
            }
        }

        Assert.assertEquals("The search jobs result is incorrect", theSameJobs, actualNumberJobsOnWebsite);

        web.close();
    }
}

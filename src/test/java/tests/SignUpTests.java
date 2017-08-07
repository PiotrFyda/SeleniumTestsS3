package tests;


import SeleniumWebApi.infrastructure.SeleniumTestCaseBaseWebSession;
import org.junit.Assert;
import org.junit.Test;
import testdata.SignUpData;

public class SignUpTests {

    private final String failStatement = "There was a problem with your submission. Errors have been highlighted below.";
    private String registrationResultValidationError;
    boolean isValidationErrorCorrect;

    @Test
    public void failRegistrationValidationErrorVerification() {

        SeleniumTestCaseBaseWebSession web = new SeleniumTestCaseBaseWebSession();

        web.signUpPage().SignUp(SignUpData.firstName, SignUpData.lastName, SignUpData.company, SignUpData.email, SignUpData.password,
                SignUpData.password, SignUpData.streetAddress, SignUpData.city, SignUpData.state, SignUpData.zipCode, SignUpData.country);

        registrationResultValidationError = web.signUpPage().getFinalValidationError();
        isValidationErrorCorrect = registrationResultValidationError.contains(failStatement);

        Assert.assertTrue("Communicate other than expected", isValidationErrorCorrect);

        web.close();
    }
}
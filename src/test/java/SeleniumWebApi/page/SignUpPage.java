package SeleniumWebApi.page;

import SeleniumWebApi.infrastructure.SeleniumTestPageBase;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SignUpPage extends SeleniumTestPageBase {

    private final String firstNameById = "input_2_1_3";
    private final String lastNameById = "input_2_1_6";
    private final String companyById = "input_2_2";
    private final String emailById = "input_2_4";
    private final String passwordById = "input_2_5";
    private final String passwordConfirmationById = "input_2_5_2";
    private final String streetAddressById = "input_2_6_1";
    private final String cityById = "input_2_6_3";
    private final String stateById = "input_2_6_4";
    private final String zipCodeById = "input_2_6_5";
    private final String countryById = "input_2_6_6";
    private final String registerButtonById = "gform_submit_button_2";
    private final String validationErrorAfterRegistrationById = "gform_2";

    private String finalValidationError = null;
    private WebElement validationError = null;
    private final int timeout = 10;

    public SignUpPage(final String baseUrl, final WebDriver driver) {
        super(baseUrl, driver);
    }

    public String getFinalValidationError() {
        return this.finalValidationError;
    }

    public SignUpPage SignUp(String firstName, String lastName, String company, String email, String password, String passwordConfirmation,
                             String streetAddress, String city, String state, String zipCode, String country) {
        driver.get(baseUrl);

        clickBylinkText("Sign Up");

        fillById(firstNameById, firstName);
        fillById(lastNameById, lastName);
        fillById(companyById, company);
        fillById(emailById, email);
        fillById(passwordById, password);
        fillById(passwordConfirmationById, passwordConfirmation);
        fillById(streetAddressById, streetAddress);
        fillById(cityById, city);
        fillById(stateById, state);
        fillById(zipCodeById, zipCode);
        selectDropOptionById(countryById, country);

        clickById(registerButtonById);

        for (int i = 0; ; i++) {

            if (i > timeout) {
                Assert.fail("Registration failed: Timeout: " + timeout);
                break;
            }

            try {
                if (driver.getCurrentUrl().equals("https://urlAfterRegistration")) {
                    break;
                }
            } catch (Exception ex) {
            }

            try {
                validationError = driver.findElement(By.id(validationErrorAfterRegistrationById));
                if (!validationError.equals(null)) {
                    break;
                }
            } catch (NoSuchElementException Exception) {
            }
            SelThreadSleep(500);
        }

        this.finalValidationError = validationError.getText();

        return this;
    }
}

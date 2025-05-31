package steps;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Map;
import org.testng.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import io.cucumber.java.en.*;
import model.FormModel;
import pages.RegisterFormPage;
import utils.FormModelFactory;

public class RegisterFormSteps {
    RegisterFormPage registerForm = new RegisterFormPage();
    FormModelFactory formModelFactory = new FormModelFactory();

    @Given("the user navigates to the registration form page")
    public void registrationFormPageNavigate() {
        registerForm.navigateToFormPage();
    }

    @When("the user fills in all the required fields")
    public void fillRegistrationForm() {
        FormModel formModel = formModelFactory.createFakeFormModel();
        registerForm.fillForm(formModel);
    }

    @When("the user clicks the submission button")
    public void submitFormButton() {
        registerForm.submitForm();
    }

    @Then("a confirmation modal with the entered data should appear")
    public void isConfirmationModalDisplayed() {
        String MessageModal = registerForm.IsModalDisplayed();
        Assert.assertEquals(MessageModal, "Thanks for submitting the form");
    }

    @When("the user enters the registration form data")
    public void fillFormWithMissingFields(io.cucumber.datatable.DataTable dataTable) {
        Map<String, String> data = dataTable.asMaps().get(0);

        String firstName = data.get("firstName");
        String lastName = data.get("lastName");
        String phoneNumber = data.get("phoneNumber");
        String email = formModelFactory.createFakeEmail();
        String address = formModelFactory.createFakeAddress();

        FormModel formModel = new FormModel(firstName, lastName, email, phoneNumber, address);
        registerForm.fillForm(formModel);
    }

    @Then("the user should see a validation message from the missing field")
    public void validateRequiredInputs() {
        boolean validateInput = registerForm.validateRequiredInputs();
        Assert.assertFalse(validateInput, "Expected this field should be empty");
    }

    @When("the user enters the email {string}")
    public void fillEmail(String email) {
        FormModel formModel = formModelFactory.createFakeFormModel();
        formModel.setEmail(email);
        registerForm.fillForm(formModel);
    }

    @Then("the email validation result should be {string}")
    public void validateEmail(String expectedResult) {
        String emailRegex = "^[a-zA-Z0-9_\\-\\.]+@[a-zA-Z0-9_\\-\\.]+\\.[a-zA-Z]{2,5}$";
        String email = registerForm.validateEmail();
        String validationResult = email.matches(emailRegex) ? "valid" : "invalid";
        assertEquals(expectedResult, validationResult);
    }

    @When("the user enters the phone number {string}")
    public void fillPhoneNumber(String phoneNumber) {
        FormModel formModel = formModelFactory.createFakeFormModel();
        formModel.setMobile(phoneNumber);
        registerForm.fillForm(formModel);
    }

    @Then("the phoneNumber validation result should be {string}")
    public void validatePhoneNumber(String expectedResult) {
        String phoneNumberRegex = "^\\d{10}$";
        String phoneNumber = registerForm.validatePhoneNumber();
        String validationResult = phoneNumber.matches(phoneNumberRegex) ? "valid" : "invalid";
        assertEquals(expectedResult, validationResult);
    }

    @When("the user clicks on the calendar input field and selects a valid date {string} {string} {string}")
    public void userSelectsValidDate(String day, String month, String year) {
        registerForm.selectDate(day, month, year);
        registerForm.getDateOfBirthValue();
    }

    @Then("the date should match the selected one {string}")
    public void validateBirthDate(String expectedResult) {
        String date = registerForm.getDateOfBirthValue();
        assertEquals(expectedResult, date);
    }

    @When("the user enters only the required registration form data")
    public void fillFormWithRequiredFields(io.cucumber.datatable.DataTable dataTable) {
        Map<String, String> data = dataTable.asMaps().get(0);

        String firstName = data.get("firstName");
        String lastName = data.get("lastName");
        String phoneNumber = data.get("phoneNumber");

        registerForm.ValidateModalObligatorios(firstName, lastName, phoneNumber);
    }

    @Then("the user should see a modal with only the four required fields filled")
    public void validateConfirmationModal() {
        List<WebElement> rows = registerForm.validateFormFields();
        for (WebElement row : rows) {
            String label = row.findElement(By.tagName("td")).getText().trim();
            String value = row.findElements(By.tagName("td")).get(1).getText().trim();

            if (label.equals("Student Name") || label.equals("Gender") || label.equals("Mobile")) {
                Assert.assertFalse(value.isEmpty(), "The field " + label + " is empty");
            } else {
                Assert.assertTrue(value.isEmpty(), "The field " + label + " should be empty");
                ;
            }
        }

    }
}
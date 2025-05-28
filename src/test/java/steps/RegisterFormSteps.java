package steps;

import java.util.Map;

import org.testng.Assert;

import io.cucumber.java.en.*;
import pages.RegisterFormPage;
import net.datafaker.Faker;

public class RegisterFormSteps {
    Faker faker = new Faker();
    RegisterFormPage registerForm = new RegisterFormPage();

    @Given("the user navigates to the registration form page")
    public void registrationFormPageNavigate() {
        registerForm.navigateToFormPage();
    }

    @When("they fill in all the required fields")
    public void registrationFormFillAndSubmit() {
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String email = faker.internet().emailAddress();
        String phoneNumber = faker.numerify("##########");
        String address = faker.address().fullAddress();

        registerForm.fillForm(firstName, lastName, email, phoneNumber, address);
    }

    @When("I click the submit button")
    public void submitFormButton() {
        registerForm.submitForm();
    }

    @Then("a confirmation modal with the entered data should appear")
    public void confirmationModalAppear() {
        String MessageModal = registerForm.verifyModalIsDisplayed();
        Assert.assertEquals(MessageModal, "Thanks for submitting the form");
    }

    @When("I enter the following data:")
    public void fillFormWithMissingFields(io.cucumber.datatable.DataTable dataTable) {
        Map<String, String> data = dataTable.asMaps().get(0);

        String firstName = data.get("firstName");
        String lastName = data.get("lastName");
        String phoneNumber = data.get("phoneNumber");
        String email = faker.internet().emailAddress();
        String address = faker.address().fullAddress();

        registerForm.fillForm(firstName, lastName, email, phoneNumber, address);
    }

    @Then("I should see a validation message for the empty field")
    public void validateRequiredInputs() {
        boolean validateInput = registerForm.validateRequiredInputs();
        Assert.assertFalse(validateInput, "Expected this field should be empty");
    }

}

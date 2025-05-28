package steps;

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

    @When("they fill in all the required fields and submit the form")
    public void registrationFormFillAndSubmit() {
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String email = faker.internet().emailAddress();
        String phoneNumber = faker.numerify("##########");
        String address = faker.address().fullAddress();

        registerForm.fillForm(firstName, lastName, email, phoneNumber, address);
    }

    @Then("a confirmation modal with the entered data should appear")
    public void confirmationModalAppear() {
        String MessageModal = registerForm.submitForm();
        Assert.assertEquals(MessageModal, "Thanks for submitting the form");
    }

}

package steps;

import io.cucumber.java.en.*;
import pages.RegisterFormPage;

public class RegisterFormSteps {
    RegisterFormPage registerForm = new RegisterFormPage();

    @Given("the user navigates to the registration form page")
    public void registrationFormPageNavigate() {
        registerForm.navigateToFormPage();
    }

}

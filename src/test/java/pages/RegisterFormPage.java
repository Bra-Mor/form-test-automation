package pages;

public class RegisterFormPage extends BasePage {

    public RegisterFormPage() {
        super(driver);
    }

    public void navigateToFormPage() {
        navigateTo("https://demoqa.com/automation-practice-form");
    }

}

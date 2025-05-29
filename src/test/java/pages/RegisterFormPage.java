package pages;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RegisterFormPage extends BasePage {
    private By inputFirstName = By.id("firstName");
    private By inputLastName = By.id("lastName");
    private By inputEmail = By.id("userEmail");
    private By inputMobileNumber = By.id("userNumber");
    private By inputAddress = By.id("currentAddress");
    private By selectGender = By.cssSelector("#genterWrapper input[type='radio'][name='gender']");
    private By selectHobbies = By.cssSelector("#hobbiesWrapper input[type=\"checkbox\"]");
    private By submitButton = By.id("submit");
    private By confirmationModal = By.id("example-modal-sizes-title-lg");
    private By closeButtonModal = By.id("closeLargeModal");

    public RegisterFormPage() {
        super(driver);
    }

    public void navigateToFormPage() {
        navigateTo("https://demoqa.com/automation-practice-form");
    }

    public void fillForm(String firstName, String lastName, String email, String phoneNumber, String address) {
        if (firstName != null)
            write(inputFirstName, firstName);
        if (lastName != null)
            write(inputLastName, lastName);
        if (phoneNumber != null)
            write(inputMobileNumber, phoneNumber);

        write(inputEmail, email);
        write(inputAddress, address);

        randomGenderSelect(selectGender);
        randomGenderSelect(selectHobbies);
    }

    public void randomGenderSelect(By locator) {
        List<WebElement> radios = driver.findElements(locator);

        Random rand = new Random();
        int randomIndex = rand.nextInt(radios.size());
        String id = radios.get(randomIndex).getDomAttribute("id");

        WebElement label = driver.findElement(By.cssSelector("label[for='" + id + "']"));
        label.click();
    }

    public void submitForm() {
        WebElement boton = driver.findElement(submitButton);
        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript("arguments[0].scrollIntoView(true);", boton);
        clickElement(submitButton);
    }

    public String verifyModalIsDisplayed() {
        WebElement modalTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(confirmationModal));
        closeModal(closeButtonModal);
        return modalTitle.getText();
    }

    public boolean validateRequiredInputs() {
        By[] inputs = { inputFirstName, inputLastName, inputMobileNumber };

        for (By locator : inputs) {
            WebElement input = driver.findElement(locator);
            String value = input.getDomProperty("value");
            if (value.isEmpty()) {
                String message = input.getDomProperty("validationMessage");
                if (!message.isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

    public void closeModal(By locator) {
        clickElement(locator);
    }

    public String validateEmail() {
        WebElement emailInput = driver.findElement(inputEmail);
        String email = emailInput.getDomProperty("value");
        return email;
    }

    public String validatePhoneNumber() {
        WebElement mobileNumberInput = driver.findElement(inputMobileNumber);
        String mobileNumber = mobileNumberInput.getDomProperty("value");
        return mobileNumber;
    }

}

// validar ingles

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
        write(inputFirstName, firstName);
        write(inputLastName, lastName);
        write(inputEmail, email);
        write(inputMobileNumber, phoneNumber);
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

    public String submitForm() {
        WebElement boton = driver.findElement(submitButton);
        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript("arguments[0].scrollIntoView(true);", boton);
        clickElement(submitButton);
        WebElement modalTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(confirmationModal));
        closeModal(closeButtonModal);
        return modalTitle.getText();
    }

    public void closeModal(By locator) {
        clickElement(locator);
    }

}

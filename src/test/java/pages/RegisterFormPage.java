package pages;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import model.FormModel;

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
    private By dateOfBirthInput = By.id("dateOfBirthInput");
    private By calendarYear = By.className("react-datepicker__year-select");
    private By calendarMonth = By.className("react-datepicker__month-select");
    private By calendarDay = By
            .xpath("//div[contains(@class, 'react-datepicker__day') and not(contains(@class, 'outside-month'))]");
    private By calendarDateInput = By.id("dateOfBirthInput");
    private By modal = By.cssSelector("table.table tbody tr");

    public RegisterFormPage() {
        super(driver);
    }

    public void navigateToFormPage() {
        navigateTo("https://demoqa.com/automation-practice-form");
    }

    public void fillForm(FormModel formModel) {
        if (formModel.getFirstName() != null)
            write(inputFirstName, formModel.getFirstName());
        if (formModel.getLastName() != null)
            write(inputLastName, formModel.getLastName());
        if (formModel.getMobile() != null)
            write(inputMobileNumber, formModel.getMobile());

        write(inputEmail, formModel.getEmail());
        write(inputAddress, formModel.getAddress());
        randomGenderSelect(selectGender);
        randomGenderSelect(selectHobbies);
    }

    /**
     * Selects randomly one of the gender radio buttons.
     * 
     * @param locator
     */
    public void randomGenderSelect(By locator) {
        List<WebElement> radios = driver.findElements(locator);

        Random rand = new Random();
        int randomIndex = rand.nextInt(radios.size());
        String id = radios.get(randomIndex).getDomAttribute("id");

        WebElement label = driver.findElement(By.cssSelector("label[for='" + id + "']"));
        label.click();
    }

    public void submitForm() {
        WebElement button = driver.findElement(submitButton);
        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript("arguments[0].scrollIntoView(true);", button);
        clickElement(submitButton);
    }

    public String IsModalDisplayed() {
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

    public void selectDate(String day, String month, String year) {
        WebElement dateInput = driver.findElement(dateOfBirthInput);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", dateInput);
        clickElement(dateOfBirthInput);

        WebElement yearDropdown = driver.findElement(calendarYear);
        Select selectYear = new Select(yearDropdown);
        selectYear.selectByVisibleText(year);

        WebElement monthDropdown = driver.findElement(calendarMonth);
        Select selectMonth = new Select(monthDropdown);
        selectMonth.selectByVisibleText(month);

        List<WebElement> days = driver
                .findElements(calendarDay);
        for (WebElement d : days) {
            if (d.getText().equals(day)) {
                d.click();
                break;
            }
        }

    }

    public String getDateOfBirthValue() {
        return driver.findElement(calendarDateInput).getDomProperty("value");
    }

    public void ValidateModalObligatorios(String firstName, String lastName, String mobileNumber) {
        write(inputFirstName, firstName);
        write(inputLastName, lastName);
        write(inputMobileNumber, mobileNumber);

        randomGenderSelect(selectGender);
    }

    public List<WebElement> validateFormFields() {
        return bringMeAllElements(modal);
    }

}

# 🧪 Form Test Automation

This project automates tests for a web form using Java, Selenium WebDriver, Cucumber, and Gradle. It follows the Page Object Model (POM) pattern, which improves test maintainability, reusability, and scalability.

---

## 📌 Why Page Object Model?

The **Page Object Model (POM)** pattern decouples automation logic from the UI structure, allowing for:

- Better code organization
- Component reuse
- Improved readability and easier maintenance
- Change isolation: if the HTML changes, only one class needs updating

---

## 📁 Project Structure

```
src/
├── main/
│ └── java/test/
│ ├─────── pages/ # Classes that represent web pages (POM)
│ └─────── model/ # Data models like FormModel
│ └─────── runner/ # Configuration
│ └─────── utils / # Utilities like data generators
├── resources/
│ └── features/ # Test scenarios written in Gherkin
```

---

## 🧰 Tecnologías Utilizadas

- Java 17
- Selenium WebDriver
- Cucumber + Gherkin
- Gradle
- TestNG
- [Faker](https://github.com/DiUS/java-faker) (generación de datos falsos)

---

## ▶️ How to Run the Project

### Prerequisites

- Java 17: `brew install openjdk@17`
- Gradle (optional): `brew install gradle`
  > ⚠️ This project uses `./gradlew`, so you don't need Gradle installed globally.

### Run Tests

```bash
# Give permission if necessary
chmod +x gradlew

# Run all tests
gradle test or ./gradlew test

```

---

## 🧪 Test Cases

Test scenarios are written in Gherkin in the file:

📄 [`RegisterForm.feature`](/src/test/resources/features/RegisterForm.feature)

They include tests such as:

- Required field validation
- Invalid formats for email or phone number
- Confirmation modal visibility

---

## 🧠 Testing Strategy

A data-driven and end-to-end flow validation strategy is applied, based on:

- Functional testing to ensure form logic
- Equivalence class partitioning to validate emails and phone numbers
- Page Object Model to decouple DOM logic

---

## ✅ Results

Test logs can be found in:

📄 [`test-output`](/build/reports/tests/test/index.html)

Cucumber test execution report is also available at:

📄 [`Cucumber Reports`](/build/reports/tests/test/index.html)

These are HTML files — open them in a browser for a detailed report.

---

## 📌 Next Steps

- [ ] Integrate GitHub Actions for continuous execution
- [ ] Generate more **dynamic test data with Faker**
- [ ] Add extended documentation under the [`/docs`](./docs) folder

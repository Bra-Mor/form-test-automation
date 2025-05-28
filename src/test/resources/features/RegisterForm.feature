Feature: Form validations
    Verify that the registration form functions correctly across its various fields.

    Background:
        Given the user navigates to the registration form page

    Scenario: Successful submission of the registration form with valid data
        When they fill in all the required fields and submit the form
        Then a confirmation modal with the entered data should appear



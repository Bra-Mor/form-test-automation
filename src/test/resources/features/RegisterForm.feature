Feature: Form validations
    Verify that the registration form functions correctly across its various fields.

    Background:
        Given the user navigates to the registration form page

    Scenario: Successful submission of the registration form with valid data
        When they fill in all the required fields
        And I click the submit button
        Then a confirmation modal with the entered data should appear

    Scenario Outline: Form submission fails when a required field is empty
        When I enter the following data:
            | firstName   | lastName   | phoneNumber   |
            | <firstName> | <lastName> | <phoneNumber> |
        And I click the submit button
        Then I should see a validation message for the empty field

        Examples:
            | firstName | lastName | phoneNumber |
            |           | Smith    | 1234567890  |
            | John      |          | 1234567890  |
            | John      | Smith    |             |

    Scenario: Successful submission of the registration form with valid data
        When they fill in all the required fields
        And I click the submit button
        Then a confirmation modal with the entered data should appear

    Scenario Outline: Validate emails structure
        When enter the email "<email>"
        Then the result should be "<result>"

        Examples:
            | email                 | result  |
            | nombre@gmail.com      | valid   |
            | correo_invalido@@mail | invalid |

    Scenario Outline: Validate phone number format
        When enter the phone number "<phoneNumber>"
        Then the phoneNumber result should be "<result>"

        Examples:
            | phoneNumber | result  |
            | 1234567890  | valid   |
            | 12345       | invalid |
            | 12345j      | invalid |

    Scenario: Calendar allows user to select a valid birth date
        When The user clicks on the calendar input field and selects a valid date
        Then  the date should match the selected one "15 Dec 1990"

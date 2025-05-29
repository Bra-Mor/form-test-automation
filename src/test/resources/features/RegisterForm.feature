Feature: Form validations
    Verify right functionality of Registration Form, including fields validation

    Background:
        Given the user navigates to the registration form page

    Scenario: Successful submission of the registration form with valid data
        When the user fills in all the required fields
        And the user clicks the submission button
        Then a confirmation modal with the entered data should appear

    Scenario Outline: Form submission fails when a required field is missing
        When the user enters the registration form data
            | firstName   | lastName   | phoneNumber   |
            | <firstName> | <lastName> | <phoneNumber> |
        And the user clicks the submission button
        Then the user should see a validation message from the missing field

        Examples:
            | firstName | lastName | phoneNumber |
            |           | Smith    | 1234567890  |
            | John      |          | 1234567890  |
            | John      | Smith    |             |

    Scenario Outline: Validate emails structure
        When the user enters the email "<email>"
        Then the email validation result should be "<result>"

        Examples:
            | email               | result  |
            | name@gmail.com      | valid   |
            | invalid_email@@mail | invalid |

    Scenario Outline: Validate phone number format
        When the user enters the phone number "<phoneNumber>"
        Then the phoneNumber validation result should be "<result>"

        Examples:
            | phoneNumber | result  |
            | 1234567890  | valid   |
            | 12345       | invalid |
            | 12345j      | invalid |

    Scenario Outline: Calendar allows user to select a valid birth date
        When the user clicks on the calendar input field and selects a valid date "<day>" "<month>" "<year>"
        Then the date should match the selected one "<result>"

        Examples:
            | day | month    | year | result      |
            | 15  | December | 1990 | 15 Dec 1990 |
            | 17  | January  | 1999 | 17 Jan 1999 |
Feature: hrms holidays data
  as a employee
  I need to review my user profile

  Scenario Outline: hrms holidays module
    Given url is "<endpoint>"
    When get request is made
    Then statuscode should be <statuscode>

    Examples:
      | endpoint        | statuscode |
      | /secure/holidays | 200        |
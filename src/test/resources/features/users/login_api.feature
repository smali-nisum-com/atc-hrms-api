Feature: hrms user authentication
  as a employee
  I need to review hrms portal

  Scenario Outline: user authentication on hrms
    Given url is "<endpoint>"
    When post request is made
    Then statuscode should be <statuscode>

    Examples:
      | endpoint     | statuscode |
      | /auth/login/ | 200        |
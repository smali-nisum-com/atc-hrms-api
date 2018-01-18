Feature: hrms user profile
  as a employee
  I need to review my user profile

  Scenario Outline: user profile on hrms
    Given url is "<endpoint>"
    When get request is made
    Then statuscode should be <statuscode>

    Examples:
      | endpoint     | statuscode |
      | /user/whoami | 200        |
Feature: Get current auth user

  @GetAuthUser
  Scenario: Login and get current auth user
    Given Lorena is using the API
    When she logs in at the API ang get the current user
    Then she should see the user information
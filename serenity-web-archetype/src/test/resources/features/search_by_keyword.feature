Feature: Search by keyword

  @Religion
  Scenario: Searching for 'Religion'
    Given Sergey is researching things on the internet
    When he looks up "Religion"
    Then he should see information about "Religion"

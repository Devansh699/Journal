Feature: Journal API Testing

  Scenario: Create a journal entry
    Given the journal entry with ID 1 exists
    When I call POST /journal
    Then the response should be 200 OK

  Scenario: Get journal entry by ID
    Given the journal entry with ID 1 exists
    When I call GET /journal/id/1
    Then the response should contain "Test Content"

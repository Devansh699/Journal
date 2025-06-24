Feature: Journal API Tests

  Background:
    * url 'http://localhost:8080'
    * def entry =
    """
    {
      "id": 1,
      "title": "First Entry",
      "content": "This is a test journal",
      "date": "2025-06-23"
    }
    """
    * def updatedEntry =
    """
    {
      "id": 1,
      "title": "Updated Entry",
      "content": "Updated content here",
      "date": "2025-06-24"
    }
    """

  Scenario: Add a journal entry
    Given path '/journal'
    And request entry
    When method POST
    Then status 200
    And match response == 'Added Successfully'

  Scenario: Get all journal entries
    Given path '/journal'
    When method GET
    Then status 200
    And match response contains entry

  Scenario: Get journal entry by ID
    Given path '/journal/id/1'
    When method GET
    Then status 200
    And match response.id == 1
    And match response.title == 'First Entry'

  Scenario: Update journal entry by ID
    Given path '/journal/id/1'
    And request updatedEntry
    When method PUT
    Then status 200
    And match response.title == 'Updated Entry'
    And match response.content == 'Updated content here'

  Scenario: Delete journal entry by ID
    Given path '/journal/id/1'
    When method DELETE
    Then status 200
    And match response.id == 1

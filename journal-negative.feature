Feature: Negative Tests for Journal API

Background:
  * url 'http://localhost:8080/journal'

Scenario: Get entry with non-existent ID
  Given path 'id', 999
  When method GET
  Then status 404

Scenario: Delete entry with invalid ID
  Given path 'id', 999
  When method DELETE
  Then status 404
  And match response == 'Not Found'

Scenario: Create entry with missing title
  Given request
    """
    {
      "id": 100,
      "content": "Missing title",
      "date": "2025-06-30"
    }
    """
  When method POST
  Then status 400

Scenario: Create entry with no body
  Given request {}
  When method POST
  Then status 400

Scenario: Update with invalid data types
  Given path 'id', 1
  And request
    """
    {
      "id": "invalid-id",
      "title": true,
      "content": 123,
      "date": null
    }
    """
  When method PUT
  Then status 400

Scenario: Update entry that doesn't exist
  Given path 'id', 999
  And request
    """
    {
      "id": 999,
      "title": "Nonexistent",
      "content": "Trying to update",
      "date": "2025-06-30"
    }
    """
  When method PUT
  Then status 404

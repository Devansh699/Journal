Feature: Journal API Testing with Multiple Scenarios

Background:
  * def setup = callonce read('setup.feature')
  * def id = setup.id
  * def updatedEntry =
    """
    {
      "id": #id,
      "title": "Updated Title",
      "content": "Updated Content",
      "date": "2025-06-27"
    }
    """
  * url 'http://localhost:8080/journal'

Scenario: Get created entry
  Given path 'id', id
  When method GET
  Then status 200
  And match response.id == id

Scenario: Update journal entry
  Given path 'id', id
  And request updatedEntry
  When method PUT
  Then status 200
  And match response.title == updatedEntry.title

Scenario: Verify updated entry
  Given path 'id', id
  When method GET
  Then status 200
  And match response.content == updatedEntry.content

Scenario: Delete the entry
  Given path 'id', id
  When method DELETE
  Then status 200

Scenario: Confirm deletion
  Given path 'id', id
  When method GET
  Then status 404

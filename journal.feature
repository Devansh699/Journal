Feature: Journal API Automation Flow

Background:
  * url 'http://localhost:8080/journal'
  * def entry =
    """
    {
      "id": 1,
      "title": "First Entry",
      "content": "This is a test journal",
      "date": "2025-06-26"
    }
    """
  * def updatedEntry =
    """
    {
      "id": 1,
      "title": "Updated Title",
      "content": "Updated Content Here",
      "date": "2025-06-27"
    }
    """

Scenario: Create a journal entry
  Given request entry
  When method POST
  Then status 200
  And match response == 'Added Successfully'

Scenario: Get the created entry
  When method GET
  Then status 200
  And match response contains entry

Scenario: Update the journal entry by ID
  Given path entry.id
  And request updatedEntry
  When method PUT
  Then status 200
  And match response.title == updatedEntry.title

Scenario: Verify update by ID
  Given path updatedEntry.id
  When method GET
  Then status 200
  And match response.content == updatedEntry.content

Scenario: Delete the journal entry
  Given path updatedEntry.id
  When method DELETE
  Then status 200
  And match response.id == 1

Scenario: Confirm deletion
  Given path 1
  When method GET
  Then status 200
  And match response == null

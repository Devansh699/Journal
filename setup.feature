Feature: Setup reusable journal entry

Scenario:
  * def entry =
    """
    {
      "id": 1,
      "title": "First Entry",
      "content": "This is a test journal",
      "date": "2025-06-27"
    }
    """
  Given url 'http://localhost:8080/journal'
  And request entry
  When method POST
  Then status 200
  * def id = entry.id

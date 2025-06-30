@GetMapping("id/{myId}")
public ResponseEntity<JournalEntry> getEntryById(@PathVariable Long myId) {
    JournalEntry entry = journalEntries.get(myId);
    if (entry != null) {
        return ResponseEntity.ok(entry); // ✅ 200 OK with body
    } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // ❌ Not Found
    }
}

@DeleteMapping("id/{myId}")
public ResponseEntity<String> removeEntryById(@PathVariable Long myId) {
    JournalEntry removed = journalEntries.remove(myId);
    if (removed != null) {
        return ResponseEntity.ok("Deleted"); // ✅ 200 OK with message
    } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not Found"); // ❌ 404 with message
    }
}

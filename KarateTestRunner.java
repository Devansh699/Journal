package com.devansh.Journal.karate;

import com.intuit.karate.junit5.Karate;

public class JournalTestRunner {

    @Karate.Test
    Karate testJournalApi() {
        return Karate.run("journal").relativeTo(getClass());
    }

}

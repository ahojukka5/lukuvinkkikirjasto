Feature: Tips can be filtered by read status

    Scenario: Only read tips are shown
        Given tip with title "hello" and url "world" is created
        And tip with title "magnanimous" and url "urlIsOnlyPrintedInListOutput" is created
        And tip with id "2" is marked read
        And list mode is selected
        When user filters by read tips
        Then output has line with text "urlIsOnlyPrintedInListOutput" 2 times


    Scenario: Only unread tips are shown
        Given tip with title "hello" and url "testValidationSearchesForThisString" is created
        And tip with title "magnanimous" and url "world" is created
        And tip with id "2" is marked read
        And list mode is selected
        When user filters by unread tips
        Then output has line with text "testValidationSearchesForThisString" 2 times

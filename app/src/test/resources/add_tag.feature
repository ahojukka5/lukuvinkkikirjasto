Feature: User can add tags to tip
    Scenario: Add tag to existing tip
        Given tip with title "Merge sort algorithm" and url "https://www.youtube.com/watch?v=TzeBrDU-JaY" is created
        And command addTags is selected
        When id "1" is entered
        And "youtube" is written to console
        Then system will respond with "Tägi 'youtube' on lisätty vinkille 1!"

Feature: Tips can be modified
    Scenario: Existing tip can be modified
        Given tip with title "Merge sort algoritm" and url "https://www.youtube.com/watch?v=TzeBrDU-JaY" is created
        And command edit is selected
        When id "1" is entered
        And "Merge sort algorithm" is written to console
        And empty line is given
        Then system will respond with "Vinkki 1 päivitetty!"

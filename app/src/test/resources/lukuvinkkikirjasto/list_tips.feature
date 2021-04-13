Feature: Saved tips can be read

    Scenario: all of the created tips are listed
        Given command add is selected
        When title "otsikko1" and url "url1.com" are entered
        Given command add is selected
        When title "otsikko2" and url "url2.com" are entered
        Given command list is selected
        Then system will respond with "otsikko1"
        Given command list is selected
        Then system will respond with "url2.com"
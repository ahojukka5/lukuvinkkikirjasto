Feature: Add tip from a url (website title as a title)

    Scenario: if valid url is entered, title is fetched automatically
        Given command addUrl is selected
        And url "https://www.google.com" is entered
        And enter is pressed
        And command list is selected
        Then system will respond with "FakeTitle"

    Scenario: if valid url is entered, title can be entered manually
        Given command addUrl is selected
        And url "https://www.google.com" is entered
        When title "potaskaaOtsikko" is entered
        Then system will respond with "Otsikoksi päivitettiin: potaskaaOtsikko"

    Scenario: if invalid url is entered, title can be entered manually
        Given command addUrl is selected
        And url "potaskaa" is entered
        When title "potaskaaOtsikko" is entered
        Then system will respond with "Otsikoksi päivitettiin: potaskaaOtsikko"


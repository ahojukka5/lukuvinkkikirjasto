Feature: A new tip can be created

    Scenario: tip can be created successfully
        Given command add is selected
        When title "otsikko" and url "url.com" are entered
        Then system will respond with "Vinkki tallennettu!"
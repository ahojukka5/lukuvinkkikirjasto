Feature: Tips can be filtered

    Scenario: Only tip with a matching title is shown
        Given tip with title "yksi" and url "yksi.com" is created
        And tip with title "kaksi" and url "kaksi.com" is created
        Given command list is selected
        And command title is selected
        And filter "kaksi" is entered
        Then filtered results should contain "kaksi.com" but not "yksi.com"
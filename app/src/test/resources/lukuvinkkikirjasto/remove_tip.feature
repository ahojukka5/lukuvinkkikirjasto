Feature: Tips can be removed from the database
	Scenario: Created tip can be removed
		Given tip with title "hello" and url "example.com" is created
		And tip with id 1 is removed
		Then system will respond with "Vinkki 1 poistettu!"

	Scenario: Tip is not removed if opration is cancelled
		Given tip with title "hello" and url "example.com" is created
		And command remove is selected
		When id "1" is entered
		And operation is cancelled
		Then output has line "Vinkki 1 poistettu!" 0 times
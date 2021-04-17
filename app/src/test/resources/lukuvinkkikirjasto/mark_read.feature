Feature: Tips can be marked as read

	Scenario: A tip can be marked as read
		Given tip with title "hello" and url "world" is created
		And command markRead is selected
		When id "1" is entered
		And operation is confirmed
		Then system will respond with "Vinkki 1 merkitty luetuksi!"

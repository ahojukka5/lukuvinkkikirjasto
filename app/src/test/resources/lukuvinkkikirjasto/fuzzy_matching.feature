Feature: Title queries with spelling mistakes match closely related tips

	Scenario: Only read tips are shown
		Given tip with title "Helsingin Yliopisto" and url "example.com" is created
		And tip with title "Helsinki" and url "example.com" is created
		And command list is selected
		And command title is selected
		When filter "hesling" is entered
		Then output has line "Helsingin Yliopisto" 2 times
		And output has line "Helsinki" 1 times
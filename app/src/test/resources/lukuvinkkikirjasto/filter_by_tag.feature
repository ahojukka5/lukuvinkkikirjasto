Feature: Listed tips can be filtered by tags
	Scenario: Tips are filtered by tags
		Given tip with title "Github" and url "github.com" is created
		And tip with title "Helsingin Yliopisto" and url "helsinki.fi" is created
		And tip with id 1 is tagged with "git"
		And list mode is selected
		When user filters by tags
		And filter "git" is entered
		Then output has line "github.com" 2 times
		And output has line "helsinki.fi" 1 times
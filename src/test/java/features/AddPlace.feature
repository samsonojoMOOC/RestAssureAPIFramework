Feature: Validate AddPlace API

@AddPlace @Regression
Scenario Outline: Validate that user can send AddPlace successfully
	Given User has API endpoint "<name>" "<language>" "<address>" "<website>"
	When user sends "AddPlaceAPI" http "post" request
	Then validate that status code in response body
	And "status" contains in it response "OK"
	And "scope" contains in it response "APP"
	And verify place_id created above maps to "<name>" using "GetPlaceAPI"

Examples:
		| name   | language   | address          | website            |
		| Gwen   | Efik       | Ikorodu          | http://gwen.person | 
		| Paul   | pidgin     | agege motor Road | http://paul.person | 
		| Anchor | Efik       | Ikorodu          | http://gwen.person | 

@DeletePlace @Regression
Scenario: Validate that user can send Delete request successfully

	Given user has endpoint
	When user sends "DeletePlaceAPI" http "post" request
	Then validate that status code in response body
	And "status" contains in it response "OK"
	



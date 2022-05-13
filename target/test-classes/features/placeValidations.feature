Feature: Validating Place API's

	@AddPlace @Regression
  Scenario Outline: Verify if Place is being succesfully added using AddPlaceAPI
    Given Add place payload with "<name>" "<language>" "<address>"
    When User call "AddPlaceAPI" with "post" http request
    Then The API call got success with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    And verify place_Id created maps to "<name>" using "getPlaceAPI"
    
	Examples:
		|name 			 		| language | address 			 	|
  	|Pawde Niwas 		| English  | Barad Nanded   |
  	#|Vaibhav Niwas 	| Spanish  | Pune Maha   		|

	@DeletePlace @Regression
  Scenario: Verify if Delete Place functionality is working
  	Given DeletePlace Payload
		When User call "deletePlaceAPI" with "post" http request
		Then The API call got success with status code 200
		And "status" in response body is "OK"
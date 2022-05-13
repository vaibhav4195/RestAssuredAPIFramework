package stepDefinations;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	@Before("@DeletePlace")
	public void beforeScenario() throws IOException {
		
		StepDefination st = new StepDefination();
		if(StepDefination.placeId==null) {
		st.add_place_payload_with("Prasad", "German", "Pluto");
		st.user_call_with_http_request("AddPlaceAPI", "POST");
		st.verify_place_Id_created_maps_to_using("Prasad", "getPlaceAPI");
		}
	}

}

package stepDefinition;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	
	@Before("@DeletePlace")
	public void beforeScenario() throws IOException {
		//Write the code that will give place_id
		//Execute this code only when place_id is null
		Step s = new Step();
		if(Step.placeId==null) {
		s.user_has_api_endpoint("Jame", "English", "Lagos", "http://qbase.com");
		s.user_sends_http_request("AddPlaceAPI", "POST");
		s.verify_place_id_created_above_maps_to_using("Jame", "GetPlaceAPI");
		}
		
	}

}

package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.Location;
import pojo.Serailize;

public class TestData {
	
	public Serailize getJsonData(String name, String language, String address, String website) {
		Serailize s = new Serailize();
		s.setAccuracy(50);
		s.setAddress(address);
		s.setLanguage(language);
		s.setName(name);
		s.setPhone_number("08065789847");
		s.setWebsite(website);
		Location l = new Location();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		s.setLocation(l);
		List<String> myList = new ArrayList<String>();
		myList.add("shoe park");
		myList.add("shop");
		s.setTypes(myList);
		return s;
		
	}
	
	public String getDeleteData(String placeId) {
		return "{\r\n" + 
				"   \r\n" + 
				"    \"place_id\":\""+placeId+"\"\r\n" + 
				"}";
	}
	

}

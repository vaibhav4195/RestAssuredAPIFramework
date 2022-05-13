package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.Location;

public class TestDataBuild {
	
	public AddPlace addPlacePayload(String name,String language, String address) {
		//To create object of AddPlace class
				AddPlace p = new AddPlace();
				p.setAccuracy(50);
				p.setName(name);
				p.setAddress(address);
				p.setLanguage(language);
				p.setPhone_number("(+91) 983 893 3937");
				p.setWebsite("http://google.com");
				
				//Types contains array so we have to create list of arrayList
				List<String> myList = new ArrayList<String>();
				myList.add("shoe park");
				myList.add("shop");
				p.setTypes(myList);
				
				//Location contains another object thats why we have to create another object
				Location l = new Location();
				l.setLat(-38.383494);
				l.setLng(33.427362);
				p.setLocation(l);
				return p;
	}
	
	public String deletePlacePayload(String placeId) {
		return "{\r\n" + 
				"    \"place_id\":\""+placeId+"\"" + 
				"}\r\n" ;
	}

}

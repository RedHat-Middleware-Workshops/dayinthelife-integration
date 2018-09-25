package com.redhat.processor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import com.redhat.model.Location;
import com.redhat.model.Location.LatLong;

public class ResultProcessor implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		List<Location> locations = new ArrayList<>();
		List<Map<String, Object>> body = exchange.getIn().getBody(List.class);
		for (Map<String, Object> item : body) {
			Location location = new Location();
			location.setId(Integer.valueOf(String.valueOf(item.get("ID"))));
			location.setName(String.valueOf(item.get("NAME")));
			location.setStatus(String.valueOf(item.get("STATUS")));
			location.setType(String.valueOf(item.get("LOCATION_TYPE")));
			
			LatLong latlong = location.new LatLong();
			latlong.setLat(String.valueOf(item.get("LAT")));
			latlong.setLng(String.valueOf(item.get("LNG")));
			
			location.setLocation(latlong);
			
			locations.add(location);
		}
		exchange.getOut().setBody(locations);
	}

}

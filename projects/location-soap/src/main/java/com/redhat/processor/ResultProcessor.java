package com.redhat.processor;

import java.util.List;
import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import com.redhat.model.ContactInfo;

public class ResultProcessor implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		List<Map<String, Object>> body = exchange.getIn().getBody(List.class);
		ContactInfo contact = new ContactInfo();
		
		
		for (Map<String, Object> item : body) {
			contact.setId(Integer.valueOf(String.valueOf(item.get("id"))));
			contact.setPhone(String.valueOf(item.get("phone")));
			contact.setOwner(String.valueOf(item.get("owner")));
			contact.setOperating_hour(String.valueOf(item.get("operating_hour")));
			
			//id,phone,owner,operating_hour
			
		}
		exchange.getOut().setBody(contact);
	}

}

package com.redhat;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.model.rest.RestBindingMode;
import org.apache.cxf.message.MessageContentsList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CamelRoutes extends RouteBuilder {
	
	@Autowired
    private CamelContext camelContext;
	

	@Override
	public void configure() throws Exception {
		
		
		
		restConfiguration()
			.component("undertow")
	    	.port(8080)
	    	.bindingMode(RestBindingMode.json)
			.contextPath("/")
	    	.dataFormatProperty("prettyPrint", "true")
	    	.enableCORS(true)
	    	.apiContextPath("/api-doc")
	    	.apiProperty("api.title", "Location API")
	    	.apiProperty("api.version", "1.0.0")
	    ;
		
		
	
		
	
	}
	
	

}

package com.redhat;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

import com.redhat.processor.ResultProcessor;

@Component
public class CamelRoutes extends RouteBuilder {
	

	@Override
	public void configure() throws Exception {
		
		
		
		ResultProcessor resultProcessor = new ResultProcessor();
		
		restConfiguration()
			.component("servlet")
        	.port(8080)
        	.bindingMode(RestBindingMode.json)
			.contextPath("/")
        	.dataFormatProperty("prettyPrint", "true")
        	.enableCORS(true)
        	.apiContextPath("/api-doc")
        	.apiProperty("api.title", "Location API")
        	.apiProperty("api.version", "1.0.0")
        ;
		
		

		rest("/locations").description("Location information")
			.produces("application/json")
			.get("/").description("Retrieve all locations data")
				.responseMessage().code(200).message("Data successfully returned").endResponseMessage()
				.to("direct:getalllocations")
			.get("/{id}")
				.responseMessage().code(200).message("Data successfully returned").endResponseMessage()
				.to("direct:getlocation")
		;

		from("direct:getalllocations")
			.to("sql:select * from locations?dataSource=dataSource")
			.process(resultProcessor)
			.log("${body}")
		;
		
		from("direct:getlocation")
			.to("sql:select * from locations where id=cast(:#id as int)?dataSource=dataSource")
			.process(resultProcessor)
			.choice()
				.when(simple("${body.size} > 0"))
					.setBody(simple("${body[0]}"))
				.otherwise()
					.setHeader("HTTP_RESPONSE_CODE",constant("404"))
			.log("${body}")
		;
		
		

	
	}
	
	

}

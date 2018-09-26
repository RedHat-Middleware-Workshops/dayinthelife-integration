package com.redhat;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

@Component
public class CamelRoutes extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		
		
		restConfiguration()
			.component("undertow")
        	.port(8085)
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
				.to("direct:getlocationAll")
			.get("/{id}")
				.to("direct:getlocation")
			.get("/phone/{id}")
				.to("direct:phone")
			.post("/add")
				.to("direct:addlocation")
		;

		from("direct:getlocationAll")
		.log("All-----")
		.removeHeaders("CamelHttp*")
		.toD("http4://localhost:8082/locations?bridgeEndpoint=true")
		.convertBodyTo(String.class)
	;
		
		from("direct:getlocation")
			.log("Location-----${headers.id}")
			.removeHeaders("CamelHttp*")
			.toD("http4://localhost:8082/locations/${headers.id}?bridgeEndpoint=true")
			.convertBodyTo(String.class)
		;
		
		from("direct:phone")
			.log("phone-----${headers.id}")
			.removeHeaders("CamelHttp*")
			.toD("http4://localhost:8082/location/phone/${headers.id}?bridgeEndpoint=true")
			.convertBodyTo(String.class)
		;
		
		from("direct:addlocation")
			.log("Add Location-----${headers.id}")
			.removeHeaders("CamelHttp*")
			.toD("http4://localhost:8082/location/?bridgeEndpoint=true")
			.convertBodyTo(String.class)
		;
			

	
	}
	
	

}
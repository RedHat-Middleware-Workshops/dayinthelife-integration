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
        
        rest("/location").description("Location information")
         .produces("application/json")
         .get("/contact/{id}").description("Location Contact Info")
             .responseMessage().code(200).message("Data successfully returned").endResponseMessage()
             .to("direct:getalllocationphone")

        ;

        from("direct:getalllocationphone")
            .setBody().simple("${headers.id}")
            .unmarshal().json(JsonLibrary.Jackson)
            .to("cxf://http://location-soap-user1.apps.cluster-5e28.5e28.example.opentlc.com/ws/location?serviceClass=com.redhat.LocationDetailServicePortType&defaultOperationName=contact")

            .process(
                    new Processor(){

                        @Override
                        public void process(Exchange exchange) throws Exception {
                            //LocationDetail locationDetail = new LocationDetail();
                            //locationDetail.setId(Integer.valueOf((String)exchange.getIn().getHeader("id")));

                            MessageContentsList list = (MessageContentsList)exchange.getIn().getBody();

                            exchange.getOut().setBody((ContactInfo)list.get(0));
                        }
                    }
            )

        ;

     }

		
	
		
	
}
	
	



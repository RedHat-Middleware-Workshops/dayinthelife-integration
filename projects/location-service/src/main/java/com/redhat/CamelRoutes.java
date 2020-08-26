package com.redhat;

import javax.annotation.Generated;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestParamType;
import org.springframework.stereotype.Component;
import com.redhat.processor.*;
import com.redhat.model.*;
import org.springframework.stereotype.Component;
import org.apache.camel.model.rest.RestBindingMode;

/**
 * Generated from Swagger specification by Camel REST DSL generator.
 */
@Generated("org.apache.camel.generator.swagger.PathGenerator")
@Component
public final class CamelRoutes extends RouteBuilder {
    /**
     * Defines Apache Camel routes using REST DSL fluent API.
     */
    public void configure() {

        ContactInfoResultProcessor ciResultProcessor = new ContactInfoResultProcessor();
        LocationResultProcessor locationResultProcessor = new LocationResultProcessor();

        restConfiguration()
            .component("servlet")
            .port(8080)
            .bindingMode(RestBindingMode.json)
            .contextPath("/")
            .dataFormatProperty("prettyPrint", "true")
            .enableCORS(true)
            .apiContextPath("/api-doc")
            .apiProperty("api.title", "Location and Contact Info API")
            .apiProperty("api.version", "1.0.0")
        ;

        rest()
         .get("/locations")
             .to("direct:getalllocations")
         .post("/locations")
             .type(Location.class)
             .to("direct:addlocation")
         .get("/locations/{id}")
             .param()
                 .name("id")
                 .type(RestParamType.path)
                 .dataType("integer")
                 .required(true)
             .endParam()
             .to("direct:getlocation")
         .get("/location/phone/{id}")
             .param()
                 .name("id")
                 .type(RestParamType.path)
                 .dataType("integer")
                 .required(true)
             .endParam()
             .outType(ContactInfo.class)
             .to("direct:getlocationdetail")
        ;

        from("direct:getalllocations")
         .to("sql:select * from locations?dataSource=dataSource")
         .process(locationResultProcessor)
         .log("${body}")
        ;

        from("direct:getlocation")
                .to("sql:select * from locations where id=cast(:#id as int)?dataSource=dataSource")
                .process(locationResultProcessor)
                .choice()
                    .when(simple("${body.size} > 0"))
                        .setBody(simple("${body[0]}"))
                    .otherwise()
                        .setHeader("HTTP_RESPONSE_CODE",constant("404"))
                .log("${body}")
        ;

            from("direct:addlocation")
                        .log("Creating new location")
                .to("sql:INSERT INTO locations (id,name,lat,lng,location_type,status) VALUES (:#${body.id},:#${body.name},:#${body.location.lat},:#${body.location.lng},:#${body.type},:#${body.status});?dataSource=dataSource")
            ;

            from("direct:getlocationdetail")
                .to("sql:select * from location_detail where id=cast(:#id as int)?dataSource=dataSource")
                .process(ciResultProcessor)
        ;
    }
}

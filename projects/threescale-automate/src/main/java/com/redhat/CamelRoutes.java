package com.redhat;

import javax.net.ssl.X509ExtendedTrustManager;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.http4.HttpComponent;
import org.apache.camel.model.rest.RestBindingMode;
import org.apache.camel.util.jsse.SSLContextParameters;
import org.apache.camel.util.jsse.TrustManagersParameters;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Component
public class CamelRoutes extends RouteBuilder {
	
	@Autowired
    private CamelContext camelContext;
	
	@Bean 
	SSLContextParameters ssl() {
		TrustManagersParameters trustManagersParameters = new TrustManagersParameters();
        X509ExtendedTrustManager extendedTrustManager = new InsecureX509TrustManager();
        trustManagersParameters.setTrustManager(extendedTrustManager);

        SSLContextParameters scp = new SSLContextParameters();
		scp.setTrustManagers(trustManagersParameters);
		return scp;
	}
	
	
	@Override
	public void configure() throws Exception {
		
		restConfiguration()
			.component("servlet")
	    	.port(8080)
	    	.bindingMode(RestBindingMode.json)
			//.contextPath("/")
	    	.dataFormatProperty("prettyPrint", "true")
	    	.enableCORS(true)
	    	.apiContextPath("/api-doc")
	    	.apiProperty("api.title", "Location API")
	    	.apiProperty("api.version", "1.0.0")
	    ;
		
		//String apiToken= "2dca460318d6e2af6b564210a169aa2c132cc2ca1a83bc23f7dd9740baa23048";
		//String openshiftappurl="apps.dayinlife.openshiftworkshop.com";
		//String userid="user11";
	
		rest("/threescale").description("Location information")
			.produces("application/json")
			.post("/automate/{apiToken}/{userid}/{openshiftappurl}").description("Automatically setup your 3scale APIs")
				.responseMessage().code(200).message("Your API is secured!").endResponseMessage()
				.to("direct:threescalesetup")
			
		;
		
		HttpComponent httpComponent = camelContext.getComponent("https4", HttpComponent.class);
		httpComponent.setX509HostnameVerifier(NoopHostnameVerifier.INSTANCE);

		//This is important to make your cert skip CN/Hostname checks
		httpComponent.setX509HostnameVerifier((s, sslSession) -> true);

		from("direct:threescalesetup")
			.log("starts")
			.log("USERNAME {{env:SSO_USERNAME}}")
			.log("PASSWORD {{env:SSO_PASSWORD}}")
			//SSO and TOKENS
			
			.removeHeaders("CamelHttp*")
			//Get TKN from SSO
				.setHeader(Exchange.HTTP_METHOD, constant("POST"))
				.setHeader(Exchange.CONTENT_TYPE, constant(MediaType.APPLICATION_FORM_URLENCODED_VALUE))
				.setBody(simple("username={{env:SSO_USERNAME}}&password={{env:SSO_PASSWORD}}&grant_type=password&client_id=admin-cli"))
			.toD("https4://sso-sso.${headers.openshiftappurl}/auth/realms/master/protocol/openid-connect/token?sslContextParameters=#ssl&bridgeEndpoint=true")
			.setHeader("tkn").jsonpath("access_token")
			//.log("return---->  ${header.tkn}")	
		
			.removeHeaders("CamelHttp*")
			//GET Client ID
				.setHeader(Exchange.HTTP_METHOD, constant("GET"))
				.setHeader("Authorization").simple("Bearer ${headers.tkn}")
			.toD("https4://sso-sso.${headers.openshiftappurl}/auth/admin/realms/${headers.userid}/clients?sslContextParameters=#ssl&bridgeEndpoint=true")
			.setHeader("idClientAdmin").jsonpath("$..[?(@.clientId == '3scale-admin')].id")
			//.log("return---->  ${headers.idClientAdmin}")	
			
			.removeHeaders("CamelHttp*")
			//GET Client Secret
				.setHeader(Exchange.HTTP_METHOD, constant("GET"))
				.setHeader("Authorization").simple("Bearer ${headers.tkn}")
			.toD("https4://sso-sso.${headers.openshiftappurl}/auth/admin/realms/${headers.userid}/clients/${headers.idClientAdmin}/client-secret?sslContextParameters=#ssl&bridgeEndpoint=true")
			.setHeader("secret").jsonpath("value")
			.log("return---->  ${headers.secret}")	
		
			
			
			//3scale Setups
			
			.removeHeaders("CamelHttp*")
			//Create Service
				//.setHeader("apiToken", constant(apiToken))
				//.setHeader("userid", constant(userid))
				//.setHeader("openshiftappurl", constant(openshiftappurl))
				.log("${headers}")
				.setHeader(Exchange.HTTP_METHOD, constant("POST"))
				.setHeader(Exchange.CONTENT_TYPE, constant(MediaType.APPLICATION_FORM_URLENCODED_VALUE))
				.setBody(simple("access_token=${headers.apiToken}&name=SSO+Location+API&deployment_option=hosted&backend_version=oidc"))
			.toD("https4://${headers.userid}-admin.${headers.openshiftappurl}/admin/api/services.xml?sslContextParameters=#ssl&bridgeEndpoint=true")
			.setHeader("serviceid").xpath("/service/id", String.class) 
			
			.removeHeaders("CamelHttp*")
			//Update service to OIDC
				.setHeader(Exchange.HTTP_METHOD, constant("PUT"))
				.setHeader(Exchange.CONTENT_TYPE, constant(MediaType.APPLICATION_FORM_URLENCODED_VALUE))
				.setBody(simple("access_token=${headers.apiToken}&backend_version=oidc"))
			.delay(1000)
			.toD("https4://${headers.userid}-admin.${headers.openshiftappurl}/admin/api/services/${headers.serviceid}.xml?sslContextParameters=#ssl&bridgeEndpoint=true")
			
			.removeHeaders("CamelHttp*")
			//Setup Proxies
				.setHeader(Exchange.HTTP_METHOD, constant("PATCH"))
				.setHeader(Exchange.CONTENT_TYPE, constant(MediaType.APPLICATION_FORM_URLENCODED_VALUE))
				.setBody(simple("access_token=${headers.apiToken}&endpoint=https%3A%2F%2Flocation-${headers.userid}-api.amp.${headers.openshiftappurl}%3A443&api_backend=http%3A%2F%2Flocation-service.${headers.userid}.svc%3A8080&sandbox_endpoint=https%3A%2F%2Flocation-${headers.userid}-api-staging.amp.${headers.openshiftappurl}%3A443&oidc_issuer_endpoint=http%3A%2F%2F3scale-admin%3A${headers.secret}%40sso-sso.${headers.openshiftappurl}%2Fauth%2Frealms%2F${headers.userid}"))
			.toD("https4://${headers.userid}-admin.${headers.openshiftappurl}/admin/api/services/${headers.serviceid}/proxy.xml?sslContextParameters=#ssl&bridgeEndpoint=true")
			
			.removeHeaders("CamelHttp*")
			.log("Service : [${headers.serviceid}] and Plan [${headers.planid}] and Account [${headers.accountid}]")
			//Create Application Plans
				.setHeader(Exchange.HTTP_METHOD, constant("POST"))
				.setHeader(Exchange.CONTENT_TYPE, constant(MediaType.APPLICATION_FORM_URLENCODED_VALUE))
				.setBody(simple("access_token=${headers.apiToken}&name=Secure"))
			.toD("https4://${headers.userid}-admin.${headers.openshiftappurl}/admin/api/services/${headers.serviceid}/application_plans.xml?sslContextParameters=#ssl&bridgeEndpoint=true")
			.setHeader("planid").xpath("/plan/id", String.class) 
			
			
			.removeHeaders("CamelHttp*")
			//Get Account id
				.setHeader(Exchange.HTTP_METHOD, constant("GET"))
				.setHeader(Exchange.HTTP_QUERY, simple("access_token=${headers.apiToken}"))
				.log("${headers}")
			.delay(1000)
			.toD("https4://${headers.userid}-admin.${headers.openshiftappurl}/admin/api/accounts.xml?sslContextParameters=#ssl&bridgeEndpoint=true")
			.setHeader("accountid").xpath("/accounts/account/id", String.class) 
			
			.removeHeaders("CamelHttp*")
			//Create Application
				.setHeader(Exchange.HTTP_METHOD, constant("POST"))
				.setHeader(Exchange.CONTENT_TYPE, constant(MediaType.APPLICATION_FORM_URLENCODED_VALUE))
				.setBody(simple("access_token=${headers.apiToken}&plan_id=${header.planid}&name=Secured+App&description=SSO+Secured+App&redirect_url=http%3A%2F%2Fwww-${headers.userid}.apps.${headers.openshiftappurl}%2F*"))
			.toD("https4://${headers.userid}-admin.${headers.openshiftappurl}/admin/api/accounts/${header.accountid}/applications.xml?sslContextParameters=#ssl&bridgeEndpoint=true")
			.setHeader("applicationid").xpath("/application/id", String.class) 
			
			.removeHeaders("CamelHttp*")
			//Update policy chain 
				.setHeader(Exchange.HTTP_METHOD, constant("PUT"))
				.setHeader(Exchange.CONTENT_TYPE, constant(MediaType.APPLICATION_FORM_URLENCODED_VALUE))
			.setBody(simple("access_token=${headers.apiToken}&policies_config=%5B+++++%7B+++++++++%22name%22%3A+%22cors%22%2C+++++++++%22version%22%3A+%22builtin%22%2C+++++++++%22configuration%22%3A+%7B+++++++++++++%22allow_headers%22%3A+%5B+++++++++++++++++%22Authorization%22+++++++++++++%5D%2C+++++++++++++%22allow_credentials%22%3A+true%2C+++++++++++++%22allow_methods%22%3A+%5B+++++++++++++++++%22GET%22%2C+++++++++++++++++%22OPTIONS%22+++++++++++++%5D%2C+++++++++++++%22allow_origin%22%3A+%22*%22+++++++++%7D%2C+++++++++%22enabled%22%3A+true+++++%7D%2C+++++%7B+++++++++%22name%22%3A+%22apicast%22%2C+++++++++%22version%22%3A+%22builtin%22%2C+++++++++%22configuration%22%3A+%7B%7D%2C+++++++++%22enabled%22%3A+true+++++%7D+%5D"))
			.toD("https4://${headers.userid}-admin.${headers.openshiftappurl}/admin/api/services/${headers.serviceid}/proxy/policies.json?sslContextParameters=#ssl&bridgeEndpoint=true")
			.log("return---->  ${body}")
			
			
			.removeHeaders("CamelHttp*")
			//Get Metrics id
				.setHeader(Exchange.HTTP_METHOD, constant("GET"))
				.setHeader(Exchange.HTTP_QUERY, simple("access_token=${headers.apiToken}"))			
			.toD("https4://${headers.userid}-admin.${headers.openshiftappurl}/admin/api/services/${headers.serviceid}/metrics.xml?sslContextParameters=#ssl&bridgeEndpoint=true")
			.setHeader("metricid").xpath("/metrics/metric/id", String.class) 
			
			.removeHeaders("CamelHttp*")
			.log("Service : [${headers.serviceid}] and Plan [${headers.planid}] and Account [${headers.accountid}] and Metric [${headers.metricid}]")
			//Get Account id
				.setHeader(Exchange.HTTP_METHOD, constant("POST"))
				.setHeader(Exchange.CONTENT_TYPE, constant(MediaType.APPLICATION_FORM_URLENCODED_VALUE))
				.setBody(simple("access_token=${headers.apiToken}&http_method=GET&pattern=%2Flocations&delta=2&metric_id=${headers.metricid}"))
			.toD("https4://${headers.userid}-admin.${headers.openshiftappurl}/admin/api/services/${headers.serviceid}/proxy/mapping_rules.xml?sslContextParameters=#ssl&bridgeEndpoint=true")
			.setHeader("metricid").xpath("/metrics/metric/id", String.class) 
			
			
			.setBody().simple("API automated, DONE! REMEBER THIS ADDRESS FOR NEXT LAB: PLAN_URL :[ https://${headers.userid}.${headers.openshiftappurl}/signup?plan_ids[]=${headers.planid} ]")
			.setHeader(Exchange.HTTP_RESPONSE_CODE).constant("200")
		;
		
		
		
		
		
	}
		
		

}

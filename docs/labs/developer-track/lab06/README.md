# Lab 4

## Managed API Endpoints

### Take control of your APIs

* Duration: 5 mins
* Audience: API Owners, Product Managers, Developers, Architects

## Overview

Once you have APIs deployed in your environment, it becomes critically important to manage who may use them and for what purpose. You also need to begin to track usage of these different users to know who is/is not succeeding in their usage. For this reason in this lab you will be adding management capabilites to the API to give you control and visibility of it's usage.

### Why Red Hat?

Red Hat provides one the leading API Management tools which provide management services. The 3scale API Management solution enables you to quickly and easy protect and manage your APIs.

### Skipping The Lab

If you are planning to follow to the next lab, there is an already running API proxy for the Location API Service in this endpoint:

```bash
https://location-service-api.amp.apps.GUID.openshiftworkshop.com
```

### Environment

**URLs:**

Check with your instruction the *GUID* number of your current workshop environment. Replace the actual number on all the URLs where you find **GUID**.

Example in case of *GUID* = **1234**:

```bash
https://master.GUID.openshiftworkshop.com
```

becomes =>

```bash
https://master.1234.openshiftworkshop.com
```

**Credentials:**

Your username is your asigned user number. For example, if you are assigned user number **1**, your username is: 

```bash
user1
```

The password to login is always the same:

```bash
openshift
```

## Lab Instructions

### Step 1: Get your API Token for Automation

Your 3scale Admin Portal provides access to a number of configuration features.

1. Open a browser window and navigate to:

    ```bash
    https://userX-admin.apps.GUID.openshiftworkshop.com/
    ```

    *Remember to replace the GUID with your [environment](#environment) value and your user number.*

1. Accept the self-signed certificate if you haven't.

    ![selfsigned-cert](images/00-selfsigned-cert.png "Self-Signed Cert")

1. Log into 3scale using your designated [user and password](#environment). Click on **Sign In**.

    ![01-login.png](images/01-login.png)

1. The first page you will land is the *API Management Dashboard*. Click on the **Gear Icon** on the top right-hand corner.

    ![02-personalsettings.png](images/02-personalsettings.png)

1. Click onto  **Token** Tab.
	![03-tokentab.png](images/03-tokentab.png)

1. Click  **Add Access Token** link to create a new management token.
	![06-menu.png](images/06-menu.png)
	
	
1. Create a new token that has the Read & Writeable rights to your management platform. Enter **Name** as secure token, check the **Account management API** checkbox and **READ & WRITE** for Permission.

	![04-setuptoken.png](images/04-setuptoken.png)
1. Please make sure you copy the **Token** to somewhere safe, and don't forget it. Click on **I have copied the token** to finish off. 

	![05-token.png](images/05-token.png)


### Step 2: Start managing your APIs

In you command line terminal or in your Che terminal enter the following CURL command:

`curl -k -X POST http://localhost:8080/threescale/automate/{YOUR_API_TOKEN}/{USERX}/{OPENSHIFT_APP_URL}`

For exmple 

`curl -k -X POST http://localhost:8080/threescale/automate/44e1592e40b12bae0f2c539bfcc7728ab8383248c1ad34495252398ebb7b6b6d/user12/apps.dayinlife.openshiftworkshop.com`

String **API automated, DONE!** Should be returned as the result.


*Congratulations!* You have configured 3scale access control layer as a proxy to only allow authenticated calls to your backend API. 3scale is also now:

* Authenticating (If you test with an incorrect API key it will fail) 
* Recording calls (Visit the Analytics tab to check who is calling your API).

## Steps Beyond

In this lab we just covered the basic creating of a proxy for our API service. Red Hat 3scale API Management also allows us to get a track of the security (as you can see in the next lab) as well as the usage of our API. If getting value from APIs is also important to you, 3scale allows you to monetize your APIs with it's embedded billing system.

Try to navigate through the rest of the tabs of your Administration Portal. Did you notice that there are application plans associated to your API? Application Plans allow you to take actions based on the usage of your API, like doing rate limiting or charging by hit or monthly usage.

## Summary

You set up an API management service and API proxies to control traffic into your API. From now on you will be able to issue keys and rights to users wishing to access the API.

You can now proceed to [Lab 5](../lab05/#lab-5)

## Notes and Further Reading

* [Red Hat 3scale API Management](http://microcks.github.io/)
* [Developers All-in-one 3scale install](https://developers.redhat.com/blog/2017/05/22/how-to-setup-a-3scale-amp-on-premise-all-in-one-install/)
* [ThoughtWorks Technology Radar - Overambitious API gateways](https://www.thoughtworks.com/radar/platforms/overambitious-api-gateways)
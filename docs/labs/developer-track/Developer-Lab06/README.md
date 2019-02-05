# Lab 6

## Managed API Endpoints

### Take control of your APIs

* Duration: 5 mins
* Audience: API Owners, Product Managers, Developers, Architects

## Overview

Once you have APIs deployed in your environment, it becomes critically important to manage who may use them and for what purpose. You also need to begin to track usage of these different users to know who is/is not succeeding in their usage. For this reason in this lab you will be adding management capabilities to the API to give you control and visibility of it's usage.

### Why Red Hat?

Red Hat provides one the leading API Management tools which provide management services. The 3scale API Management solution enables you to quickly and easy protect and manage your APIs.

### Skipping The Lab

If you are planning to follow to the next lab, there is an already running API proxy for the Location API Service in this endpoint:

```bash
http://location-service-international.dil.opentry.me
```

### Environment

**Credentials:**

Your username is your assigned user number. For example, if you are assigned user number **1**, your username is: 

```bash
user1
```

Please ask your instructor for your password.

**URLs:**

If you haven't done so already, you need to login to the **Red Hat Solution Explorer** webpage so that a unique lab environment can be provisioned on-demand for your exclusive use.  You should open a web browser and navigate to: 

```bash
https://tutorial-web-app-webapp.dil.opentry.me
```

You will be presented with a login page where you can enter your unique credentials:

![design-login](images/design-50.png "Login")

Enter your credentials and click **Log in**.  You'll notice a web-page appear which explains that a *unique environment* is being provisioned.

![design-login](images/design-51.png "Provision")

Once the environment is provisioned, you will be presented with a page that presents all the available applications which you'll need in order to complete the labs:

![design-login](images/design-52.png "Applications")

## Lab Instructions

### Step 1: Get API Token for Automation

Your 3scale Admin Portal provides access to a number of configuration features. An administration token is needed when automating setups for your API. This step will let you create a new token for setup.

1. Open a browser window and navigate to:

    ```bash
    https://userX-admin.dil.opentry.me/p/login
    ```

1. Accept the self-signed certificate if you haven't.

1. Log into 3scale using your designated [user and password](#environment). Click on **Sign In**.

    ![01-login.png](images/01-login.png)

1. The first page you will land is the *API Management Dashboard*. Click on the **Gear Icon** on the top right-hand corner.

    ![02-personalsettings.png](images/02-personalsettings.png)

1. Click onto  **Tokens** Tab.
	![03-tokentab.png](images/03-tokentab.png)

1. Click  **Add Access Token** link to create a new management token.
	![06-menu.png](images/06-menu.png)
	
	
1. Create a new token that has the Read & Writeable rights to your management platform. Enter Name as **securetoken**, check the **Account management API** checkbox and **READ & WRITE** for Permission.

	![04-setuptoken.png](images/04-setuptoken.png)

1. Please make sure you copy the **Token** to somewhere safe, and don't forget it. Click on **I have copied the token** to finish off. 

	![05-token.png](images/05-token.png)


### Step 2: Start managing your APIs

By running the command lines, it will automatically setup the 3scale API configuration and start managing the API you have exposed! 
In you command line terminal or in your Che terminal enter the following CURL command:

*Replace USERX as your user id, such as user1, user26, and OPENSHIFT_APP_URL, if you are not sure, check with your instructor*

```bash
curl -X POST http://threescale-automate-international.dil.opentry.me/threescale/automate/{YOUR_API_TOKEN}/{USERX}/{OPENSHIFT_APP_URL}
```

For example: 

```bash
curl -X POST http://threescale-automate-international.dil.opentry.me/threescale/automate/829405ec3d2dd0f91aa8435347827135c323c69757dd2dfb49ed41aa8ceb13ef/user26/dil.opentry.me
```

String **API automated, DONE!** should be returned as the result.


*Congratulations!* You have configured 3scale access control layer as a proxy to only allow authenticated calls to your backend API. 3scale is also now:

* Authenticating (If you test with an incorrect API key it will fail) 
* Recording calls (Visit the Analytics tab to check who is calling your API).

#### Common Gotcha
If your encountered error:
```org.apache.camel.http.common.HttpOperationFailedException: HTTP operation failed invoking https://{USERX}-admin.dil1.opentry.me/admin/api/services.xml with statusCode: 422```

This may be due to a failed build. Troubleshoot by deleting SSO Location API.
SSO Location API can be located here:

* In your browser, `https://{USERX}-admin.dil1.opentry.me/config/services`
* In the tab menu, click **APIs**
* Click SSO Location API - **Definition**
* Click **edit**
* Click **I understand the consequences, proceed to delete 'SSO Location API' service.**

Try running this command again in the terminal:
```curl -X POST http://threescale-automate-international.dil.opentry.me/threescale/automate/{YOUR_API_TOKEN}/{USERX}/{OPENSHIFT_APP_URL}```

## Steps Beyond

In this lab we just covered the basic creating of a proxy for our API service. Red Hat 3scale API Management also allows us to get a track of the security (as you can see in the next lab) as well as the usage of our API. If getting value from APIs is also important to you, 3scale allows you to monetize your APIs with it's embedded billing system.

Try to navigate through the rest of the tabs of your Administration Portal. Did you notice that there are application plans associated to your API? Application Plans allow you to take actions based on the usage of your API, like doing rate limiting or charging by hit or monthly usage.

## Summary

You set up an API management service and API proxies to control traffic into your API. From now on you will be able to issue keys and rights to users wishing to access the API.

You can now proceed to [Lab 7](../lab07/#lab-7)

## Notes and Further Reading

* [Red Hat 3scale API Management](http://microcks.github.io/)
* [Developers All-in-one 3scale install](https://developers.redhat.com/blog/2017/05/22/how-to-setup-a-3scale-amp-on-premise-all-in-one-install/)
* [ThoughtWorks Technology Radar - Overambitious API gateways](https://www.thoughtworks.com/radar/platforms/overambitious-api-gateways)

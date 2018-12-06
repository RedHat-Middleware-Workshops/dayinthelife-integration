# Lab 4

## Managing API Endpoints

### Take control of your APIs

* Duration: 15 mins
* Audience: API Owners, Product Managers, Developers, Architects

## Overview

Once you have APIs deployed in your environment, it becomes critically important to manage who may use them, and for what purpose. You also need to begin to track usage of these different users to know who is or is not succeeding in their usage. For this reason, in this lab, you will be adding management capabilities to the API to give you control and visibility of it's usage.

### Why Red Hat?

Red Hat provides one the leading API Management tools that provides API management services. The [3scale API Management](https://www.3scale.net/) solution enables you to quickly and easily protect and manage your APIs.

### Skipping The Lab

If you are planning to skip to the next lab, there is an already running API proxy for the Location API Service in this endpoint:

```bash
https://location-service-api.amp.dil.opentry.me
```

### Environment

**Credentials:**

Your username is your assigned user number. For example, if you are assigned user number **1**, your username is: 

```bash
user1
```

Please ask your instructor for your password.

**URLs:**

If you haven't done so already, you need to login to the **Red Hat Solution Explorer** webpage so that a unique lab environment can be provisioned on-demand for your exclusive use. You should open a web browser and navigate to: 

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

### Step 1: Define your API Proxy

Your 3scale Admin Portal provides access to a number of configuration features.

1. Click on the `3scale Admin Dashboard` from the Red Hat Solution Explorer. The URL should look like

    ```bash
    https://userX-admin.dil.opentry.me/p/login
    ```

    *Remember the `X` variable in the URL with your assigned user number.*

1. Accept the self-signed certificate if you haven't.

1. Log into 3scale using your designated [user and password](#environment). Click on **Sign In**.

    ![01-login.png](images/01-login.png)

1. The first page you will land is the *API Management Dashboard*. Click on the **API** menu link.

    ![01a-dashboard.png](images/01a-dashboard.png)

1. This is the *API Overview* page. Here you can take an overview of all your services. Click on the **Integration** link.

    ![02-api-integration.png](images/02-api-integration.png)

1. Click on the **edit integration settings** to edit the API settings for the gateway.

    ![03-edit-settings.png](images/03-edit-settings.png)

1. Keep select the **APIcast** deployment option in the *Gateway* section.

    ![04-apicast.png](images/04-apicast.png)

1. Scroll down and keep the **API Key (user_key)** Authentication.

    ![05-authentication.png](images/05-authentication.png)

1. Click on **Update Service**.

1. Click on the **add the Base URL of your API and save the configuration** button.

    ![04-base-url](images/04-base-url.png)

1. Leave the settings for `Private Base URL`, `Staging Public Base URL`, and `Production Public Base URL` as it is. We will come back to the screen to update the correct values in later step.

1. Scroll down and expand the **MAPPING RULES** section to define the allowed methods on our exposed API.

    *The default mapping is the root ("/") of our API resources, and this example application will not use that mapping. The following actions will redefine that default root ("/") mapping.*

    ![07b-mapping-rules.png](images/07b-mapping-rules.png)

1. Click on the **Metric or Method (Define)**  link.

    ![07b-mapping-rules-define.png](images/07b-mapping-rules-define.png)

1. Click on the **New Method** link in the *Methods* section.

    ![07b-new-method.png](images/07b-new-method.png)

1. Fill in the information for your Fuse Method.

    * Friendly name: **Get Locations**

    * System name: **locations_all**

    * Description: **Method to return all locations**

    ![07b-new-method-data.png](images/07b-new-method-data.png)

1. Click on **Create Method**.

1. Click on the **Add mapping rule** link.

    ![07b-add-mapping-rule.png](images/07b-add-mapping-rule.png)

1. Click on the edit icon next to the GET mapping rule.

    ![07b-edit-mapping-rule.png](images/07b-edit-mapping-rule.png)

1. Type in the *Pattern* text box the following: 

    ```bash
    /locations
    ```

1. Select **locations_all** as Method from the combo box.

    ![07b-getall-rule.png](images/07b-getall-rule.png)

### Step 2: Define your API Policies

Red Hat 3scale API Management provides units of functionality that modify the behavior of the API Gateway without the need to implement code. These management components are know in 3scale as policies.

The order in which the policies are executed, known as the “policy chain”, can be configured to introduce differing behavior based on the position of the policy in the chain. Adding custom headers, perform URL rewriting, enable CORS, and configurable caching are some of the most common API gateway capabilities implemented as policies.

1. Scroll down and expand the **POLICIES** section to define the allowed methods on our exposed API.

    ![01-policies](images/policies-01.png "Policies")

    *The default policy in the Policy Chain is APIcast. This is the main policy and most of the times you want to keep it*.

1. Click the **Add Policy** link to add a new policy to the chain.

    ![02-add-policy](images/policies-02.png)

    _Out-of-the-box 3scale includes a set of policies you can use to modify the way your API gateway behaves. For this lab, we will focus on the **Cross Origin Resource Sharing (CORS)** one as we will use it in the consumption lab_.

1. Click in the **CORS** link to add the policy.

    ![03-cors-policy](images/policies-03.png "CORS")

1. Put your mouse over the right side of the policy name to enable the reorder of the chain. Drag and drop the CORS policy to the top of the chain.

    ![04-chain-order](images/policies-04.png "Chain Order")

1. Now **CORS** policy will be executed before the **APIcast**. Click the **CORS** link to edit the policy.

    ![05-cors-configuration](images/policies-05.png "Cors Configuration")

1. In the *Edit Policy* section, click the green **+** button to add the allowed headers.

    ![06-add-headers](images/policies-06.png "Add Allow Headers")

1. Type **Authorization** in the *Allowed headers* field. 

    ![07-authorization-header](images/policies-07.png "Add Authorization Header")

1. Tick the **allow_credentials** checkbox and fill in with a star (**\***) the *allow_origin* text box.

    ![08-allow-origin](images/policies-08.png "Allow Origin")

1. Click twice the green **+** button under *ALLOW_METHODS* to enable two combo boxes for the CORS allowed methods.

1. Select **GET** from the first box and **OPTIONS** from the second box.

    ![09-allow-methods](images/policies-09.png "Allow Methods")

1. Click the **Submit** button to save the policy configuration.

### Step 3: Configure the Upstream Endpoint

1. Scroll back to the top of the page. Fill in the information for accessing your API:

    * Private Base URL: **http://location-service.international.svc:8080**

    * Staging Public Base URL: **https://location-userX-api-staging.amp.dil.opentry.me:443**

    * Production Public Base URL: **https://location-userX-api.amp.dil.opentry.me:443**

    *Remember to replace the X with your user number*.

    *We are using the internal API service, as we are deploying our services inside the same OpenShift cluster*.

    ![07-baseurl-configuration.png](images/07-baseurl-configuration.png)

1. Scroll down to the **API Test GET request**.

1. Type in the textbox:

    ```bash
    /locations
    ```

1. Click on the **Update the Staging Environment** to save the changes and check the connection between client, gateway and API.

    ![08-update-staging.png](images/08-update-staging.png)

    *If everything works, you will get a green message on the left*.

1. Click on **Back to Integration &amp; Configuration** link to return to your API overview.

    ![08aa-back-to-integration.png](images/08aa-back-to-integration.png)

1. Click on the **Promote v.1 to Production** button to promote your configuration from staging to production.

    ![08a-promote-production.png](images/08a-promote-production.png)

*Congratulations!* You have configured 3scale access control layer as a proxy to only allow authenticated calls to your backend API. 3scale is also now:

* Authenticating (If you test with an incorrect API key it will fail) 
* Recording calls (Visit the Analytics tab to check who is calling your API).

## Steps Beyond

In this lab we just covered the basics of creating a proxy for our API service. Red Hat 3scale API Management also allows us to keep track of  security (as you will see in the next lab) as well as the usage of our API. If getting money for your APIs is also important to you, 3scale  allows you to monetize your APIs with its embedded billing system.

Try to navigate through the rest of the tabs of your Administration Portal. Did you notice that there are application plans associated to your API? Application Plans allow you to take actions based on the usage of your API, like doing rate limiting or charging by hit (API call) or monthly usage.

## Summary

You set up an API management service and API proxies to control traffic into your API. From now on you will be able to issue keys and rights to users wishing to access the API.

You can now proceed to [Lab 5](../lab05/#lab-5)

## Notes and Further Reading

* [Red Hat 3scale API Management](https://www.3scale.net/)
* [Developers All-in-one 3scale install](https://developers.redhat.com/blog/2017/05/22/how-to-setup-a-3scale-amp-on-premise-all-in-one-install/)
* [ThoughtWorks Technology Radar - Overambitious API gateways](https://www.thoughtworks.com/radar/platforms/overambitious-api-gateways)

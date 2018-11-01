# Lab 8

## API Consumption

### Connect Applications and APIs

* Duration: 15 mins
* Audience: API Consumers, Developers, Architects

### Overview

APIs provide the building blocks for applications, but it is applications which deliver functionality to the end users. hence to see APIs in action it helps to see how applications can call APIs to provide new functionality. In this lab you'll be able to create a simple web application which consumes the API you built earlier ojn in the exercises.  

### Why Red Hat?

Applications can be built from many technologies. In this case we use a simple web application, but a wide range of Red Hat and non-Red Hat technologies could be used.

### Skipping The Lab

If you decide to skip the lab you can check how a SSO enabled web applications looks like:

```bash
http://www-international.dil.opentry.me/
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

### Step 1: Update OpenShift Deployment

OpenShift let you automatically redeploy your changes when you setup a Continuous Integration / Continuous Deployment (CI/CD) pipeline through the use of webhook. For this lab we will trigger the new build and deployment manually through the OpenShift Console.

1. Go back to your OpenShift web console. Navigate to your project's overview page.

1. Scroll down and click in the www link in the BUILDS section.

   ![01-scroll-down](images/deploy-10.png "Scroll Down")

1. In the build configuration page, replace the `CLIENT_ID` from `CHANGE_ME`to the one generated from [Lab 06 Step 3.5](https://github.com/RedHatWorkshops/dayinthelife-integration/tree/master/docs/labs/citizen-integrator-track/lab06#step-3-login-to-developer-portal)

https://github.com/RedHatWorkshops/dayinthelife-integration/tree/master/docs/labs/citizen-integrator-track/lab06#step-3-login-to-developer-portal

   ![02-client-id](images/deploy-11.png "Change Client ID")

1. Click Save button to persist the changes. A green pop up will show you that the changes were saved.

1. Click the Start Build button to trigger a new build using the new environment variables pointing to your service.

   ![03-start-build](images/deploy-12.png "Start Build")

1. A new build will be triggered. Expand the row by clicking the Builds Icon.

   ![04-view-build](images/deploy-13.png "View Build")

*The build process checks out the code from the git repo, runs a source-to-image container image build, and redeploys the container with the new image using a rolling upgrade strategy.*

1. Wait for until the new Build to complete and the rolling upgrade to finish to test your new deployment.

   ![22-updated-app](images/consume-22.png "Updated App")

### Step 2: Update Secured Service with Red Hat Single Sign On Application Callback

Redirect URLs are a critical part of the OAuth flow. After a user successfully authorizes an application, the authorization server will redirect the user back to the application with either an authorization code or access token in the URL. Because the redirect URL will contain sensitive information, it is critical that the service doesn’t redirect the user to arbitrary locations.

1. Open a browser window and navigate to:

    ```bash
    http://sso-sso.dil.opentry.me/auth/admin/userX/console/
    ```

    *Remember to replace the X with your user number.*

1. Log into Red Hat Single Sign On using your designated [user and password](#environment). Click on **Sign In**.

    ![00-login-sso.png](images/00-login-sso.png "RH SSO Login")

1. Select **Clients** from the left menu.

    ![00-clients.png](images/00-clients.png "Clients")

    *3scale, through it's [zync](https://github.com/3scale/zync/) component, already synchronized the application information into the Red Hat SSO security realm*.

1. Click on the **CLIENT_ID** link to view the details.

    ![24-client-application](images/consume-24.png "Client Application")

    *Remember to replace CLIENT_ID with the one you got in the [API Security Lab](../lab04/#step-4-create-a-test-app). It will easily identificable as its and hexadecimal name*.

1. Scroll down, type in and select the following options in the application configuration:

    * Access Type: **Public**
    * Standard Flow Enabled: **ON**
    * Implicit Flow Enabled: **OFF**
    * Valid Redirect URIs: **[http://www-userX.dil.opentry.me//*](http://www-userX.apps.GUID.openshiftworkshop.com/*)**
    * Web Origins: **\***

    *Remember to replace the X your user number.*

    ![25-client-config](images/consume-25.png "Client Configuration")

1. Finally, click **Save** button to persist the changes.

### Step 3: Updating OpenShift Deployment

OpenShift let you automatically redeploy your changes when you setup a Continuous Integration / Continuous Deployment (CI/CD) pipeline through the use of webhook. For this lab we will trigger the new build and deployment manually through the OpenShift Console.

1. Go back to your OpenShift web console. Navigate to your project's overview page.

1. Scroll down and click in the **www** link in the *BUILDS* section.

    ![10-builds](images/deploy-10.png "Builds")
    
1. Select **Edit** uner the **Actions** dropdown menu. 
	![10-build-edit](images/03-build-edit.png "Edit Build Config")

1. Under Git Repositoy URL, click on the advance options. 
	![10-advance-option](images/11-advance-option.png "Choose Advance Option")

1. Add **secured** to the Git Reference.So our new International Inc Web Page will now be using Red Hat SSO to secure it's location information.
	![12-git-reference](images/12-git-reference.png "Edit Git Reference")



1. Scroll down to Environment Variable section. **Replace** the unprotected endpoint URL with the new value of your 3scale-protected Location Service API URL. Also add this new three environment variables **SSO\_URL**, **SSO\_REALM** and **CLIENT\_ID**.

    _Click **Add Value** to add additional rows_.

    * Name: **API\_BACKEND\_URL**
    * Value: **https://location-userX-api.amp.dil.opentry.me/locations**
    * Name: **SSO\_URL**
    * Value: **http://sso-sso.dil.opentry.me**
    * Name: **SSO\_REALM**
    * Value: **userX**
    * Name: **CLIENT\_ID**
    * Value: **REPLACE\_ME**

    *Replace REPLACE\_ME with the Client ID you got in the [API Security Lab](../lab04/#step-4-create-a-test-app), it should look like `e343da3`. Remember to replace the GUID with your [environment](#environment) value and your user number*.

    ![14-environment](images/deploy-14.png "Replace URL")

1. Click **Save** button to persist the changes. A green pop up will show you that the changes were saved.

1. Click the **Start Build** button to trigger a new build using the new environment variables pointing to your service.

    ![12-start-build](images/deploy-12.png "Start Build")

1. A new build will be triggered. Expand the row by clicking the **Builds** Icon.

    ![20-build-configuraion](images/consume-20.png "Build Configuration")

    *The build process checks out the code from the git repo, runs a source-to-image container image build, and redeploys the container with the new image using a rolling upgrade strategy*.

1. Wait for until the new **Build to complete** and the rolling upgrade to finish to test your new deployment.

    ![21-build-complete](images/consume-21.png "Build Complete")

1. Refresh the browser tab where you opened the International Inc web page or click the route URL to check out the changes.

    ![22-updated-app](images/consume-22.png "Updated App")

    _You can notice now the **Sign In** button in the page_.

### Step 5: Test the Single Sign On Integration

1. Let's test the integration. Click the **Sign In** button.

1. You are being redirected to Red Hat Single Sign On **Login Page**. Login using the user credentials you created in the [API Security Lab](../lab04/#step-2-add-user-to-realm)

    * Username: **developer** (credentials from developer portal)
    * Password: **PWD for developer** (credentials from developer portal)

    ![23-realm-login](images/consume-23.png "Login Realm")

1. You will be redirected again to the **LOCATIONS** page where now you will be able to see the map with the International Inc Offices.

    ![11-locations-page](images/consume-14.png "Locations Page")

 *Congratulations!* You have successfully used the Keycloak Javascript Adapter to protect International Inc's Locations Service with Single Sign On.

## Steps Beyond

So, you want more? You can explore in detail the documentation on the Javascript Adapter to check what other things can you get from your authenticated user.

## Summary

In total you should now have been able to follow all the steps from designing and API, deploying it's code, issuing keys, connecting OpenID connect and calling it from an application. This gives you a brief overview of the creation and deployment of an API. There are many variations and extensions of these general principles to explore!

You can now proceed to [Lab 9](../lab09/#lab-9)

## Notes and Further Reading

* [Red Hat 3scale API Management](http://microcks.github.io/)
* [Setup OIDC with 3scale](https://developers.redhat.com/blog/2017/11/21/setup-3scale-openid-connect-oidc-integration-rh-sso/)

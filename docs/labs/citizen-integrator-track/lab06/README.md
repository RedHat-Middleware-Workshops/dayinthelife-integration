# Lab 6

## API Developer Portal

### Publishing APIs to Developer Portal

* Duration: 20 mins
* Audience: API Owners, Product Managers, Developers, Architects

## Overview

The focal point of your developers experience is the API developer portal, and the level of effort you put into it will directly correlate to the level of success of your API program, and API developer engagement.

### Why Red Hat?

[Red Hat 3scale API Management](https://www.3scale.net/) Solution provides a built-in, state-of-the-art CMS portal, making it very easy to create your own branded hub with a custom domain to manage developer interactions, and help increase API adoption.

You can customize the look and feel of the entire Developer Portal to match your own branding. You have complete control over every element of the portal, so you can make it as easy as possible for developers to learn how to use your API.

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
https://tutorial-web-app-webapp.apps.newton-46c9.openshiftworkshop.com
```

You will be presented with a login page where you can enter your unique credentials:

![design-login](images/design-50.png "Login")

Enter your credentials and click **Log in**.  You'll notice a web-page appear which explains that a *unique environment* is being provisioned.

![design-login](images/design-51.png "Provision")

Once the environment is provisioned, you will be presented with a page that presents all the available applications which you'll need in order to complete the labs:

![design-login](images/design-52.png "Applications")

## Lab Instructions

### Step 1: Customizing Developer Portal

1. Click on the `3scale Admin Dashboard` from the Red Hat Solution Explorer. The URL should look like

    ```bash
    https://userX-admin.apps.newton-46c9.openshiftworkshop.com/p/login
    ```

    *Remember the `X` variable in the URL is your assigned user number.*

1. Accept the self-signed certificate if you haven't.

1. Log into 3scale using your designated [user and password](#environment). Click on **Sign In**.

    ![01-login.png](images/01-login.png)

1. Click on the **Developer Portal** tab to access the developer portal settings.

    ![10-developer-portal.png](images/10-developer-portal.png)

1. Click on the **Visit Developer Portal** to take a look of how your developer portal looks like.

    ![11-visit-devportal.png](images/11-visit-devportal.png)

1. You can see there is a default portal with information on your API, and how to signup. Unfortunately the API information is incorrect.

    ![12-devportal-overview.png](images/12-devportal-overview.png)

    *We will edit our portal to update it with the correct information, and we will add the cool shadowman logo*.

1. Go back to your admin portal browser tab and scroll down to the content sub-sections for the **Layouts** and select the **Main layout** menu option.

    ![12-main-layout.png](images/12-main-layout.png)

1. First look for the **navbar** section of the main HTML. Replace the **{{ provider.name }}** for the shadowman image link:

    `<img src="https://www.redhat.com/profiles/rh/themes/redhatdotcom/img/logo.png" alt="{{ provider.name }}">`

    ![13-custom-logo.png](images/13-custom-logo.png)

1. Click the *Publish* button at the bottom of the editor to save the changes and made them available in the site.

    ![14-publish-devportal.png](images/14-publish-devportal.png)

1. Go back to browse the top of the content sub-sections and find the **Homepage** section.

    ![15-homepage-devportal.png](images/15-homepage-devportal.png)

1. Change all the code **Echo** references in the homepage code for **Office Locations**.

1. Click the **Publish** button.

1. Refresh your Developer Portal's browser tab to check the changes. Your Developer Portal should now look like this:

    ![16-updated-devportal.png](images/16-updated-devportal.png)

### Step 2: Register New Accounts Using Developer Portal

1. Take the place of one of your developers and signup for the **Basic** plan.

    ![16a-signup-limited.png](images/16a-signup-limited.png)

1. Fill in your information and an email to register as a developer. Click on the **Sign up** button.

    ![16b-signup-form.png](images/16b-signup-form.png)

1. The system will try to send a message with an activation link.

    ![16bb-signup-thankyou.png](images/16bb-signup-thankyou.png)

    *Currently the lab environment doesn't have a configured email server, so we won't be able to receive the email*.

1. Go back to your *Admin Portal* tab and navigate to **Developers** to activate the new account.

    ![16bc-developers-tab.png](images/16bc-developers-tab.png)

1. Find your user under the *Accounts* and click the **Activate** link.

    ![16cc-activate-account.png](images/16cc-activate-account.png)

    *Your user is now active and can log into the portal*.

### Step 3: Login to Developer Portal

1. As your portal is not currently public, you will need your portal code to login. You can get the code in your admin portal navigating to: **Settings > Developer Portal > Domains &amp; Access**.

    ![16d-access-portal.png](images/16d-access-portal.png)

1. Open a new *Incognito/Private* browser window to test the Developer Portal login. Navigate to:

    ```bash
    https://userX.apps.newton-46c9.openshiftworkshop.com/
    ```

1. Type your portal code to finish the login.

    ![16e-ingress-code.png](images/16e-ingress-code.png)

1. Sign in to the portal.

    ![16f-dev-signin.png](images/16f-dev-signin.png)

1. You will land in the developers homepage, where you will be able to check your developers settings and retrieve your newly created **Client ID** and **Client Secret**.

    ![16g-user-credentials.png](images/16g-user-credentials.png "Application Credentials")

*Congratulations!* You have successfully customized your Developer Portal and completed a Sign Up process.

## Steps Beyond

So, you want more? Click the **Documentation** link. Where does it takes you? *API Docs* is where you can add your interactive documentation for your APIs. Is based on the known *Swagger UI* interface.

You can add from the Admin Portal under *API Docs* the API definition to generate the live testing.

## Summary

In this lab you discovered how to add a developer facing experience to your APIs. Developers in your organization or outside of it can now register, gain access to API keys and develop sample applications.

You can now proceed to [Lab 7](../lab07/#lab-7)

## Notes and Further Reading

Red Hat 3scale Developer Portal's CMS consists of a few elements:

* Horizontal menu in the Admin Portal with access to content, redirects, and changes
* The main area containing details of the sections above
* CMS mode, accessible through the preview option

![09-developer-portal.png](images/09-developer-portal.png)

[Liquid](https://github.com/Shopify/liquid) is a simple programming language used for displaying and processing most of the data from the 3scale system available for API providers. In 3scale, it is used to expose server-side data to your API developers, greatly extending the usefulness of the CMS while maintaining a high level of security.

### Links

* [Developer Portal Documentation](https://access.redhat.com/documentation/en-us/red_hat_3scale/2.2/html/developer_portal/)
* [Liquid markup language](https://github.com/Shopify/liquid)
* [And Overview of Liquid](https://www.shopify.com/partners/blog/115244038-an-overview-of-liquid-shopifys-templating-language)

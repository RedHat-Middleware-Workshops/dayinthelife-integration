# Lab 2

## API Mocking

### Bring your APIs to life

* Duration: 20 mins
* Audience: Developers, Architects, Testers, Quality Engineers

## Overview

When building and working with APIs, you often need to simulate the responses of the system before it has been fully completed. This is what we explore in this workshop - mocking up API structures quickly so they can be subjected to testing without having to create all the final service code.

### Why Red Hat?

Red Hat combines a number of commercial and Open Source tools to cover each part of the API Design lifecycle. In this lab we'll be using the [Microcks](http://microcks.github.io/) open source tool.

### Skipping The Lab

If you are planning to follow the next lab, here is a [link](wip-link) to the REST mock service running the Location API.

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

### Step 1: Create a Microcks Job

1. Open a browser window and navigate to:

    ```bash
    http://microcks.dil.opentry.me
    ```

1. Log in into Microks using your designated [user and password](#environment).

   ![mock-openshift-login](images/mock-09.png "Openshift Login")

1. You are now in the main Microcks page. Click the **Importers** button to access the Importers page.

   ![mock-jobs](images/mock-11.png "Job")

1. Click the **Upload** button to upload the **Locations-UserX.yaml** spec generated from [Lab 1](lab01.md).

   ![mock-jobs](images/mock-12.png "Upload Spec")
    
1. Click on **Upload**.

   ![mock-jobs](images/mock-13.png "Upload")

   *Congratulations! The spec is successfully imported*

1. Navigate to **APIs | Services** tab.

   ![mock-jobs](images/mock-14.png "Job")

1. Click on the link for your username. eg: **Locations-User1**.

   ![mock-jobs](images/mock-15.png "Select services")

1. Click on the arrow to expand the **Operation GET /locations**.

   ![mock-mock-service](images/mock-16.png "Mock Service")


1. You can check that the example we added to the definition in [Lab 1](lab01.md) will be used to return the mock values. Copy and save the **Mocks URL**, we will use that endpoint to test the REST mock service.

    ![mock-mock-operation](images/mock-17.png "Mock Operation")

### Step 2: Test the REST Mock Service

We now have a working REST mock service listening for requests. We will use an online cURL tool to test it.

1. Open a browser window and navigate to:

    ```bash
    https://onlinecurl.com/
    ```

1. Enter the copied URL from **Step 9**. It should look like this:

    ```bash
    http://microcks.dil.opentry.me/rest/Locations-User1/1.0.0/locations
    ```

1. Click the **START YOUR CURL** button.

    ![mock-curl-service](images/mock-18.png "cURL Service")

1. The page will load the response information from the service. You will be able to see the *RESPONSE HEADERS* and the actual *RESPONSE_BODY*. This last part contains the examples we add during the design phase.

    ![mock-curl-response](images/mock-19.png "cURL Response")

*Congratulations!* You have successfully configure a Microcks Job to create a REST mock service to test your API.

## Steps Beyond

> So, you want more? ...

## Summary

In this lab you used Microcks to configure a REST mock service for the API definition you created in the previous lab. REST mock services allows you to simulate a REST API service when you are in a prototyping stage of your API program journey. 

Microcks allows you to test a number of various responses for client application requests. When deploying API, micro-services or SOA practices at large scale, Microcks solves the problems of providing and sharing consistent documentation and mocks to the involved teams. It acts as a central repository and server that can be used for browsing but also by your Continuous Integration builds or pipelines.

You can now proceed to [Lab 3](../lab03/#lab-3)

## Notes and Further Reading

* Microcks
  * [Webpage](http://microcks.github.io/)
  * [Jenkins Plugin](http://microcks.github.io/automating/jenkins/)
  * [Installing on OpenShift](http://microcks.github.io/installing/openshift/)

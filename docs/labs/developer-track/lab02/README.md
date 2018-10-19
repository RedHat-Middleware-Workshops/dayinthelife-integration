# Lab 2

## API Mocking

### Bring your APIs to life

* Duration: 20 mins
* Audience: Developers, Architects, Testers, Quality Engineers

## Overview

When building and working with APIS, you often need to simulate the responses of the system before it has been fully completed. This is what we explore in this workshop - mocking up API structures quickly so they can be subjected to testing withouth having to create all the final service code.

### Why Red Hat?

Red hat combines and number of commercial and Open Source tools to cover each part of the API Design lifecycle. In this lab we'll be using the Microcks open source tool.

### Skipping The Lab

We know sometime we don't have enough time to go over step by step on the labs. So here is a [short video](wip-link) where you can see how to mock the API you previously created using Microcks.

If you are planning to follow the next lab, here is a [link](wip-link) to the REST mock service running the Location API.

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

### Step 0: Setup the collaboration environment using Git (Gogs)

For this lab we require a collaboration environment based on Git. You can use GitHub, GitLab or other Git provider to finish this lab. If you don't want to use your personal account, the provided lab environment has an user provided for you in Gogs. 

Follow this instructions to set up the repository.

1. Open a browser window and navigate to:

    ```bash
    http://gogs.apps.GUID.openshiftworkshop.com/user/login?redirect_to=
    ```

1. Log into Gogs using your designated [user and password](#environment). Click on **Sign In**.

    ![mock-sign-in](images/mock-01.png "Sign In")

1. In the main page, click in the **+** sign in the right top corner to display the *New* menu. Click the **New Migration** option.

    ![mock-gogs-new](images/mock-02.png "New Migration")

1. Fill in the information of the repository migration with the following values:

    * Clone Address: **https://github.com/RedHatWorkshops/dayinthelife-openapi.git**
    * Owner: **UserX**
    * Repository Name: **locations-api**

    ![mock-gogs-migration](images/mock-03.png "New Migration Repository")

1. Click on **Migrate Repository** to fork the GitHub repo into Gogs.

1. Switch to branch `dev-track-lab-02`

    ![mock-gogs-upload](images/mock-04.png "Upload File")

1. Open the `locations-api` folder and click on the filename link **Locations.json** to open and review the file.

    ![mock-gogs-file](images/mock-05.png "File Uploaded")

1. If everything is fine, click the RAW button to get the raw download version of the file.

    ![mock-gogs-file](images/mock-06.png "File Uploaded")

1. Copy the browser tab URL. Store that URL address as you will use it in the next steps of the lab. The URL should look like the following:

    ```bash
    http://gogs.apps.GUID.openshiftworkshop.com/user1/locations-api/raw/dev-track-lab-02/locations-api/Locations.json
    ```

    *If you feel more comfortable, you can also copy and paste the RAW button link from the previous step*.

### Step 1: Create a Microcks Job

1. Open a browser window and navigate to:

    ```bash
    http://microcks.apps.GUID.openshiftworkshop.com/
    ```

1. Log in into Microks using your designated [user and password](#environment).

    ![mock-openshift-login](images/mock-07.png "Openshift Login")

1. You are now in the main Microcks page. Click the **Jobs** button to access the Jobs page.

    ![mock-jobs](images/mock-08.png "Job")

1. Click the **ADD JOB...** button to create your first job.

    ![mock-add-job](images/mock-09.png "Add Job")

1. In the Add Job dialog, type in the following information replacing **X** with your user number and GUID with your working [environment](#environment):

    * Name: **Locations-UserX**
    * Repository URL: **http://gogs.apps.GUID.openshiftworkshop.com/user1/locations-api/raw/dev-track-lab-02/locations-api/Locations.json**

    *You can also copy and paste the raw url you saved from the Gogs repository (Step 0)*.

    ![mock-job-details](images/mock-10.png "Job Details")

1. After your job is created, click the **ACTION** menu and select the **Activate** option.

    ![mock-job-activate](images/mock-11.png "Activate Job")

1. Repet the last step, but now select the **Start** option. This will start the synchronization job.

    ![mock-job-start](images/mock-12.png "Start Job")

1. Refresh your window to get it to the latest state.

1. You will se 3 labels next to your Job. Click the **Services** label.

    ![mock-job-services](images/mock-13.png "Job Services")

1. In the dialog you will see your service listed. Click on the **Locations-1.0** link.

    ![mock-job-service](images/mock-14.png "Job Service")

1. Click **OK** to dismiss the dialog.

1. This is your new REST mock service based on the OpenAPI definition you just loaded to Microcks. Click the **Operation GET /locations** link to check the example under that operation.

    ![mock-mock-service](images/mock-15.png "Mock Service")

1. You can check that the example we added to the definition in [Lab 1](lab01.md) will be used to return the mock values. Copy and save the **Mocks URL**, we will use that endopoint to test the REST mock service.

    ![mock-mock-operation](images/mock-16.png "Mock Operation")

### Step 2: Test the REST Mock Service

We now have a working REST mock service listening for requests. We will use an online cURL tool to test it.

1. Open a browser window and navigate to:

    ```bash
    https://onlinecurl.com/
    ```

1. Enter the following URL: **http://microcks.apps.GUID.openshiftworkshop.com + {{your-user-api-mocks-url}}**. Remember to replace the GUID with your [environment](#environment) values and your user number. It should look like this:

    ```bash
    http://microcks.apps.dayinlife.openshiftworkshop.com/rest/Locations/1.0/locations
    ```

1. Click the **START YOUR CURL** button.

    ![mock-curl-service](images/mock-17.png "cURL Service")

1. The page will load the response information from the service. You will be able to see the *RESPONSE HEADERS* and the actual *RESPONSE_BODY*. This last part contains the examples we add during the design phase.

    ![mock-curl-response](images/mock-18.png "cURL Response")

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

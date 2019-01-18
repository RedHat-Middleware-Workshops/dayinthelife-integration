# Lab 1

## API Design

### Create an OpenAPI Specification using Apicurio Studio

* Duration: 10 mins
* Audience: API Owner, Product Manager, Developers, Architects

## Overview

As APIs become more widespread in the enterprise, consistent design and usage is critically important to improve reusability. The more reusable APIs are, the less friction there is for other internal or external teams to make progress. Having design standards and tools baked into the API development and maintenance process is a very powerful way to enable this consistency.

### Why Red Hat?

Red Hat is one of the founding members of the Linux Foundation OpenAPI Initiative (OAI) which produces the leading standard for REST API specifications. Red Hat consistently uses this standard throughout its tooling, starting with the Apicurio Studio API Designer editor.

### Skipping The Lab

If you are planning to skip this lab and follow the next one, here is a [link](https://github.com/RedHatWorkshops/dayinthelife-integration/tree/master/docs/labs/citizen-integrator-track/resources) to the specification generated in this lab.

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

### Step 1: Creating APIs with Apicurio Studio

1. Open a browser window and navigate to:

    ```bash
    http://apicurio-studio.dil.opentry.me
    ```

1. Log in using your designated [user and password](#environment).

    ![design-login](images/design-01.png "Login")

1. Click on **APIs** in the left side navigation menu from the Dashboard page.

    ![design-apis](images/design-02.png "APIs")

1. Click on **Create New API**.

    ![design-new-api](images/design-03.png "Create New API")

1. Create a brand new API by completing the following information:

    * Type: **Open API 3.0.2**
    * Name: **Locations-UserX** (Replace *X* with your user number)
    * Description: **Locations API**

    ![design-create-api](images/design-04.png "Create API")

1. Click on **Create API**.

1. Finally, click on **Edit API** to start editing your newly created API.

    ![design-edit-api](images/design-05.png "Edit API")

### Step 2: Editing APIs

You are now in the main screen to edit your APIs. Apicurio is a graphical, form-based API editor. With Apicurio you don't need master in and out all the details of the **OpenAPI Specification**. It allows you to design beautiful, functionals APIs with zero coding.

Let's start crafting your API.

1. Time to prepare our data definitions for the API. Click on the **Add a datatype** link under the *Data Types*.

    ![design-add-datatype](images/design-15.png "Add Datatype")

1. Fill in the *Name* field with the value **location**. Expand the *Enter the JSON Example (optional)* to paste the following example, then click **Save**:

    * Name: **location**
    * JSON Example:

        ```bash
        {
            "id": 1,
            "name": "International Inc Corporate Office",
            "location": {
                "lat": 51.5013673,
                "lng": -0.1440787
            },
            "type": "headquarter",
            "status": "1"
        }
        ```
    * Choose to create a REST Resource with the Data Type: **No Resource**

    ![design-JSON-Example](images/design-16.png "JSON Example")

1. Apicurio automatically tries to detect the data types from the provided example.

    ![design-definition-types](images/design-17.png "Definition Data Types")

    *Time to start creating some paths*.

### Step 3: Adding Paths

#### 3a: Add `/locations` path with GET method

The `/locations` path with an HTTP GET method will return a complete set of all location records in the database.

1. Click on the **Add a path** link under the *Paths* section. APIs need at least one path.

    ![design-add-path](images/design-06.png "Add Path")

1. Fill in the new resource path with the following information:

    * Path: **/locations**

    ![design-path](images/design-07.png "Path")

1. Click **Add**.

    *By default, Apicurio suggest a series of available operations for your new path*.

1. Click **Create Operation** under the *GET* operation.

    ![design-create-operation](images/design-08.png "Create Operation")

1. Click on the green **GET** operation button to edit the operation information.

    ![design-get-operation](images/design-09.png "Get Operation")

    *As you can notice, Apicurio Editor guides you with warning for the elements missing in your design*.

1. Click on the **Add a response** link under *Responses* to edit the response for this operation.

    ![design-add-response](images/design-58.png "Add Response")

1. Leave the **200** option selected in the  *Response Status Code* combo box and click on **Add**.

    ![design-add-response-code](images/design-11.png "Add Response Code")

1. Scroll down to the bottom of the page. Move your mouse over the **200 OK** response to enable the options. Click the *No response media types defined* drop-down. Now click on the **Add Media Type** button.

    ![design-edit-response](images/design-59.png "Add Media Type")


1. Click on the *Add* button to accept **application/json** as the Media Type.

    ![design-location-type](images/design-18.png "Location Type")

1. Click on the *Type* dropdown and select **Array** and **location**.

    ![design-location-type](images/design-18a.png "Location Type")

1. Click the **Add an example** link to add a Response Example. 

    *This will be useful to mock your API in the next lab*.

    ![design-add-example](images/design-19.png "Add Example")

1. Fill in the information for your response example:

    * Name: **all**
    * Example:

        ```bash
        [
            {
                "id": 1,
                "name": "International Inc Corporate Office",
                "location": {
                    "lat": 51.5013673,
                    "lng": -0.1440787
                },
                "type": "headquarter",
                "status": "1"
            },
            {
                "id": 2,
                "name": "International Inc North America",
                "location": {
                    "lat": 40.6976701,
                    "lng": -74.259876
                },
                "type": "office",
                "status": "1"
            },
            {
                "id": 3,
                "name": "International Inc France",
                "location": {
                    "lat": 48.859,
                    "lng": 2.2069746
                },
                "type": "office",
                "status": "1"
            }
        ]
        ```

    ![design-response-example](images/design-20.png "Response Example")

1. Click on the drop-down next to the `No description` message, and enter `Returns an array of location records` as the description.  Click the check-mark button to accept the description.

    ![design-response-example](images/design-54.png "Enter description")

1. Click on the green **GET** operation button to highlight the list of operations.

    ![design-get-operation](images/design-31.png "Get Operation")
    

#### 3b: Add `/locations/{id}` path with GET method

The `/locations/{id}` path will return a single location record based on a single `id` parameter, passed via the URL.

1. Now we need to create another path.  Click on the `+` symbol to add a new path, then enter `/locations/{id}` for the **Path** property.  Click **Add**.

    ![design-location-type](images/design-37.png "Location")

1. Scroll over the `id` *Path Parameter* value, then click the **Create** button.

    ![design-location-type](images/design-37a.png "Location")

1. Click the drop-down arrow, then update the `id` Path Parameter by selecting `Integer` as the **Type** and `32-Bit Integer` as the sub-type.

    ![design-location-type](images/design-38.png "Path Parameter")

1. Click on the `Create Operation` button underneath **GET**, then click the green **GET** button.

    ![design-location-type](images/design-39.png "Path Parameter")

1. Click on the **Add a response** link under *Responses* to edit the response for this operation.

    ![design-add-response](images/design-10.png "Add Response")

1. Leave the **200** option selected in the  *Response Status Code* combo box and click on **Add**.

    ![design-add-response-code](images/design-11.png "Add Response Code")

1. Scroll down to the bottom of the page. Move your mouse over the **200 OK** response to enable the options. Click the *No response media types defined* drop-down. Now click on the **Add Media Type** button.

    ![design-edit-response](images/design-12.png "Add Media Type")


1. Click on the *Add* button to accept **application/json** as the Media Type.

    ![design-location-type](images/design-18.png "Location Type")

1. Click on the *Type* dropdown and select **location**.

    ![design-location-type](images/design-40.png "Location Type")

1. Click on the drop-down next to the `No description` message, and enter `Returns a single location record` as the description.  Click the check-mark button to accept the description.

    ![design-response-example](images/design-56.png "Enter description")

#### 3c: Add `/locations/phone/{id}` path with GET method

The `/locations/phone/{id}` path will return a single location record based on a single phone number parameter, passed via the URL.

1. Now we need to create another path.  Click on the `+` symbol to add a new path, then enter `/locations/phone/{id}` for the **Path** property.  Click **Add**.

    ![design-location-type](images/design-41.png "Location")

1. Click on the `Create Operation` button underneath **Get**, then click the green **Get** button.

    ![design-location-type](images/design-42.png "Get operation")

1. Scroll down to the `id` path parameter to highlight the row, and click the `Create` button that appears.

    ![design-location-type](images/design-53.png "Get operation")

1. Click the drop-down arrow next to `No Type`, then update the `id` Path Parameter by selecting `Integer` as the **Type** and `32-Bit Integer` as the sub-type.

    ![design-location-type](images/design-64.png "Path Parameter")

1. Click on the **Add a response** link under *Responses* to edit the response for this operation.

    ![design-add-response](images/design-61.png "Add Response")

1. Leave the **200** option selected in the  *Response Status Code* combo box and click on **Add**.

    ![design-add-response-code](images/design-11.png "Add Response Code")

1. Scroll down to the bottom of the page. Move your mouse over the **200 OK** response to enable the options. Click the *No response media types defined* drop-down. Now click on the **Add Media Type** button.

    ![design-edit-response](images/design-62.png "Add Media Type")


1. Click on the *Add* button to accept **application/json** as the Media Type.

    ![design-location-type](images/design-18.png "Location Type")

1. Click on the *Type* dropdown and select **location**.

    ![design-location-type](images/design-63.png "Location Type")

1. Click on the drop-down next to the `No description` message, and enter `Returns a location record` as the description.  Click the check-mark button to accept the description.

    ![design-response-example](images/design-57.png "Enter description")

### Step 4: Download the API definition

1. Click the **Locations-UserX** link to return to the API admin page.

    ![design-locations-api](images/design-22.png "Locations API")

1. To start using your new API definition, display the API menu from the kebab link. Click the **Download (YAML)** option from the menu.

    ![design-download-yaml](images/design-23.png "Download API")

1. This will start the download of your API definition file. It could take a few seconds to start the download. **Save** it to your local disk drive. 

1. You can open the file with any text editor. Take a look at the source file. Everything is there.

    ![design-api-source](images/design-24.png "API Definition Source")

*Congratulations!* You have created your first API definition based on the OpenAPI Specification  using Red Hat's Apicurio. Don't lose track of the file, you will use this definition for your next lab.

## Steps Beyond

So, you want more? Did you notice the link **source** when editing the *Paths* or the *Definitions*? Get back to the API editor and follow the link. What do you see? Apicurio lets you follow the form-based editor or go one step beyond and also lets you direct edit the source of your API definition.

## Summary

In this lab you used Apicurio Studio to create a simple API definition using the OpenAPI Specification. You learned how to author and download a standards compliant API Specification using Red Hat's APICurio.

You can now proceed to [Lab 2](../lab02/#lab-2)

## Notes and Further Reading

* Apicurio
  * [Webpage](https://www.apicur.io)
  * [Roadmap](https://www.apicur.io/roadmap/)
* OpenAPI
  * [OpenAPI Initiative](https://www.openapis.org/)
  * [OpenAPI Specification 3.0.2](https://github.com/OAI/OpenAPI-Specification/blob/master/versions/3.0.2.md)

# Lab 5

## Fuse Online

* Duration: 20 mins
* Audience: Developers and Architects

## Overview

When it comes to quick API development, you need both the integration experts as well as application developers to easily develop, deploy the APIs. Here is how to create a simple API with Fuse online. 

### Why Red Hat?

Red Hat Fuse integration solution empowers integration experts, application developers, and business users to engage in enterprise-wide collaboration and high-productivity self-service. 


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

### Step 1: Create database connection

1. Via the **Red Hat Solution Explorer** webpage, click the **Red Hat Fuse** link:

   ![00-design-53.png](images/design-53.png "Login to Fuse Online")

1. The first time that you hit the Fuse Online URL, you will be presented with an *Authorize Access* page.  Click the **Allow selected permissions** button to accept the defaults.

   ![00-design-54.png](images/design-54.png "Accept permissions")

1. Click on **Connections > Create Connection**

   ![00-create-connection.png](images/00-create-connection.png "Create Connection")

1. Select **Database**

   ![01-select-database.png](images/01-select-database.png "Select Database")

1. Enter below values for Database Configuration

    ```
    Connection URL: jdbc:postgresql://postgresql.OCPPROJECT.svc:5432/sampledb
    Username      : dbuser
    Password      : password
    Schema        : <blank>
    ```
*Remember to replace the OCPPROJECT with the OpenShift project(NameSpace) you created in last lab.*

1. Click **Validate** and verify if the connection is successful. Click **Next** to proceed.

  ![02-click-validate.png](images/02-click-validate.png "Validate")

6. Add `Connection details`. `Connection Name: LocationDB` and `Description: Location Database`. Click **Create**.
   
   ![03-connection-details.png](images/03-connection-details.png "Add Connection Details")

7. Verify that the `Location Database` is successfully created.

### Step 2: No CODE API developement 

Description goes here

1. Click on **Integrations > Create Integration** 

  ![04-create-integration.png](images/04-create-integration.png "Create Integration")

2. Choose **API Provider**

   ![02-api-provider.png](images/n02-api-provider.png "Add Choose API Provider")

3. Select **Create from scratch**

  ![n03-api-from-scratch](images/n03-api-from-scratch.png "Add Choose API Provider")
  
4. Change the name of the API to `Location` and click on the Add a path link under the Paths section.
![n04-api-name](images/n04-api-name.png "Set the name of the API")

5. Fill in the new resource path with the following information:
 - Path: /locations 	
![n05-api-path](images/n05-api-path.png "Set the path of the API")

6. Click on the Add a datatype link under the Data Types.
![n06-data-type](images/n06-data-type.png "Select Data Type")

7. Fill in the Name field with the value location. Expand the Enter the JSON Example to paste the following example, then click Save:
 - Name: locationinput
 - JSON Example:
 
 ``
 {
	  "id": 1,
	  "name": "Kamarhati",
	  "type": "Regional Branch",
	  "status": "1",
	  "location": {
	    "lat": "-28.32555",
	    "lng": "-5.91531"
	  }
	}
 ``
 
 ![n07-location-input-datatype](images/n07-location-input-datatype.png "Select Data Type")
 ![n08-location-input-datatype-save](images/n08-location-input-datatype-save.png "Select Data Type")


8. Create another datatype, this time with the following config and click save.
 - Name: location
 - JSON Example:
 
 ``
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
 ``
 ![n09-location-datatype](images/n09-location-datatype.png "Select Data Type")

9. You will be able to see the two datatypes created. 
![n10-datatype-all](images/n10-datatype-all.png "Data Type All")
 
10. Click on the Create Operation link under POST to create a new POST operation.
![n11-post-method](images/n11-post-method.png "POST Method")

11. Edit the description of the post method to *Add Location* and click the orange POST button to edit the operation
![n12-post-description](images/n12-post-description.png "POST Method")

12. Click on **Add a request Body**
![n13-request](images/n13-request.png "Request Body")

13. Choose **locationinput** as the *Request Body Type*
![n14-post-requst-location-input](images/n14-post-requst-location-input.png "Request Body as locationinput")

14. Click the Add a response link.
![n13-response](images/n13-response.png "Response Body")

15. Set the Response Status Code value to 201. Click Add.
![n15-post-response](images/n15-post-response.png "Response Body")

16. Click on **No Description* and place *Location added* in Description box. Click on the tick to save the changes
![n16-post-description](images/n16-post-description.png "Post Description")

17. Click on the Type dropdown and select location.
![n17-post-response-type](images/n17-post-response-type.png "Post Response Type")!

18. On the top section, under operation id, name it **addLocation** and click on tick to save the changes. On the very top of the page, click on Save button to return to Fuse Online in order for us to start the API implementation.
![n18-post-operation-id](images/n18-post-operation-id.png "Response Body")

19. Click Next.
![n19-start-of-integration](images/n19-start-of-integration.png "Response Body")

20. Set `Integration Name: addLocation` and `Description: add Location`

  ![n20-integration-name](images/n20-integration-name.png "Webhook Configuration")

21. Click on Add Location operation. 

  ![n21-choose-operation](images/n21-choose-operation.png "Webhook Configuration")


22. Since we are adding incoming data into the database, click on the plus sign in between API entry point and return endpoint, select `Add connection` 

 ![n22-add-db-connection](images/n22-add-db-connection.png "Add DB Connection")

23. Click on `LocationDB` from the catalog and then select `Invoke SQL`

 ![n24-invoke-sql](images/n24-invoke-sql.png "Invoke SQL")

24. Enter the SQL statement and click **Done**.

 ```
   INSERT INTO locations (id,name,lat,lng,location_type,status) VALUES (:#id,:#name,:#lat,:#lng,:#location_type,:#status )
 ```

 ![n25-sql-statement.png](images/n25-sql-statement.png "Invoke SQL 2")

25. In between top API endpoint and the Database connection, click on the plus sign and select `Add step` and select `Data mapper`

 ![n26-input-data-mapping](images/n26-input-data-mapping.png "Data Mapper")
 ![n27-choose-data-mapping](images/n27-choose-data-mapping.png "Data Mapper")


26. Drag and drop the matching **Source** Data types to all their corresponding **Targets** as per the following screenshot. When finished, click **Done**.

 ![n28-data-map-db.png](images/n28-data-map-db.png "Configure Mapper")

27. In between the Database connection and the endpoint, click on the plus sign and select `Add step` and select `Data mapper`

 ![n29-output-data-mapping](images/n29-output-data-mapping.png "Data Mapper")
 ![n30-choose-data-mapping](images/n30-choose-data-mapping.png "Data Mapper")


26. Drag and drop the matching **Source** Data types to all their corresponding **Targets** as per the following screenshot. When finished, click **Done**.

 ![n31-data-map-response](images/n31-data-map-response.png "Configure Mapper")

27. Click **Publish** on the next screen.

 ![n32-publish](images/n32-publish.png "Publish Integration")

*Congratulations*. You successfully published the integration. (Wait for few minutes to build and publish the integration)

### Step 3: Create a POST request

We will use an online cURL tool to create the `101th` record field in database.

1. Copy the `External URL` per the below screenshot

   ![14-copy-URL.png](images/14-copy-URL.png "Copy URL")

1. Open a browser window and navigate to:

   ```
     https://onlinecurl.com/
   ```

1. Below are the values for the request. Note: `id:101` in the payload as we are creating `101th` record in the database.

   ```
     URL: http://i-addlocation-demo.apps.55b9.openshift.opentlc.com/locations

     --header (-H):  Content-Type: application/json

     --data (-d): {"id": 101, "name": "Kamarhati", "type": "Regional Branch", "status": "1", "location": { "lat": "-28.32555", "lng": "-5.91531" }}

     --request (-X): POST
   ```

   ![15-online-curl.png](images/15-online-curl.png "Online URL")

1. The page will load the `204` response information from the service which means the request was successfully fulfilled.

   ![16-response-header.png](images/16-response-header.png "Response Header")


1. Click on **Activity > Refresh** and verify if the newly record is created.

   ![17-activity-refresh.png](images/17-activity-refresh.png "Activity Refresh")

1. _(Optional)_ Visit the application URL in browser and verify if the record can be fetched.

  **REQUEST**
  ```
   http://location-service-international.dil.opentry.me/locations/101
  
  ```

  **RESPONSE**
  ```
    {
      "id" : 101,
      "name" : "Kamarhati",
      "type" : "Regional Branch",
      "status" : "1",
      "location" : {
        "lat" : "-28.32555",
        "lng" : "-5.91531"
      }
    }
  ```

## Summary

In this lab you discovered how to create an adhoc API service using Fuse Online. 

You can now proceed to [Lab 6](../lab06/#lab-6)


## Notes and Further Reading

* Fuse Online
  * [Webpage](https://www.redhat.com/en/technologies/jboss-middleware/fuse-online)
  * [Sample tutorials](https://access.redhat.com/documentation/en-us/red_hat_fuse/7.1/html-single/fuse_online_sample_integration_tutorials/index)
  * [Blog](https://developers.redhat.com/blog/2017/11/02/work-done-less-code-fuse-online-tech-preview-today/)



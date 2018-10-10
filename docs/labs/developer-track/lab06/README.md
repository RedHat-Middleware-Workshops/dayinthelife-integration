# Lab 6

## Fuse Online

## todo

* Duration: 20 mins
* Audience: Developers and Architects

## Overview

TBD

### Why Red Hat?

TBD

### Skipping the lab

TBD

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

### Step 1: Create database connection

1. Open a browser window and navigate to:

    ```bash
    http://https://syndesis-user1.apps.GUID.openshift.opentlc.com/
    ```

    *Remember to replace the GUID with your [environment](#environment) value and your user number.*

1. Click on **Connection --> Create Connection**

   ![00-create-connection.png](images/00-create-connection.png "Create Connection")

1. Select **Database**

   ![01-select-database.png](images/01-select-database.png "Select Database")

1. Enter below values for Database Configuration

    ```
    Connection URL: jdbc:postgresql://postgresql.user1.svc:5432/sampledb
    Username      : dbuser
    Password      : password
    Schema        : keep it empty
    ```

1. Click **Validate** and verify if the connection is successful. Click **Next** to proceed.

  ![02-click-validate.png](images/02-click-validate.png "Validate")

6. Add below `Connection details` and click **Create**

   ```
   Connection Name: LocationDB
   Description    : Location Database
   ```

   
   ![03-connection-details.png](images/03-connection-details.png "Add Connection Details")

7. Verify that the `Location Database` is successfully created.



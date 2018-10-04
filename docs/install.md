# Installation

## Installing Day In Life Workshop on OpenShift

This page describes the installation of the Day In Life Workshop from the latest sources from GitHub.

### Pre-requisites

You will need an OpenShift Container Platform to install this workshop on. You can order a vanilla provisioning from the Red Hat Product Demo System (RHPDS) following this [instructions](https://mojo.redhat.com/docs/DOC-1175640).

To install the Day In Life Workshop, you need to have a host machine with the latest stable release version of the OpenShift client tools.

You can download the OpenShift Client Tools from [Red Hat Developers Portal Site](https://developers.redhat.com/products/openshift/download/) or follow the instructions on how to [Install the CLI](https://docs.openshift.com/container-platform/3.9/cli_reference/get_started_cli.html#installing-the-cli) from the openshift.com webpage.

You'll want to know how to [fork](https://help.github.com/articles/fork-a-repo/) and [clone](https://help.github.com/articles/cloning-a-repository/) a Git repository, and how to [check out a branch](https://git-scm.com/docs/git-checkout#git-checkout-emgitcheckoutemltbranchgt).

Day In Life Workshop can be installed using automated Ansible playbooks or following the manual steps.

### Installing using Ansible

We provide an Ansible playbook to install all the required components and software for this workshop.

Installing with Ansible requires creating an inventory file with the variables for configuring the system. Example inventory files can be found in the ansible/inventory folder. The following options are supported:

Name | Description | Default | Required
--- | --- | --- | ---
namespace | Namespace where Day In Life Management will be installed. | threescale | Yes, if *threescale* is enabled
sso_project | Namespace where Red Hat Single Sign On will be installed. | rh-sso | Yes, if *sso* is enabled
apicurio_project | Namespace where Apicurio will be installed. | apicurio | Yes, if *apicurio* is enabled
gogs_project | Namespace where Gogs will be installed. | gogs | Yes, if *gogs* is enabled
microcks_project | Namespace where Microcks will be installed | microcks | Yes, if *microcks* is enabled
backend_project | Namespace where Backend will be installed | international | Yes, if *backend* is enabled
sso_version | The version tag used for getting the RH SSO templates. | ose-v1.4.9 | No
ocp_domain | Root domain of the OpenShift cluster. For example: `GUID.openshiftworkshop.com` | | Yes
ocp\_apps\_domain | Root domain fpr the applications. For example: `apps.GUID.openshiftworkshop.com`  | | Yes
usersno | Number of user tenants that will be created. | | Yes
threescale | Enable Red Hat Day In Life Management. | true | No
apicurio | Enable Apicurio Studio. | true | No
gogs | Enable Gogs Git Service. |  true | No
microcks | Enable Microcks. | true | No
sso | Enable Red Hat Single Sign On. | true | No
che | Eclipse Che IDE. | true | No
backend | Enable App Backend Service | true | No
user_projects | | true | No
configure_only | Do not install the software, just configure existing installations. The software should be running in the same namespaces defined in the above vars. | false | No
create_tenants | Whether the workshop  should be installed in a multitenant mode or not. 3scale admin domains and developer portals for each of the users created during installation will be created. | true | Yes, if *threescale* is enabled
create_realms | If installing in multitenant mode, this will enable the creation of a RH SSO security realm for each one of the users created during installation. | true | No
create_ides | If installing in multitenant mode, this will enable the creation of a Eclipse Che IDE in each of the user projects. | true | No

An [example inventory](../support/ansible/inventory/workshop.inventory.example) that installs all the components in multitenant mode from the *bastion* machine looks like this:

```bash
[workshop]
localhost ansible_connection=local

[workshop:vars]
ocp_domain=GUID.openshiftworkshop.com
ocp_apps_domain=apps.GUID.openshiftworkshop.com
usersno=20
threescale=true
apicurio=true
gogs=true
microcks=true
sso=true
backend=true
user_projects=true
configure_only=false
create_tenants=true
create_realms=true
```

### Procedure from Bastion

The recommended way to install the workshop is running the ansible playbook from the OpenShift cluster bastion machine. This is the fastest way to run the installer as it's already running in the cluster closest to the master node.

1. Login to the bastion machine following the email instructions.

    ```bash
    ssh -i /path/to/ocp_workshop.pem ec2-user@bastion.GUID.openshiftworkshop.com
    ```

    *Remember to update the GUID with your cluster environment variable and the path to the downloaded PEM file.*

1. Git Clone the Day In Life Workshop repository:

    ```bash
    git clone https://github.com/jbossdemocentral/3scale-api-workshop.git
    ```

1. Become super user running the following command:

    ```bash
    sudo su
    ```

1. Change to the project *install* folder:

    ```bash
    cd 3scale-api-workshop/support/install
    ```

1. Run the Ansible playbook.

    ```bash
    ansible-playbook -i ansible/inventory/workshop.inventory ansible/playbooks/openshift/install.yml 
    ```

### Procedure from Laptop

1. Login to the OpenShift cluster.

    ```bash
    oc login -u opentlc-mgr https://master.GUID.openshiftworkshop.com --insecure-skip-tls-verify
    ```

    *If you install on OpenShift, it is required that you have cluster-admin access in order to set up the required roles for creating namespaces and managing resources in those namespaces*.

1. Run the Ansible playbook.

    ```bash
    ansible-playbook -i ansible/inventory/workshop.inventory ansible/playbooks/openshift/install.yml 
    ```

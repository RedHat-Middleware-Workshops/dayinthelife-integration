# Day In The Life Workshop Installation Guide

## How to Install the Workshop Content


### Pre-requisites

* You will need an OpenShift Container Platform environment, that will host the Integreatly (RHMI) system, to install this workshop on. You can order a vanilla provisioning from the Red Hat Product Demo System (RHPDS) following this [instructions](https://mojo.redhat.com/docs/DOC-1175640).

* To install the Day In Life Workshop, you need to a personal workstation (PC) with the latest stable release version of the OpenShift client tools.

* You can download the OpenShift Client Tools from [Red Hat Developers Portal Site](https://developers.redhat.com/products/openshift/download/) or follow the instructions on how to [Install the CLI](hhttps://docs.openshift.com/container-platform/4.6/cli_reference/openshift_cli/getting-started-cli.html) from the openshift.com webpage.

* You'll want to know how to [fork](https://help.github.com/articles/fork-a-repo/) and [clone](https://help.github.com/articles/cloning-a-repository/) a Git repository, and how to [check out a branch](https://git-scm.com/docs/git-checkout#git-checkout-emgitcheckoutemltbranchgt).

* Day In Life Workshop has to be installed using Ansible playbooks.

### Installing DIL Workshop

There are two options to installing, either use the pre-provisioned environemnt provided by RHPDS, or you may choose to install on your own OpenShift Container Platform.

#### OPTION 1 (FOR RED HAT STAFF, PARTNERS AND IBM STAFF)

1. Login to [RHPDS ](https://rhpds.opentlc.com) and provision the *Day In the Life - Agile Integration* catalog item.
   
2. Populate the required fields on the order form, particularly the *Number of users* field with the required no of attendees for the workshop.

3. Login and validate that the environment is functional, using user login information sent to you in an email, upon completion of the provisioning.

Watch this video to understand the complete process: https://youtu.be/PJ8RWVhpfcw

#### OPTION 2 (TO INSTALL ON YOUR OWN OPENSHIFT CONTAINER PLATFORM)

### Preparing for an Ansible playbook execution

Provision the OpenShift Container Platform version 4.6 on the cloud provider of your choice and ensure you have admin credentials to the cluster. A rule of thumb is to provision 1 worker node for 3 users, so ensure you have sufficient number of worker nodes for the required users.

### Installation Instructions

This section describes how to install this workshop on the OpenShift Container Platform cluster which you have setup previously.

The installation is performed using an Ansible playbook, executed from within the Bastion node of the OCP cluster.

This is the fastest way to install, as the playbook runs in the cluster closest to the Master node.


1. SSH into the Bastion server:
```
bash
ssh <your_user_id>@bastion.<your_domain>
```
2. Ensure the *Ansible* CLI is installed. You can install it using the following command:

```
sudo dnf install ansible
```

3. Clone the installer repo:
```
git clone https://github.com/redhat-cop/agnosticd.git
```

4. Login to OCP cluster as admin.

5. Set the following environment.  Be sure to replace *XX* with the number of users provisioned for your cluster:
```
echo "export WORKLOAD=ocp4-workload-dil-agile-integration" >> ~/.bashrc
echo "export NUM_USERS=xx" >> ~/.bashrc
echo "export OCP_USERNAME=<your_ocp_admin>" >> ~/.bashrc
source ~/.bashrc
```
*Assign the number of attendees in the workshop - as the value for NUM_USERS*
*Assign your OpenShift admin userid to OCP_USERNAME*

6. Change to the local git directory: `cd agnosticD/ansible`


7. Run the Ansible playbook script:
```
ansible-playbook -i localhost, -c local  ./configs/ocp-workloads/ocp-workload.yml \
                    -e"ocp_username=${OCP_USERNAME}" \
                    -e"ocp_workload=${WORKLOAD}" \
                    -e"num_users=${USER_COUNT}" \
                    -e"ACTION=create"
```
*NOTE*: This Ansible playbook is idempotent, meaning you can run it as many times as you like.  If there are any failures the first time around, just run the script again and it should resolve the issue. 

8. Login to OpenShift console after successful installation and ensure all pods are running, and projects created.


### INSTALLATION IS COMPLETE!






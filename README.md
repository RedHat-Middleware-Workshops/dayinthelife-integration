# Agile integration with APIs & Containers: A hands-on workshop

The agile integration with APIs and containers event is a hands-on workshop for developing, testing, and deploying cloud-native, integrated solutions. This full-day workshop will start with an overview of agile integration and a discussion of the necessary architecture. We’ll also present examples of how customers are using Red Hat’s agile integration methodology to stay competitive.

The second half of the day breaks into 2 tracks for different audiences:  
- **API design and management** — For individuals leading API development and integration efforts that require some coding or development work. 
- **API development and security** — For individuals leading the API integration effort of existing services. This activity is user interface (UI) driven and will allow the integrator to successfully deploy, integrate (Red Hat Fuse), secure, and manage API services.
Each track will offer hands-on lab experience relevant to the audience.  Labs and sessions will cover accelerating the development of cloud-native applications, developing API-centric services, providing API security, and establishing operational management.

## Agenda

| Time        | Activity           |
| ------------- | ------------- |
| 8:00-9:00 a.m.      | Registration and breakfast |
| 9:00-10:00 a.m.      | An introduction to agile integration concepts, use cases, and roadmap |
| 10:00-11:00 a.m.      | *A day in the life* hands-on developer demo  |
| 11:00-11:15 a.m.      | Break |
| 11:15 a.m.-12:00 p.m.      | **Lab:** Contract-first API development<br>*Choose your own adventure:*<br>__Track one:__ API design and management<br>__Track two__: API development and security |
| 12:00-1:00 p.m.      | Lunch |
| 1:00-3:30 p.m.      | **Lab cont.:** Contract-first API development<br>*Choose your own adventure:*<br>__Track one:__ API design and management<br>__Track two:__ API development and security |
| 3:30-4:00 p.m.      | Recap and summary |


## Lab Instructions


[Citizen integrator track](docs/labs/citizen-integrator-track)

[Developer track](docs/labs/developer-track)

## RHPDS Instructions

This section describes how to install his workshop in a RHDPS cluster that is running the integratly workshop.

RHPDS requires the installation playbook to be run within the bastion server:

1. SSH into the bastion server;
2. Switch to root user: `sudo -i`;
3. Clone the installer repo `git clone https://github.com/RedHatWorkshops/dayinthelife-integration.git`;
4. Get the master node url: `oc get nodes -o jsonpath='{.items[?(@.metadata.labels.node-role\.kubernetes\.io/master == "true")].metadata.name}'`;
5. Change the master host in the inventory file (`support/install/ansible/inventory/integreatly.inventory`) to use the internal master node hostname retrieved in the previous step;
6. Replace `dayinlife` substring by your cluster guid for both `ocp_domain` and `ocp_apps_domain` vars;
7. Cd into `support/install/ansible` and run the integreatly playbook: `ansible-playbook -i inventory/inventory/9. integreatly.inventory playbooks/openshift/integreatly.yml`.

You can run the installation playbook from any machine as long as you are able to ssh into the master node.

NOTE: Skip steps `4` and `5` if running the playbook from the master node.

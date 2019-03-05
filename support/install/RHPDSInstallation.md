## RHPDS Instructions

This section describes how to install this workshop in a RHDPS cluster that is running the integreatly workshop.

RHPDS requires the installation playbook to be run within the bastion server:

1. SSH into the bastion server;
2. Switch to root user: `sudo -i`;
3. Clone the `V2` branch of the installer repo `git clone https://github.com/RedHatWorkshops/dayinthelife-integration.git -b V2`;
4. Get the master node url: `oc get nodes -o jsonpath='{.items[?(@.metadata.labels.node-role\.kubernetes\.io/master == "true")].metadata.name}'`;
5. Change the master host in the inventory file (`support/install/ansible/inventory/integreatly.inventory`) to use the internal master node hostname retrieved in the previous step;
6. Replace `dayinlife` substring by your cluster guid for both `ocp_domain` and `ocp_apps_domain` vars;
7. Update `usersno` the number of users you prefer to setup in you cluster
8. Update `is_on_RHPDS` to true
9. Update the `ocp_domain`, `ocp_apps_domain`, and `usersno` parameters in the `backend.inventory`, `gogs.inventory`, `userproject.inventory` and `workshop.inventory` files as well.
10. cd into `support/install/ansible` and run the integreatly playbook: `ansible-playbook -i inventory/integreatly.inventory playbooks/openshift/integreatly.yml`.

You can run the installation playbook from any machine as long as you are able to ssh into the master node.

NOTE: Skip steps `4` and `5` if running the playbook from the master node.

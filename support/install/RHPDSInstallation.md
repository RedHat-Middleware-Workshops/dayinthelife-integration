## RHPDS Instructions

This section describes how to install this workshop in a RHDPS cluster that is running the integreatly workshop.

RHPDS requires the installation playbook to be run within the bastion server:

1. SSH into the bastion server;
2. Switch to root user: `sudo -i`;
3. Clone the installer repo `git clone https://github.com/RedHatWorkshops/dayinthelife-integration.git -b V2`;
4. Get the master node URL: 
```
export MASTER_INTERNAL=`oc get nodes -o jsonpath='{.items[?(@.metadata.labels.node-role\.kubernetes\.io/master == "true")].metadata.name}'`
```
5. Update inventory files with URLs from previous step.  Change to local git directory: `cd dayinthelife-integration/support/install/ansible/inventory/`
6. Replace the internal master node hostname retrieved in the previous step in `integreatly.inventory`;
7. Replace `dayinlife` substring by your cluster guid for both `ocp_domain` and `ocp_apps_domain` vars;
8. Update `usersno` the number of users you prefer to setup in you cluster
9. Update `is_on_RHPDS` to true
10. Update the `ocp_domain`, `ocp_apps_domain`, and `usersno` parameters in the `backend.inventory`, `gogs.inventory`, `userproject.inventory` and `workshop.inventory` files as well. Ex: 
```
export INTERNAL_DOMAIN=`echo $MASTER_INTERNAL | sed -r 's/master1\.|\.internal//g'`
sed -i -e "s/ocp_domain=.*$/ocp_domain=${INTERNAL_DOMAIN}.openshiftworkshop.com/g" *.inventory
sed -i -e "s/ocp_apps_domain=.*$/ocp_apps_domain=apps.${INTERNAL_DOMAIN}.openshiftworkshop.com/g" *.inventory
sed -i -e "s/usersno=.*/usersno=5/g" *.inventory
```
11. Run Ansible playbooks: `cd ~/dayinthelife-integration/support/install/ansible`, and run the integreatly playbook: `ansible-playbook -i inventory/integreatly.inventory playbooks/openshift/integreatly.yml`.

You can run the installation playbook from any machine as long as you are able to ssh into the master node.

NOTE: Skip steps `4` and `5` if running the playbook from the master node.

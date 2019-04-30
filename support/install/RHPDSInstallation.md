## RHPDS Instructions

This section describes how to install this workshop in a RHDPS cluster that is running the Integreatly workshop.

RHPDS requires the installation playbook to be run within the Bastion server:

1. SSH into the Bastion server;
2. Switch to root user: `sudo -i`;
3. Clone the installer repo `git clone https://github.com/RedHatWorkshops/dayinthelife-integration.git`;
4. Set the master node URL and number of users.  Be sure to replace *XX* with the number of users provisioned for your cluster: 
```
export MASTER_INTERNAL=`oc get nodes -o jsonpath='{.items[?(@.metadata.labels.node-role\.kubernetes\.io/master == "true")].metadata.name}'`
export NUM_USERS=XX
```
5. Change to the local git directory: `cd dayinthelife-integration/support/install/ansible/inventory/`
6. Run the following command to update the `master`, `ocp_domain`, `ocp_apps_domain`, and `usersno` parameters in the `backend.inventory`, `gogs.inventory`, `userproject.inventory`, `workshop.inventory` and `integreatly.inventory` files: 
```
export INTERNAL_DOMAIN=`echo $MASTER_INTERNAL | sed -r 's/master1\.|\.internal//g'`
sed -i -e "s/master1.CITY-GUID.internal.*$/${MASTER_INTERNAL}/g" integreatly.inventory
sed -i -e "s/ocp_domain=.*$/ocp_domain=${INTERNAL_DOMAIN}.openshiftworkshop.com/g" *.inventory
sed -i -e "s/ocp_apps_domain=.*$/ocp_apps_domain=apps.${INTERNAL_DOMAIN}.openshiftworkshop.com/g" *.inventory
sed -i -e "s/usersno=.*/usersno=${NUM_USERS}/g" *.inventory
```
7. Run the Ansible playbook script: `ansible-playbook -i /root/dayinthelife-integration/support/install/ansible/inventory/integreatly.inventory /root/dayinthelife-integration/support/install/ansible/playbooks/openshift/integreatly.yml`.

*NOTE*: This Ansible playbook is idempotent, meaning you can run it as many times as you like.  If there are any failures the first time around, just run the script again and it should resolve the issue.

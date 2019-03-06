## RHPDS Instructions

The section describes how to uninstall this workshop in a RHDPS cluster that is running the integratly workshop.


1. SSH into the bastion server;

2. Switch to root user: 
```
sudo -i
```

3. Clone the integration repo 
```
git clone https://github.com/RedHatWorkshops/dayinthelife-integration.git
```

4. Get the master node url
```
oc get nodes -o jsonpath='{.items[?(@.metadata.labels.node-role\.kubernetes\.io/master == "true")].metadata.name}'
```

5. Change the master host in the inventory file (`support/install/ansible/inventories/uninstall.workshop.inventory`) to use the internal master node hostname retrieved in the previous step;

6. cd ```support/uninstall/ansible``` 

7. Run the integreatly playbook. Remember to set correct value for `usersno` that was passed during installation.
```
ansible-playbook -i inventories/uninstall.workshop.inventory playbooks/openshift/uninstall.yml -e usersno=<value>
```


#!/bin/bash


TARGET_HOST="bastion.phoenix.openshiftworkshop.com"
OCP_USERNAME="opentlc-mgr"
WORKLOAD="ocp-workload-dayinlife"
GUID=phoenix
OCP_DOMAIN="phoenix.openshiftworkshop.com"
TOKEN=$(oc whoami -t)



# a TARGET_HOST is specified in the command line, without using an inventory file
ansible-playbook --become --become-user=${OCP_USERNAME} -i ${TARGET_HOST}, ./configs/ocp-workloads/ocp-workload.yml \
                 -e"ansible_ssh_private_key_file=~/.ssh/ocp-workshop.pem" \
                 -e"ansible_ssh_user=ec2-user" \
                 -e"ANSIBLE_REPO_PATH=`pwd`" \
                 -e"ocp_username=${OCP_USERNAME}" \
                 -e"ocp_workload=${WORKLOAD}" \
                 -e"guid=${GUID}" \
                 -e"ocp_user_needs_quota=true" \
                 -e"ocp_domain=${OCP_DOMAIN}" \
                 -e"ocp_apps_domain=apps.${OCP_DOMAIN}" \
                 -e"openshift_oauth_token=${TOKEN}" \
                 -e"ACTION=create"

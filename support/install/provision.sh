#!/bin/bash
# author: Rodrigo Ramalho rramalho@redhat.com
# usage: ./provision.sh 10
#         where 10 is the number of users
die () {
    echo >&2 "$@"
    exit 1
}

[ "$#" -eq 1 ] || die "Number of users must be informed: e.g: ./provision.sh 10"

if [ -z "$GUID" ]; then
    echo "GUID variable is not set" 
    die
fi

if [[ $EUID -ne 0 ]]; then
    echo "This script must be run as root" 
    die
fi

h=$(hostname)
hostname="${h%%.*}"

if [ "$hostname" != "bastion" ]; then
    echo "This script must be run in bastion server"
    die
fi

export NUM_USERS=$1
cd ~/
git clone https://github.com/hodrigohamalho/dayinthelife-integration.git
cd dayinthelife-integration/support/install/ansible/inventory
export MASTER_INTERNAL=`oc get nodes -o jsonpath='{.items[?(@.metadata.labels.node-role\.kubernetes\.io/master == "true")].metadata.name}'`
export INTERNAL_DOMAIN=`echo $MASTER_INTERNAL | sed -r 's/master1\.|\.internal//g'`
sed -i -e "s/master1.CITY-GUID.internal.*$/${MASTER_INTERNAL}/g" integreatly.inventory
sed -i -e "s/ocp_domain=.*$/ocp_domain=${INTERNAL_DOMAIN}.open.redhat.com/g" *.inventory
sed -i -e "s/ocp_apps_domain=.*$/ocp_apps_domain=apps.${INTERNAL_DOMAIN}.open.redhat.com/g" *.inventory
sed -i -e "s/usersno=.*/usersno=${NUM_USERS}/g" *.inventory
cd ~/dayinthelife-integration/support/install/ansible
ansible-playbook -i /root/dayinthelife-integration/support/install/ansible/inventory/integreatly.inventory /root/dayinthelife-integration/support/install/ansible/playbooks/openshift/integreatly.yml
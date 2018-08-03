# Installing 3scale AMP 2.x On Premise on OpenShift

The following lab exercise walks through the procedure to install and setup a single tenant 3scale AMP instance on OpenShift.

### Prerequisites

1. You have access to a running OpenShift instance
2. You have `oc` tools installed locally and can connect to your OpenShift instance.  If not, follow the documentation [here](https://docs.openshift.com/container-platform/3.10/cli_reference/get_started_cli.html#installing-the-cli)


### Login as developer and start AMP with template

1. Obtain the public IP for your OpenShift environment, as you'll need it to run the below commands
2. Run the below commands, and replace the associated <PUBLIC_IP> with the public IP for your OpenShift lab environment

```
oc login https://<PUBLIC_IP>:8443 --insecure-skip-tls-verify

oc new-project 3scale-amp

oc new-app --file https://raw.githubusercontent.com/RedHatWorkshops/dayinthelife-integration/master/labs/operations-track/artifacts/amp.yml --param TENANT_NAME=3scale --param WILDCARD_DOMAIN=<PUBLIC_IP>.xip.io >> /tmp/3scale_amp_provision_details.txt
```
3. Verify the script ran correctly by verifying the output log.  Notice the admin login credentials underneath the `system` section.  You'll need these credentials later.

```

cat /tmp/3scale_amp_provision_details.txt


--> Deploying template "3scale-amp/system" for "amp.yml" to project 3scale-amp

     system
     ---------
     Login on Login on https://3scale-admin.54.86.18.216.xip.io as admin/gu8edykg    <===== LOGIN with these credentials

     * With parameters:
        * AMP_RELEASE=er3
        * ADMIN_PASSWORD=gu8edykg # generated
        * ADMIN_USERNAME=admin
        * APICAST_ACCESS_TOKEN=rthdeuql # generated
        * ADMIN_ACCESS_TOKEN=4o2txf0v4e3wgvtw # generated
        * WILDCARD_DOMAIN=<PUBLIC_IP>.xip.io
        * SUBDOMAIN=3scale
        * MySQL User=mysql
        * MySQL Password=qfnt75jf # generated
        * MySQL Database Name=system
        * MySQL Root password.=7dhquse7 # generated
        * SYSTEM_BACKEND_USERNAME=3scale_api_user
        * SYSTEM_BACKEND_PASSWORD=a3i3n7by # generated
        * REDIS_IMAGE=rhscl/redis-32-rhel7:3.2-5.3
        * SYSTEM_BACKEND_SHARED_SECRET=s4wpndxj # generated
```

4. Via the CLI, resume the database tier pods by running the following commands:

```
for x in backend-redis system-memcache system-mysql system-redis zync-database; do echo Resuming dc:  $x; sleep 2; oc rollout resume dc/$x; done
```

5. Via the OpenShift Web Console or CLI, verify the Database pods are running:

```
oc get pods
NAME                      READY     STATUS    RESTARTS   AGE
backend-redis-1-q2hnc     1/1       Running   0          53s
system-memcache-1-ggjgm   1/1       Running   0          49s
system-mysql-1-pg7rm      1/1       Running   0          1m
system-redis-1-klthg      1/1       Running   0          44s
zync-database-1-w66qf     1/1       Running   0          52s
```

```
2. Resume backend listener and worker deployments:

for x in backend-listener backend-worker; do echo Resuming dc:  $x; sleep 2; oc rollout resume dc/$x; done
```

```
3. Resume the system-app and its two containers:

oc rollout resume dc/system-app
```

```
4. Resume additional system and backend application utilities:

for x in system-resque system-sidekiq backend-cron system-sphinx; do echo Resuming dc:  $x; sleep 2; oc rollout resume dc/$x; done
```

```
5. Resume apicast gateway deployments:

for x in apicast-staging apicast-production; do echo Resuming dc:  $x; sleep 2; oc rollout resume dc/$x; done
```

```
6. Resume remaining deployments:

for x in apicast-wildcard-router zync; do echo Resuming dc:  $x; sleep 2; oc rollout resume dc/$x; done
```

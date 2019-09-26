# Day In The Life Workshop Troubleshooting Guide

## Inaccessible consoles

* Fix for issue where certain administrative consoles become inaccessible (after Integreatly cluster shutdown and restart):

1. Check pod status within the OpenShift project where console service is found:
```
# oc get po -n <project_name>
```

2. Identify unstable pod(s). In this example, *codeready-6ff4c6ccb8-2lr6k* pod is unstable.
```
NAME                                  READY     STATUS       RESTARTS     AGE
codeready-6ff4c6ccb8-2lr6k            0/1       CrashLoopBack   87         5h
codeready-operator-7547984678-jzpc6   1/1       Running         3          5h
postgres-95db94985-p7m2s              1/1       Running         1          1h

```

3. Delete the unstable pod:
```
# oc delete pod codeready-6ff4c6ccb8-rk7wf
```

4. Check pod status in real-time within the OpenShift project:
```
# oc get po -w -n <project_name>
```
* Notice that a new pod, containing the same service, is created to replace the deleted pod.

5. (Optional) If the problem of unstable pod(s) persist, delete other pod(s) that the unstable pod(s) are dependent on.
* *For instance, the unstable pod in the example is dependent on the Postgresql database pod.*
* *Delete the Postgresql database pod, and once it is fully active, delete the unstable CodeReady Workspaces pod.*

6. Tail the pod log in order to observe startup process:
```
# oc logs -f codeready-6ff4c6ccb8-rk7wf
```

[Click to return to Landing Page](https://agileintegration.ga)

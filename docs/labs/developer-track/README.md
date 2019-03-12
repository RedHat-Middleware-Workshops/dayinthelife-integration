# Agenda
## Audience: API Developer
### Scope: Walk the user through an API development and integration process leveraging OpenShift, Ansible, Apicurio, Microcks, Fuse, 3scale AMP

1. [Review Apicurio API Designer](lab01/#lab-1)
2. [Microcks API Mocking](lab02/#lab-2)
3. [Swagger to Rest and DB (with Che)](lab03/#lab-3)
4. [SOAP to Rest](lab04/#lab-4)
5. [Setup 3scale API management](lab05/#lab-5)
6. [API Developer Portal](lab06/#lab-6)
7. [Securing Node.js endpoint development](lab07/#lab-7)
8. [Fuse Online Bonus Lab](lab08/#lab-8)

RESOLVE timeout

CLOSE THE CURRENT DOWNLOAD PROCESS,

Goto a new Terminal and go to 
/home/user/.m2 and vi settings.xml

Add the following content to reset the timeout time:
```
<settings>
  ....
  
  <servers>
    <server>
      <id>central</id>
      <configuration>
            <timeout>120000</timeout>
      </configuration>
    </server>
  </servers>

 ....
</settings>
```
save and exit. 

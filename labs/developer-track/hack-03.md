# Third Hack

-Instructor lead-

Establish an API endpoint for notification from external partners. 

![Second Hack](images/hack-02-01.png)

- Add connection for messaging broker for Input queue and announcement topic. Select the **Connections** on the side menu, and click on **Create Connection** button on the top right hand corner.

 
### New Connection
- Click on the AMQ Message Broker Connector.
![Integration hack three](images/hackthree-integration-01.png)
 
- Configure your AMQ Message Broker settings accordingly, all the information should be avaliable in your OSE env, within the Broker's secret setting. 

   	- Connection *URL : tcp://broker-amq-tcp:61616*
   	- User Name: *amq*
   	- Password: *topSecret*
   	- Check Certificate: *Disable*
![Integration hack three](images/hackthree-integration-11.png)

- And click **Next** when done. 

- Give any name to your broker connection, and click **create** 

### Integration one
- **Create new integration**. Select the **Integration** on the side menu, and click **Create Integration** button on the top right hand corner.


- Click on the WebHook Connector.

  ![Integration hack two](images/hackthree-integration-02.png)

- Select *Incoming Webhook* .

  ![Integration hack two](images/hackthree-integration-03.png)

- Click on Next. 


- Configure the Output data type,
  - Select Type: JSON Instance
  - Definition:
    ```
    {
      "level": "Warning",
      "content": {
        "title": "Tester",
        "msg": "This is the message for everyone!!"
      }
    }
    ```
  
- For end connector setting, and select the  < YOUR_AMQ_MESSAGING_BROKER_CONNECTION >   

- Select *Publish messages action*. 

  ![Integration hack three](images/hackthree-integration-07.png)

- Configure the name of the queue to listen
	- Destination Name: notifications
	- Destination Type: Topic 
	- Disable persisttent


- Configure the Output data type,  
  	- Select Type: JSON Instance
	- Definition: 
	
     ```
    {
      "level": "Warning",
      "content": {
        "title": "Tester",
        "msg": "This is the message for everyone!!"
      }
    } 
	```
- Click **DONE** and then **Publsih**

- Give a name to the route and click **Finish**
- Wait for integration to finish deployment. Once the the integration is fully deployed, you will find an API automatically appear, copy it. *(You can come back to this integration after you are done with the next one.)*

![Integration hack three](images/hackthree-integration-12.png)
![Integration hack three](images/hackthree-integration-18.png)

### Integration two 
- **Create new integration**. Select the **Integrations** on the side menu, and click on **Create Integration** in the center.


- Select < YOUR_AMQ_MESSAGING_BROKER_CONNECTION > .

  ![Integration hack one](images/hackthree-integration-02.png)

- Select *subscribe for messages action*. 


- Configure the name of the queue to listen and click *Next*
	- Destination Name: notifications
	- Destination Type: Topic 


- Configure the Output data type,  
  	- Select Type: JSON Instance
	- Definition: 
		```
		{
      "level": "Warning",
      "content": {
        "title": "Tester",
        "msg": "This is the message for everyone!!"
      }
    }
		```
  

- For end connector setting, and select the  < YOUR_BROKER_CONNECTION >  (**AMQP**) 
![Integration hack one](images/hackthree-integration-13.png)

- Select *Publish messages action*. 

- Configure the name of the queue to listen
	- Destination Name: notifications
	- Destination Type: Topic 
	- Disable persisttent

  

- Configure the Output data type,  
  	- Select Type: JSON Instance
	- Definition: 
	
		```
		{
		"type": "Success",
		"header": "Christina",
		"message": "This is the message for <strong>everyone</strong>!!"
		}
		```	

- On the integration route menu, hover over the **+** sign between first and last connection, click on **Add a Step**



- Select **Data Mapper** Step


- Map from Source ->  to Target -> Custom 
	- content -> msg **to**  message
	- content -> title **to**  header
	- content -> level **to**  type
	
![Integration hack one](images/hackthree-integration-13.png)

- Click **DONE** and then **Publsih**

- Give a name to the route and click **Finish**



- Open your terminal window, try entering the follow command to call the API you just established.
![Integration hack three](images/hackthree-integration-18.png)

```
curl -k -H 'Content-Type: application/json' -d '{ "level": "Warning", "content": { "title": "API Tester", "msg": "This is the message coming from API!!!!"}}' <YOUR_API_EXTERNAL_URL>
```
 
- Go to your UI, http://www-hackathon-ui-<USER_NAME>.apps.fuserhte.openshiftworkshop.com, and a notification should appear!

![Integration hack three](images/hackthree-integration-19.png)
![Integration hack three](images/hackthree-integration-20.png)

- Try a couple of more calls, and you have an **API** exposed using **Fuse Online**!
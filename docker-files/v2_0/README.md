## T-Nova Test Setup

If you have docker installed then simply execute the following command at the terminal, you may have to run it with sudo option.

```
docker-compose up
```

Once the containers are all setup, then you must configure the rabbit-mq component for receiving events from the ATOS Accounting module. Assuming that the docker IP is localhost, do the following -

* go to http://localhost:15672/
	* under Exchanges tab - create an exchange called **tnova** of type *direct*.
	* under Queues tab create a new queue titled **cyclops**
	* come back to Exchanges tab and click on **tnova** entry
 	* associate the queue **cyclops** with this exchange using routing key **event**

The **accounting** service can be accessed at *http://localhost:8080/accounts/* link.

Register three objects with accounting POST call on *accounts* endpoint -

```
{
	"instanceId": "id01",
	"productId": "vnf1",
	"agreementId": "vnfidf61",
	"relatives": "id02",
	"productType": "vnf",
	"flavour": "whatever",
	"startDate": "2015-06-10T00:00:00Z",
	"lastBillDate": "2015-06-10T00:00:00Z",
	"providerId": "f1",
	"clientId": "p1",
	"status": "running",
	"billingModel": "PAYG",
	"period": "P1D",
	"priceUnit": "EUR",
	"periodCost": 1.5,
	"setupCost": 2,
	"renew": true
}
```
And -

```
{
    "instanceId": "id02",
    "productId": "s1",
    "agreementId": "ns10",
    "relatives": "id01, id03",
    "productType": "ns",
    "flavour": "whatever",
    "startDate": "2015-06-11T00:00:00Z",
    "lastBillDate": "2015-06-11T00:00:00Z",
    "providerId": "p1",
    "clientId": "c1",
    "status": "running",
    "billingModel": "PAYG",
    "period": "P1D",
    "priceUnit": "EUR",
    "periodCost": 1,
    "setupCost": 1,
    "renew": true
}
```

And finally - 

```
{
    "instanceId": "id03",
    "productId": "vnf2",
    "agreementId": "vnfidf52",
    "relatives": "id02",
    "productType": "vnf",
    "flavour": "whatever",
    "startDate": "2015-06-16T00:00:00Z",
    "lastBillDate": "2015-06-25T00:00:00Z",
    "providerId": "f1",
    "clientId": "p1",
    "status": "running",
    "billingModel": "PAYG",
    "period": "P1D",
    "priceUnit": "EUR",
    "periodCost": 1,
    "setupCost": 1,
    "renew": true
}
```

If all works as planned, you should see events in the rabbitmq queue titled *cyclops*.

### Enabling the SLA module
Using *curl* make sure that all SLA enforecements have been srated for the corresponding agreementId used above.

```
curl -H "Accept: application/json" -H "Content-type: application/json" -u user:password -X PUT localhost:9040/enforcements/vnfidf61/start

curl -H "Accept: application/json" -H "Content-type: application/json" -u user:password -X PUT localhost:9040/enforcements/vnfidf52/start

curl -H "Accept: application/json" -H "Content-type: application/json" -u user:password -X PUT localhost:9040/enforcements/ns10/start
```

In case you need to change the status of a running service, this can be done through swagger UI located at *http://localhost:8080/docs/*. Or just replace *localhost* with the correct hostname if it is different.

Simply go to the **servicestatus** row and send a POST request to change the status of existing service, in the examples above - the service id is: **id02**.

You can supply one of the following *new_status* value -

* running
* stopped


For further question please contact: harh@zhaw.ch
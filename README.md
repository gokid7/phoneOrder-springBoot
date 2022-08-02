# vodafoneAssignment
Vodafone Assignment
--Activating top-up orders
POST method ;
http://localhost:8080/order/create

request=>
{
"orderId" : 1,
"gsmNumber" : "5557778899",
"status" : 1
}

response=>
{
"id": 1,
"orderId": 1,
"gsmNumber": "5557778899",
"status": 1
}

--Deactivating top-up orders
PUT method;
http://localhost:8080/order/deActive/5557778899

response=>
{
"id": 1,
"orderId": 1,
"gsmNumber": "5557778899",
"status": 0
}

--Listing top-up order
GET method ;
http://localhost:8080/order/5557778899

response=>
[
{
"id": 1,
"orderId": 1,
"gsmNumber": "5557778899",
"status": 1
}
]
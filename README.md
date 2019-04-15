This is a simple Spring Boot and MongoDB example for creating order API

To create an order we have to create an account first and add inventory

If account is not present or inventory is zero order create API will throws exception

Postman APIs:

Account API: /account/create

{
  "name" : "Rahul Khandelwal",
  "email": "rahul.p@caratlane.com"
}

Inventory API: /inventory/create

{
	"itemName": "laptop",
	"qty": 3,
	"price": 1200
}

API: /order/create

{
	"email": "rahul.p@caratlane.com",
	"items": [{"itemName": "laptop", "qty": 2}]
}
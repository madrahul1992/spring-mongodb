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

**RateLimiter:** Resources are limited by a rate limiter.

Rate limiter is implemented using redis and sliding window log algorithm.

For each limiter key we will storing last accessed time and count all the request withing time frame.

The functionality is in RateLimiter class.

````
    protected boolean allow(int maxRequest, String key, Long TIME_WINDOW_IN_SECONDS) {
        Long nowSeconds = System.currentTimeMillis() / 1000;
        jedis.zremrangeByScore(key, 0, nowSeconds-TIME_WINDOW_IN_SECONDS);
        Long count = jedis.zcount(key, "-inf", "+inf");
        boolean allowed = count < maxRequest;
        if (allowed) {
            jedis.zadd(key, nowSeconds, "" + System.currentTimeMillis());
        }
        return allowed;
    }
````

Limit is imposed on below parameters.
1. IP address limiter 
2. URL limiter
3. User ID limiter
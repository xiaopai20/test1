# Interview take home task

We would like you to complete the coding task outlined below. There are no tricks or pitfalls and we are looking for a clean, concise solution that you would be happy to have reviewed by your peers.  Please include sufficient tests to show the code is functioning as requested.  If you need to make any assumptions please document these in your code.

## The task

Create a Spring Boot service implementation which exposes the following REST endpoints 

- Retrieve a customer for a given customer id - GET /customers/{id}
- Retrieve an account for a given account id - GET /accounts/{id}
- Retrieve a transaction for a given transaction id - GET /transactions/{id}
- Retrieve a list of transactions for a given account id in reverse time order - GET /accounts/{id}/transactions

Access to all endpoints should only be permitted if the request has a header with the name "lean-token" and the value is contained within the whitelist file provided.

CSV files have been provided which contain example data and this data should be served by your REST APIs.  We encourage the judicious use of 3rd party dependencies to aid your solution but please use Maven or Gradle build configuration.  It is not necessary to containerise your solution or provide Swagger/OpenAPI documentation.
 Please commit your code to a GitHub private repository and add adam@leantech.me, ilya@leantech.me and michael@leantech.me as contributors so that a member of our team can review your solution.
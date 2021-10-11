Bus booking application ( Spring boot application)

We designed the application for registering the users for bus booking system and retrieving user details.

Workflow (Functionalities) : 

User required to provide the below details as a request : 
1. First Name 
2. Last Name
3. Address
4. Email Id
5. Mobile number

User details will be validated for mobile number and email Id and if the user already registered using either the email Id or the mobile number then he will not be able to register again.

If the validation becomes successful then the user will be registered successfully.

We can retrieve the user details by using the mobile number or the email Id.

We can also retrieve all the users as a CSV file , using the getAllUsers end point.



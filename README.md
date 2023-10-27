# Meal Buddy App Server

Meal Buddy is a virtual kitchen assistant that helps you manage your recipes and grocery list. This repository is the server side of a full stack application that connects with the [Meal Buddy Client](https://github.com/courtneymcodes/meal-app-client/tree/main).

## Tools and Technologies

- Java
- Spring Boot
- H2 Database
- Spring Security
- JSON Web Tokens
- IntelliJ
- Cucumber
- Rest Assured
- Postman
- Git Version Control


## Planning

### ERD Diagram

![erd diagram](/erd.png)


### User Stories

As a user, I want to be able to register so that I can have my own account
- The user must enter an email address and password
- The user must enter a unique email address
- A user is created if they do not exist
- If a user already exists, an exception should be thrown

As a user, I want to be able to log in with my email address and password so that I can access my personalized content
- The user must enter an email address and password
- If the user enters valid credentials, a user is authenticated and has access to their content
- An exception is thrown if credentials are invalid
	
As a user, I want to be able to save recipes to my favorites list so that I can reference them in the future
- If an identical recipe already exists, an exception is thrown
- If recipe does not exist, it is added to the user's favorites list
	
As a user, I want to be able to see a list of recipes that I have saved so that I can use them
- All recipes from the user's favorites list should be provided

As I user, I want to be able to view a specific recipe I have saved
- The recipe with the given id should be provided

As a user, I want to be able to remove a recipe from my favorites list
- If the recipe with the given id exists it is removed from their favorites list
- If the recipe does not exist, an exception is thrown

As a user, I want to be able to add ingredients to my shopping list
- If an identical ingredient already exists in the users shopping cart, an exception is thrown
- If the ingredient does not exist, it is added to the user's shopping cart

As a user, I want to be able to view all ingredients in my shopping list
- All ingredients in the user's cart should be available

As a user, I want to be able to remove an ingredient from my shopping list that I no longer need to buy
- If the ingredient exists in the user's shopping cart, it is removed 
- If the ingredient doesn't exist, an error is thrown

As a user, I want to have a cart so that I can keep track of ingredients that I need 
- A cart is automatically initialized at first log in
- A user can have only one cart

### Endpoints

![endpoints.png](/endpoints.png)





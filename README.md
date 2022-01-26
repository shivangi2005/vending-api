### How to Run application
- Run Command prompt and go to project folder vending-api
- execute run (type run and enter)
- Application will be initialised and ask to enter number of coins for each coin value
- After machine is initialised, it asks to select the product
- Based on user selection, user will be asked to enter coins one by one until total price is matched
- Change is calculated and return coins will be displayed

### Configuration
- products and prices can be configured in application.yml file under properties/application.yml


### Folder Structure
- main
  - config
  - exception
  - model
  - service
-test
  - service

### Future Work
- Service test line coverage is 90% which can be further increased.
- Framework(s) like MeanBean can be added to cover tests for bean classes
- jacoco plug-in has been added for the test report, further rules can be added to have minimum coverage
- REST cotrollers can be added to expose as REST API


## Design
- I have used Spring boot framework for this requirement which implicitly use design patterns like Dependency injection, Singleton, Factory Method, Proxy and Template patterns.
- I have used services to hold the business logic which can be injected dynamically to the framework and can be easily customised for the different requirement
- IO service  has been created for accepting the input and display result which can be replaced by any other I/O depending upon user interface
- APIs can be easily exposed to web / microservices based architecture as the framework can be used to configure controllers


### Vending machine Life cycle:

Initialisation -> Ready -> select product -> Accept User change -> Return Change -> Ready

### Process:

1. Initialisation:
   Initialisation includes intialising products and it prices, initial change (coins) to intial float total_change. intialise customer_change to 0

2. Ready to accept Input

3. Input product
   Accept product

4. Input customer change
   This includes accepting coins until matches with product price.

5. Calculate Change:
   calculate, deduct coins from toal_amount  and return change.

6. Continue Step 2



### Model:
- Product -> name, price
- Change -> penny, 2p, 5p, 10p, 20p, 50p, 1£,2£
- State -> Init, Ready, Select_Product, Accept_change, Return_change


### Service:
- CalculatorService
- InitialisationService
- ProductService
- I/O Service

### Controller
-main 

### Interface
- manage lifecycle status (main)


	

	

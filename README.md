## How to Run application



##Folder Structure


##Design

###Vending machine Life cycle:

Initialisation -> Ready -> select product -> Accept User change -> Return Change -> Ready

###Process:

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



###Model:
- Product -> name, price
- Change -> penny, 2p, 5p, 10p, 20p, 50p, 1£,2£
- State -> Init, Ready, Select_Product, Accept_change, Return_change


###Service:
- CalculatorService
- InitialisationService
- ProductService
- I/O Service

###Controller
-main 

###Interface
- manage lifecycle status (main)


	

	

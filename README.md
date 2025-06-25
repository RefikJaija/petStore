Java Pet Store CLI Exercise

This is a simple command-line application I put together to manage a pet store. It lets you create users, add pets, and even simulate users buying pets.

Quick Notes on How I Built It

    Fixed Date for Age: I hardcoded a specific date for calculating pet ages. This just made the pricing consistent for testing purposes.

    Random Data: To make it easy to play around, I added commands to generate random users and pets from predefined lists.

    Simple Buying: When a user "buys" a pet, the app just finds the first available and affordable pet without an owner.

How to Get It Running (Super Easy!)

What You'll Need:

    Java Development Kit (JDK) 11 or higher

    Apache Maven (for building and running)

Steps:

    Open your terminal and go to the project's main folder (where pom.xml is).

    Compile the code: mvn compile

    Run the application: mvn exec:java -Dexec.mainClass="com.petstore.Main"

    The app will start and wait for your commands!

Commands You Can Use:

    create-users: Generates some random users. 

create-pets: Generates some random cats and dogs. 

list-users: Shows all the users. 

list-pets: Shows all the pets. 

buy: Users try to buy pets. 

history-log: Shows a log of buy commands. 

user: <FirstName>, <LastName>, <email@address.com>, <budget>: Create a specific user. 

    Example: user: Alice, Smith, alice.s@example.com, 100

pet: <Name>, <Type>, <Description>, <YYYY-MM-DD>, <rating (for Dogs only)>: Create a specific pet. 

    Example (Cat): pet: Paws, Cat, Fluffy, 2023-04-01

    Example (Dog): pet: Rover, Dog, Friendly, 2021-06-15, 7

exit: Stops the application. 

Running Tests:

To run the unit tests: mvn test

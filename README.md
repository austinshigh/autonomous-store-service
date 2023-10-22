## Store Controller Service Design Document

**Date:** 9/23/2021  
**Author:** Austin High

### Introduction

Improvements in sensors, robots, and electronic payment capabilities have made the fully automated store a reality. This document outlines the implementation of a Controller that is part of a larger back-end service for a grocery store that operates without employees. Robots stock shelves, clean floors, check inventory, fetch items for customers, and respond to customer questions. Sensor-embedded turnstiles check out customers and monitor for fires. Cameras monitor customers' location throughout stores.

### Overview

The Store Controller Service is designed to listen to specific events that occur within stores that belong to the StoreModelService. When an event is created, the Controller service is notified, and the event is passed to the Controller. The Controller determines what the event is and responds accordingly. That could mean announcing an emergency within the store and opening all turnstiles when a fire is detected. Or it could mean updating the contents of a customer's basket and a shelf's inventory when a camera senses that a customer has added or removed an item from their basket. The Controller service manages a variety of events and ensures that the appropriate actions are taken within the StoreModelService.

**Organization:**

1. Requirements
2. System Architecture
3. Use Cases
4. Class Diagram
5. Sequence Diagrams
6. Class Dictionary
7. Design Details
8. Testing
9. Risks

### Requirements

This section defines the requirements for the Store Controller Service. Implementation of the service must fulfill these requirements:

**Observer Pattern:**

1. The Controller and Model service implement the Observer pattern.
   a. The Device interface in the StoreModelService will act as the subject and must contain methods `attach()`, `detach()`, and `notify()`.
   b. The StoreController will implement the Observer interface which contains the `update()` method.

**Attach / Detach Observer:**

- Adds an Observer to the Subject (device)'s list of observers.

**Notify Observer:**

- Device class maintains a list of all observers, the Device class has a method, `notify()`, that calls the `update(event)` method on all observers.

**Create Event:**

- Creates an event object and notifies all observers that a new event has been created.

**Command Pattern:**

1. The Controller implements the Command pattern.
   a. When the `update()` method is called on the StoreController, the event that is passed is used to create a Command object of the correct type. The StoreController does not know what logic is contained within the Command classes, it simply knows how to instantiate and execute them.

**Command Interface:**

- The Command interface ensures that all command classes implement the same `execute()` method.

**Store Controller:**

- Acts as an Observer on devices and listens as events are added to devices. When an event is added, the StoreController's `update(event)` method is performed, which instantiates the appropriate type of Command. It then runs the event's `execute()` method to perform the event's logic.

**Event Handling:**

- The event class parses events that are passed as strings to the command processor. The event class reads the first word to determine what type of event is contained. Then, it verifies that the appropriate number of arguments are given in the appropriate format and stores them as attributes. If an event is not of a valid type, the event class should throw an error.

### System Architecture

The Store Model Service is part of the larger Store 24X7 Software System. This UML Component diagram shows all of the system's components. This document outlines implementation details for the Store Model Service.

**Store Controller Service:**

- The Store Controller Service monitors the sensors and controls all of the appliances in the store. The Controller Service is responsible for monitoring the events received from the sensors located in the store and responding by appropriately controlling the appliances. In addition, the Controller Service is responsible for listening for voice commands and responding by controlling the appropriate appliance.
- The Controller Service can also respond to general questions about the state of the store. The Controller Service is responsible for checkout and processing transactions. It uses the Ledger Service to manage transactions and account balances for customers.

**Ledger Service:**

- The Ledger Service specifies an implementation of a blockchain service as a means of accepting payment from customers. The Ledger Service manages the transactions, accounts, and blocks that make up the Blockchain. Once a transaction has been submitted and added to the blockchain, its contents become immutable.

**Store Model Service:**

- The Store Model Service is responsible for managing the domain entities of the Store24x7 System. Domain entities include the store, inventory, products, customers, baskets, turnstiles, robot assistants, tasks, and sensors. The Store Model Service provides an API for interacting with those objects. The API supports querying the state of the entities, as well as updating the state. The Model Service maintains the state of all domain objects.

### Authentication Service

The Authentication Service manages the authentication of users and controls access to the store. The Entitlement Service first identifies the user through face and/or voice recognition. Once identified, the Authentication Service is used to gate access to control appliances.

### Use Cases

The following Use Case diagram **/diagrams** describes the use cases supported by the Store Model System.

**Actors:**
Actors of the Store Model System include Robot Assistants, the Store Controller, and Customers.

**Robot Assistants:**

- Responsible for stocking shelves, cleaning spills, checking inventory, fetching items for customers, and responding to customer questions.

**Store Controller:**

- Responsible for monitoring customer location, tracking basket activity, keeping the store clean, maintaining inventory, and detecting fires.

**Customer:**

- Customers can enter the store through the turnstile, add items to their basket, remove items from their basket, ask for their account balance, and ask for the location of an item.

**Use Cases:**

- **Fetch Product:**

  - Microphone event, triggered when the customer says "Please get me <number> of <product>". The Controller passes the following command to the robot closest to where the product exists in inventory for the store, "fetch <number> of <product> from <aisle> and <shelf> and bring to customer <customer> in aisle <customer_location>".

- **Missing Person:**

  - Microphone event, triggered by customer phrase "can you help me find <customer_name>". Locates the customer by name by iterating through all customers marked as currently in the store, and then announces via speaker in Store, "<customer_name> is in <aisle>.

- **Emergency:**

  - Camera event, triggered when a fire, flood, earthquake, or armed intruder is detected. Controller opens all turnstiles, announces "There is a <emergency> in <aisle>, please leave <store> immediately". Then commands a robot to "address <emergency> in <aisle>", and commands remaining robots to "Assist customers leaving the <store>".

- **Basket Event:**

  - Camera event, triggered when a customer is spotted adding or removing products from a shelf. Controller adds/removes the product to/from the customer's basket. Then performs the appropriate add/remove action on the shelf. Finally, if the stock is below a threshold of 50%, a robot is tasked with restocking the shelf.

- **Customer Seen:**

  - Camera event, triggered when a customer is spotted in an aisle. Controller makes an API call to StoreModelService to update the customer's location to their current aisle.

- **Check Account Balance:**

  - Microphone event, triggered when the customer says "What is the total basket value?". Controller calls the API to compute basket total. Then checks the user's account balance. Creates a speaker event to instruct the customer "total value of basket items is <value> which is (more | less) than your account balance of <balance>.

- **Assist Customer to Car:**

  - Turnstile event, triggered when the customer's total basket weight exceeds 10lbs. Controller commands a robot to assist the customer to their car.

- **Broken Glass:**

  - Microphone event, triggered by the sound of breaking glass. Controller commands the closest robot to "clean up the broken class in <aisle>".

- **Enter Store:**

  - Turnstile event, triggered when the customer is waiting to enter the turnstile. First, the customer is looked up by ID. Then their wallet balance is checked; a positive balance is required for store entry. If the customer's wallet balance is positive, they are assigned a basket, the turnstile is opened, and a welcome message is announced "Hello <customer_name>, welcome to <store name>!"

- **Cleaning Event:**

  - Camera event triggered when a product is spotted on the floor in an aisle of the store. Controller instructs a robot to "clean-up <product> in <aisle>".

- **Checkout:**
  - Turnstile event, triggered when the customer approaches a turnstile. Controller first identifies the customer. Then computes the basket total. A transaction is created in the ledger, and the transaction is submitted to the blockchain. Turnstiles are opened, and a checkout message is announced "goodbye <customer_name>, thanks for shopping at <store_name>!"

### Sequence Diagram

The following diagrams **/diagrams/sequence** outline the possible sequences that may occur when a customer attempts to checkout of the store.

### Class Diagram

The following class diagram **/diagrams** defines the classes defined in the design.

### Class Dictionary

This section specifies the class dictionary for the Store Model Service. Device and Observer are defined within the package "com.cscie97.store.model". The remaining classes should be defined within the package "com.cscie97.store.controller".

**Device**

- **Interface**

| Method Name   | Signature                   | Description                                                                                                           |
| ------------- | --------------------------- | --------------------------------------------------------------------------------------------------------------------- |
| createEvent() | (event: Event) : string     | Creates a new event to the specifications provided in the API interface, calls the notify method to update observers. |
| attach()      | (observer: Observer) : void | Adds an observer to the observer list                                                                                 |
| detatch()     | (observer: Observer) : void | Removes an observer from the observer list                                                                            |
| notify()      | () : void                   | Calls the updateEvent() method on all observers when a new event is created.                                          |

- **Association Name**

| Association Type | Description                                                                     |
| ---------------- | ------------------------------------------------------------------------------- |
| eventList        | List of events, added as events are called. Documents executed events.          |
| observerList     | List of observers, all observers are notified when the notify method is called. |

**Observer**

- **Interface**

| Method Name | Signature             | Description                                                                                                                              |
| ----------- | --------------------- | ---------------------------------------------------------------------------------------------------------------------------------------- |
| update()    | (event: Event) : void | Retrieves the eventType of the passed event. Instantiates the appropriate command object, calls the execute() method on the new command. |

**StoreController**

- **Class (Implements Observer Interface)**

**Command**

- **Interface**

| Method Name | Signature | Description                                                                                                                      |
| ----------- | --------- | -------------------------------------------------------------------------------------------------------------------------------- |
| execute()   | () : void | This allows for encapsulation of each command's logic. The StoreController can execute a command's logic, knowing only its type. |

**FetchProduct**

- **Class (Implements Command)**

- **Properties**

| Property Name | Type   | Description                                 |
| ------------- | ------ | ------------------------------------------- |
| storeId       | string | ID of store where fetch is called           |
| customerId    | string | ID of customer requesting product           |
| quantity      | int    | Quantity of products that customer desires. |
| productId     | string | ID of product that customer desires         |

- **Method**

| Method Name | Signature | Description                                                                                                                                   |
| ----------- | --------- | --------------------------------------------------------------------------------------------------------------------------------------------- |
| execute()   | () : void | Command robot to: fetch <quantity> of <productId> from <aisleId> and shelfId> and bring to customer <customerId> in aisle <customer_location> |

**MissingPerson**

- **Class (Implements Command)**

- **Properties**

| Property Name | Type   | Description                                      |
| ------------- | ------ | ------------------------------------------------ |
| storeId       | string | ID of store where customer location is requested |
| customerName  | string | Name of customer to be located                   |

- **Method**

| Method Name | Signature | Description                                                                                      |
| ----------- | --------- | ------------------------------------------------------------------------------------------------ |
| execute()   | () : void | Locates customer by name, creates speaker event to announce "<customerName> is in aisle <aisle>" |

**Emergency**

- **Class (Implements Command)**

- **Properties**

| Property Name | Type   | Description                                |
| ------------- | ------ | ------------------------------------------ |
| storeId       | string | ID of store where camera spotted emergency |
| aisleId       | string | ID of aisle where camera spotted emergency |
| emergencyType | string | {fire, flood, earthquake, armed intruder}  |

- **Method**

| Method Name | Signature | Description                                                                                                                                                |
| ----------- | --------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------- |
| execute()   | () : void | Opens turnstiles, announces "There is a <emergency> in <aisle>, please leave <store> immediately", Sends command to robot "address <emergency> in <aisle>" |

**BasketEvent**

- **Class (Implements Command)**

- **Properties**

| Property Name | Type   | Description                              |
| ------------- | ------ | ---------------------------------------- |
| storeId       | string | ID of store where basket event occurred. |
| customerId    | string | ID of customer who performed event       |
| aisleId       | string | ID of aisle where event was performed    |
| shelfId       | string | ID of shelf that was modified            |
| productId     | string | ID of product that was added or removed  |

- **Method**

| Method Name | Signature | Description                                                                                                                                                                                                            |
| ----------- | --------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| execute()   | () : void | Adds or removes product to or from the specified customer's basket. Adds or removes product from the appropriate shelf and commands robot to perform restock on the specified shelf if the product is below 50% stock. |

**CustomerSeen**

- **Class (Implements Command)**

- **Properties**

| Property Name | Type   | Description                         |
| ------------- | ------ | ----------------------------------- |
| storeId       | string | ID of store where customer was seen |
| aisleId       | string | ID of aisle where customer was seen |
| customerId    | string | ID of customer who was seen         |

- **Method**

| Method Name | Signature | Description               |
| ----------- | --------- | ------------------------- |
| execute()   | () : void | Updates customer location |

**CheckAccountBalance**

- **Class (Implements Command)**

- **Properties**

| Property Name | Type   | Description                                               |
| ------------- | ------ | --------------------------------------------------------- |
| customerId    | string | ID of customer who is asking for an account balance check |

- **Method**

| Method Name | Signature | Description                                                                                                                                                             |
| ----------- | --------- | ----------------------------------------------------------------------------------------------------------------------------------------------------------------------- | -------------------------------------------- |
| execute()   | () : void | Computes the value of items in the basket, checks the account balance for the customer, creates a speaker event: "total value of basket items is <value> which is (more | less) than your account balance of <balance> |

**AssistCustomerToCar**

- **Class (Implements Command)**

- **Properties**

| Property Name | Type   | Description                                     |
| ------------- | ------ | ----------------------------------------------- |
| storeId       | string | ID of store where the customer needs assistance |
| customerId    | string | ID of the customer who needs assistance         |

- **Method**

| Method Name | Signature | Description                                                                                                           |
| ----------- | --------- | --------------------------------------------------------------------------------------------------------------------- |
| execute()   | () : void | Checks the weight of the basket, if the basket weighs over 10lbs, command a robot to assist the customer to their car |

**BrokenGlass**

- **Class (Implements Command)**

- **Properties**

| Property Name | Type   | Description                       |
| ------------- | ------ | --------------------------------- |
| storeId       | string | ID of store where glass broke     |
| aisleId       | string | ID of aisle where glass is broken |

- **Method**

| Method Name | Signature | Description                                          |
| ----------- | --------- | ---------------------------------------------------- |
| execute()   | () : void | Commands robot to "clean-up broken glass in <aisle>" |

**Enter Store**

- **Class (Implements Command)**

- **Properties**

| Property Name | Type   | Description                                      |
| ------------- | ------ | ------------------------------------------------ |
| storeId       | string | ID of store that the customer is trying to enter |
| customerId    | string | ID of the customer trying to enter               |
| turnstileId   | string | ID of turnstile that the customer is waiting at  |

- **Method**

| Method Name | Signature | Description                                                                                                                                                                                      |
| ----------- | --------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ |
| execute()   | () : void | Look up the customer by ID, check for a positive account balance, assign the customer a basket, open the turnstile, and send a welcome message "Hello <customer_name>, welcome to <store name>!" |

**Cleaning Event**

- **Class (Implements Command)**

- **Properties**

| Property Name | Type   | Description                                   |
| ------------- | ------ | --------------------------------------------- |
| storeId       | string | ID of store where event occurred              |
| productId     | string | ID of product on the floor                    |
| aisleId       | string | ID of aisle where the product is on the floor |

- **Method**

| Method Name | Signature | Description                                       |
| ----------- | --------- | ------------------------------------------------- |
| execute()   | () : void | Commands robot to "clean-up <product> in <aisle>" |

**Checkout**

- **Class (Implements Command)**

- **Properties**

| Property Name | Type   | Description                             |
| ------------- | ------ | --------------------------------------- |
| storeId       | string | ID of store where checkout is requested |
| customerId    | string | ID of the customer checking out         |

- **Method**

| Method Name | Signature | Description                                                                                                                                                                                                                                                                    |
| ----------- | --------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ |
| execute()   | () : void | Identifies the customer by ID, computes the total cost of the customer's basket, creates a transaction, submits the transaction to the blockchain, opens the turnstile, commands turnstile to send the message "goodbye <customer_name>, thanks for shopping at <store_name>!" |

- **Association Name**

| Association Type | Description                                                                                                                          |
| ---------------- | ------------------------------------------------------------------------------------------------------------------------------------ |
| ledger           | LedgerService association used to check customer's account balances, create transactions, and commit transactions to the blockchain. |

**Event**

- **Class**

- **Properties**

| Property Name | Type   | Description                                                                                                                      |
| ------------- | ------ | -------------------------------------------------------------------------------------------------------------------------------- |
| EventType     | string | This attribute is queried to determine what type of event is being passed, the remaining object attributes are read accordingly. |
| arg0          | string | Queried as either storeId or customerId depending on the type of event.                                                          |
| arg1          | string | Optional value holding an argument depending on the event type                                                                   |
| arg2          | string | ""                                                                                                                               |
| arg3          | string | ""                                                                                                                               |
| arg4          | string | ""                                                                                                                               |

**Design Details**

The core component for the Store 24x7 System is the StoreModelService class. The StoreModelService provides an API for interacting with the Stores and implements the API methods that manage the Customers, Products, Devices, Baskets, Aisles, and Shelves that make up the Stores.

The Store Controller is responsible for responding to events that occur within the stores with actions. The Store Controller maintains a reference to the CommandProcessor class to give commands to the StoreModelService, as well as the Ledger service to access payment information. In future iterations, the Controller will interact with the StoreModelService using an API with token verification.

The Controller Service implements the Observer interface and acts as an observer to the Devices in stores. When a new event is triggered via the CLI, the device's observers are notified. The notification process consists of iterating through all of the device's observers and passing them the event. The Controller Service takes this event, categorizes it as a command object, instantiates the appropriate command, and executes the command's logic.

The Controller provides automation for the Sensor and Appliance behavior within all stores handled by the StoreModelService. The Controller can handle multiple StoreModelServices if the need arises.

**Testing**

Implement a test driver class called TestDriver that implements a static main() method. The main() method should accept a single parameter, which is a command file. The main method will call the CommandProcessor.processCommandFile(file:string) method, passing in the name of the provided command file. The TestDriver class should be defined within the package "cscie97.store.test".

Three test CLI files will be included. They contain the API commands included in the "Assignment3_Script_Syntax.pdf" document.

Command to build program:

javac com/cscie97/store/model/_.java com/cscie97/store/controller/_.java com/cscie97/ledger/_.java com/cscie97/store/test/_.java

java -cp . com/cscie97.store.test.TestDriver store.script
java -cp . com.cscie97.store.test.TestDriver store_b.script
java -cp . com.cscie97.store.test.TestDriver store_c.script

**Risks**

- The model currently has no authentication system. An authentication system should be implemented to control which methods users are allowed to call.

- The in-memory implementation leaves the system vulnerable to losing store, customer, product, inventory, and device information. This should be corrected as soon as possible.

- In the event of an emergency, customers' baskets are all cleared, but products are not returned to their shelves. This is possibly a fair assumption to make, as if all turnstiles open, there is likely an emergency. However, if the use-case allowed for the return of products to shelves, this would be a more realistic representation of events in a fully automated store.

- No process is currently outlined to account for the updating of inventory levels, customer basket values, or customer location after a crash in the system. Some kind of daily snapshot or interval update of the model state would be needed to have a more complete picture of a fully automated store.

- Some stores may want real-time and historical data regarding customer movements and product removals from shelves. Further API work would be needed to provide such tracking and a means of storing and retrieving the data for analysis.

- The description here only lists methods and properties without values, descriptions, or comments. To have a fully functioning program, each of these would need to be documented and implemented.

- Testing is a critical aspect of implementing the system. Testing should include both unit tests and integration tests. Each class and method needs to be tested to ensure it performs as expected and that all interactions between classes and services are functioning correctly.

- The above design is fairly complex, and thus, proper testing will require substantial effort. Comprehensive testing will help ensure the system behaves as intended and can handle various scenarios and edge cases.

- The development and integration of a blockchain-based payment system may introduce additional complexities, such as dealing with network latency, potential security vulnerabilities, and scalability challenges. Careful consideration and testing are required to address these risks.

- The deployment of physical robots and sensors in real-world stores introduces challenges related to hardware maintenance, scalability, and environmental factors (e.g., sensor malfunctions). These challenges need to be addressed for successful real-world implementation.

- Continuous monitoring and support will be required to ensure the system's availability and reliability. This includes addressing any issues that may arise in real-time, ensuring data consistency, and updating the system to adapt to changing business requirements and technology advancements.

- Data privacy and security concerns are significant risks, especially when dealing with customer information, voice recognition, and payment data. Implementing robust security measures and compliance with data protection regulations is essential.

- Ethical considerations related to customer surveillance, data usage, and automation need to be addressed. It's important to establish transparent policies and gain customers' trust regarding how their data is collected and used, as well as the ethical implications of fully automated stores.

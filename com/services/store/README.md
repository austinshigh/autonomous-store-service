# Store Model Service Design Document

## Date: 9/23/2021

## Author: Austin High

## Introduction

Improvements in sensors, robots, and electronic payment capabilities have made the fully automated store a reality. This document outlines the implementation of a back-end service for a grocery store that operates without employees. Robots stock shelves, clean floors, check inventory, fetch items for customers, and respond to customer questions. Sensor-embedded turnstiles check out customers and monitor for fires. Cameras monitor customers’ location throughout stores. The Store Model Service is designed to implement and manage any number of stores. As customers add items to their shopping basket, sensors add the items to a virtual basket. This basket keeps track of the items, their prices, and the quantities that the user plans to purchase. When the user exits via turnstile, the turnstile automatically deducts their total from their blockchain account, deducts the basket items from inventory, and clears their basket.

## Overview

Eliminating employees decreases overhead and increases the store owner’s ability to manage a uniform customer experience. The largest advantage for the customer is made possible with blockchain payments and sensors that track the user’s basket contents. Replacing the traditional check-out process saves the customer time. Payment becomes as quick as walking through a turnstile.

## Requirements

This section defines the requirements for the Store Model Service. Implementation of the Store Model Service must fulfill these requirements:

### Store Model Service

The Store Model Service must handle the provisioning of new Stores, controlling store appliances, accessing store sensor data, and monitoring customer data. Service should allow creation and control of multiple Stores. Service must also provide an API for provisioning of read and update access to all Store Model Service Entities. Entities include stores, inventory, products, customers, baskets, turnstiles, robot assistants, tasks, and sensors.

### Store

The Store is a model that represents a physical store instance. The Store Model Service is capable of managing many stores. For this reason, each store requires a globally unique identifier. The Store Model must also contain the store’s name and physical address. The Store maintains a hashmap of its aisles, which in turn hold references to the store’s shelves. Each store maintains a map of Customers who have shopped there, as well as a map of the store’s devices.

### Aisle

An aisle is a container unit for shelves. Aisles contain a number that is unique. The number is not required to be globally unique, only unique per store. In addition to aisle number, the aisle maintains an aisle name, aisle description, and aisle location. An aisle is either stored on the floor (on display for customers) or in the storeroom. Aisles maintain a hashmap of shelf id’s mapped to shelves.

### Shelf

A shelf contains an identifier that is unique per aisle, a shelf name, and shelf description. The shelf is either a low, medium, or high shelf, and is one of five temperatures. Shelf temperature must be within the set (frozen, refrigerated, ambient, warm, hot), defaulting to ‘ambient’ if no temperature is specified.

### Product

Products themselves do not represent a physical asset of the store. Products hold product data that is referenced by inventory items. A product maintains a unique id, name, description, category, price (in whole, blockchain units), size (weight and volume), and temperature. Temperature refers to the temperature at which a given product must be shelved. Product temperature set is the same as the shelf set, and upon adding inventory to a shelf, temperature compatibility should be enforced.

### Inventory

Inventory is used to define products that exist in the stores and are either available for sale currently or on a shelf in the storeroom. Inventory carries a globally unique id, a count of products, and the product capacity (maximum number of products per shelf). An Inventory maintains a reference to the product that it describes, as well as its location (store, aisle, and shelf). Before updating inventory, the service should assert that the new inventory count for the specific item being updated is greater than 0, and the total inventory count is less than or equal to the capacity of the shelf.

### Customer

Customer represents a shopper. All customers have globally unique identifiers, are tracked and are classified either as guests or registered shoppers. Store sensors update a customer's location when they are spotted using either voice or facial recognition. If registered, a Customer also contains the person’s first name, last name, email, blockchain address, and basket id. Guests are not allowed to remove items from the store; the system should throw an alert in this case.

### Basket

When a shopper adds a product to their basket or cart while shopping, it is added to their basket. The Basket maintains a reference to the customer that it belongs to. It maintains a globally unique id, as well as a map of product id’s mapped to the quantity of the product held in the basket. If the service attempts to retrieve the basket of a basketless customer, a new basket object should be created for the customer. When a basket is cleared, the customer’s reference to the basket should be cleared in addition to the basket’s attributes and associations.

### Sensor

Sensors are cameras and microphones. They are IoT devices that record and share data with the store. Sensors are used for listening to customer commands, recognizing customers to update customer location, and locating fires. A Sensor has a globally unique id, a name, and a location within the store, and the sensor type.

### Appliance

An Appliance is an IoT device that can record data like a Sensor but can also be controlled. They have the same attributes as sensors, plus type-specific methods. Appliances include Speakers, Robots, and Turnstiles. When the appliance class receives a command, it returns a response.

### API Commands:

The Store Model Service will support the following set of commands:

1. Create a new store
2. Show store details
3. Create a new aisle
4. Show aisle details
5. Create shelf
6. Show shelf details
7. Create customer
8. Show customer details
9. Update customer location
10. Create inventory item
11. Show inventory details
12. Update inventory count
13. Show product details
14. Get a basket by customer id, create a new basket if the customer has no basket
15. Add a product to a customer’s basket
16. Remove a product from a customer’s basket
17. Clear the contents of a basket and remove customer association
18. Get all products and product quantities in a basket by basket id
19. Create a sensor
20. Show sensor details
21. Create an event to simulate a sensor event
22. Create an appliance
23. Show appliance details
24. Create an appliance event, simulating a sensor event
25. Send a command to an appliance

## System Architecture:

The Store Model Service is part of the larger Store 24X7 Software System. This diagram **(/diagrams)** shows all of the system’s components. This document outlines implementation details for the Store Model Service.

## Store Model Service

The Store Model Service is responsible for managing the domain entities of the Store 24x7 System. Domain entities include the store, inventory, products, customers, baskets, turnstiles, robot assistants, tasks, and sensors. The Store Model Service provides an API for interacting with those objects. The API allows clients to perform actions on stores, aisles, shelves, products, inventory, customers, baskets, sensors, and appliances.

This document provides an overview of the Store Model Service and its various components. The Service aims to support fully automated stores, making shopping convenient and efficient for customers while reducing the need for traditional employees.

## Store Controller Service

The Store Controller Service monitors the sensors and controls all of the appliances in the store. The Controller Service is responsible for monitoring the events received from the sensors located in the store and responding by appropriately controlling the appliances. In addition, the Controller Service is responsible for listening for voice commands and responding by controlling the appropriate appliance.

The Controller Service can also respond to general questions about the state of the store. The Controller Service is responsible for checkout and processing transactions. It uses the Ledger Service to manage transactions and account balances for customers.

## Authentication Service

The Authentication Service manages the authentication of users and controls access to the store. The Entitlement Service first identifies the user through the face and/or voice recognition. Once identified, the Authentication Service is used to gate access to control appliances.

### Use Cases

This Use Case diagram **(/diagrams)** describes the use cases supported by the Store Model System.

#### Actors

Actors of the Store Model System include Robot Assistants, the Store Controller, and Customers.

##### Robot Assistants

Responsible for stocking shelves, cleaning spills, checking inventory, fetching items for customers, and responding to customer questions.

##### Store Controller

Responsible for monitoring customer location, tracking basket activity, keeping the store clean, maintaining inventory, and detecting fires.

##### Customer

Customers can enter the store through the turnstile, add items to their basket, remove items from their basket, ask for their account balance, and ask for the location of an item.

### Class Diagram

This class diagram **(/diagrams)** defines all classes used in the service.

### Class Dictionary

This section specifies the class dictionary for the Store Model Service. The classes should be defined within the package "com.services.storemodelservice".

#### StoreModelService

The StoreModelService is the top-level interface for the program. The StoreModelService handles controlling appliances, accessing sensor and appliance states, monitoring and supporting customers, creating sensor events, and managing store configurations.

# Methods

| Method Name            | Signature                                                 | Description                                                                                                           |
| ---------------------- | --------------------------------------------------------- | --------------------------------------------------------------------------------------------------------------------- |
| getStore               | (storeId : string) : Store                                | Return the store for the given store id.                                                                              |
| createStore            | (store : Store) : void                                    | Verify that store id is unique, add store to StoreModelService map.                                                   |
| getProduct             | (productId : string) : Product                            | Return the product for the given product id.                                                                          |
| createProduct          | (product : Product) : void                                | Verify unique product id, add product to StoreModelService map.                                                       |
| getCustomer            | (customerId : string) : Customer                          | Return the customer for the given customer id.                                                                        |
| createCustomer         | (customer : Customer) : void                              | Verify that the customer id is unique, verify that blockchainAddress is valid, add customer to StoreModelService map. |
| updateCustomerLocation | (customerId : string, location : CustomerLocation) : void | Get the customer by id, set the location where the customer was seen.                                                 |
| getInventory           | (inventoryId : string) : Inventory                        | Return the inventory for the given inventory id.                                                                      |
| getDevice              | (deviceId : string) : Device                              | Return device for the given device id.                                                                                |
| createCommand          | (deviceId : string, command : string) : string            | Create a command, send it to the device with the given id, return the device’s message.                               |

# Associations

| Association Name | Type                  | Description                          |
| ---------------- | --------------------- | ------------------------------------ |
| customerMap      | map<string, Customer> | Map of customer ids and Customers.   |
| productMap       | map<string, Product>  | Map of product ids and Products.     |
| storeMap         | map<string, Store>    | Map of store id’s and Stores.        |
| basketMap        | map<string, Basket>   | Map of basket ids and Baskets.       |
| deviceIdMap      | map<string, string>   | Map of device ids and store ids.     |
| inventoryIdMap   | map<string, string>   | Map of inventory id’s and store ids. |

# Properties

| Property Name | Type   | Description                                                                                    |
| ------------- | ------ | ---------------------------------------------------------------------------------------------- |
| authToken     | string | Auth token value to be used in future implementation to provision access to StoreModelService. |

## Store

The Store class maintains a model of a single store instance. The store model service can support many stores. The store class supports devices for the purposes of monitoring customers, checking customers out, and detecting fires and spills within the store. The store class also maintains an inventory tracking system that groups inventory by aisle and shelf.

# Properties

| Property Name | Type   | Description                |
| ------------- | ------ | -------------------------- |
| id            | string | Globally unique identifier |
| name          | string | Store name                 |

# Associations

| Association Name | Type                   | Description                                                            |
| ---------------- | ---------------------- | ---------------------------------------------------------------------- |
| inventoryMap     | map<string, Inventory> | Map of inventory id’s and Inventory objects.                           |
| deviceMap        | map<string, Device>    | Map of device id’s and Device objects.                                 |
| address          | Address                | Store’s physical address                                               |
| aisleMap         | <string, Aisle>        | Map of aisle numbers and Aisles.                                       |
| customerMap      | Customer               | Map of customer ids and customers that have been spotted in the store. |
| inventoryMap     | map<string, Inventory> | Map of inventory id’s and Inventory objects.                           |

## Methods

| Method Name     | Signature                 | Description                                                                                                                                                                                                                                                                                                                                            |
| --------------- | ------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ |
| getAisle        | (number : string) : Aisle | Return the aisle for the given aisle number.                                                                                                                                                                                                                                                                                                           |
| createAisle     | (aisle : aisle) : void    | Verify that aisle id is unique. Create new aisle.                                                                                                                                                                                                                                                                                                      |
| createDevice    | (device : Device) : void  | Create a new device and add to map in Store class, as well as specified StoreModelService’s map of deviceids and store ids.                                                                                                                                                                                                                            |
| createInventory | (item : Inventory) : void | Verify that product temperature and shelf temperature are equivalent. Verify that the specified store has the specified aisle and shelf. Verify that the count for the new inventory does not exceed the capacity of the shelf by getting the specified shelf and summing all inventory item counts. Add inventory to StoreModelService inventory map. |

# Address

Class used to provide organization for Store class. Holds a store’s physical address.

# Properties

| Property Name | Type   | Description        |
| ------------- | ------ | ------------------ |
| street        | string | Ex. “Broad Street” |
| city          | string | Ex. “Philadelphia” |
| state         | string | Ex. “Pennsylvania” |

# Aisle

The Aisle class represents the physical store aisles that every autonomous store has. Aisles are either in the storeroom or on the floor. If they are on the floor, they are customer-facing, and customers may draw from their inventory. If they are in the storeroom, robots use their inventory to re-stock floor aisles.

# Properties

| Property Name | Type   | Description                                                                                            |
| ------------- | ------ | ------------------------------------------------------------------------------------------------------ |
| number        | string | Aisle number, required to be unique for each store, but not for overall StoreModelService ex “aisle_1” |
| name          | string | Aisle name ex. “Dairy”                                                                                 |
| description   | string | Description of aisle content, ex. “Milk, Cheese, Sour Cream”                                           |

## Associations

| Association Name | Type               | Description                                                             |
| ---------------- | ------------------ | ----------------------------------------------------------------------- |
| aisleLocation    | AisleLocation      | Enumeration class containing aisle location options, (floor, storeroom) |
| shelfMap         | map<string, Shelf> | Map of shelf id’s and Shelf objects                                     |

## Methods

| Method Name | Signature                  | Description                                                                    |
| ----------- | -------------------------- | ------------------------------------------------------------------------------ |
| getShelf    | (shelfId : string) : Shelf | Return the shelf for the given shelf id.                                       |
| createShelf | (shelf : Shelf) : void     | Verify that shelf has a unique id, create shelf and add to map in Aisle class. |

# AisleLocation

The AisleLocation is an enumeration class that is used to store the location of a given aisle.

# Properties

| Property Name | Type        | Description                                                                |
| ------------- | ----------- | -------------------------------------------------------------------------- |
| floor         | Enum string | This location is used for customer-facing aisles                           |
| storeroom     | Enum string | This location is used for shelves that store products for stocking shelves |

# Shelf

The shelf class models the autonomous store’s physical shelves. A shelf object maintains a map of the Inventory objects that are on the shelf, as well as the shelf height, and the temperature of the shelf.

# Properties

| Property Name | Type   | Description                                                                                          |
| ------------- | ------ | ---------------------------------------------------------------------------------------------------- |
| id            | string | Aisle number, autoincrement when added, unique for each aisle, but not for each Store, ex. “shelf_1” |
| name          | string | ex. “Milk”                                                                                           |
| description   | string | ex. “50% off on Wednesdays”                                                                          |

## Associations

| Association Name | Type        | Description                                                                                                            |
| ---------------- | ----------- | ---------------------------------------------------------------------------------------------------------------------- |
| level            | Level       | Value required. (low, medium, high)                                                                                    |
| temperature      | Temperature | The enumeration class defaults to “ambient” if no temperature is specified. (ambient, frozen, refrigerated, warm, hot) |

# ShelfLevel

ShelfLevel is an enumeration class that is used to store the placement of a given shelf, high, medium, or low.

# Properties

| Property Name | Type        | Description    |
| ------------- | ----------- | -------------- |
| high          | Enum string | Shelf location |
| medium        | Enum string | Shelf location |
| low           | Enum string | Shelf location |

# Temperature

Temperature is an enumeration class that describes the temperature of products and shelves.

# Properties

| Property Name | Type        | Description                                                                                |
| ------------- | ----------- | ------------------------------------------------------------------------------------------ |
| ambient       | Enum string | Default temperature for aisles and products if no temperature attribute is specified.      |
| frozen        | Enum string | Used for products that must be stored in a freezer, and to denote freezer aisles           |
| refrigerated  | Enum string | Used for products with must be stored in refrigeration, and to denote refrigerator aisles. |
| warm          | Enum string | Used for products with must be stored under a warmer, and to denote warmer aisles.         |
| hot           | Enum string | Used for products with must be stored under a heater, and to denote heater aisles.         |

# Inventory

The Inventory class manages physical instances of products in stores. An Inventory object can contain multiple products, but they are all grouped on the same shelf and are all identical. The Inventory object tracks the count of objects on each shelf. When the count is low, robots are called to restock the shelf. Inventory objects have a maximum capacity which is checked when adding new inventory to the shelf.

# Properties

| Property Name | Type             | Description                                                           |
| ------------- | ---------------- | --------------------------------------------------------------------- |
| id            | string           | Unique inventory identifier across StoreModelService                  |
| count         | Unsigned integer | Current count of product items on the shelf, may not be less than 0.  |
| capacity      | Unsigned integer | Total shelf capacity. The inventory count may not exceed this number. |

## Associations

| Association Name | Type    | Description                                 |
| ---------------- | ------- | ------------------------------------------- |
| productMap       | Product | The product that the inventory stocks       |
| storeMap         | Store   | The store that owns the inventory object    |
| aisleMap         | Aisle   | The aisle that stores the inventory object  |
| shelfMap         | Shelf   | The shelf that the inventory object sits on |

## Methods

| Method Name     | Signature             | Description                                                                                                                |
| --------------- | --------------------- | -------------------------------------------------------------------------------------------------------------------------- |
| updateInventory | (count : uint) : void | Verify that the new inventory count is greater than 0 and less than the shelf capacity. If so, update the inventory count. |

# Product

The Product class is maintained by the StoreModelService. The Product class contains all information required for selling a product. Id, name, description, category, size, and price. The class also contains the temperature that the product is required to be stored in. This temperature must match that of the shelf that the product is stored on.

# Properties

| Property Name | Type   | Description                                                    |
| ------------- | ------ | -------------------------------------------------------------- |
| id            | string | Unique identifier for a product across StoreModelService       |
| name          | string | Ex “Ruffled Potato Chips”                                      |
| description   | string | Ex “Made with 100% sunflower oil”                              |
| category      | string | Ex “Chips”                                                     |
| price         | uint   | Price per 1 unit of product (in blockchain currency of Units). |

## Associations

| Association Name | Type        | Description                                                                              |
| ---------------- | ----------- | ---------------------------------------------------------------------------------------- |
| temperature      | Temperature | Enumeration class stores the temperature environment that the product must be stored in. |
| size             | Size        | Contains weight and volume information for product                                       |

# Size

The Size class contains weight, and or volume information for a product.

# Properties

| Property Name | Type | Description            |
| ------------- | ---- | ---------------------- |
| weight        | int  | Weight in ounces       |
| volume        | int  | Volume in fluid ounces |

# Customer

The Customer class is stored in the StoreModelService. This ensures that membership in one store guarantees membership in another store implemented by the same StoreModelService. Along with identifier information, the Customer class contains the customer’s blockchainAddress. This address is used in conjunction with the Device and Basket classes to check customers out automatically as they leave the store.

# Properties

| Property Name     | Type     | Description                                                                                 |
| ----------------- | -------- | ------------------------------------------------------------------------------------------- |
| id                | string   | Globally unique identifier                                                                  |
| firstName         | string   | Ex “Connor”                                                                                 |
| lastName          | string   | Ex “Norton”                                                                                 |
| email             | string   | Customer email address                                                                      |
| blockchainAddress | string   | Customer blockchain address used for billing                                                |
| registered        | boolean  | If false, the customer is a guest. Guests are not permitted to remove items from the store. |
| timeLastSeen      | datetime | Stores the time that the customer was last seen by a device.                                |

## Associations

| Association Name | Type     | Description                                                                        |
| ---------------- | -------- | ---------------------------------------------------------------------------------- |
| basket           | string   | Null if the customer does not have a basket. Nullified when the basket is emptied. |
| lastLocation     | Location | Stores the location where the customer was last seen by a device.                  |

## Methods

| Method Name | Signature   | Description                                                                                                                         |
| ----------- | ----------- | ----------------------------------------------------------------------------------------------------------------------------------- |
| getBasketId | () : string | Return the basket id that belongs to the given customer id. If no basket exists, create a new basket and attach it to the customer. |

# CustomerLocation

The CustomerLocation class stores information pertaining to where the customer was last seen. This information is collected and updated via store sensors.

# Properties

| Property Name | Type   | Description                                          |
| ------------- | ------ | ---------------------------------------------------- |
| storeId       | string | Id of the store where the customer was last seen     |
| aisleNumber   | string | number of the aisle where the customer was last seen |

# Basket

The basket class stores a count of all of the items that a user is carrying through the store. When a user checks out by leaving through a turnstile, the contents of the basket are summed, the user’s blockchain account is debited, and the items are removed from inventory.

# Properties

| Property Name   | Type              | Description                                          |
| --------------- | ----------------- | ---------------------------------------------------- |
| id              | string            | Unique basket identifier across StoreModelService    |
| productCountMap | map<string, uint> | Map of product id’s and count of products in basket. |

## Associations

| Association Name | Type     | Description                                                                       |
| ---------------- | -------- | --------------------------------------------------------------------------------- |
| customer         | Customer | The customer that the basket belongs to. Null if no customer is using the basket. |

## Methods

| Method Name             | Signature                                 | Description                                                                                                                                                                 |
| ----------------------- | ----------------------------------------- | --------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| addProductToBasket      | (productId : string, count : uint) : void | Verify that product exists. Update product map in basket to new value.                                                                                                      |
| removeProductFromBasket | (productId : string, count : uint) : void | Check to ensure that the new product count is > 0. If equal to zero, remove product from map entirely, if < 0 throw error. Else, update product map in basket to new value. |
| clearBasket             | () : void                                 | Nullify basket reference in customer object. Nullify product map and customer id in basket.                                                                                 |
| getBasketTotal          | () : uint                                 | Iterates through the map of basket contents, multiplying each product cost by its quantity and finding the total basket cost.                                               |

# Device - Interface

The device interface is implemented by sensors and robots

## Methods

| Method Name | Signature               | Description                                                  |
| ----------- | ----------------------- | ------------------------------------------------------------ |
| showDevice  | () : void               | Show device details                                          |
| createEvent | (event : string) : void | Creates an event to send to device                           |
| getId       | () : string             | Used to get the device id, used when adding device to a map. |

# Turnstile

The Store Service equips a turnstile to answer customer questions and trigger the checkout and payment procedure.

# Robot

The Store Service equips a Robot to answer customer questions and clean spills in the store.

# Speaker

The Store Service equips a speaker to answer customer questions.

# StoreModelServiceException

The StoreModelServiceException is returned from the Ledger API methods in response to an error condition. The StoreModelServiceException captures the action that was attempted and the reason for the failure.

# Properties

| Property Name | Type   | Description                                              |
| ------------- | ------ | -------------------------------------------------------- |
| action        | string | Action that was performed (e.g. “create store”)          |
| reason        | string | Reason for the exception (e.g. “store id is not unique”) |

# CommandProcessor

The CommandProcessor is a utility class for feeding the Store Model Service a set of operations using command syntax.

## Methods

| Method Name        | Signature                   | Description                                                                                                   |
| ------------------ | --------------------------- | ------------------------------------------------------------------------------------------------------------- |
| processCommand     | (command:string) : void     | The output of the command is formatted and displayed to stdout. Throw a CommandProcessorException on error.   |
| processCommandFile | (commandFile:string) : void | Process a set of commands provided within the given command file. Throw a CommandProcessorException on error. |

# CommandProcessorException

The CommandProcessorException is returned from the CommandProcessor methods in response to an error condition. The CommandProcessorException captures the command that was performed and the reason for the failure. In the case where commands are read from a file, the line number of the command should be included in the exception.

# Properties

| Property Name | Type   | Description                                              |
| ------------- | ------ | -------------------------------------------------------- |
| command       | string | The command that was performed (e.g. “define store”)     |
| reason        | string | Reason for the exception (e.g. “store id is not unique”) |
| lineNumber    | int    | The line number of the command is in the input file.     |

## Design Details

The core component for the Store 24x7 System is the StoreModelService class. The StoreModelService provides an API for interacting with the Stores and implements the API methods that manage the Customers, Products, Devices, Baskets, Aisles, and Shelves that make up the Stores. The implementer will find utility methods on those objects. Utility methods on the Store class handle creating and fetching Aisles. Utility methods on the Aisle class handle creating and fetching Shelves.

The inventory class describes products that sit on shelves, either in storage or ready for purchase by customers. The map of Inventory objects is maintained by the Store class. It is important that products are stocked according to temperature. For example, products that must be stored frozen should be stocked in freezer shelves.

Since the store utilizes cashier-less checkouts via turnstiles, it is essential that customer Baskets are properly accounted for. Customers maintain a reference to their Basket, and a Basket maintains a reference to a Customer. When a customer's basket is emptied, both references must be nullified.

Withstanding Aisle numbers and Shelf IDs (which are unique within the scope of a given store), all other class ID values must be globally unique. This is to satisfy the requirement that Customer, Store, Basket, Product, and Inventory may all be retrieved using only their ID.

Customer, Store, Product, and Inventory objects are all stored in maps in the StoreModelService. This is because the StoreModelService is designed to handle the implementation of multiple stores. When a store is dissolved, we still want other stores to have access to the Customer data that belonged to that store. We also want customers to be able to shop in a variety of stores without needing to create new accounts for each store.

Devices, which consist of sensors and appliances, are contained in the same map. In order to differentiate device type, the Store uses the Device method getType().

## Changes to Design

Several changes were made to the original design:

**Attributes Added to StoreModelService:**

- `authToken` attribute added (string) to handle incoming API requests, provisioning access to appropriate token values.
- `Stores` attribute added (map of store IDs and Stores) to allow the service to manage multiple stores.
- `BasketIds` attribute added (map of basket IDs and customer IDs) to allow StoreModelService to fetch a basket by basket ID without iterating through all customers and checking their basket maps.
- `DeviceIds` attribute added (map of device IDs mapped to store IDs) to allow StoreModelService to fetch a device by device ID without iterating through stores and checking each store's device map.
- `Products` attribute added (map of product IDs and products) to ensure all stores are using the same product information.

**Attributes Added to Customer Class:**

- `Basket` reference added to Customer Class to allow a basket to clear the Customer -> Basket and Basket -> Customer references when a basket is cleared.

**Attribute Modified in the Inventory Class:**

- The product association is modified. Rather than holding the product ID, the class holds a reference to the product object. This way, the Inventory class can pull product details without querying the store class for a product by product ID.

## Testing

Implement a test driver class called TestDriver that implements a static main() method. The main() method should accept a single parameter, which is a command file. The main method will call the `CommandProcessor.processCommandFile(file: string)` method, passing in the name of the provided command file. The TestDriver class should be defined within the package "services.store.test".

A single test CLI file will be included. It contains the API commands included in the "store_model_script.txt" document.

# Equipment Rental Database Project

This repository contains the Equipment Rental Company Database project for CSE 3241 (Section 140-36285). All SQL is written for implementation in SQLite. 

The database documents operations for a company that rents out useful equipment for family and community projects  (e.g. house renovations/fixes, plumbing, painting, watering systems, gardening, electrical systems, siding and windows, roof, computer and internet devices, sensors and controllers, etc). The equipment rented is delivered and picked up by a drone. The company has a drone fleet to perform the delivery service to members and warehouses to store equipment.

## Database Description and Documentation

All technical information related to the database, its structure, and its specifications can be found in the [Documentation](/Documentation) subdirectory of this repository. Files include:

* [ERD.pdf](/Documentation/ERD.pdf): Entity-Relational Diagram
* [Relational Schema.pdf](/Documentation/Relational%20Schema.pdf): Relational schema
* [Tables.md](/Documentation/Tables.md): Description and justification of all tables, including functional dependencies and normalization level
* [Indexes.md](/Documentation/Indexes.md): Description and justification of 3 indexes
* [Views.md](/Documentation/Views.md): Description and justification of views, including relational algebra, SQL implementation, and sample output
* [Transactions.md](/Documentation/Transactions.md): Description and justification of 3 sample transactions, including SQL implementation

## User Manual

All information related to using the database can be found in the [User Manual](/User%20Manual) subdirectory of this repository. Files include:

* [Tables.md](/User%20Manual/Tables.md): Information related to tables including entities, attributes, datatypes, and constraints
* [Sample Queries.md](/User%20Manual/Sample%20Queries.md): Sample queries and descriptions, including relational algebra, SQL, and screenshots of execution
* [INSERT Syntax.md](/User%20Manual/INSERT%20Syntax.md): Information related to adding items to database, including SQL syntax, dependencies/restrictions, and examples for each table
* [DELETE Syntax.md](/User%20Manual/DELETE%20Syntax.md): Information related to removing items from database, including SQL syntax, dependencies/restrictions, and examples for each table

## User Interface

The java code used to implement the user interface is included in the [User Interface](/User%20Interface/DatabaseInterface.java). As well as an [Eclipse zip file](/User%20Interface/equipment-rental-UI.zip). AS well as a pdf of all [example outputs](/User%20Interface/Example%20Output.pdf).

## Database File

[equipment-rental.db](/equipment-rental.db) is a binary version of the database, suitable for opening using SQLiteStudio.

## Database Recovery

If the binary version of the database is corrupted, lost, or cannot be opened, the [Recovery](Recovery) subdirectory of this repository can be referenced to recreate and populate it from scratch using SQLite. Files include:

* [Instructions.md](/Recovery/Instructions.md): Instructions for using the following files to recreate the database
* [CREATE.txt](/Recovery/CREATE.txt): Contains all necessary SQL for creation of database schema on an empty database using SQLite, including comments, indexes, and views
* [INSERT.txt](/Recovery/INSERT.txt): Conatins all necessary SQL for populating database with sample data using SQLite and the previous file, including comments

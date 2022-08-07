## GUI-Based Scheduling Application

Author: Joseph DeDona  
Contact: jdedon3@wgu.edu  
Application Version: 1.0.0  
Date: 03/30/2022

IDE Version: IntelliJ IDEA Community Edition 2021.1.1 x64

JDK: java 17.0.1 2021-10-19 LTS  
Java(TM) SE Runtime Environment (build 17.0.1+12-LTS-39)  
Java HotSpot(TM) 64-Bit Server VM (build 17.0.1+12-LTS-39, mixed mode, sharing)

JavaFX version: JavaFX-SDK-17.0.1

Database Driver: mysql-connector-java-8.0.25
____

## Purpose

This program is a GUI-based appointment scheduling program backed by a PostgreSQL database for persistent storage. The program stores data about customers, users, contacts, and appointments. The program supports the creation of new customers and appointments and the modification of existing customers and appointments. The program also contains functionality to develop specific reports that describe or display stored data.
____

## Running the Program

Upon launching the program, a login page will be displayed. The language of the login page will either be French or English depending on the system default. Upon entering a valid username and password combo, a main menu screen will be displayed.

The main menu screen provides links to the customers, appointments, and reports screens.

The customers screen allows for the creation, maintenance, and deletion of customer rows within the customers table in database. It displays a table of all customers and contains links to the add and modify customer screens that implement this functionality.

The appointments screen allows for the creation, maintenance, and deletion of appointment rows within the appointments table in the database. It displays a table of all appointments and contains links to the add and modify appointment screens that implement this functionality.

The reports screen contains functionality that produces three different types of reports.
____

## Reports
Report 1: The total number of customer appointments by specified type and month. This report displays how many appointments of a specified type exist in a specified month. 
  
Report 2: A schedule for each contact in the organization. This is displayed in a table that includes appointment ID, title, type, description, start date/time, end date/time, and customer ID.  
  
Report 3: The total number of appointments by specified customer ID. This report displays how many appointments exist in the appointments table for the specified customer.

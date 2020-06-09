# MARA
#### Author

Guyoguyes

#### Description

Mara is application that automates the recording of wildlife data by rangers. Rangers are enable to record the normal animals and Endangered their health, age, sighted location and keep record of the date recorded.

#### Technologies used
-> Java
-> Spark
-> Gradle
-> Maven
-> Materialize
-> Bootstrap
-> Handlebars
-> Postgres

#### Installation and SetUp
git clone the project "https://github.com/Guyoguyes/Mara.git"

install java , gradle , postgres

Open the project in your java IDE

Run Build

Database using PSQL in psql-shell
 -> CREATE DATABASE mara;
 -> \c mara;
 -> CREATE TABLE rangers (id serial PRIMARY KEY, name VARCHAR, budgeno int);
 -> CREATE TABLE animals (id serial PRIMARY KEY, name VARCHAR, type VARCHAR, health VARCHAR, age VARCHAR, rangerId int);
 -> CREATE TABLE locations (id serial PRIMARY KEY, name VARCHAR);
 -> CREATE TABLE sightings (id serial PRIMARY KEY, animalId int, rangerId int, locationId int)
 
 ### Bugs
 
 No Bugs Clean
 
 #### Collaboration
 
 contact [email](g.abduba43@gmail.com)

#### LICENSE

GNU GENERAL PUBLIC LICENSE


# Technical Solution

This assignment has 2 microservices built using Springboot version 3.0.3 & Java 17.

## 1) Movies API ##
### Endpoint : /api/v1/movies/iswon ###

It accepts mandatory query parameter "title", which should be any movie title from the provided CSV file. Database table will be created loading the data from CSV file when application starts. 
The api searches the "Best Picture" category in the H2 database to find out whether has won or not & return the indicator accordingly to the client.


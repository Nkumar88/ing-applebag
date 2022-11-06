-----------------------Description----------------------- 
Applebag application provides 3 APIs to manage the apple bags for sale as mentioned below:

1. Get: /getBags: This API returns 3 bags from Bag table (DESC sorted by ID) if records exist. 
                  In case there are less than 3 records, it returns http 206 with a message to add at least 3 bags.
2. GET: /getBags/{numOfBags}: This API returns the number of bags provided as an optional parameter numOfBags. 
                              It returns http 404, if the requested number of bags are not exist.
3. POST: /addBags: This API allows to add a new bag in DB. The payload needs to have below values:
                   {
                       "numOfApples":[Number, 1<=value<=100],
                       "supplier":[String, any of ("Royal Gala","Pink Lady","Kanzi Apple","Elstar Apples")]
                       "price":[Number, 1<=value<=50]
                   }

-----------------------Steps to build, test and run the project-----------------------
1. Clone the project

2. Navigate to directory which has pom.xml

3. Build the project using maven package:
   Command: mvn package
   Above command will,
   - download all the dependencies
   - build the project
   - run the unit tests on project
   - create the executable jar at target/ing-applebag.jar

4. Run the project:
   Command: java -jar target/ing-applebag.jar
   Above command will,
   - initialize the tomcat and start the app on port 8090.

5. Test APIs: Below urls can be used to test the APIs  
   - localhost:8090/getBags --> to get 3 bags
   - localhost:8090/getBags/{numOfBags} --> to get number of bags
   - localhost:8090/addBag --> To add a bag
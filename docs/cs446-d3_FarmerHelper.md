## Project title: FarmerHelper
# Prototype demonstration
| First Name | Last Name | Quest ID | Github ID  |
|------------|-----------|----------|------------|
| Dingjie    | Hu        | d36hu    | harryhu009 |
| Kevin      | Dong      | k27dong  | k27dong    |
| Shurui     | Cui       | s32cui   | Kenonder   |
| Yiru       | Wu        | y645wu   | yiruW      |
| Yuwei      | Xi        | y29xi    | YUWEIXI    |
| Zihan      | Liu       | z594liu  | ZihanL     |
----
Overview:

Welcome page:

Login page:

Signup page:  
The signup page is for users to create their accounts. Producers can enter their username, password, email, and phone number. The username and password are required fields, and the submit button will be disabled if either username or password is blank. After clicking the submit button, the user will jump to the login page, and the username and password will automatically fill in. If the user clicks the cancel button, the user will go back to the welcome page. The user’s information has not been stored yet, and the signup account is currently simulated. 

Add page:  
There are four input boxes, which allow the user to enter the product name, the detailed description of the product, the current stock, and the price. And users can add pictures. There are four platforms shown in the demo, in practice, optional platform information will be provided by the server and displayed for users to choose from. In order to generate compliance data for the server, we will perform checks on user input. The input will be restricted to numbers for current stock and price. Items can only be submitted after the user has filled in all fields. At present, the function of adding pages has been completed. The focus of the next development is to use the real server, API testing, and development.     
    
Main Product List page:     
This is the main page for this app. It contains four main components, the title bar, search bar, scrollable product lists and an add button. When there are no products, it only includes the title bar and the app button. The title bar contains the profile icon and the stock alarm icon, and one would direct to the profile page and provide warning information if some products are out of stock. Right now, both icons are simulated, and further implementations are required. Clicking on the image shown on the product lists, it could jump to the product specification page and clicking on the add button, it goes to the add page.     
  
Product Specification page:   
This page contains detailed product information, including the product’s name, description, stock number and price. Farmers could edit the product specifications by clicking the bottom edit button and updating the data to the database. Currently, the data shown on this page is fake. The actual data will be fetched when the databases are set up. 
  
Congrats page:   
This page shows that the product has been added and published successfully. Users can choose to preview the recently added product by clicking the “preview” button or going back to the main product list page.

Preview page:  
Users can preview the recently added product to ensure all input information is correct. The information shown comes from the input of the add page.

Architecture:

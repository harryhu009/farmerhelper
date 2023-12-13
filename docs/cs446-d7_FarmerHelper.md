## Project title: FarmerHelper
# Final Report
| First Name | Last Name | Quest ID | Github ID  |
|------------|-----------|----------|------------|
| Dingjie    | Hu        | d36hu    | harryhu009 |
| Kevin      | Dong      | k27dong  | k27dong    |
| Shurui     | Cui       | s32cui   | Kenonder   |
| Yiru       | Wu        | y645wu   | yiruW      |
| Yuwei      | Xi        | y29xi    | YUWEIXI    |
| Zihan      | Liu       | z594liu  | ZihanL     |
----  

Final Status:   
Below are all the pages we have done for this project, and its database is set on FIrebase. 

Add page: There are four input boxes that allow users to enter the product’s name, description, current stock, and price information. Users could also add a product’s picture. For receiving correct user inputs, the stock and the price information are restricted to numbers, and the user can click on the submit button only after filling all required fields. If nothing is filled in, the software will prompt the user to enter the complete field. After the user completes the input of all product information, the APP will interact with DFC's data server and store the information submitted by the user into the database.  

Congrats page: This page shows that the product has been added and published successfully. Users can see the corresponding product name and can choose to preview the recently added product by clicking the “preview” button or going back to the main product list page.  

Preview page: Users can preview recently added products, the page shows the product name, price, stock, and product description, and the information displayed is from the input of the add page. For price and inventory, we also display the weight unit and price unit of the responsibility to ensure that all user input information is correct. When the user confirms that the information is correct, click the "confirm" button, and the software will jump to the main interface.  


Main Product List page: This is the main page for this app, where it shows all generated products. It has four parts from top to bottom: the title bar, search bar,   scrollable product lists, and an add button. When there are no products, it only includes the title bar and the add button, and the add button is large and evident in   the middle of the page, in this case, to inform users to add their first product. When products are in the market database, it shows a scrollable product list with   clickable product images. Since the database is connected, the product images are real and not hardcoded. Clicking on the image directly to the product specification   page and clicking on the add button goes to the add page. We were thinking about adding a user profile page jumping from this main page but considering the time   limitation and adjusting user’s information has not been the main functionality in our app, it has not been implemented. We have the user information saved in the    database, and the logic for modifying it is similar to the ones modifying the products’ information. One thing that needs to be noticed is that if the current user is a non-owner (worker), the add button is disabled since they cannot add or delete products. Besides these two, they could make all other adjustments.    



How you mitigated harm:   

One feedback we have received is that farmers and labourers spend most of their time in the field, and while they are doing the fieldwork, their hands could become    dirty, and it is hard to tap the buttons. Though we cannot support text-to-speech technology at this point, we are trying to mitigate this harm by adjusting the UI     component. We are trying to make the layout clear and make the button big enough to tap. For example, for the main page, where it used to show a combination of    clickable images on the left and its description on the right, we are removing the description to make the clickable image bigger and allow users to click on them    easier. We are trying to make the app as easy as possible with clear and consistent UI components. Fonts, colours and buttons are consistent throughout the pages.   
The adding and editing procedures are also easy to follow. Especially for the editing page, users could directly edit all information on the same page of the    displaying page with old information in the input box without inputting all information again. They could just change the part they want.   

Overview of what each member of our team did:
Our group separated our tasks mostly by assigning different pages of the app. The overall database setup and class setup are discussed in meetings, and then one or two of the members would implement them for others to use besides doing their pages. The list below states the general tasks we have done individually. 

Dingjie Hu:
   Add page design, programming, and function implementation.  
   The layout of add page and congrats page design.  
   Implement the FireBase dependency files, check database stability, and ensure data interaction (JSON format) between app and Firebase.  
   Congrats page design, programming, and function implementation.  
   Write the corresponding parts from D1 to D7.  

Zihan:             
   Main page and Product List page design and function implementation   
   Adjust database to match DFC data keywords   
   Overall design and UI adjustments     
   Write the corresponding parts from D1 to D7, do final check and make adjustments    





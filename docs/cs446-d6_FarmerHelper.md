## Project title: FarmerHelper
# Design Pattern Examples
| First Name | Last Name | Quest ID | Github ID  |
|------------|-----------|----------|------------|
| Dingjie    | Hu        | d36hu    | harryhu009 |
| Kevin      | Dong      | k27dong  | k27dong    |
| Shurui     | Cui       | s32cui   | Kenonder   |
| Yiru       | Wu        | y645wu   | yiruW      |
| Yuwei      | Xi        | y29xi    | YUWEIXI    |
| Zihan      | Liu       | z594liu  | ZihanL     |
----
Architecture description:   
The main architectural styles used are the client-server architecture and the MVVM. First, our software application performs data interaction between the client and the server. When the user uses the Farmerhelper application, the software interacts with the Firebase database for client-server information exchange and task retrieval.  

We use Firebase as the database of Farmerhelper, which is designed by Google for Android mobile software and has the characteristics of better adaptability, efficiency, and easier maintenance. Firebase provides a real-time database, and user information and uploaded product information will be packaged in JSON format and saved in the products table. Converting data into JSON format is more conducive to later maintenance, thus reducing the operation and maintenance cost of the software. Data fields for products include product content, which has name, description, weight, price, image id, and unique identifier. The software uses the Storage provided by Firebase to store product images uploaded by users. And when storing, name the image as the image id in the product JSON information. Therefore, we can obtain product information through the real-time database and search firebase storage through the image id field to get corresponding images.  

For non-functional, client-server architecture style to ensure the security and reliability of the application. Because user data can be stored on multiple servers, when one server is under maintenance, other servers can be guaranteed to work normally, ensuring customer data's security and service reliability. And it is convenient to expand and upgrade the application. Since the client and the server run on different platforms, it is easy to add new servers to expand new services, such as providing customers with a multi-functional food sales platform in the future.       
 <p>
   <img width="30%" alt="Client-Server architecture diagram" src="[https://drive.google.com/file/d/1ySPqHu0annnfsyCmpFqbXqlZB5XFn-gc/view?usp=sharing]">
</p>
System design:
Our group has followed the Adapter design pattern and the Strategy design pattern. 

Adapter design pattern

The adapter pattern was used such that the client could store any data needed in the database by either typing on a text field or clicking on buttons. In this case, images, strings, and float numbers are stored. It reduces the coupling by no need to create multiple different input interfaces for various data types. For example, for the product stock and price values, users could input a string type same as other text data like the product’s name, and after transforming to the float type, it becomes the desired type received by the interface of the DFC server.   
For future usage, if users want to upload other information with different types, no adjustment to the front end interface or the interface of the DFC server needs to be adjusted, but only the adapter class. Also, if the protocol of DFC has been updated or other data servers are being used, adjustments could be made in the adapter class to better suit its interface without changing it from the user’s end.    
The adapter design pattern has been helpful, especially in interacting with third-party services that may require specific data. Adapting the interfaces expands the abilities an original app could do.    


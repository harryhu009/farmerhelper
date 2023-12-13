## Project title: FarmerHelper
# Project Proposal
| First Name | Last Name | Quest ID | Github ID  |
|------------|-----------|----------|------------|
| Dingjie    | Hu        | d36hu    | Harryhu009 |
| Kevin      | Dong      | k27dong  | k27dong    |
| Shurui     | Cui       | s32cui   | Kenonder   |
| Yiru       | Wu        | y645wu   | yiruW      |
| Yuwei      | Xi        | y29xi    | YUWEIXI    |
| Zihan      | Liu       | z594liu  | ZihanL     |

----

Our group chose to work with the default project.

FarmerHelper is an app designed for food producers who wish to sell their products on online food retail platforms. It interfaces with the Data Food Consortium (DFC) API and allows users to upload and modify their products shown through the online platforms.  
From the producers' point of view, this project is interesting because it delivers the ultimate technological solution for one of the most crucial problems for modern food producers, challenging them to properly and efficiently sell their products through the internet. It could be frustrating for them to update the same products' information on several different platforms, and producers need to check each platform's stock numbers regularly. We intend to solve this problem, along with many unlisted, with an Android app and the help of the Data Food Consortium. From a developer's point of view, this is also a fun project because although the demand is stated, the implementation would require a deep understanding of software architectures. We have to pick the design patterns carefully and architectural styles to ensure the app is easy to use while maintaining high performance and responsiveness, all within a beautiful UI. For these reasons, it is evident that the project is worth the investment.  
Ever since the beginning of the information revolution and the popularizing of intelligent devices, many industries have changed drastically and dramatically. It is clear that the traditional way for local food merchants to sell their products is getting outdated, and the pandemic has only accelerated this process. As a group of university students, we wish to bring changes to the business and introduce a faster, more intelligent, and more efficient method for producers to manage their products. FarmerHelper is a challenging project with huge potential and scalability. It is based on Android but could easily be adapted to other platforms such as iOS or the web. We believe the app helps us have a better understanding of software architectures.
This project makes sense to be brought up on mobile because one of the motivations for this initiative includes laptops are sometimes hardly accessible by food producers as most of them work outdoors. It would be much easier for our customers if they could use the application in any location, such as farmlands, warehouses, or retail stores. Even if our users work under wet or dirty conditions, as long as the device is connected to the internet, they can still update their stocks and adjust their products' information quickly through a few clicks on their phone.

1. Functional Properties  
   1.1 Users will be able to create, read, update and delete (CRUD) their products on the food sales platform.   
   1.2 Users will be able to view available food sales platforms.  
   1.3 Users will be able to set stock and price levels for each platform.  
   1.4 Users will be able to receive a reminder if the stock on one platform needs to be added.  
   1.5 Users will be able to login to the same account from different devices. 
   
2. User scenarios

   2.1 Food producer Ken often spends a lot of time uploading products to multiple online sales platforms. So he decided to use our app to help him quickly upload products to multiple web platforms and manage his items. Ken first logs into our app by registering as a producer, and the main interface that follows is a prominent submit product button. Then Ken clicks the gold submit button to enter the submission interface. Ken fills in the product's picture, description, inventory and other information, and when submitting, he can select multiple online sales platforms he wants to submit, and set different prices for the corresponding platforms.Then Ken can also see the products he uploaded on the main interface, and easily modify the product information by clicking the icon.Ken has had a very convenient experience with our app, increasing his efficiency.
   
   2.2 Kelly is an employee of Ken's farm and is usually responsible for helping Ken manage products on multiple platforms. Since there are many platforms, she hopes to receive out-of-stock notifications from the platform uniformly, so that she can perform replenishment and other processing operations in a timely manner. When Kelly uploads products to multiple platforms, she will receive timely notifications when a platform product is out of stock. When Kelly receives the notification, she can directly click the confirmation button to enter the product details page, and directly modify the product information, which is very convenient. By using our app, Kelly can easily manage products on multiple platforms on one interface and replenish products in a timely manner based on notifications.

3. Sequence Diagrams

<p align="center">
   <img width="90%" alt="product view" src="https://user-images.githubusercontent.com/43528898/171976633-9f842b60-49e2-4a81-8970-0b5d12dfdbc0.png">
</p>
<p align="center">
   <img width="90%" alt="product view" src="https://user-images.githubusercontent.com/43526956/171769844-6d80bedf-38f6-4c7a-a910-212cc803b271.png">
</p>

4. Non-Functional Properties    
      High Usability - Users can update their product’s information within 2-3 steps. They can also easily switch between different pages through links at the top.     
      Friendly UI design - Buttons and text (at least size 16) are magnified and direct for farmers to see and process. Multi-layered submenus are avoided for simplicity.     
      High Scalability - More online food retail platforms can be added to the app in the future.   

5. Human values  
   5.1 Wealth: Since users will earn more in small outlets than the wholesaler, our app will use the DFC service which provides a data infrastructure for product and stock data to be shared between platforms. Therefore, users could sell their products on more platforms and the shared stock data can maximize their income.  
   5.2 Responsibility: This software expands the sales channels of agricultural products for crop growers through the Internet, increases their income and solves the potential problem of unsalable agricultural products, showing social responsibility.  
   5.3 Self-Indulgence Enjoying Life: Since producers spend more time selling through the small outlets than the wholesaler, our app reduces the time consumption for producers to upload and manage their products on different platforms, and gives users more free time.

6. Stakeholders   
      Users - Those directly producing food products and wishing to access direct retails, such as farmers, bakers, growers, butchers, jam makers etc.    
      Those producers will use this app to manage their products’ information on different platforms.      
      Project team members - Our group as the developers for this app. We create the user interface for users to modify their products’ information.     

7. Population of Users  
      The targeted users for this application would be local food producers who seek to sell their product without going through the trouble of comparing platforms or managing prices and stocks manually, such as farmers, bakers, growers, butchers and jam makers.
 

9.Mockup

<p>
   <img width="30%" alt="product view" src="https://user-images.githubusercontent.com/43526956/171769326-fe062d80-8b94-4b49-a54f-f7031f2650fc.jpg">
   <img width="30%" alt="product view" src="https://raw.githubusercontent.com/ZihanL/FarmerHelper/main/image1.png">
   <img width="30%" alt="product view" src="https://raw.githubusercontent.com/ZihanL/FarmerHelper/main/image3.png">
   <img width="30%" alt="product view" src="https://raw.githubusercontent.com/ZihanL/FarmerHelper/main/image4.png">
</p>
<p>
   <img width="30%" alt="product view" src="https://user-images.githubusercontent.com/43528898/171976645-e726eef4-c633-4253-9ac3-52d69d7cd916.png">   
   <img width="30%" alt="product view" src="https://user-images.githubusercontent.com/43528898/171976651-6adf267f-d39d-444c-9a5c-671c17d80d4d.png">   
   <img width="30%" alt="product view" src="https://user-images.githubusercontent.com/43528898/171976659-dd6c4e75-d714-4c49-97ee-78de368e5179.png">   
   <img width="30%" alt="product view" src="https://user-images.githubusercontent.com/43428588/171767949-566b119a-924a-49cc-a334-6a0ee643ee37.png">
   <img width="30%" alt="product view" src="https://user-images.githubusercontent.com/71619604/171977247-fde72768-3141-498f-a10e-7cad0c239778.png">
   <img width="30%" alt="product view" src="https://user-images.githubusercontent.com/71619604/171977714-b034e76b-3c40-421d-b663-a0e980e82f8d.png">

</p>



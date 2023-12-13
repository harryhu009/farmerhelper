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

Abstract Factory Design pattern:  

In real life, beverage stores need to provide different flavors of drinks for different customers, which requires the abstract factory design pattern to reduce coupling for different flavors of drinks.
The real-world example of our Abstract Factory Design pattern is a drink store that can serve different types of beverages. In our example, the General Beverage Store is an abstract factory that provides an interface to make different cold or hot beverages, such as juice, coffee, and milk. Juice, Coffee, and Milk represent the abstract factories that make cold or hot drinks. They respectively provide interfaces to implement beverages under the cold beverage category and beverages under the hot beverage category.   


Adapter   
A real-world example that many people use would be a USB-C to 3.5mm headphone adapter. Many modern phones have removed their 3.5mm jack. It may have benefited the company but raised inconvenience for many customers who prefer using wired headphones. The Adapter pattern has been used well to solve this problem, allowing users to use the USB-C to 3.5 mm headphone jack adapter, switching a 3.5mm plug to a USB-C plug to connect the headphones to the phones. As shown in the diagram below, the target is the USB-C socket on the phone, the client is the present user who wants to connect their wired headphones to their phones, the adapter is the 3.5 mm plug from a wired headphone, and the adapter is the USB-C to 3.5mm headphone jack adapter. It has not changed any complex devices of both the headphones or the phones, but with a simple adapter that adapts the different jack, it makes the wired headphones again for phones.   
USB-C to 3.5mm headphone adapter:   
<img width="661" alt="Screen Shot 2022-07-19 at 11 30 38 AM" src="https://user-images.githubusercontent.com/43528898/179789861-5a36cc01-a44d-4268-8379-fa8f5e27c258.png">
It has a low coupling since the adapter does not change the device itself and the number of interfaces current devices have is countable. We could easily convert one interface to fit another interface with several adapters and no signal loss.   
For future usage, this adapter can also be used with any other devices that need a jack switch from 3.5mm to USB-C. For example, people could use it to connect a speaker with a 3.5mm jack to the phones. If other types of jacks are required, adapters could also be added to one another in real life.


SingleTon design pattern   
People have bank accounts and they want to withdraw a certain amount of money from their account. If two people login into the same account at two different places, and perform withdrawing at the same time, then it may cause trouble: 
If they both want to withdraw all the money from the account at almost the same time, and one person has done the withdrawal, but the bank has not updated the database with the new amount of money, then the account may still show the second person with the same amount of money. So, the money in this account is withdrawn twice.
In this case, the SingleTon design pattern is helpful in preventing such mistakes. Under the SingleTon design pattern, only one “withdraw instance” (which can perform the withdrawal) can be created for each account. So, if one person is performing withdrawal and gets the access to the withdraw instance, the second person must wait until the access to the withdraw instance is freed by the first person, and the mistake will not happen anymore.

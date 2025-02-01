"# DemoRailwaySpring" 
This project implements a bookstore management system using Spring and Spring Security for user authentication and authorization.
The system allows users to register and log in either as a Customer or an Admin. It provides different functionalities depending on the user role.

Admin Role:
View all users: The admin can see a list of all users registered in the system.
Create and delete users: The admin has the ability to add or remove users from the system.
Manage books: The admin can add or delete books from the store's inventory.
Customer Role:
View books: Customers can browse and view the available books in the store.
Add and delete books: Customers can also add new books and delete (buy) books.

The application uses Spring Security for securing the authentication and authorization processes, ensuring that only authorized users can perform sensitive operations such as adding or deleting users or books.

The system is hosted on Railway, providing seamless deployment and scalability.

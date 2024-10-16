# Library Management System with Multi-Storage Data Persistence

## Overview

This Library Management System is a comprehensive solution for managing books, users, and loan transactions. It supports two distinct data storage methods: File-based Storage and SQL Database Storage (MySQL). Users can choose their preferred storage method during runtime, and the system ensures seamless data management across both methods, supporting essential CRUD operations and loan tracking functionalities.

## Key Features

### Multi-Storage Support

- File-Based Storage: Data is stored in text files with structured formats.
- SQL Database Storage: Data is stored in a MySQL database using tables for books, users, and transactions.

The system allows users to select the storage method at runtime, and all subsequent operations will utilize the chosen method.

### Book Management

- Add, remove, update, and list books in the library.
- Track loan status and details, including book ID, title, author, and genre.

### User Management

- Add, remove, update, and list users in the library.
- Manage user profiles, including their name, contact details, and loan information.

### Loan and Transaction Management

- Log loan transactions, including fees and late returns.
- Support for loan extensions, late fees, and tracking of borrowed books.

### Consistent Data Handling

Ensure that data remains consistent across the chosen storage method, whether it is file-based or database-based.

## Storage Options

- File-Based Storage
Data is saved and retrieved using text files. Separate files are maintained for books, users, and transactions, with each entry represented in a structured, comma-separated format. This option is ideal for lightweight applications or when a database is not available.

- SQL Database (MySQL)
This storage option uses a MySQL database to store and retrieve all records. The system interacts with the database using SQL queries to perform CRUD operations on tables for books, users, and transactions. This method ensures robust data management for larger-scale applications.

## How It Works

### Storage Selection at Runtime

Upon starting the application, users are prompted to select their preferred storage method:
- File-based Storage
- SQL Database Storage
  
The system automatically adapts all data handling operations (add, delete, update, and read) based on the selected storage.

## Book Operations

- Adding Books: New books can be added to the library. The system prevents duplication by checking the book ID.
- Removing Books: Books can be removed unless they are currently loaned out.
- Updating Books: Users can update the loan status of books after lending or returning them.
- Listing Books: The system can display all available and currently loaned books.

## User Operations

- Adding Users: New users can be added with their unique ID and personal information.
- Removing Users: Users can be removed from the system as long as they have no active loans.
- Updating Users: User data, including loan details and fees, can be updated based on their transactions.
- Listing Users: The system displays all users registered in the library.

## Loan and Transaction Operations

- Loaning Books: Users can borrow available books from the library. Loan fees are calculated based on the loan duration and user type (e.g., public or faculty).
- Returning Books: Users can return borrowed books. If the return is late, a fine is applied.
- Loan Extensions: Users can request loan extensions for specific books (e.g., textbooks), with restrictions on the number of allowed extensions.
- Listing Transactions: The system keeps a record of all loan transactions, including loan fees and late penalties.

## Contributing

Feel free to fork this project and submit pull requests for improvements or new features. Ensure all contributions are well-documented and thoroughly tested.

## License
This project is licensed under the MIT License. See the LICENSE file for more details.

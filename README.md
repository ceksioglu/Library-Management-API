# Library Management System

Welcome to the Library Management System project. This system aims to facilitate the management of books, authors, categories, publishers, and borrowing records within a library setting.

## Getting Started

To get started with the API, you can access the Swagger documentation after the project runs (http://localhost:8081/swagger-ui/index.html#/). This documentation provides detailed information about the available endpoints and how to interact with them.

## Technologies Used

- Java
- Spring Boot
- Hibernate
- PostgreSQL
- Maven

## Features

- **Book Management:** CRUD operations for books, including retrieval by categories and publishers.
- **Author Management:** CRUD operations for authors.
- **Category Management:** CRUD operations for book categories.
- **Publisher Management:** CRUD operations for publishers.
- **Borrowing Management:** Tracking of book borrowing records.

## Setup Instructions

1. Clone the repository.
2. Configure your PostgreSQL database in `application.properties`.
3. Build the project using Maven: `mvn clean install`.
4. Run the application: `java -jar target/library-management-1.0.jar`.

## API Endpoints

- **GET /api/books:** Retrieve all books.
- **POST /api/books:** Create a new book.
- **GET /api/authors:** Retrieve all authors.
- **POST /api/authors:** Create a new author.
- **GET /api/categories:** Retrieve all categories.
- **POST /api/categories:** Create a new category.
- **GET /api/publishers:** Retrieve all publishers.
- **POST /api/publishers:** Create a new publisher.
- **GET /api/borrowings:** Retrieve all borrowing records.
- **POST /api/borrowings:** Create a new borrowing record.

## Contribution

Contributions are welcome! If you'd like to contribute to this project, please fork the repository and submit a pull request with your changes.

## License

This project is licensed under the MIT License - see the LICENSE file for details.

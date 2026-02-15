# Item API (Spring Boot)

Simple Java backend application that provides a RESTful API for managing items.
Data is now stored in an H2 database using Spring Data JPA.

## Tech Stack
- Java 17
- Spring Boot
- Spring Web MVC
- Spring Data JPA
- H2 Database
- Bean Validation (`jakarta.validation`)
- Lombok

## Run the Application
From the project root:

```bash
./mvnw spring-boot:run
```

App default URL:

```text
http://localhost:8080
```

## Database (H2)
This project uses an in-memory H2 database:
- JDBC URL: `jdbc:h2:mem:itemdb`
- Username: `sa`
- Password: *(empty)*

H2 web console:
- URL: `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:mem:itemdb`

## Item Model
The API stores items with:
- `id` (auto-generated)
- `name` (required)
- `description` (required)
- `category` (required)
- `price` (required, must be >= 0)

## API Endpoints

### 1) Add a New Item
- **Method:** `POST`
- **Path:** `/api/v1/items`
- **Body (JSON):**

```json
{
  "name": "Laptop",
  "description": "14-inch lightweight laptop",
  "category": "Electronics",
  "price": 799.99
}
```

- **Success Response:** `201 Created`

### 2) Get a Single Item by ID
- **Method:** `GET`
- **Path:** `/api/v1/items/{id}`
- **Success Response:** `200 OK`
- **Not Found Response:** `404 Not Found`

## Input Validation
When creating an item:
- `name`, `description`, `category` must not be blank.
- `price` must be provided and must be `>= 0`.

If validation fails, response is `400 Bad Request` with field-level errors.

## Example cURL Commands

Create item:

```bash
curl -X POST http://localhost:8080/api/v1/items \
  -H "Content-Type: application/json" \
  -d '{
    "name":"Movie Subscription",
    "description":"Monthly plan",
    "category":"Entertainment",
    "price":15.99
  }'
```

Get item by id:

```bash
curl http://localhost:8080/api/v1/items/1
```

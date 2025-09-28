# common-api

`common-api` is a lightweight and reusable API response & exception handling utility for Spring Boot applications.  
It provides a standardized `Api<T>` response wrapper, `BaseErrorCode` interface for error codes, and built-in exception handling.

## Features

- Standardized JSON API responses for Spring Boot applications
- Built-in support for success and error responses
- Custom error codes via `BaseErrorCode`
- Exception handling with `CustomExampleException` and `ExampleExceptionHandler`
- Flexible usage with custom objects and HTTP status codes

## Response Structure

All API responses follow this JSON structure:

```json
{
  "metaData": {
    "success": true | false,
    "code": <HTTP status code>,
    "message": "<optional message>"
  },
  "data": <payload or null>
}
```

## Installation

### Gradle

```groovy
dependencies {
    implementation 'io.github.seob7:common-api:0.0.2'
}
```

### Maven

```xml
<dependency>
    <groupId>io.github.seob7</groupId>
    <artifactId>common-api</artifactId>
    <version>0.0.2</version>
</dependency>
```

## Usage

### 1. Success Responses

**Usage Example:**

```java
@GetMapping("/example")
public ResponseEntity<Api<String>> example() {
    return Api.OK("example response");
}
```

**Response JSON:**

```json
{
  "metaData": {
    "success": true,
    "code": 200,
    "message": null
  },
  "data": "example response"
}
```

### 2. Success Responses with Custom Object

**Usage Example:**

```java
@GetMapping("/user")
public ResponseEntity<Api<User>> getUser() {
    return Api.OK(new User("tester", 1), "success", HttpStatus.CREATED);
}
```

**Response JSON:**

```json
{
  "metaData": {
    "success": true,
    "code": 201,
    "message": "success"
  },
  "data": {
    "name": "tester",
    "age": 1
  }
}
```

---

### 3. Error Responses with BaseErrorCode

**Step 1: Define Error Codes**    
Define error codes implementing `BaseErrorCode`:

```java
public enum ExampleErrorCode implements BaseErrorCode {

    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "User not found"),
    INVALID_REQUEST(HttpStatus.BAD_REQUEST, "Invalid request data");

    private final HttpStatus httpStatus;
    private final String message;

    ExampleErrorCode(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    @Override
    public HttpStatus getHttpStatus() { return httpStatus; }

    @Override
    public String getMessage() { return message; }
}
```

**Step 2: Define Custom Exception**      
Define custom exception to wrap the error code:

```java
public class CustomExampleException extends RuntimeException {
    private final BaseErrorCode errorCode;

    public CustomExampleException(BaseErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public BaseErrorCode getErrorCode() {
        return errorCode;
    }
}
```

**Step 3: Define Exception Handler**  
Define exception handler to automatically convert exceptions to JSON responses:

```java
@RestControllerAdvice
public class ExampleExceptionHandler {

    @ExceptionHandler(CustomExampleException.class)
    public ResponseEntity<Api<BaseErrorCode>> handleCustomException(CustomExampleException e) {
        return Api.ERROR(e.getErrorCode());
    }

}
```
- With this handler, any thrown CustomExampleException is automatically converted into a standardized JSON response.

**Usage Example:**

```java
@GetMapping("/error1")
public ResponseEntity<Api<BaseErrorCode>> error1() {
    throw new CustomExampleException(ExampleErrorCode.USER_NOT_FOUND);
}
```

**Response JSON:**

```json
{
  "metaData": {
    "success": false,
    "code": 404,
    "message": "User not found"
  },
  "data": null
}
```

### 4. Direct Error Responses (Without Exception)

```java
@GetMapping("/error2")
public ResponseEntity<Api<String>> error2() {
    return Api.ERROR("Custom error message", HttpStatus.INTERNAL_SERVER_ERROR);
}
```

**Response JSON:**

```json
{
  "metaData": {
    "success": false,
    "code": 500,
    "message": "Custom error message"
  },
  "data": null
}
```

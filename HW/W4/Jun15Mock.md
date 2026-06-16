### 1. How to increase young generation size in heap?
Use JVM options such as -Xmn, or by tuning the ratio with -XX:NewRatio. 
Ex: -Xmn512m sets the young generation size to 512 MB. 
If young generation is too small, minor GC may happen frequently. 
But if it is too large, old generation may become too small and cause full GC, 
so we need to monitor GC logs before tuning.

### 2. How does Spring MVC work?
Spring MVC follows the Model-View-Controller pattern. HTTP request hits DispatcherServlet which acts as the front controller. 
HandlerMapping identifies the correct controller. 
HandlerAdapter invokes the method, resolving parameters (@RequestBody, @PathVariable). The method returns a ResponseEntity or object. 
For REST APIs, HttpMessageConverter (Jackson) serializes it to JSON and writes to @ResponseBody or @RestController.
For view-based apps, ViewResolver maps a view name to a template (Thymeleaf), renders HTML, and writes to the response.


### 3. How do you handle exceptions in Java?
In Java, exceptions can be handled using try-catch-finally, throws, and custom exception classes.
Checked exceptions must be handled or declared, while unchecked exceptions usually represent programming errors.

In Spring Boot, we often use @RestControllerAdvice with @ExceptionHandler to handle exceptions globally.
This keeps error handling centralized and makes API responses more consistent.

### 4. REST API vs Message Queue
REST API is synchronous communication, meaning the client sends a request and waits for the response.
Ex: real-time request-response operations, such as getting user profile data.

Message queue is asynchronous communication, where the producer sends a message and the consumer processes it later.
Ex: background tasks, event-driven systems, and decoupling services.

### 5. What annotations do we use to configure customized Actuator?
Spring Boot Actuator provides production-ready endpoints for monitoring application health, metrics, and information.
Common annotations include @Endpoint, @ReadOperation, @WriteOperation, and @DeleteOperation for creating custom actuator endpoints.
We also use @Component to register the custom endpoint as a Spring bean.
In application.properties, we configure exposed endpoints using management.endpoints.web.exposure.include.

Example:
@Component
@Endpoint(id = "custom")
public class CustomActuatorEndpoint {

    @ReadOperation
    public String getStatus() {
        return "custom actuator endpoint";
    }
}

### 6. Can abstract class have no abstract method?
Yes, an abstract class can have no abstract methods. 
We can still mark a class as abstract to prevent direct object creation. 
This is useful when we want to provide common fields or methods for child classes. 
Subclasses can inherit the common behavior and override methods if needed.

### 7. How can you use Optional?
Optional is used to avoid NullPointerException and represent a value that may or may not exist.
It is commonly used as a return type instead of returning null.
We can use methods like of(value), ofNullable(value), empty() orElse(default value), orElseThrow().
In Spring Data JPA, methods like findById() return Optional<T>.

Example:
User user = userRepository.findById(id)
    .orElseThrow(() -> new RuntimeException("User not found"));
    
### 8. What is functional interface?
A functional interface is an interface that has exactly one abstract method.
It can be used with lambda expressions and method references.
The @FunctionalInterface annotation is optional but helps the compiler check the rule.
Examples include Runnable, Callable, Predicate, Consumer, Supplier, and Function.

### 9. Why do you use POST instead of PUT?
use POST when creating a new resource and the server usually generates the ID.
POST is not idempotent, meaning sending the same request multiple times may create multiple records.

We use PUT when updating or replacing a resource at a known URI. 
PUT is idempotent, meaning sending the same request multiple times should produce the same result.

### 11. What is WebFlux? Have you used it in your project?
Spring WebFlux is Spring’s asyn, reactive, non-blocking web framework built on Project Reactor.
using non-blocking I/O to handle asynchronous requests with Mono<T> and Flux<T> from Project Reactor as return types.
It returns Mono for Single object and  Flux for multiple results, 
Ex: high-concurrency applications, streaming data, and services that call many external APIs.

I mainly used Spring MVC, but I understand WebFlux and would use it when the system
needs non-blocking I/O and better scalability.

### 11. What is HashMap?
HashMap is a key-value data structure in Java. It stores data using hashing, so lookup, insert,
and delete operations are usually O(1). Internally, it uses an array of buckets,
and collisions are handled by linked lists or red-black trees after Java 8.
HashMap is not thread-safe, so in concurrent scenarios we should use ConcurrentHashMap.

### 13. What is Enable Auto Configuration?
@EnableAutoConfiguration is a Spring Boot annotation that automatically configures beans
 based on dependencies in the classpath. It is included inside @SpringBootApplication.
Ex: if Spring Boot detects Spring MVC, it auto-configures DispatcherServlet;
 if it detects JPA, it auto-configures database-related beans.
 We can exclude unwanted auto-configuration using exclude in @SpringBootApplication.

### S3
https://rt-bucket06.s3.us-east-2.amazonaws.com/2026-06-15.mp4

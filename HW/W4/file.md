### What Spring Boot version have you used?
I have experience with both Spring Boot 2.x and 3.x. One major difference is that Spring Boot 3 requires Java 17+ and migrated from the javax.* namespace to jakarta.*. Spring Boot 3 also provides improved performance, observability, and support for modern Java features.

### How do you write a REST API in Spring Boot?
I typically create REST APIs using the Controller-Service-Repository layered architecture. The controller layer exposes endpoints using annotations such as @RestController, @GetMapping, @PostMapping, @PutMapping, and @DeleteMapping. The controller delegates business logic to the service layer, which interacts with the repository layer for data access.

### Recursion vs Iteration
Recursion solves a problem by calling itself, while iteration uses loops such as for or while. 
Recursion is often more readable for problems like DFS, tree traversal, and backtracking, but it consumes additional stack memory and may cause a StackOverflowError for deep recursion. Iteration is generally more memory-efficient and is often preferred in high-throughput production systems.

### What is a Fair Lock?
A fair lock grants access to threads in the order they requested the lock, following a FIFO policy. In Java, a fair lock can be created using:
ReentrantLock lock = new ReentrantLock(true);
Fair locks help prevent thread starvation, although they may reduce throughput compared to non-fair locks.

### What are Sealed Classes?
Sealed classes were introduced in Java 17 and allow developers to restrict which classes can extend or implement a class or interface. 
This improves type safety and makes inheritance hierarchies more predictable.
Only the permitted classes can extend Shape.

### What is the Spring Framework?
Spring Framework is a Java framework that simplifies enterprise application development through features such as IoC (Inversion of Control) and AOP (Aspect-Oriented Programming). 
It promotes loose coupling, easier testing, and better maintainability. Spring Boot is built on top of Spring Framework and provides auto-configuration and starter dependencies to simplify application setup.

- How does IoC work?

IoC (Inversion of Control) means the Spring container manages object creation, dependency injection, and bean lifecycle instead of developers creating objects using the new keyword. During application startup, Spring scans bean definitions, creates bean instances, and injects dependencies automatically. 
This reduces coupling between components and improves testability.

What annotations are commonly used for IoC?

Common stereotype annotations include:
@Component
@Service
@Repository
@Controller
@RestController

These annotations register classes as Spring beans managed by the IoC container.

Additional annotations:
@Configuration
@Bean

are used to define beans manually.

Spring supports three types of dependency injection:
Constructor injection is preferred because it makes dependencies explicit, improves testability, and prevents NullPointerException during runtime.

### What bean scopes are available in Spring?
Spring supports several bean scopes:
Singleton (Default) One bean instance per Spring container.
Prototype: A new bean instance is created every time it is requested.
Request Scope: One bean instance per HTTP request.
Session Scope:One bean instance per user session.
Application Scope:One bean instance shared across the entire web application.

### Spring Framework?
Spring Framework provides IoC and AOP to simplify enterprise application development. IoC manages object creation and dependency injection, while AOP handles cross-cutting concerns such as logging, auditing, and transaction management.

IoC
The Spring container creates and manages beans instead of developers manually instantiating objects. Dependencies are injected automatically, resulting in loose coupling and easier testing.

Dependency Injection
Spring supports constructor, setter, and field injection. Constructor injection is generally preferred because it improves immutability, testability, and dependency visibility.
Bean scopes include singleton, prototype, request, session, and application. Singleton is the default scope and creates a single bean instance for the entire Spring container.

### S3

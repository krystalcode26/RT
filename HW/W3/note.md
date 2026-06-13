### introduce what is Spring Framework
Spring is a comprehensive, open-source Java application framework centered on Dependency Injection (IoC) and Aspect-Oriented Programming, designed to make enterprise Java development simpler and more testable. 
It provides a rich ecosystem of modules — Spring MVC for web, Spring Data for persistence, Spring Security for auth — all built on a common core container. Its philosophy is to keep your business code POJO-based and let the framework handle infrastructure concerns through configuration and convention.

### What Spring Version Did You Use?
You can choose SpringBoot 2 or SpringBoot 3.I have mainly used Spring Boot 3.x in my recent projects. Spring Boot 3 requires Java 17 or later, and it is based on Spring Framework 6.
The major change in Spring 6 was the Jakarta EE namespace migration and the requirement for Java 17. I track Spring's release cadence and typically stay on the latest supported minor version.
### How Do You Define a Profile?
You define a profile using the @Profile("profileName") annotation on a @Component, @Bean, or @Configuration class, so it only loads when that profile is active. 
For properties, you create files like application-dev.yml or application-prod.yml — Spring automatically picks them up based on the active profile. You can also use @ActiveProfiles in tests to specify which profile to load during testing.
### 29. What Discovery Service Implementation Have You Used?
I've primarily used Netflix Eureka via Spring Cloud Netflix, where each microservice registers itself on startup and sends heartbeats to stay listed. I
I've also worked with Consul, which adds health checking and key-value store capabilities beyond basic discovery. In Kubernetes environments, I've relied on native K8s service discovery with Kubernetes DNS, reducing the need for a separate Eureka cluster.
### What is AOP?
Aspect-Oriented Programming is a cross-cutting concerns — behaviors that span multiple classes (logging, security, transactions, caching) — into reusable "aspects" instead of duplicating logic everywhere. 
In Spring, an aspect is a class annotated with @Aspect containing advice methods that execute at defined joinpoints (method executions matched by pointcut expressions). 
Spring AOP is proxy-based and works at the method level; for field-level interception you'd need full AspectJ weaving.
### How to Write Spring Boot to Call from Frontend to Backend and Save Data to Database?
Three layer
The frontend calls a REST endpoint via HTTP (e.g., POST /api/users) with a JSON body; 
the @RestController receives it, deserializes it into a DTO, and calls a @Service method. 
The service performs business logic, maps the DTO to a JPA @Entity, and calls the @Repository (a JpaRepository) to persist it to the database. 
The controller then returns a ResponseEntity with the saved object and a 201 Created status back to the frontend.
### Desrible Spring MVC

Spring MVC is a web framework built on the front-controller pattern
DispatcherServlet receives all requests and routes them to @Controller classes based on @RequestMapping definitions. 
The controller processes the request (usually delegating to a service), populates a model, and returns a view name for traditional MVC or returns JSON data (for REST) directly serializes the response body. 
Key components: HandlerMapping, HandlerAdapter, ViewResolver, HttpMessageConverter, and ModelAndView.


### How Do You Validate Input Data in Spring Boot?
Validation rule on model/dto/entity
I annotate DTO fields with Bean Validation constraints (@NotNull, @Size(min=2, max=50), @Email, @Pattern) 
add @Valid to the controller method parameter. 
Spring automatically validates the incoming request body before the method executes and throws MethodArgumentNotValidException if constraints are violated. I catch that in my @RestControllerAdvice and return a 400 response listing each field error with its message.

### What is Spring Boot Actuator?
Spring Boot Actuator exposes production-ready endpoints for monitoring and managing status — health checks, metrics, environment info, thread dumps, HTTP trace, and more. 
It integrates with monitoring systems like Prometheus(time series database) and Grafana(visual) through Micrometer, enabling real-time observability. 
Common endpoints include /actuator/health, /actuator/metrics, /actuator/info, and /actuator/env.

First Import dependencies
Then Configure expose endpoints: application-dev.properties -> bean/cache, etc with minimum explosion to environment
Third persist metrics in time series ex: configure Prometheus as db source

External users - Azure account/ clod resources, don’t have internal access level

### How Does Spring MVC Work?
HTTP request hits DispatcherServlet. 
HandlerMapping identifies the correct controller method. 3. 
HandlerAdapter invokes the method, resolving parameters (@RequestBody, @PathVariable). The method returns a ResponseEntity or object. 
HttpMessageConverter (Jackson) serializes it to JSON and writes to the response. 
For view-based apps, ViewResolver maps a view name to a template (Thymeleaf), renders HTML, and writes it to the response.

### What is a Controller, How Do You Use It, and How Do You Implement It?
A @Controller (or @RestController for APIs) is the web layer component that maps HTTP requests to Java methods via @RequestMapping/@GetMapping/@PostMapping etc. 
It receives request data via @RequestBody, @PathVariable, and @RequestParam, delegates to the service layer, and returns a response. 
Implementation: annotate a class with @RestController, define methods with HTTP method annotations, inject services via constructor, and return response objects or ResponseEntity<T>.

### What is WebFlux?
Spring WebFlux is Spring's async, reactive, non-blocking web framework built on Project Reactor, designed for high-concurrency scenarios with fewer threads. 
Two styles 
-	Servlet(sync thread per request tomcat 6,7,9,10 -> Tomcat supports async)
-	Reactor Library(Cannel, Group, workGroup)2020-2022 in parallel/debug/ WebFlux(Mono(Single object), Flux a group of objects) -> Java 21 -> Virtual
Instead of the traditional Servlet-based model, it uses Mono (0–1 item) and Flux (0–N items) as return types for async, event-driven processing. 
It's ideal for microservices that call many downstream APIs or handle streaming data, where blocking I/O would waste threads.

### How Do You Connect the Database in Spring Boot?
1. import appropriate starter (spring-boot-starter-data-jpa) and the JDBC driver dependency. 
2. Configure the datasource in properties / application.yml: spring.datasource.url, username, password, and spring.jpa.hibernate.ddl-auto. (conncetion pool size, connection timeout)
Another way: @Configuration + @Bean
@Value (application name)? 
3. configure data source - Spring Boot auto-configures the DataSource, EntityManagerFactory, and TransactionManager — you just define @Entity classes and @Repository interfaces extending JpaRepository.

### How Do You Handle Global Exceptions in Spring Boot?
I use @RestControllerAdvice combined with @ExceptionHandler methods to centralize all exception handling in one class rather than duplicating try-catch blocks across controllers. Each handler method maps a specific exception type to a structured error response with an appropriate HTTP status code. This keeps controllers clean and ensures consistent error response formats across the entire API.

### Spring Boot Annotations
@SpringBootApplication (combines @Configuration, @EnableAutoConfiguration, @ComponentScan), 
Class level: @RestController, @Service, @Repository, @Component, @Autowired, @Value, @Bean, @Configuration. 
Web-specific: @RequestMapping, @GetMapping, @PostMapping, @PathVariable, @RequestBody, @ResponseStatus. Testing: @SpringBootTest, @MockBean, @DataJpaTest, @WebMvcTest.


### How Does Spring IoC Work — Annotations, Injection, and Bean Types?
The IoC (Inversion of Control) container manages object creation and lifecycle — you declare beans and the container injects dependencies rather than you instantiating them manually. 
Key annotations: @Component, @Service, @Repository, @Controller mark classes as beans; @Bean defines beans in @Configuration classes; @Autowired, @Inject, and constructor injection wire dependencies. 
Dependency injection includes constructor injection, field injection, setter injection.
In general, we choose constructor injection more maintainable and prevents nullPointerException because it can instantiate at boot up time
Bean types include singleton (default and use for), prototype (create a new bean for each request), request(created for each new request), session(each of the independent application context), application, and websocket scopes)
1.	reading metadata(@SpringbootApplication(exclude=”com”))
2.	bean instantiation @lazy
3.	annotation integrated 
4.	life cycle -> container circular @Lazy

### 54. How Many Ways to Inject a Bean and Which Is Most Used?
Three ways: constructor, setter, and field injection. 
Constructor injection is most widely used in modern Spring development because it enforces immutability, makes dependencies explicit, and simplifies unit testing (no Spring context needed — just call new MyService(mockDep)). 
Field injection (@Autowired on a field) is convenient but considered an antipattern because it hides dependencies and requires reflection to test.

### By-Name vs. By-Type
By-type (@Autowired) resolves the dependency by matching the declared type — it fails if there are multiple beans of the same type without a qualifier. 
By-name (@Qualifier or @Resource) resolves by the bean's registered name, which is more explicit and avoids ambiguity when you have multiple implementations of the same interface. 
Best practice is to rely on by-type for single implementations and add @Qualifier only when there are multiple candidates.

###  Why Constructor Injection?
Constructor injection makes dependencies explicit, immutable (can be final), and guarantees the object is fully initialized prevent a NullPointerException from a missing dependency. 
It also makes unit testing easy since you can pass mock objects directly through the constructor without needing a Spring context. 
Spring recommends constructor injection, and Lombok's @RequiredArgsConstructor can auto-generate it with zero boilerplate.

### 27. What Java Version Can We Use with Spring Boot 3?
Spring Boot 3.x requires a minimum of Java 17 (the latest LTS at the time of release) and supports Java 21 fully, including virtual threads via Project Loom. 
Java 21's virtual threads can be enabled in Spring Boot 3.2+ with spring.threads.virtual.enabled=true to boost throughput for blocking workloads without switching to reactive. 
Using Java 17+ also unlocks language features like records, sealed classes, and pattern matching that pair well with Spring Boot 3 idioms.

### What is DispatcherServlet?
DispatcherServlet is the front controller of Spring MVC — it's the single entry point for all HTTP requests, routing them to the appropriate @Controller based on URL mappings.
It uses to HandlerMapping to find the right controller, HandlerAdapter to invoke it, and ViewResolver to render the response. 
In Spring Boot, it's auto-configured and registered without any XML; 
for REST APIs it works with HttpMessageConverters (e.g., Jackson) to serialize/deserialize JSON.

### introduce what is Spring Framework

Spring is a comprehensive, open-source Java application framework using IOC, DI, and AOP to make enterprise Java development simpler. 

It provides different modules — Spring MVC for web, Spring Data for persistence, Spring Security for auth and all sharing IOC container. 

It's mainly for user to keep working on business logic and let Spring handle infrastructure through configuration and convention. This makes applications easier to test, maintain, and evolve.

### What Spring Version Did You Use?

User can choose SpringBoot 2 or 3. I  mainly use Spring Boot 3.x and it's minimum require is Java 17 or later version, and it is based on Spring Framework 6.

The major change in Spring 6 was the Jakarta EE namespace migration replacing the older javax.*(extension) packages.

### How Do You Define a Profile?

There are three steps to define a profile. Define a profile, create application.properties, and test class.

I define a profile using the @Profile("profileName") annotation on a @Component, @Bean, or @Configuration class, so bean only loads when the profile is active. 

For application.properties, I create files like application-dev.yml or application-prod.yml for different environments, so Spring automatically picks them up based on the active profile. 

Then, I use @ActiveProfiles in tests to specify which profile to load during testing.

### 29. What Discovery Service Implementation Have You Used?

I've used Netflix Eureka via Spring Cloud Netflix, where each microservice registers itself on startup and sends heartbeats to stay listed.

In Kubernetes environments, I've relied on native K8s service discovery through DNS, reducing the need for a separate Eureka cluster.

Choosing between them depends on the infrastructure — Eureka fits classic VM-based deployments, while Kubernetes DNS is the natural choice in container orchestration.

### What is AOP?

AOP is a way to separate cross-cutting logic from business logic. It span multiple classes (logging, security, transactions, caching) to reusable "aspects" instead of writing duplicate code across many classes. 

There are two ways in AOP. Use @RestControllerAdvice or @Aspect class.
First I'll use  @RestControllerAdvice with @ExceptionHandler for global exception handling.
Second, I'll use @Aspect with @Pointcut (WHERE selector) containing Advice methods (WHEN) that execute at defined @JoinPoints (method executions matched by pointcut expressions). 

Advice methods includes @BEFORE,@AFTER,@AFTERTHROWING,@AFTERRETURNING,and @AROUND.

Spring AOP is proxy-based and works at the method level; for field-level you'd need full AspectJ weaving.

### How to Write Spring Boot to Call from Frontend to Backend and Save Data to Database?

To write Spring Boot, I follow three layer architecture: Controller, Service, and Repository layer.

The frontend send a REST endpoint via HTTP (e.g., POST /api/users) with a JSON body; 
  - HTTP methods include GET,POST,PUT,PATCH,DELETE.
  - HTTP request use HTTP method, url, request header, and request body.

@RestController receives request, then deserializes it into a DTO, and calls a @Service method. 

@Service performs business logic, maps the DTO to a JPA @Entity, and calls the @Repository (a JpaRepository) to persist it to the database. 

The controller then returns a ResponseEntity with the saved object and a Http status 201 (Created status) back to the frontend.

Http status includes 1 to 5. 1 information 2 success 3 redirect 4 client errors 5 server errors.

### Desrible Spring MVC

Spring MVC is a web framework built on the front-controller pattern.

DispatcherServlet is a entry point for all HTTP requests and uses HandlerMapping to find the correct @Controller method, and HandlerAdapter to invoke it while resolving parameters like @RequestBody or @PathVariable.

The controller processes the request (usually delegating to a service), populates a model.

For MVC, ViewResolver maps the returned view name and render the response as HTML templates.
For REST APIs, HttpMessageConverters (typically Jackson) serialize/deserialize the return value to JSON and write it to the ResponseBody. 

### How Do You Validate Input Data in Spring Boot?

Validation rule on model/dto/entity.

I annotate DTO fields with Bean Validation constraints (@NotNull, @NotBlank, @Size, @Pattern) 
add @Valid to the corresponding controller method parameter. 

Spring automatically validates the incoming request body before the method executes and throws MethodArgumentNotValidException if constraints are violated. 

@RestControllerAdvice will catch that and return a 400 response listing each field error with its message. 

This keeps controllers clean and ensures a consistent error format across the entire API.

### What is Spring Boot Actuator?

Spring Boot Actuator exposes production-ready HTTP endpoints to monitor and manage application status — health checks, metrics, environment info. 

I add the actuator dependency first and configure which endpoints are exposed per environment in application.yml, keeping exposure minimum in production to reduce the attack surface. 

Actuator integrates with Micrometer to push metrics to Prometheus which is a time-series database,and visualize in Grafana for real-time dashboard. 

External teams like cloud platform or DevOps can monitor application health without needing internal access.


### How Does Spring MVC Work?
Spring MVC is a web framework built on the front-controller pattern.
DispatcherServlet is a entry point for all HTTP requests.

HTTP request hits DispatcherServlet. 

HandlerMapping identifies the correct controller. 
HandlerAdapter invokes the method, resolving parameters (@RequestBody, @PathVariable). 

The controller processes the request (usually delegating to a service), populates a model.

For REST APIs, HttpMessageConverter (Jackson) serializes it to JSON and writes to the response. 
For MVC, ViewResolver maps a view name to a template and renders the response as HTML templates.

### What is a Controller, How Do You Use It, and How Do You Implement It?

A @Controller (or @RestController for APIs) is the web layer component that maps HTTP requests to Java methods via @RequestMapping/@GetMapping/@PostMapping etc. 

A @RestController combines @Controller and @ResponseBody so every method return value is written directly to the response body. 

Then inject the service layer via constructor injection, then use @RequestBody to deserialize the incoming JSON, 
@PathVariable for URL segments, and @RequestParam for query parameters. 

The method delegates business logic to the service and returns either a plain object (auto-serialized to JSON) or a ResponseEntity when I need explicit control over the HTTP status and headers.

A @Controller (or @RestController for APIs) is the web layer component that maps HTTP requests to Java methods via @RequestMapping/@GetMapping/@PostMapping etc. 

### What is WebFlux?
Spring WebFlux is Spring's async, reactive, non-blocking web framework built on Project Reactor, 
designed for high-concurrency scenarios where the traditional one-thread-per-request (Servlet) model would waste resources. 

Instead of blocking a thread while waiting for I/O, it uses event-driven processing with two types
- Mono for a single async result
- Flux for a stream of results.

It's ideal for microservices that call many downstream APIs or handle streaming data, 
since a small number of threads can serve a large number of concurrent requests. 

With Java 21, virtual threads offer a simpler alternative for many blocking workloads,
WebFlux remains the better fit when need full reactive backpressure or true streaming.

---------------------
Two styles 
-	Servlet(sync thread per request tomcat 6,7,9,10 -> Tomcat supports async)
-	Reactor Library(Cannel, Group, workGroup)2020-2022 in parallel/debug/ WebFlux(Mono(Single object), Flux a group of objects) -> Java 21 -> Virtual
Instead of the traditional Servlet-based model, it uses Mono (0–1 item) and Flux (0–N items) as return types for async, event-driven processing. 
It's ideal for microservices that call many downstream APIs or handle streaming data, where blocking I/O would waste threads.

### How Do You Connect the Database in Spring Boot?

I add spring-boot-starter-data-jpa and the appropriate JDBC driver to the Maven/Gradle dependencies, 
then configure the datasource in properties / application.yml 
with spring.datasource.url, username, password, and spring.jpa.hibernate.ddl-auto. 

Spring Boot auto-configures the DataSource, EntityManagerFactory, and TransactionManager, 
I only need to define @Entity classes and @Repository interfaces extending JpaRepository. 

I also tune the connection pool with settings like pool size and connection timeout to match the expected load.


### How Do You Handle Global Exceptions in Spring Boot?

I create a @RestControllerAdvice class that centralizes all exception handling in one place rather than duplicating try-catch blocks across controllers. 

Each @ExceptionHandler method maps a specific exception type — like MethodArgumentNotValidException or EntityNotFoundException — to a structured error response with an appropriate HTTP status code. 

This keeps every controller clean and guarantees a consistent error response format across the whole API. 

I also log exceptions at the appropriate severity level and avoid leaking internal stack traces to clients.

I use @RestControllerAdvice combined with @ExceptionHandler methods to centralize all exception handling in one class rather than duplicating try-catch blocks across controllers. 

Each handler method maps a specific exception type to a structured error response with an appropriate HTTP status code. 

This keeps controllers clean and ensures consistent error response formats across the entire API.

### Spring Boot Annotations

@SpringBootApplication is the entry-point annotation that combines @Configuration, @EnableAutoConfiguration, and @ComponentScan, bootstrapping the entire application with a single declaration. 

At the class level, @RestController, @Service, @Repository, and @Component mark beans for component scanning,
while @Configuration and @Bean define explicit bean factories. 

Web-specific annotations like @RequestMapping, @GetMapping, @PathVariable, and @RequestBody handle request routing and binding.

For testing, @SpringBootTest loads the full context, @WebMvcTest isolates the web layer, @DataJpaTest isolates persistence, and @MockBean injects test doubles.


### How Does Spring IoC Work — Annotations, Injection, and Bean Types?

The IoC (Inversion of Control) container manages object creation and lifecycle,
it reads class-level metadata (via @ComponentScanning or use @Bean in @Configuration) at startup, 
instantiates all beans in the correct order, and injects their dependencies rather than use NEW keyword. 

Beans Types are registered by type and optionally by name, and the container resolves constructor or field injection automatically. 
constructor injection more testable and prevents nullPointerException cause it can instantiate at boot up time.
Setter injection is useful for optional.
Field injection (@Autowired on a field) is convenient but the downside is it hides dependencies and requires reflection to test.

The default scope is singleton, meaning one shared instance per application context; other scopes like prototype(create a new bean for each request), request(created for each new request), and session(each of the independent application context) create new instances with different lifetimes. 

The container also manages lifecycle callbacks and can handle circular dependencies with @Lazy if truly unavoidable.


### 54. How Many Ways to Inject a Bean and Which Is Most Used?

Three ways: constructor, setter, and field injection. 

Constructor injection is most widely used in modern Spring development because it enforces immutability, makes dependencies explicit, and simplifies unit testing (no Spring context needed — just call new MyService(mockDep).

Setter injection is useful for optional

Field injection (@Autowired on a field) is convenient but the downside is it hides dependencies and requires reflection to test.

### By-Name vs. By-Type

By-type (@Autowired) matching the declared type of dependency.
If there are multiple beans of the same type use by-name to resolve it. 

By-name (@Qualifier or @Resource) check bean's registered name to avoid ambiguity when you have multiple implementations of the same interface. 

Use case: rely on by-type for single implementations and add @Qualifier only when there are multiple candidates. Also can use @Primary to declare as a primary bean.

###  Why Constructor Injection?

Constructor injection makes dependencies explicit, immutable (can be final), and guarantees the object is fully initialized prevent a NullPointerException from a missing dependency. 

It also makes unit testing easy since you can pass mock objects directly through the constructor without needing a Spring context. 
Spring recommends constructor injection, and Lombok's @RequiredArgsConstructor can auto-generate it with zero boilerplate.

### What Java Version Can We Use with Spring Boot 3?
Spring Boot 3.x requires a minimum of Java 17 (the latest LTS at the time of release) and support update versions.
Using Java 17+ also unlocks language features like records, sealed classes, and pattern matching that pair well with Spring Boot 3.

Java 21's virtual threads can be enabled in Spring Boot 3.2+ with spring.threads.virtual.enabled=true to boost throughput for blocking workloads without switching to reactive. 


### What is DispatcherServlet?
DispatcherServlet is the entry point of Spring MVC for all HTTP requests, 
routing them to the appropriate @Controller based on URL mappings.

It uses to HandlerMapping to find correct controller, HandlerAdapter to invoke it with resolved parameters(@RequestBody, @PathVariable).

For MVC, ViewResolver maps the returned view name and render the response as HTML templates.
For REST APIs, HttpMessageConverters (typically Jackson) serialize/deserialize the return value to JSON and write it to the ResponseBody. 

In Spring Boot, it's auto-configured and registered without any XML; 


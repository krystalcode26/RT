### PR

https://github.com/krystalcode26/Spring_Boot_Project_1/branches

### introduce what is Spring Framework

Spring is a comprehensive, open-source Java application framework using IOC, DI, and AOP to make enterprise Java development simpler and handle infrastructure through configuration.

It provides different modules — Spring MVC for web, Spring Data for persistence, Spring Security for auth and all sharing IOC container. 

IoC starts from @SpringBootApplication which combines @EnableAutoConfiguration, @Configuration, and @ComponentScan to register beans.

Use controller to map HTTP requests to Java methods via REST API design
@RestController receives request, then deserializes it into a DTO, and calls a @Service method. 
business logic layer, maps the DTO to a JPA @Entity, and calls the @Repository – DAO layer (a JpaRepository) to persist it to the database. 
If a class does not clearly belong to these three layers, I can use @Component.

For third-party objects you don't own, you define them explicitly using @Bean methods inside a @Configuration class. For bean scopes, I can use @Scope. Common scopes include singleton, prototype, request, session, and application. The default scope is singleton.

For DI, I prefer constructor injection over setter or field injection since it makes dependencies explicit and avoid null pointer exceptions, 
and I use @Autowired to wire them By-type and If there are multiple beans of the same type use use @Qualifier by-name or @Primary to resolve conflicts.

On the AOP and exception handling side, there are two ways.
One is @RestControllerAdvice with @ExceptionHandler for centralized error handling.
Another one I use @Aspect with @Pointcut to select WHERE to define and advice annotations to declare when like @Before, @After, @AfterReturning, @AfterThrowing, and @Around to intercept execution at different points. 

For REST API design, I use @RequestMapping at the class level and @GetMapping, @PostMapping, @PutMapping, @DeleteMapping at the method level, pulling request data with @RequestParam, @PathVariable, @RequestHeader and @RequestBody.

Finally, for validation I trigger it with @Valid on request bodies and define constraints directly on fields using @NotNull, @NotBlank, and @Size.



### Staff Entity

This file defines the Staff entity.
An entity represents a database table in Java.

@Entity tells JPA that this class should be mapped to a database table.
@Table(name = "staffs") means the table name is staffs.

The id field is the primary key.
@GeneratedValue(strategy = GenerationType.IDENTITY) means the database will automatically generate the ID.

firstName, lastName, and email are columns in the table.
For email, nullable = false means it is required, and unique = true means no two staff members can use the same email.

Lombok annotations like @Getter, @Setter, @AllArgsConstructor, and @NoArgsConstructor reduce boilerplate code by generating getters, setters, and constructors automatically.

### StaffDto

This file defines the StaffDto.
DTO means Data Transfer Object.

It is used to transfer staff data between the client and the server.
We use DTOs instead of exposing the entity directly, because DTOs help separate API data from database structure.

This DTO contains id, firstName, lastName, and email.

The validation annotations check the request data before it reaches the service layer.

@NotBlank means the field cannot be empty or only spaces.
@Size controls the minimum and maximum length.
@Email checks whether the email format is valid.

For example, first name and last name must be between 2 and 50 characters, and email must be a valid email address.


### StaffMapper

This file defines the StaffMapper.

The mapper is used to convert between Staff entity and StaffDto.

mapToStaffDto converts a Staff entity into a StaffDto.
This is usually used when sending data back to the client.

mapToStaff converts a StaffDto into a Staff entity.
This is usually used before saving data into the database.

The reason we use a mapper is to keep conversion logic in one place.
It also keeps the entity layer and DTO layer separated.

Both methods are static, so we can call them directly using StaffMapper.mapToStaffDto() or StaffMapper.mapToStaff() without creating a mapper object.

### StaffRepository

This file defines the StaffRepository.
It is an interface that extends JpaRepository<Staff, Long>.
Staff means this repository manages the Student entity.
Long means the primary key type is Long.

By extending JpaRepository, we automatically get common database methods like save, findById, findAll, deleteById, and count.

We do not need to write the implementation manually.
Spring Data JPA creates the implementation automatically at runtime.

This repository belongs to the data access layer.
The typical flow is controller, service, repository, then database.

### StaffController

This file defines the StaffController.
@RestController tells Spring that this class handles REST API requests and automatically converts Java objects into JSON responses.

@RequestMapping("/api/staffs") sets the base URL for all endpoints in this controller.

@AllArgsConstructor is a Lombok annotation that generates a constructor for dependency injection.
The controller depends on StaffService, which contains the business logic.

REST Controller that exposes CRUD APIs for the Staff resource.

The base URL is /api/staffs.
POST /api/staffs creates a new staff member.
GET /api/staffs/{id} retrieves a staff member by ID.
GET /api/staffs retrieves all staff members.
PUT /api/staffs/{id} updates a staff member.
DELETE /api/staffs/{id} deletes a staff member.

The controller receives HTTP requests, validates input using @Valid, calls the service layer for business logic, and returns responses wrapped in ResponseEntity with appropriate HTTP status codes.

### StaffServiceImpl

This file is the service implementation for staff business logic.

It implements the StaffService interface and provides the actual logic for creating, reading, updating, and deleting staff records.

@Service - tells Spring this class is a service-layer bean.
@RequiredArgsConstructor 
  - @RequiredArgsConstructor is from Lombok. It creates a constructor for all final fields, so   Spring can inject StaffRepository.
  - 
public class StaffServiceImpl implements StaffService

private final StaffRepository staffRepository;
- This repository is used to communicate with the database.

### S3
https://rt-bucket06.s3.us-east-2.amazonaws.com/springboot.mp4

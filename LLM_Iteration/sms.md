# Spring Boot

flowchart TB
    Client[Client / Postman / Frontend]
    Controller[Controller Layer]
    Service[Service Layer]
    Repository[Repository Layer]
    DB[(PostgreSQL Database)]

    Client -->|HTTP JSON| Controller
    Controller --> Service
    Service --> Repository
    Repository --> DB


### REST API
The controller exposes endpoints under /api/students:

HTTP Method	URL	Action
POST - Create
GET - Get one/Get all
PUT - fully Update
DELETE - Delete
The client sends and receives JSON

@RestController marks the class as a REST endpoint handler.
@RequestMapping, @GetMapping, @PostMapping, etc. map URLs and HTTP methods to Java methods.

### DTO vs Entity
Two similar classes exist on purpose:

Student (Entity) — tied to the database (@Entity, @Table, @Column)
StudentDto (DTO) — plain data object for the API
Why both?

The API shape doesn’t have to match the database exactly
You avoid exposing internal DB details
You can change the DB without breaking the API
StudentMapper converts between them.


### JPA / Hibernate (ORM)
JPA (Java Persistence API) lets you work with database rows as Java objects.

Student is annotated with:

@Entity — this class is a DB table
@Table(name = "students") — table name
@Id + @GeneratedValue — auto-increment primary key
@Column — column mapping
Hibernate (JPA implementation) creates/updates the students table because of:

spring.jpa.hibernate.ddl-auto=update
So you define the model in Java; the schema follows from that.


### Spring Data JPA Repository
public interface StudentRepository extends JpaRepository<Student, Long>
You don’t write SQL for basic CRUD. Spring generates implementations for:

save()
findById()
findAll()
deleteById()
The repository is the data access layer.


### Service layer
The service sits between controller and repository:

Controller: HTTP concerns (status codes, paths)
Service: business rules and orchestration
Repository: database operations
Example in StudentServiceImpl:

studentRepository.findById(id)
    .orElseThrow(() -> new ResourceNotFoundException("Student does not exist..."));
If the student isn’t found, it throws a custom exception instead of returning null.

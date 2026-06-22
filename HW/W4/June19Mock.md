1. What is Singleton Design Pattern?
Singleton is a design pattern that ensures only one instance of a class exists throughout the application.
Ex: shared resources such as configuration managers, logging services, and cache managers.
The implementation usually includes a private constructor, a static instance variable, and a public getInstance() method.
 
3. Where can we set CORS (backend or frontend or both)?
CORS stands for Cross-Origin Resource Sharing and is used when the frontend and backend run on different domains, ports, or protocols. configure CORS on both the frontend and backend, but in production I prefer handling it on the backend using @CrossOrigin, WebMvcConfigurer, or Spring Security configuration. 
This allows centralized control of which origins are allowed to access the APIs.
 
4. Can you write hints in Hibernate?
Yes. Hibernate hints are used to influence how Hibernate executes queries and interacts with the database. 
Ex: query timeout, fetch size, read-only mode, and cache usage. However, in practice I rarely use Hibernate hints because SQL tuning, indexing, and caching usually provide more significant performance improvements.
Example: query.setHint("org.hibernate.readOnly", true);
 
5. Monolithic vs Microservices
A monolithic application contains all business logic in a single deployable unit, typically organized into controller, service, and repository layers. 
A microservice architecture splits the application into multiple independent services that communicate through APIs or messaging systems. 
Monoliths are simpler to develop initially, while microservices provide better scalability, fault tolerance, and independent deployment.
 
6. Will you choose Stored Procedures or Java Hibernate Logic?
In most cases I prefer Java and Hibernate logic because it is easier to maintain, test, version-control, and migrate across different databases. 
Stored procedures can offer better performance for complex database operations because the execution happens inside the database server. 
I usually reserve stored procedures for heavy data-processing workloads or database-specific optimizations.
 
7. I have a Person table (name, age). Return the oldest person's name and age.
SELECT name, age
FROM Person
ORDER BY age DESC
LIMIT 1;
I sort the records by age in descending order and return the first row. 
This gives me the oldest person and their age. 
If multiple people share the same maximum age, I may use additional logic depending on the business requirement.
 
8. SQL Coding: 
Order table and Customer table, find the largest price in 10 years and return price + customer name
Assumption:
•	orders(customer_id FK, price, order_date) 
•	customers(customer_id PK, customer_name) 
SELECT c.customer_name,
       o.price
FROM orders o
JOIN customers c
    ON o.customer_id = c.customer_id
WHERE o.order_date >= CURRENT_DATE - INTERVAL '10 years'
ORDER BY o.price DESC
LIMIT 1;
First, I join the order table with the customer table using the customer ID. 
Then I filter orders from the last 10 years, sort by price descending, and return the top record. Before coding, I would clarify the table relationship and date column format with the interviewer.
 
9. What annotations and configurations did you use in Eureka?
I used @EnableEurekaServer on the Eureka Server and @EnableDiscoveryClient on microservices that need service registration and discovery. In application.yml, I configured the Eureka Server URL using eureka.client.service-url.defaultZone. Eureka allows services to register dynamically and discover other services without hardcoding IP addresses and ports.
Example:
@EnableEurekaServer
@SpringBootApplication
public class EurekaServerApplication {
}
eureka:
  client:
    service-url:
      defaultZone: http://localhost:8761/eureka
 
10. What's your responsibility in a Microservice Architecture?
My primary responsibility is developing and maintaining business modules such as Order Service, User Service, or Payment Service. Besides writing business logic, I also work with infrastructure components such as API Gateway, Eureka, Config Server, monitoring tools, and database integration. I monitor service health, troubleshoot production issues, and ensure my services are properly registered, secured, and scalable.
 
11. If A → B → C and some services return 500 errors, what should we do?
The first priority is to stop the impact on users by redirecting traffic to healthy instances, activating standby services, or triggering fallback logic through a circuit breaker. After stabilizing the system, I would use monitoring tools such as Kibana, Grafana, or distributed tracing to identify which service is generating the 500 errors. Once the root cause is found, I would fix the issue and validate recovery before returning traffic to normal.
 
12. How do you secure communication in Microservices?
I secure service-to-service communication using HTTPS/TLS encryption to prevent man-in-the-middle attacks. I also use authentication and authorization mechanisms such as OAuth2, JWT, or API Gateway security policies. Additionally, internal services are usually deployed within private networks and only the API Gateway is exposed to the public internet.
 
13. When do you use a Message Queue between Services?
I use a message queue when services do not need an immediate response or when I want to decouple systems. 
Ex: email notifications, order processing, audit logging, and asynchronous event handling. 
Message queues such as Kafka or RabbitMQ improve scalability, reliability, and fault tolerance by allowing services to process messages independently.
 
14. SQL: JOIN, GROUP BY, and COUNT
JOIN is used to combine data from multiple tables based on a related column such as a primary key and foreign key. 
GROUP BY is used to group rows that share the same value, and COUNT is an aggregate function used to count records within each group. 
These are commonly used together to generate summary reports.
Example:
SELECT d.dept_name,
       COUNT(*)
FROM employee e
JOIN department d
    ON e.dept_id = d.dept_id
GROUP BY d.dept_name;
 
15. SQL Coding: emp(dept_id FK) table and dept table
Return Employee Name and Department Name
SELECT e.emp_name,
       d.dept_name
FROM employee e
JOIN department d
    ON e.dept_id = d.dept_id;
    
Count Employees per Department
SELECT d.dept_name,
       COUNT(*) AS employee_count
FROM employee e
JOIN department d
    ON e.dept_id = d.dept_id
GROUP BY d.dept_name;

Find Department with Most Employees
SELECT d.dept_name,
       COUNT(*) AS employee_count
FROM employee e
JOIN department d
    ON e.dept_id = d.dept_id
GROUP BY d.dept_name
ORDER BY employee_count DESC
LIMIT 1;

Since dept_id in the employee table is a foreign key referencing the department table, I would use a JOIN to combine employee and department information. 
If aggregation is required, I would use GROUP BY together with functions such as COUNT, AVG, or MAX. The exact query depends on whether the requirement is detail data or summary data.

S3:https://rt-bucket06.s3.us-east-2.amazonaws.com/June19.mp4

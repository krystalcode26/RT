Homework 8
1.	TCP 3-way handshaking
Before two computers can exchange data reliably over TCP, they perform a three-way handshake to establish a connection. Think of it like a formal greeting before a conversation:
•	SYN (Synchronize): The client sends a SYN packet to the server, essentially saying 'I want to connect. Here is my starting sequence number.'
•	SYN-ACK (Synchronize-Acknowledge): The server responds with a SYN-ACK packet, saying 'I got your request and I agree. Here is my starting sequence number. Acknowledge mine.'
•	ACK (Acknowledge): The client sends a final ACK packet confirming 'I got your sequence number. Let's talk.' The connection is now established.

This handshake ensures both sides are ready and have agreed on initial sequence numbers for tracking data order. After this, data can flow in both directions. When done, a similar four-step process (FIN/ACK) closes the connection.

2. TCP vs UDP
TCP (Transmission Control Protocol): TCP is connection-oriented, reliable, and ordered. It guarantees that every packet arrives, arrives in order, and is error-checked. If a packet is lost, TCP automatically retransmits it. This reliability comes at a cost: overhead from handshaking, acknowledgments, and flow control makes TCP slower. 
Use case: web browsing (HTTP), email, file transfers, anything where data integrity is critical.

UDP (User Datagram Protocol): UDP is connectionless and has no guarantees. Packets may be lost, arrive out of order, or be duplicated — UDP doesn't care. But because it skips all that overhead, it's much faster. Use UDP for: video streaming, online gaming, VoIP calls, DNS lookups — cases where speed matters more than perfection, and a dropped frame is better than a delayed one.

3. What is Tomcat
Apache Tomcat is an open-source web server and servlet container developed by the Apache Software Foundation. In simpler terms, it's the software that runs Java web applications. When you write a Java web app using Servlets or JSP (JavaServer Pages), Tomcat provides the runtime environment to execute that code and handle HTTP requests. It's not a full-blown application server like JBoss or WebLogic — it focuses on running Java web components. Spring Boot applications, for example, often embed Tomcat as their default server so the app can run as a standalone program without a separate Tomcat installation.

4. Basic Components of Tomcat
•	Server: The top-level component representing the entire Tomcat instance. There is one Server per JVM process.
•	Service: Contains a set of Connectors and an Engine. A Server can have multiple Services.
•	Connector: Handles incoming connections on a specific port and protocol (like HTTP on port 8080 or HTTPS on 8443). It accepts requests and passes them to the Engine.
•	Engine: The core request processing component. It receives requests from Connectors and routes them to the correct virtual Host.
•	Host: Represents a virtual host (a domain name). One Engine can have multiple Hosts, allowing Tomcat to serve multiple websites.
•	Context: Represents a single web application deployed in Tomcat. Each Context maps to a specific path (like /myapp).
•	Valve: A pipeline component that can intercept and process requests before they reach the application — used for logging, authentication, etc.

5. What is a Web Server
A web server is software (and sometimes hardware) that accepts HTTP/HTTPS requests from clients and serves responses — typically static files like HTML, CSS, images, and JavaScript. When a browser requests a webpage, the web server finds the file and sends it back. Examples of popular web servers include Apache HTTP Server, NGINX, and Microsoft IIS.
A web server is different from an application server. A web server mainly delivers static content, while an application server runs dynamic code (like Java, Python, or Node.js) and may connect to databases. In practice, a web server often sits in front of an application server, handling static files itself and forwarding dynamic requests to the app server.

6. Three-Tier Architecture
Three-tier architecture is a software design pattern that separates an application into three logical layers:
Presentation Tier (Frontend): What the user sees and interacts with — the UI. This is your web browser, mobile app, or desktop interface. It sends requests to the middle tier and displays results.

Application Tier (Backend / Business Logic): The middle layer that processes requests, applies business rules, and coordinates data access. This is where your application code (Java, Python, Node.js, etc.) runs.

Data Tier (Database): The bottom layer that stores and retrieves data — your SQL or NoSQL database. Only the application tier talks directly to the data tier; the presentation tier never accesses the database directly.
This separation makes each layer independently scalable, maintainable, and replaceable. You can swap the database without changing the frontend or redesign the UI without touching business logic.

7. OSI Model — 7 Layers
The OSI (Open Systems Interconnection) model is a conceptual framework that describes how data travels from one computer to another over a network. It has 7 layers, each with a specific job:
Physical Layer: Deals with the actual physical transmission of bits — electrical signals over cables, light pulses over fiber, or radio waves for Wi-Fi. Defines voltages, pin layouts, and timing.

Data Link Layer: Responsible for transferring data between directly connected devices on the same network (like Ethernet). Handles framing, MAC addresses, and error detection at the link level. Switches operate here.

Network Layer: Handles routing packets across multiple networks to reach the destination. IP addresses live here. Routers operate at this layer, deciding the best path for each packet.

Transport Layer: Ensures reliable data delivery end-to-end. This is where TCP and UDP live. TCP adds sequencing, error correction, and flow control. Port numbers are also defined here.

Session Layer: Manages sessions (ongoing conversations) between applications. It establishes, maintains, and terminates connections. Handles session recovery if a connection drops.
Presentation Layer: Handles data format translation, encryption, and compression. It ensures the data from the application layer is in a format the receiving application can understand (e.g., converting character encodings, encrypting data with TLS).

Application Layer: The layer closest to the end user. This is where application-level protocols live: HTTP for web browsing, SMTP for email, FTP for file transfer, DNS for name resolution. This is what developers interact with most directly.

A helpful mnemonic for the layers bottom to top: Please Do Not Throw Sausage Pizza Away (Physical, Data Link, Network, Transport, Session, Presentation, Application).

8. Video Notes (Videos 1–19)
Notes placeholder — to be filled in based on specific video content from your course materials. The following structure can be used for each video:

- 1  Spring build applications easier
     Spring Framework provide some features to makes complex and heavy Java easier.
     Spring includes manu projects Spting Boot, Spring Framework, Spring Cloud, Spinrg     Data, Spring AI...
     
- 2 E-commerse website
    Add/update/delete, sort by category, search bar
    Frontend and Backend different package
- 3 Framework: Spring 6
    Language: Java language
    OOPs concept, Thread, Collection, Exception
    Build tool: Maven / Gradle
    Database: JDBC
    Hibernate -> Spring Data JPA
    XML, Json
    IDE(Integrated Development Environment): VScode, IntelliJ(ultimate version),   Eclipse
    JDK version >= 17, LTS version is better -> 21
- 4 Dependency Injection & IOC
    Three layer:
      - Controller layer
        create the object of service to communicate with service
      - Service layer (Buisness logic)
        create object of repository to communicate with repository
      - Repository layer

  General creating objects incldue the whole cycle of it -> create, manage, and destroy it.
  Instead insert NEW keyword follow IOC principle and use DI to create the object.
  
    IOC: Inversion of Control principle - use DI to implement.
  
    DI: Dependency Injection design pattern.
        - Constructor Injection: pass the reference of service
        - Setter Injection: create a setter for service
        - Field Injection(Not recommend): loose coupling, not able to mock test.
              Don't have a concrete implementation of one class in the other, you code for interfaces.

- 5 Spring Boot: Opinionated Framework
    It is better than Spring Framwork. Convention over Configuration.
    General before run:
     - need to talk to the framework -> Configuration
     - need Server Ex: Tomcat
       
  spring initializer -> start.spring.io
  .war: war archive, need to push .war to Tomcat to run it
  .jar: it has embedded Tomcat, so no need to install extra Tomcat.

  Dependencies:
    - choose only the thing you need. Ex: Spring Web, Lombok ....
    - @RestController, @RequestMapping

-  6 Dependency Injection using Spring Boot
     Spring has its own container inside JVM, which is IOC container (all objects inside it).
     SpringApplicaton.run() -> create IOC container

     General create NEW object inside JVM to call method.
     How to get reference to object?
     
     ApplicationContext context = SpringApplicaton.run();
     getBean() get the object you want.
     use annotation at class level -> @Component
     But Spring poject use IOC, DI for that.
   
-  7 Autowiring - goes by type of class, it will search the type of class
     Reference to object:
     Instance variables by default set to null.
     New keyword
     use DI
        - Field Injection
          add @Component annotation at class level
          ApplicationContext interface

          add @Autowired at field level
          @Autowired - connect two classes and get instances
        - Constructor Injection
          add @Autowired at constructor level
          @Autowired - connect two classes and get instances

   Loose couping -> Interfaces
   If there are multiple objects extend the same interface? One is laptop and one is desktop type of class. Both of them has @Component at class level and
   @Autowired at main class field level.
   -> need to add certain annotation. @Primary at class Then Autowiring will goes to this one.
   -> Or add @Qualifier("laptop") -> Then Autowiring will goes to the class with laptop type.
-  8 Spring with Boot
     - create new project in IntelliJ and choose Maven Archetype rather than Spring.
     - Archetype: projects structure.
     - Need to add dependency manually and create configuration file.
        - Add Spring Dependency -> Maven Repository search for spring context and copy dependencies into pom.xml. Remember reload Maven.
        - Add to main -> ApplicatonContext context = new ClassPathXmlApplicationContext(); -> create container
          Add xml configuration -> new ClassPathXmlApplicationContext("spring xml")
          Ex: ApplicatonContext context = new ClassPathXmlApplicationContext("spring xml");
              Dev obj  = (Dev) context.getBean("dev)
              obj.build() -> call the build() in Dev.java
        - create resources folder and create a file spring.xml
          
-  9 Spring XML Config
     - Document type definition
     - search for bean configuration on google and add definition to spring.xml.
   
     - spring.xml:
          <beans (bean configuratoin.....)>
               <bean id ="dev" class="com.name.Dev"> </bean>
               <bean id ="lap1" class="com.name.Laptop"> </bean>
          </beans>
     
-  10 Constructor and Setter Injection in Spring
      Setter injection:
      spring.xml: set <property> inside <bean> Ex: <property name = "age" value = "18" />
      setter, getter in Dev.java

      For object laptop
        - spring.xml: specify property for laptop in spring.xml inside <bean></bean>
          Ex: <property name="laptop" ref="lap1" />
        - setter, getter of laptop in Dev.java

      Constructor injection:
      spring.cml" set <constructor> inside <bean> Ex: <constructor-arg value="14" />
      set constructor in Dev.java

       for object laptop
        - spring.xml: specify property for laptop in spring.xml inside <bean></bean>
          Ex: <constructor-arg ref="lap1" />
        - set constructor of laptop in Dev.java

       
- 11 Autowire in Spring
     Multiple objects implements the same interface
     Ex: Class laptop, desktop implements Computer interface
  
     - spring.xml: specify property for laptop in spring.xml inside <bean></bean>
          use autowire byName/byType
          Ex: <bean id ="dev" class="com.name.Dev" autowire="byName/byType"> </bean>
              <bean id="lap1" class="com.name.Laptop"> </bean>
              <bean id="desk" class="com.name.Desktop"> </bean>

              if id use similar, you can add primary as the one you want to refer
              <bean id="com" class="com.name.Laptop" primary="true"> </bean>
              <bean id="com1" class="com.name.Desktop"> </bean>

              - byType in spring.xml
              Ex: <bean id ="dev" class="com.name.Dev" autowire="byType"> </bean>
              if use byType in App.java -> main function
              use Dev.class, so it refers to the type Dev
              Dev odj = context.getBean(Dev.class)
          
- 12

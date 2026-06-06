Homework 8

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




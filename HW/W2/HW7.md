# HW7

### 1. Optimized Singleton Pattern
The Singleton pattern ensures a class has only one instance throughout the application's lifetime. 
The optimized version uses double-checked locking to be both thread-safe and efficient. Locks only when the instance is null — after creation, no locking overhead.
First check — avoids locking every time after instance is created (performance)
Second check — prevents duplicate creation if two threads both passed the first check simultaneously.

Why volatile?
Without volatile, the JVM can reorder instructions — a thread might see a partially constructed object.
volatile prevents instruction reordering and ensures the fully constructed object is visible to all threads.


public class Singleton {

    // 'volatile' ensures all threads see the most up-to-date value
    // Without it, a thread could see a partially constructed object
    private static volatile Singleton instance;

    // Private constructor prevents anyone from calling 'new Singleton()'
    // from outside this class
    private Singleton() {}

    // Public static method — the only way to get the instance
    public static Singleton getInstance() {

        // First check: avoid entering synchronized block if already created
        // This makes repeated calls fast once initialized
        if (instance == null) {

            // Synchronized block: only one thread enters at a time
            // Prevents two threads from both seeing null and both creating instances
            synchronized (Singleton.class) {

                // Second check: in case another thread created it
                // between the first check and entering the synchronized block
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }

        // Return the one and only instance
        return instance;
    }
}

### 2. Design Pattern Use Cases by Industry
Singleton
Banking: A central configuration manager that holds database connection credentials and interest rate tables. Every part of the banking system reads from the same configuration object, ensuring consistency. Only one instance should exist to prevent conflicting settings.
E-Commerce: A shopping cart session manager or a global logging service that tracks every transaction. One instance manages all log writes to avoid file corruption from concurrent writers.
Health Insurance: A master policy rules engine that holds the current coverage rules and formulary (approved drug list). It's loaded once at startup and shared across all claims processing threads.

Factory
Banking: A payment processor factory that creates the right payment handler (CreditCardProcessor, WireTransferProcessor, ACHProcessor) based on transaction type. The caller just says 'give me a payment processor' without knowing the implementation details.
E-Commerce: A notification factory that creates Email, SMS, or Push notification objects depending on the user's communication preference setting. New channels can be added without changing the calling code.
Health Insurance: A claim factory that creates the correct claim object (MedicalClaim, DentalClaim, VisionClaim, PharmacyClaim) based on claim type codes in the incoming data feed.

Builder
Banking: Building a complex loan application object step by step: set borrower info, then add co-signers, then add collateral, then add loan terms. A builder ensures the object isn't used until fully and correctly constructed.
E-Commerce: Constructing a purchase order with many optional fields: shipping address, billing address, gift wrapping, promo codes, multiple line items. The builder pattern makes this readable and prevents invalid states.
Health Insurance: Creating a complex insurance policy object with optional riders (dental, vision, mental health), coverage levels, deductibles, and beneficiaries. A builder guides the construction step by step and validates completeness before issuing.

Proxy
Banking: A security proxy around account access that checks whether the authenticated user has permission to view or modify the account before delegating to the real account service. It adds a security layer transparently.
E-Commerce: A caching proxy for product catalog data. The proxy checks if the product details are cached before making an expensive database call. If cached, it returns the cached version immediately — the caller doesn't know the difference.
Health Insurance: A lazy-loading proxy for patient medical records. Fetching full records is expensive, so the proxy returns a lightweight placeholder object. The full record is only loaded from the database when actually accessed.

### 3. What is Reflection
Reflection is the ability of a program to examine and modify its own structure and behavior at runtime. In Java, the java.lang.reflect package allows you to inspect a class's methods, fields, and constructors even if you don't know the class at compile time. You can call methods by name (as a String), create instances of classes dynamically, and change the accessibility of private fields.
For example, with reflection you could write: Class.forName("com.example.Dog").getDeclaredMethod("bark").invoke(instance) — calling the bark() method on a Dog without ever importing the Dog class directly.
Reflection is used heavily by frameworks like Spring (dependency injection), Hibernate (ORM mapping), and JUnit (test discovery). The trade-off is that it's slower than direct calls and can break encapsulation, so it's used sparingly in application code.

### 4. HTTP Status Codes
1xx - information
2xx — Success
Ex: 200 OK: The request worked exactly as expected. The server found what was asked for and is returning it. This is the standard successful response for GET requests.
3xx - Redirect
4xx - Client Errors
  Ex: 404 Not Found: The server can't find the requested resource. Either the URL is wrong, the resource was deleted, or it never existed.
5xx — Server Errors
  Ex: 500 Internal Server Error: Something went wrong on the server side — an unhandled exception, a bug, a crashed service. It's not the client's fault. The client can try again later, but the server team needs to investigate.

### 5. What is HTTP
HTTP stands for HyperText Transfer Protocol. It is the foundation of data communication on the World Wide Web. 
HTTP is an application-layer protocol that defines the rules for how messages are formatted and transmitted between clients (like browsers) and servers. 
It is stateless — each request is independent, and the server doesn't remember previous requests from the same client (which is why cookies and sessions exist to add state on top of HTTP).
HTTPS is HTTP with encryption (via TLS/SSL), which protects data in transit from eavesdropping and tampering. HTTP typically uses port 80; HTTPS uses port 443.

### 6. HTTP Methods: GET, POST, PUT, DELETE, PATCH
GET: Fetches a resource. Parameters are passed in the URL query string. Example: GET /users/123 retrieves user 123.
POST: Sends data to the server to create a new resource. 
      The data is in the request body. 
      Ex: POST /users creates a new user with the provided details.
PUT: Replaces an entire resource with the provided data. 
      If the resource exists, it's completely overwritten. 
      If it doesn't exist, it may be created. 
      Ex: PUT /users/123 replaces all of user 123's data.
DELETE: Removes a resource. Example: DELETE /users/123 deletes user 123.
PATCH: Partially updates a resource — only the fields you send are changed, 
      everything else stays the same. 
      Ex: PATCH /users/123 with {"email": "new@email.com"} updates only the email.

### 7. POST vs PATCH
POST is used to create a new resource. The server assigns the ID. 
POST is not idempotent (calling it twice creates two resources). 

PATCH is used to partially update an existing resource — you only send the fields that need to change. 
PATCH may or may not be idempotent depending on implementation.

### 8. POST vs PUT
Both can create or update, but the difference is in completeness and where the ID comes from. 
PUT is idempotent (calling it multiple times has the same effect as calling it once).
PUT replaces the entire resource at a specific ID — you provide the full object and the target URL 
(e.g., PUT /users/123). 

POST is not idempotent.
POST creates a new resource where the server assigns the ID — you post to a collection URL 
(e.g., POST /users). 


### 9. What is Idempotent? Which Methods Are Idempotent?
An operation is idempotent if performing it multiple times produces the same result as performing it once. 
In other words, repeating the request doesn't cause additional side effects after the first call.

For example: DELETE /users/123 called three times still results in user 123 being deleted — the second and third calls have no additional effect (the user is already gone).

Idempotent HTTP methods: GET, PUT, DELETE, HEAD, OPTIONS.
Non-idempotent: POST (each call creates a new resource), and in some implementations PATCH.
Why does this matter? Idempotency is important for reliability.
If a network request times out and you retry it, an idempotent operation is safe to repeat without causing duplicate data or unintended side effects.




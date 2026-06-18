### What annotations used in spring?
Spring Annotations are used to configure and wire components without XML. 
Common ones include @Component, @Service, @Repository, @RestController, @Autowired, @Bean, @Transactional, and @RequestMapping.

### New feature java 17
Java 17 introduced sealed classes, records, pattern matching for instanceof, text blocks, and enhanced switch expressions.

### equals() vs ==
== checks if two variables point to the same memory location, 
while equals() checks if two objects have the same value. 
If you don't override equals(), it defaults to reference comparison just like ==.

### how to inject bean with the same type
Injecting beans of the same type is done using @Qualifier("beanName") along with @Autowired to tell Spring exactly which bean to use.

### OOP principles

### What is Executor library 
is a Java framework for managing thread pools. Instead of creating threads manually, you submit tasks and the Executor handles thread lifecycle and reuse automatically.
Core vs Max pool — core pool threads stay alive even when idle, while max pool defines the upper limit when load increases. If core size is 0, threads are only created on demand and recycled when done.

### Java 21 features
Java 21 brought virtual threads for high concurrency, sequenced collections for ordered access, records for immutable data, pattern matching for switch, and an upgraded ZGC garbage collector.
Java LTS versions are 8, 11, 17, and 21. Most production systems run on one of these long-term support releases.

### What is thread local?
ThreadLocal gives each thread its own private copy of a variable so threads never share it, removing the need for synchronization. It is commonly used for storing per-request user data in web applications.

### java version

### Injection types
Injection types in Spring are constructor injection (recommended), setter injection, and field injection using @Autowired directly on a field.

### Have you used PATCH
PUT replaces the entire resource, PATCH only updates the specific fields that changed.
PUT — idempotent

Every time you send the same PUT request, it replaces the entire row with the exact same data. The result is always the same regardless of how many times you call it.
PATCH — not guaranteed idempotent

PATCH only updates specific fields, so the result depends on the current state of the data. For example, if PATCH increments a counter or appends to a list, calling it multiple times produces different results each time.


### Garbage Collector 
automatically frees memory from objects no longer in use. 
The JVM runs it in the background so you never manage memory manually. 
Common collectors include G1 (default), ZGC, and Parallel GC.

How to configure
I configure the GC type via JVM args at deployment time, for example -XX:+UseZGC or -XX:+UseG1GC. This is typically a one-time configuration written into your deployment script — developers don't need to touch it again unless switching platforms.

### S3

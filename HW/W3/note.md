#### JVM Tuning
To optimize the JVM tuning starts with heap memory — always set -Xms equal to -Xmx in production to avoid costly resize pauses, 
and use -XX:MaxRAMPercentage=75.0 in containers so the JVM respects Docker memory limits. 
Choose the right garbage collector for your workload — G1GC for most production apps with -XX:MaxGCPauseMillis=200 to target pause time, 
or ZGC for low latency with large heaps. 
Always measure before tuning — enable GC logging with -Xlog:gc*:file=gc.
log and -XX:+HeapDumpOnOutOfMemoryError so you have visibility into what's actually happening before changing any parameter.

#### what parameters you know about gc , change gc parameters?
Common GC-related JVM parameters include -Xms and -Xmx for configuring heap size, 
-XX:+UseG1GC or -XX:+UseZGC for selecting the garbage collector, 
-Xlog:gc for GC logging in Java 9+. 
We can also tune GC behavior using parameters like -XX:MaxGCPauseMillis and adjust Metaspace with -XX:MaxMetaspaceSize.

#### Where do you use in Singleton in your project?
thread pool/db connection pool/ wide tables DTO
Singleton objects thread pools , data connection pool, 


####  What is the SOLID Principle?
SOLID is a set of 5 object-oriented design principles that make software more maintainable, scalable, and testable:
S — Single Responsibility Principle (SRP) A class should have ONE reason to change.  
O — Open/Closed Principle (OCP) Open for extension, closed for modification.
L — Liskov Substitution Principle (LSP) implements based on polymorphism. Subtypes must be substitutable for their base types without breaking behavior.
I — Interface Segregation Principle (ISP) Don't force classes to implement interfaces they don't use.
D — Dependency Inversion Principle (DIP) Depend on abstractions, not concrete implementations.

#### Can we customized the key?



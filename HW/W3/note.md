#### JVM Tuning
To optimize the JVM tuning starts with heap memory.
- always set -Xms equal to -Xmx in production to avoid resize pauses,
- use -XX:MaxRAMPercentage=75.0 in containers so the JVM respects Docker memory limits. 
- choose the right garbage collector for your workload
  — G1GC for most production apps with -XX:MaxGCPauseMillis=200 to target pause time, 
  - ZGC for low latency with large heaps. 
Always measure before tuning
  — use GC logging with -Xlog:gc*:file=gc.
  - log and -XX:+HeapDumpOnOutOfMemoryError so you have visibility into what's actually happening before changing any parameter.

#### Can we customized the key?
Yes, but must override both hashCode() and equals() correctly. Equal objects must have the same hash code. If you only override one, hash collision may happen and HashMap will behave incorrectly (duplicates or keys not found). 

#### what parameters you know about gc , change gc parameters?
Common GC-related JVM parameters: -Xms and -Xmx for configuring heap size, 
-XX for selecting the garbage collector. Ex: -XX:+UseG1GC or -XX:+UseZGC
-Xlog:gc for GC logging in Java 9+. 
-tune GC behavior using parameters -XX:MaxGCPauseMillis to target pause time
- adjust Metaspace with -XX:MaxMetaspaceSize.


####  What is the SOLID Principle?
SOLID is a set of 5 object-oriented design principles that make software more maintainable, scalable, and testable:

S — Single Responsibility Principle (SRP) A class should have ONE reason to change.  

O — Open/Closed Principle (OCP) code should open for extension, closed for modification.

L — Liskov Substitution Principle (LSP) implements based on polymorphism. Child class should be able to replace its parent class without breaking behavior.

I — Interface Segregation Principle (ISP) use small and focused interfaces instead of one huge interface.

D — Dependency Inversion Principle (DIP) high-level modules should depend on abstractions rather than concrete implementations.




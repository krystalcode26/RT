#Interview Template 

### Definition → Purpose → Example → Real Project

Example: What is HashMap?
####Definition
HashMap is a key-value data structure that implements the Map interface.
####Purpose
It provides fast lookup, insertion, and deletion with average O(1) time complexity.
####How it works
Internally it uses hashCode() to find a bucket and equals() to locate the correct key inside the bucket. Collisions are handled using linked lists or Red-Black trees in Java 8+.
####Real-world usage
In projects, I use HashMap for caching data, storing configurations, and building lookup tables.
 
### Comparison Template
For questions like:
•	List vs Set 
•	ArrayList vs LinkedList 
•	HashMap vs ConcurrentHashMap 
•	Future vs CompletableFuture 
•	Comparable vs Comparator 

Use:
####First define A then define B.
####Main Difference
The key difference is ...
####When to Use
Use A when ...
Use B when ...

Example
List<String> list = new ArrayList<>();
Set<String> set = new HashSet<>();
 
### Multithreading Template
Definition - A thread is the smallest unit of execution within a process.
Why - We use multithreading to improve responsiveness and utilize CPU resources efficiently.
Problem - Multiple threads accessing shared resources may cause race conditions.
Solution - We use synchronized, Lock, atomic classes, or thread-safe collections.
Real Example - In a web application, multiple requests are processed concurrently using thread pools.

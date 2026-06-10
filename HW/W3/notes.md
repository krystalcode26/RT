### Design the locking schema so that when a thread call method1(), it needs to until some other thread call method2()?
Locking schema thread waits on another thread's signal. This is a wait/notify pattern.
Use wait() to wait for other thread to release a lock. 
Other thread use notify() / notifyAll() to wake up threads.
Another way is Reentrantlock.

### If we need to design task management application, create task, query task,/crud, how would you approach it?
Identify which kind of application - web application / application with Java OOP implementation.
For Java application: Map interface, Multiple users -> use concurrentHashMap.
How to store? need task Id and task itself and need to do OOP implementation of task object.
When we build those tasks the builder design pattern what kind of task? one-time task or some are scheduled or some has peer review so it will be delivered.
Some tasks need cooperations like need multiple employees to work together.

### fairlock
a locking mechanism via new ReentrantLock grants lock access to threads in the order. 
longest-waiting thread the chance to acquire the lock first before any other thread. 
This prevents thread starvation but slightly slower.

### How did you debug?
check the log for that description (http status, log error/exception) -> trace original request -> reproduce(prod) -> dev(request payload + postman+local IntelliJ) -> debug + breakpoint

### features in Java 11
- LTS release after Java 8.
- String utility methodsEx: isBlank(), lines(), strip(), repeat().
- Files.readString() / writeString()
- var in lambda parameters.
- ZGC (Z Garbage Collector) as an experimental feature and refining the G1 GC
- new HTTP Client API for modern HTTP/2 and async requests. 

### how to group people to key(age), value(list of people)
stream API/for loop -> Collectors.groupingBy()

video:


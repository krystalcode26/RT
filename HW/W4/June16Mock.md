**If you override hashCode() but not equals(), what happens?**

Two objects with the same hash code can still be treated as different keys because `equals()` falls back to reference equality. 

A `HashMap` uses `hashCode()` to find the right bucket, then uses `equals()` to decide if a key already exists. 

Without overriding `equals()`, it can't distinguish a hash collision from a value update — so you get duplicate keys and lookups that return `null`. 

The contract is always: override both together.

**How do you write a REST API endpoint in Spring Boot?**

1. @RestController (which combines @Controller` + @ResponseBody) and set the base URL with @RequestMapping("/api/v1/products"). 

2. Endpoint methods use `@GetMapping`, `@PostMapping`, `@PutMapping`, `@DeleteMapping` for CRUD operations. Define a sub-URL on each (e.g., `@GetMapping("/{id}")`).

3. Input — extract data via `@PathVariable`, `@RequestParam`, `@RequestHeader`, and `@RequestBody`.

4. Output — return a `ResponseEntity<T>` wrapping the payload and an HTTP status code (e.g., `200 OK`, `201 Created`).

5. Validation — add `@Valid` on the method parameter and define rules (`@NotNull`, `@Size`, etc.) in the DTO.

6. Exception handling — use `@RestControllerAdvice` to catch and map exceptions to appropriate HTTP responses globally.


**What is the difference between map() and filter() in the Java Stream API?**

Both are intermediate operations, but they serve different purposes and accept different functional interfaces.
filter(Predicate<T>) — selects elements matching a condition. The predicate takes one input and returns a boolean.
map(Function<T, R>) — transforms each element into a new form. The function takes one input and returns a different output type.
Example: use filter to keep only employees over 30, then use map to convert those Employee objects into DTOs before returning them to the frontend.

**What is Thread State?**

A thread begins its life in the NEW state the moment it is created but before `start()` is called — it exists as an object but has no OS thread behind it yet. 

Once `start()` is invoked, it transitions to RUNNABLE, which means it is either actively executing on the CPU or sitting in the run queue waiting for the scheduler to give it a turn. 

From RUNNABLE, three things can pull a thread off the CPU.

The first is lock contention BLOCKED — if the thread tries to enter a synchronized block that another thread already holds, it moves to BLOCKED, where it waits passively until the lock is released and it can re-enter the RUNNABLE pool. 

The second is an indefinite WAITING — if the thread calls wait(), `oin(), or `LockSupport.park()`, it enters WAITING, where it sits until another thread explicitly wakes it up via notify, notifyAll(), or unpark(). 

The third is TIMED_WAITING — calls like `Thread.sleep(ms)` or `wait(ms)` put the thread into TIMED_WAITING, which behaves exactly like WAITING except the thread wakes itself up automatically once the timeout expires, without needing any signal from outside.

Finally, when run() completes — either normally or by throwing an uncaught exception — the thread enters TERMINATED, its final resting state, from which it can never be restarted.

**What is ThreadLocal in Java?**

`ThreadLocal` is a wrapper that gives each thread its own isolated copy of a variable. 

Other threads cannot read or modify it. 

When a thread is paused and later resumes, its `ThreadLocal` values are restored 

so computation can continue from where it stopped.


**Does Java's clone() perform a shallow or deep copy by default?**

Java performs a shallow copy by default. `Object.clone()` copies the top-level fields, but any fields that are references will still point to the same objects in memory.

To perform a deep copy, you must implement the `Cloneable` interface and override `clone()` to **manually copy each nested object recursively.

**What is pattern matching in Java (instanceof)?**

Pattern matching (stable since Java 17 experimental version) eliminates the need for an explicit cast after an `instanceof` check. 

The compiler binds the variable `s` automatically, removing the boilerplate cast. This makes conditional type-checking cleaner and less error-prone.

In older Java you would write:`if (obj instanceof String) { String s = (String) obj; s.toLowerCase(); }`

With pattern matching:`if (obj instanceof String s) { s.toLowerCase(); }`

**What is CORS and how do you configure it in Spring Boot?**

CORS (Cross-Origin Resource Sharing) is a browser security mechanism that blocks requests from one origin (host + port) to a different origin by default. 

For example, an Angular app on `localhost:4200` cannot call a Spring Boot API on `localhost:8080` without permission — the different port makes them different origins.


**What is the difference between @Autowired by type and by name in Spring?**

Spring injects beans **by type** by default. If there is only one bean of the required type, it is injected automatically.

When multiple beans of the same type exist you have two options:— `@Primary` on one bean to mark it as the default.— `@Qualifier("beanName")` at the injection point to specify exactly which bean you want.



**What is Spring Boot Actuator?**

Spring Boot Actuator adds production-ready monitoring endpoints, exposing information like health status (/actuator/health), metrics  (`/actuator/metrics`), environment properties, and thread dumps — all over HTTP. The metrics endpoint is powered by Micrometer underneath —  middleman between your application and your monitoring tool.  — Prometheus, Datadog, CloudWatch, or any other backend — just by changing a dependency. 


**How do you send an HTTP request from Angular to a Spring Boot backend?**

1. Import HttpClientModule in your ppModule.

2. Inject HttpClient into a service class.

3. Call http.get(), http.post(), http.put(), or `http.delete()` with the backend URL. You can also pass request headers, query params, and a request body.

4. Each call returns an `Observable` — subscribe in your component to receive the response.

By default this is synchronous in the reactive sense (the Observable runs on the JS event loop). 

For a promise-based async/await style, pipe through .toPromise() or `firstValueFrom() and use async/await.

**S3**
https://rt-bucket06.s3.us-east-2.amazonaws.com/Jun16.mp4

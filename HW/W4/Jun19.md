### Singleton Pattern — Lazy Loading + Eager Loading
#### What is Singleton?
The Singleton pattern is a design pattern that ensures a class has only one instance throughout the entire application lifecycle, and provides a global access point to that instance. 

It is one of the most fundamental Gang of Four patterns and is widely used in scenarios where exactly one shared object must coordinate actions across the system — such as a configuration manager, a connection pool, or a logging service.

b. Why Singleton?
use when creating multiple instances of a class would either waste resources or cause inconsistent behavior.

Ex: a database connection pool should not be instantiated multiple times because each instance would open its own set of connections, leading to resource exhaustion and unpredictable state. 
An application configuration object should be shared across all components so every part of the system reads from the same source of truth. 

Singleton solves this by enforcing a single, shared instance and lazy or eager initialization depending on performance and safety requirements.

#### Eager Loading — the instance is created at class-loading time, before it is ever requested. 
This is thread-safe by default because the JVM guarantees that static fields are initialized atomically during class loading.

public class EagerSingleton {

    // Instance created immediately when the class is loaded
    private static final EagerSingleton INSTANCE = new EagerSingleton();

    private EagerSingleton() {
        // private constructor prevents external instantiation
    }

    public static EagerSingleton getInstance() {
        return INSTANCE;
    }
}

#### Lazy Loading with Double-Checked Locking — the instance is created only when first requested. 

The volatile keyword prevents instruction reordering by the CPU, and the double-checked lock avoids synchronization overhead on every subsequent call after the instance is already initialized.

public class LazySingleton {

    // volatile ensures visibility and prevents partial initialization
    private static volatile LazySingleton instance;

    private LazySingleton() {}

    public static LazySingleton getInstance() {
        if (instance == null) {                    // first check (no lock)
            synchronized (LazySingleton.class) {
                if (instance == null) {            // second check (with lock)
                    instance = new LazySingleton();
                }
            }
        }
        return instance;
    }
}

In Spring Boot, you almost never implement Singleton manually. Spring manages beans as singletons by default — every @Service, @Repository, and @Component is a singleton bean within the application context unless you explicitly declare a different scope. 

Spring's IoC container handles thread safety, lifecycle, and initialization order for you.

### Strategy Design Pattern
a. What is the Strategy Pattern?
The Strategy pattern is a behavioral design pattern that encapsulates each algorithm in a separate class, and makes them interchangeable at runtime. 

Instead of hard-coding a specific algorithm inside a class, you delegate the behavior to a strategy interface, and the concrete implementation can be swapped without modifying the client code. This supports the Open/Closed Principle — the class is open for extension (new strategies) but closed for modification.

b. Why Strategy?
use when multiple variations of a behavior that could apply to the same object depending on context to avoid complex if-else or switch statements. 

For example, in a payment processing system, you might support credit card, PayPal, and bank transfer — each with completely different processing logic. 

Without Strategy, you end up with deeply nested conditionals that are fragile and hard to extend. With Strategy, adding a new payment method means adding a new class that implements the strategy interface, with zero changes to existing code.

c. How I Implement It in My Project
Strategy pattern for a payment processing service in an e-commerce application.

First, I defined the strategy interface:
public interface PaymentStrategy {
    void pay(double amount);
    String getPaymentType();
}
Then I created concrete strategy implementations, each as a Spring @Component:

@Component
public class CreditCardPaymentStrategy implements PaymentStrategy {

    @Override
    public void pay(double amount) {
        System.out.println("Processing credit card payment of $" + amount);
    }

    @Override
    public String getPaymentType() {
        return "CREDIT_CARD";
    }
}

@Component
public class PayPalPaymentStrategy implements PaymentStrategy {

    @Override
    public void pay(double amount) {
        System.out.println("Processing PayPal payment of $" + amount);
    }

    @Override
    public String getPaymentType() {
        return "PAYPAL";
    }
}

In the context class (the service), Spring automatically injects all implementations of PaymentStrategy into a list, and I build a map keyed by payment type for O(1) lookup:
@Service
public class PaymentService {

    private final Map<String, PaymentStrategy> strategyMap;

    // Spring injects all PaymentStrategy beans automatically
    public PaymentService(List<PaymentStrategy> strategies) {
        this.strategyMap = strategies.stream()
            .collect(Collectors.toMap(
                PaymentStrategy::getPaymentType,
                Function.identity()
            ));
    }

    public void processPayment(String paymentType, double amount) {
        PaymentStrategy strategy = strategyMap.get(paymentType);
        if (strategy == null) {
            throw new IllegalArgumentException("Unsupported payment type: " + paymentType);
        }
        strategy.pay(amount);
    }
}
The key advantage of this Spring-native approach is that adding a new payment method — say BankTransferPaymentStrategy — only requires creating a new @Component class. 

The PaymentService requires absolutely no changes because Spring will automatically discover and inject the new bean. This makes the system highly extensible and cleanly follows both the Strategy pattern and the Open/Closed Principle.

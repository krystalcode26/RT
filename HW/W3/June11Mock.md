### check HW12 NOTES and VIDEO

1. what spring boot version you used
 (2.0/3.0) -> 
java version compatability 
-> javax-> jarkata 

 2. what annotations you used in spring 
 spring implements IOC by DI, scan all annotation
-> IoC -> @SpringBootApplication 
                       configuration use -> @EnableAutoConfiguration, 
                           @SpringConfiguration, 
                        auto scan use->   @ComponentScan -> @Controller, @RestController, @Service, @Repository, @Component, @Bean + @Configuration

        Bean scope use @Scope (Singleton, prototype, session, request, application)

-> DI -> @Autowire -> field, constructor, setter

 + AOP -> 
RestControllerAdvice -> Exception class 
@Aspect -> @Pointcut Where? When? @Around, @Before, @After, @Afterthrowing, @Afterreturn 

    Restful endpoint design -> 
@RestController = @Controller + @ResponseBody / @RequestMapping / @GetMapping/POST/PUT/DELETEMapping 
Request input params: @RequestParam, @Pathvariable, @requestheader, @requestbody
Response -> @ResponseBody, -> HTTP status code 

Type vs name -> type conflicts -> @Qualifier (beanName)

Spring implements IoC (Inversion of Control) through Dependency Injection, using annotations scanned at startup. The entry point is @SpringBootApplication, which combines three key annotations: @EnableAutoConfiguration (to auto-configure Spring based on the classpath), @SpringBootConfiguration (to mark the class as a configuration source), and @ComponentScan (to automatically detect Spring-managed components). Components are registered as beans using stereotypes like @Controller, @RestController, @Service, @Repository, and @Component, while @Bean paired with @Configuration allows explicit bean declaration in config classes. Bean lifecycle scope is controlled via @Scope, supporting modes such as singleton, prototype, session, request, and application.
For Dependency Injection, @Autowired can be applied at the field, constructor, or setter level. When type conflicts arise between multiple beans of the same type, @Qualifier resolves ambiguity by specifying the exact bean name.
Spring also supports AOP (Aspect-Oriented Programming) through @Aspect, where @Pointcut defines where advice applies, and directional annotations — @Before, @After, @Around, @AfterReturning, and @AfterThrowing — define when it runs. For global exception handling, @RestControllerAdvice intercepts exceptions across all controllers in one place.
On the web layer, @RestController (a shorthand combining @Controller and @ResponseBody) is used alongside @RequestMapping or the method-specific @GetMapping, @PostMapping, @PutMapping, and @DeleteMapping to define RESTful endpoints. Request data is bound using @RequestParam (query parameters), @PathVariable (URL segments), @RequestHeader (HTTP headers), and @RequestBody (JSON body). Responses are returned via @ResponseBody paired with appropriate HTTP status codes.
3. what is enable auto configuration


5. how to stop auto configuration in spring boot
@SpringBootApplciation(exclude= com.google.model.*)


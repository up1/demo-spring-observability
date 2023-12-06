# Workshop Spring Boot and Observability
* Application metric
* Distributed tracing
* Centralized log

## Step 1 :: Create project
* Actuator
* OpenTelemetry
* Zipkin exporter

pom.xml
```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
<dependency>
    <groupId>io.micrometer</groupId>
    <artifactId>micrometer-tracing-bridge-otel</artifactId>
    <version>1.2.0</version>
</dependency>
<dependency>
    <groupId>io.opentelemetry</groupId>
    <artifactId>opentelemetry-exporter-zipkin</artifactId>
    <version>1.32.0</version>
</dependency>

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-aop</artifactId>
</dependency>
```

## Step 2 :: Start [zipkin server](https://zipkin.io/)
```
$docker container run -d -p 9411:9411 openzipkin/zipkin
```

Access to Zipkin server with url=http://localhost:9411

## Step 3 :: Config spring boot project
application.properties
```
# Tracing
spring.application.name=demo-service
management.tracing.sampling.probability=1.0
management.zipkin.tracing.endpoint=http://localhost:9411/api/v2/spans

# Logging :: add tracing id in log message
logging.pattern.correlation=[${spring.application.name:},%X{traceId:-},%X{spanId:-}] 
logging.include-application-name=false
```

## Step 4 :: Start project and run api
* http://localhost:8080/hello
* See tracing in Zipkin

Log message in console
```
INFO 20832 --- [nio-8080-exec-2] [demo-service,6619e49451055ee4dd3153638b1a46d4,aab712bd20264b2c] c.e.demo_observable.HelloController      : Called function sayHi
```

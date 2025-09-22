# OURAMAZONE - Distributed Microservices E-commerce Platform

A distributed e-commerce platform built with Spring Boot microservices architecture, demonstrating modern cloud-native patterns including service discovery, circuit breakers, distributed tracing, and message queuing.

## 🏗️ Architecture Overview

This project implements an Amazon-like e-commerce platform using microservices architecture with the following components:

### Core Microservices
- **User Service** (`amazone-user`) - User management and authentication
- **Product Service** (`amazone-product`) - Product catalog management
- **Order Service** (`amazone-order`) - Order processing and management
- **Inventory Service** (`amazoneInventory`) - Inventory tracking and management
- **Search Service** (`amazoneSearch`) - Product search functionality
- **User Management Service** (`amazoneUsers`) - Enhanced user operations with Hystrix

### Infrastructure Services
- **Eureka Server** (`eureka`) - Service discovery and registration
- **Gateway Service** (`gateway`) - API Gateway for routing requests
- **Zuul Consumer** (`eureka-amazone-consumer-zuul`) - Load balancing and routing

## 🛠️ Technology Stack

### Core Technologies
- **Java 8**
- **Spring Boot 2.0.3**
- **Spring Cloud Finchley.RELEASE**
- **Maven** for dependency management

### Microservices Components
- **Spring Cloud Netflix Eureka** - Service Discovery
- **Spring Cloud Gateway** - API Gateway
- **Netflix Zuul** - Edge Service
- **Netflix Hystrix** - Circuit Breaker Pattern
- **Spring Cloud Sleuth** - Distributed Tracing
- **Zipkin** - Tracing Analysis

### Data & Messaging
- **Spring Data JPA** - Data persistence
- **H2 Database** (Development)
- **RabbitMQ** - Message Queuing
- **Spring Cache** - Caching support

### Monitoring & Observability
- **Hystrix Dashboard** - Circuit breaker monitoring
- **Zipkin Tracing** - Request tracing across services
- **Spring Boot Actuator** - Health checks and metrics

## 🚀 Getting Started

### Prerequisites
- Java 8 or higher
- Maven 3.6+
- RabbitMQ Server
- Zipkin Server (optional, for tracing)

### Quick Start

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd OURAMAZONE
   ```

2. **Start Infrastructure Services**
   
   Start Eureka Service Discovery:
   ```bash
   cd spring-cloud/eureka
   mvn spring-boot:run
   ```
   Eureka Dashboard: http://localhost:8761

3. **Start Core Microservices**
   
   In separate terminals, start each service:
   
   ```bash
   # User Service (Port 8060)
   cd spring-cloud/amazone-user
   mvn spring-boot:run
   
   # Product Service (Port varies)
   cd spring-cloud/amazone-product
   mvn spring-boot:run
   
   # Order Service (Port varies)
   cd spring-cloud/amazone-order
   mvn spring-boot:run
   
   # Inventory Service (Port varies)
   cd spring-cloud/amazoneInventory
   mvn spring-boot:run
   
   # Search Service (Port varies)
   cd spring-cloud/amazoneSearch
   mvn spring-boot:run
   
   # Enhanced User Service (Port varies)
   cd spring-cloud/amazoneUsers
   mvn spring-boot:run
   ```

4. **Start Gateway Services**
   ```bash
   # Gateway Service
   cd spring-cloud/gateway
   mvn spring-boot:run
   
   # Zuul Consumer
   cd spring-cloud/eureka-amazone-consumer-zuul
   mvn spring-boot:run
   ```

### Optional Services

**Start Zipkin for Distributed Tracing:**
```bash
curl -sSL https://zipkin.io/quickstart.sh | bash -s
java -jar zipkin.jar
```
Zipkin UI: http://localhost:9411

**Start RabbitMQ:**
```bash
# Install and start RabbitMQ based on your OS
# Default management UI: http://localhost:15672
```

## 📋 Service Details

### User Service (`amazone-user`)
- **Port:** 8060
- **Purpose:** User authentication and management
- **Endpoints:**
  - User registration and login
  - User profile management
- **Features:** Service discovery integration

### Product Service (`amazone-product`)
- **Purpose:** Product catalog management
- **Endpoints:**
  - `GET /products` - Get all products
  - `GET /products/{id}` - Get product by ID
  - `POST /products/AddProduct` - Add new product
  - `DELETE /products/{id}/delete` - Delete product
  - `GET /products/getprice` - Get product price
- **Features:** Circuit breaker, Hystrix dashboard

### Order Service (`amazone-order`)
- **Purpose:** Order processing and management
- **Features:** 
  - Integration with User and Product services
  - Order processing workflow
  - Service-to-service communication

### Inventory Service (`amazoneInventory`)
- **Purpose:** Inventory tracking and reporting
- **Endpoints:**
  - `POST /inventory/addInventory` - Add inventory record
  - `GET /inventory` - Get all inventory records
  - `POST /inventory/findInventoryForDate` - Get inventory by date
- **Features:** RabbitMQ integration, sales reporting

### Search Service (`amazoneSearch`)
- **Purpose:** Product search and discovery
- **Features:** Enhanced search capabilities

### Enhanced User Service (`amazoneUsers`)
- **Purpose:** Advanced user management
- **Features:** 
  - Hystrix circuit breaker
  - Caching support
  - Advanced user operations

## 🔧 Configuration

### Service Discovery
All services are registered with Eureka Server running on `localhost:8761`. Services discover each other using service names rather than hardcoded URLs.

### Distributed Tracing
Services are configured with Spring Cloud Sleuth and Zipkin for distributed tracing:
```properties
spring.sleuth.sampler.probability=100
spring.zipkin.baseUrl=http://localhost:9411/
```

### Circuit Breaker
Hystrix is configured for fault tolerance with dashboard monitoring available.

### Load Balancing
Services use `@LoadBalanced` RestTemplate for client-side load balancing.

## 🧪 Testing

### Manual Testing
1. Check service registration: http://localhost:8761
2. Test individual service endpoints
3. Monitor circuit breakers via Hystrix Dashboard
4. View request traces in Zipkin UI

### Sample Data
Services include CommandLineRunner implementations that populate sample data on startup:
- Sample users (salma@gmail.com, inas@gmail.com)
- Sample products (skirt category)
- Default user credentials for testing

## 📊 Monitoring

### Health Checks
- Eureka Dashboard: http://localhost:8761
- Individual service health endpoints: `/actuator/health`

### Tracing
- Zipkin UI: http://localhost:9411
- Trace requests across all microservices

### Circuit Breaker Monitoring
- Hystrix Dashboard available for monitoring circuit breaker status
- Real-time metrics for service health

## 🔍 API Documentation

### Common Patterns
- All services follow RESTful API conventions
- Standard HTTP status codes
- JSON request/response format
- Error handling with proper status codes

### Authentication
- User authentication handled by User Service
- JWT tokens or session-based authentication (implementation dependent)

## 🐛 Troubleshooting

### Common Issues
1. **Service Discovery Issues**
   - Ensure Eureka server is running first
   - Check network connectivity between services
   - Verify service names in configuration

2. **Port Conflicts**
   - Check if ports are already in use
   - Modify `application.properties` files if needed

3. **Database Issues**
   - Services use H2 in-memory database by default
   - Check JPA entity configurations
   - Verify database initialization

4. **RabbitMQ Connection**
   - Ensure RabbitMQ server is running
   - Check connection configuration
   - Verify queue declarations

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests if applicable
5. Submit a pull request

## 📝 License

This project is for educational and demonstration purposes.

## � Developers

This project was developed by:
- **Salma Alkhalily** - [@SalmaAlkhalily](https://github.com/SalmaAlkhalily)
- **Inas Nasri**

## �📧 Contact

For questions and support, please contact the development team.

---
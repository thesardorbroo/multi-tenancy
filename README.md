# 🍎 Spring Boot Multi-Tenancy Example

This project demonstrates a multi-tenancy architecture in Spring Boot using `AbstractRoutingDataSource`. It includes:

- `Fruit` — a tenant-specific entity (data is isolated per tenant)
- `Person` — a common entity (shared across all tenants)

## 📌 Description

This project allows tenant-specific data handling based on dynamic DataSources, where tenant context is determined at runtime using an HTTP header.

## 🧰 Technologies

- Java 17+
- Spring Boot 3.x
- Spring Data JPA
- PostgreSQL
- Lombok
- Maven

## 🧠 Multi-Tenancy Architecture

### 🔹 Key Components

| Component                 | Description                                                                                                                            |
|---------------------------|----------------------------------------------------------------------------------------------------------------------------------------|
| `TenantProperties`        | Scans the `.yml`([application.yml example](/src/main/resources/application.yml)) config and loads all tenant DataSource configurations |
| `DynamicDatasource`       | Extends `AbstractRoutingDataSource` and routes DB access by tenant ID                                                                  |
| `TenantContext`           | Stores the current tenant ID in a `ThreadLocal` during the request                                                                     |
| `TenantIdentifiedAspect`  | AOP component that extracts the tenant ID and sets it in the `TenantContext`                                                           |
| `FruitController`         | Handles tenant-specific entity operations                                                                                              |
| `PersonController`        | Handles common/shared entity operations                                                                                                |

## 🚀 Getting Started

1. Clone the repository:

   ```bash
   git clone https://github.com/thesardorbroo/multi-tenancy.git
   cd multi-tenancy
   ```

2. Run `PostgreSQL` docker container:

   ```bash
   docker compose -f ./docker/postgresql.yml up -d
   ```
   
   > [!IMPORTANT]
   > Enter inside postgresql container and execute postgresql commands and initialize databases and default data. 
   > SQL queries is located in [init-db.sql](/src/main/resources/sql/init-db.sql) file.

   > [!NOTE]
   > Entering inside postgresql container:
   > 
   > ```bash
   > docker exec -it multi-tenancy-db /bin/sh
   > ```
   > 
   > Entering inside postgresql database
   > ```bash
   > psql multi-tenancy -U root
   > ```
   > 
   > Use `\c <database name>` for connecting another database inside same postgresql container

3. Run application:
   ```bash
   ./mvnw spring-boot:run
   ``` 
   This command runs application in terminal. You can run application with IDE also

4. Send HTTP request and check:
   
   ```bash
   curl -X GET http://localhost:8080/api/fruits \
   -H "Content-Type: application/json" \
   -H "X-Tenant-Id: 1"'
   ```
   Request gets fruits from tenant database. You can change value of `X-Tenant-Id` to `2` also.
   
   ```bash
   curl -X GET http://localhost:8080/api/persons \
   -H "Content-Type: application/json"
   ```
   Request gets person from command database. API doesn't require `X-Tenant-Id` because it is global for all.

## 📬 Contact

If you have any questions, suggestions, or want to contribute, feel free to reach out:

**Author:** Sardorbroo
**GitHub:** [@Sardorbro11](https://t.me/Sardorbro11)  
**Email:** thesardorisfire@gmail.com



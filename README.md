# 🎟️ Voucher API

A REST API for discount coupon management, featuring JWT authentication, individual voucher generation, bulk generation, and flash promotions.

## 🚀 Technologies

- Java 21
- Spring Boot
- Spring Security + JWT
- Spring Data JPA
- PostgreSQL
- Swagger / OpenAPI 3.1
- Lombok

## ⚙️ Getting Started

### Prerequisites

- Java 21+
- PostgreSQL installed and running
- Maven

### 1. Clone the repository

```bash
git clone https://github.com/cauafern/voucher-api.git
cd voucher-api
```

### 2. Set up the database

Create the database in PostgreSQL:

```sql
CREATE DATABASE voucher_db;
```

### 3. Configure `application.properties`

Fill in the file `src/main/resources/application.properties` with your credentials:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/voucher_db
spring.datasource.username=your_username
spring.datasource.password=your_password

api.security.secret=your_jwt_secret_key
```

### 4. Run the application

```bash
./mvnw spring-boot:run
```

The API will be available at `http://localhost:8080`. You can explore and test all endpoints through the Swagger UI at:

http://localhost:8080/swagger-ui/index.html

## 🔐 Authentication

This API uses **JWT** authentication. To access protected endpoints:

1. Create a user at `POST /users`
2. Login at `POST /auth/login` with your email and password
3. Copy the returned token
4. Click **Authorize** in Swagger and paste the token as `Bearer {token}`

## 📦 Endpoints

### Auth
| Method | Route | Description | Auth Required |
|--------|-------|-------------|---------------|
| POST | `/auth/login` | Login with email and password, returns JWT | ❌ |

### Users
| Method | Route | Description | Auth Required |
|--------|-------|-------------|---------------|
| POST | `/users` | Register a new user | ❌ |

### Vouchers
| Method | Route | Description | Auth Required |
|--------|-------|-------------|---------------|
| POST | `/vouchers/generate` | Generate an individual voucher | ✅ |
| POST | `/vouchers/generate/flash` | Generate a flash promotion voucher | ✅ |
| POST | `/vouchers/generate/all` | Generate vouchers for all users | ✅ |

### Products
| Method | Route | Description | Auth Required |
|--------|-------|-------------|---------------|
| POST | `/products` | Register a new product | ✅ |

## 🗄️ PostgreSQL Setup on Linux

If you run into authentication issues on Linux, edit the `pg_hba.conf` file:

```bash
sudo sed -i 's/ident/md5/g' /var/lib/pgsql/data/pg_hba.conf
sudo systemctl restart postgresql
```

## 📝 License

This project is licensed under the MIT License.

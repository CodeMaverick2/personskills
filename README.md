# Person Skills Management - DevSecOps CI/CD

Java 17 Maven Spring Boot application with a comprehensive CI/CD pipeline: Checkstyle, CodeQL, OWASP, tests, Docker build, Trivy scan, smoke test, DockerHub push; CD deploys to Kubernetes using declarative YAML.

## Architecture

**CI (ci.yml)**: Sequential pipeline with all required stages - Checkout → Setup Runtime → Linting → SAST → SCA (Configured/Optional) → Unit Tests → Build → Docker Build → Image Scan → Runtime Test → Registry Push.

**CD (cd.yml)**: Production-ready Kubernetes deployment triggered heavily on CI success. Validates manifests → Deploys to cluster → Rollout verification → OWASP ZAP DAST scan.

## Security

Checkstyle, CodeQL, OWASP Dependency Check (Configured), Trivy (container scanning), non-root container execution. Shift-left security: quality and security gates prevent vulnerable code from reaching production.

## Run Locally

**Prerequisites:** Java 17, Maven 3.9+, MySQL, Docker, kubectl

```bash
# Build and run
mvn clean package
java -jar target/*.jar

# Tests and checks
mvn test
mvn checkstyle:check

# Docker
docker build -t personskills:latest .
docker run -p 8080:8080 personskills:latest

# Kubernetes
kubectl apply -f k8s/deployment.yaml
kubectl apply -f k8s/service.yaml
kubectl get pods
```

## GitHub Secrets

- `DOCKERHUB_USERNAME` - DockerHub login
- `DOCKERHUB_TOKEN` - DockerHub access token
- `KUBECONFIG` - Base64 encoded Kubernetes configuration for cluster access

## Features

- Person management (CRUD operations)
- Skill management (CRUD operations)
- Person-Skill relationship management
- RESTful API endpoints with validation
- Web interface using Spring MVC + JSP
- MySQL database integration with JPA
- Input validation and error handling
- Lombok for reducing boilerplate code
- Comprehensive test coverage
- Docker containerization

## Prerequisites

- Java 17 or higher
- Maven 3.9+
- MySQL Server
- Docker (for containerization)
- kubectl (for Kubernetes deployment)

## Technology Stack

- Spring Boot 3.4.5
- Spring Data JPA
- Spring MVC
- MySQL Database
- JSP & JSTL for views
- Lombok
- Maven for dependency management
- JUnit for testing

## Project Structure

```
src/
├── main/
│   ├── java/
│   │   └── com/
│   │       └── example/
│   │           └── personskills/
│   │               ├── controller/    # REST and web controllers
│   │               ├── model/         # Entity classes (Person, Skill)
│   │               ├── repository/    # JPA repositories
│   │               ├── service/       # Business logic services
│   │               └── PersonSkillsApplication.java
│   └── resources/
│       ├── static/    # Static resources (CSS, JS)
│       ├── templates/ # JSP templates
│       └── application.properties
└── test/             # Unit and integration tests
```

## Getting Started

### Local Development Setup

1. **Clone the repository:**
   ```bash
   git clone [repository-url]
   cd personskills
   ```

2. **Configure MySQL Database:**
   - Create a database named `personskills`
   - Update `src/main/resources/application.properties` with your MySQL credentials:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/personskills
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   ```

3. **Build the project:**
   ```bash
   mvn clean install
   ```

4. **Run the application:**
   ```bash
   mvn spring-boot:run
   ```

5. **Access the application:**
   - Web Interface: http://localhost:8080
   - REST API: http://localhost:8080/api/
   - Health Check: http://localhost:8080/actuator/health

### Docker Setup

1. **Build the Docker image:**
   ```bash
   docker build -t personskills:latest .
   ```

2. **Run with Docker:**
   ```bash
   docker run -p 8080:8080 --env-file .env personskills:latest
   ```

### Kubernetes Deployment

1. **Apply manifests:**
   ```bash
   kubectl apply -f k8s/deployment.yaml
   kubectl apply -f k8s/service.yaml
   ```

2. **Check deployment:**
   ```bash
   kubectl get pods
   kubectl get services
   ```

## API Endpoints

### Person Endpoints
- `GET /api/persons` - Get all persons
- `GET /api/persons/{id}` - Get person by ID
- `POST /api/persons` - Create new person
- `PUT /api/persons/{id}` - Update person
- `DELETE /api/persons/{id}` - Delete person

### Skill Endpoints
- `GET /api/skills` - Get all skills
- `GET /api/skills/{id}` - Get skill by ID
- `POST /api/skills` - Create new skill
- `PUT /api/skills/{id}` - Update skill
- `DELETE /api/skills/{id}` - Delete skill

### Person-Skill Relationship Endpoints
- `GET /api/persons/{personId}/skills` - Get skills for a person
- `POST /api/persons/{personId}/skills/{skillId}` - Add skill to person
- `DELETE /api/persons/{personId}/skills/{skillId}` - Remove skill from person

## References

- [GitHub Actions](https://docs.github.com/en/actions)
- [CodeQL](https://codeql.github.com/)
- [OWASP Dependency Check](https://owasp.org/www-project-dependency-check/)
- [Trivy](https://aquasecurity.github.io/trivy/)
- [Kubernetes](https://kubernetes.io/)


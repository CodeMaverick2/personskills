# Person Skills Management System

A Spring Boot application for managing person skills and their relationships. This application provides a RESTful API and web interface for managing persons, skills, and their associations.

## Features

- Person management (CRUD operations)
- Skill management (CRUD operations)
- Person-Skill relationship management
- RESTful API endpoints
- Web interface using JSP
- MySQL database integration
- Input validation
- Lombok for reducing boilerplate code

## Prerequisites

- Java 17 or higher
- Maven
- MySQL Server
- IDE (recommended: Eclipse or IntelliJ IDEA)

## Technology Stack

- Spring Boot 3.4.5
- Spring Data JPA
- MySQL Database
- JSP & JSTL for views
- Lombok
- Maven for dependency management

## Project Structure

```
src/
├── main/
│   ├── java/
│   │   └── com/
│   │       └── example/
│   │           └── personskills/
│   │               ├── controller/    # REST and web controllers
│   │               ├── model/         # Entity classes
│   │               ├── repository/    # JPA repositories
│   │               ├── service/       # Business logic
│   │               └── PersonSkillsApplication.java
│   └── resources/
│       ├── static/    # Static resources
│       ├── templates/ # JSP templates
│       └── application.properties
└── test/             # Test cases
```

## Getting Started

1. Clone the repository:
   ```bash
   git clone [repository-url]
   ```

2. Configure MySQL:
   - Create a database named `personskills`
   - Update `application.properties` with your MySQL credentials

3. Build the project:
   ```bash
   mvn clean install
   ```

4. Run the application:
   ```bash
   mvn spring-boot:run
   ```

5. Access the application:
   - Web Interface: http://localhost:8080
   - API Documentation: http://localhost:8080/swagger-ui.html

## API Endpoints

### Person Endpoints
- GET /api/persons - Get all persons
- GET /api/persons/{id} - Get person by ID
- POST /api/persons - Create new person
- PUT /api/persons/{id} - Update person
- DELETE /api/persons/{id} - Delete person

### Skill Endpoints
- GET /api/skills - Get all skills
- GET /api/skills/{id} - Get skill by ID
- POST /api/skills - Create new skill
- PUT /api/skills/{id} - Update skill
- DELETE /api/skills/{id} - Delete skill

### Person-Skill Relationship Endpoints
- GET /api/persons/{personId}/skills - Get skills for a person
- POST /api/persons/{personId}/skills/{skillId} - Add skill to person
- DELETE /api/persons/{personId}/skills/{skillId} - Remove skill from person

## Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## License

This project is licensed under the MIT License - see the LICENSE file for details.

## Acknowledgments

- Spring Boot team for the amazing framework
- MySQL team for the database
- All contributors who have helped in the development 
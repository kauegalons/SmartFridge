# SmartFridge

SmartFridge is an AI-powered application that helps users monitor the food available in their refrigerator and generate personalized recipe suggestions based on those ingredients. The project combines Java, Spring Boot, and LangChain4j to create a practical solution for reducing food waste and improving daily meal planning.

## Overview

The project is designed to make it easier to understand what is currently available at home and transform those ingredients into useful meal ideas. By cataloging stored food items and analyzing their categories, the application can suggest recipes that fit the available inventory.

This project is still in its early stages, but it is structured to evolve into a smarter ingredient-management and recipe-generation system.

## Key Features

- Manage food items stored in the refrigerator
- Categorize ingredients for better organization
- Model food inventory with Java entities
- Prepare the backend structure for AI-powered recipe generation
- Integrate LangChain4j for future intelligent recommendations

## Technology Stack

- Java 17+
- Spring Boot
- Maven
- JPA / Hibernate
- LangChain4j

## Project Structure

```text
SmartFridge/
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/
│   │           └── java10x/
│   │               └── SmartFridge/
│   │                   ├── controller/
│   │                   ├── model/
│   │                   ├── repository/
│   │                   └── service/
│   └── test/
│       └── java/
├── pom.xml
├── mvnw
├── mvnw.cmd
├── README.md
└── .gitignore
```

## Current Status

The application currently includes the initial project structure and core domain entities, with the foundation for food inventory management and future AI-driven recipe recommendations.

## Getting Started

### Prerequisites

- Java 17 or higher
- Maven
- IntelliJ IDEA or another Java IDE

### Run the application

```bash
./mvnw spring-boot:run
```

## Future Goals

- Improve ingredient registration and validation
- Add inventory tracking with expiration dates
- Expand the food category model
- Integrate AI-based recipe generation using LangChain4j
- Recommend meals based on available ingredients and preferences

## License

This project is currently under development and is intended for learning, experimentation, and future product expansion.

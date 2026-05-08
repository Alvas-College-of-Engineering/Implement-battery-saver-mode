# Battery Saver Web Application

A Java-based dynamic web application that simulates a battery saver mode system. It monitors device battery level, automatically adjusts settings to save power, and displays status and mode activation.

## Features

- Simulated battery level monitoring
- Automatic activation of saver mode when battery is low (<20%)
- Deactivation when battery is sufficient (>50%)
- Clean and simple web UI
- Real-time status updates

## Technologies Used

- Java 17
- Spring Boot 3.2.0
- Thymeleaf for templating
- Maven for build management

## How to Run

1. Ensure you have Java 17 and Gradle installed.
2. Clone or download the project.
3. Navigate to the project directory.
4. Run `gradle bootRun`
5. Open http://localhost:8080 in your browser.

If Gradle is not installed, install it from https://gradle.org/install/

Alternatively, use Maven: change build.gradle to pom.xml (provided in comments), install Maven from https://maven.apache.org/install.html, then run `mvn spring-boot:run`

## Project Structure

- `src/main/java/com/example/batterysaver/` - Java source files
- `src/main/resources/templates/` - Thymeleaf templates
- `src/main/resources/` - Application properties
- `pom.xml` - Maven configuration

## Classes

- `BatterySaverApplication`: Main Spring Boot application class
- `BatteryMonitor`: Service class for battery monitoring and saver mode logic
- `BatteryController`: Web controller for handling requests
# Tic-Tac-Toe

## Introduction
This project is built using Java 21 and JDK 21, leveraging JavaFX for the user interface.

## Prerequisites
- Java 21
- JDK 21
- Maven

## Dependencies

| Dependency        | Group ID       | Artifact ID    | Version      |
|-------------------|----------------|----------------|--------------|
| JavaFX Controls   | org.openjfx    | javafx-controls| 21-ea+24     |
| JavaFX FXML       | org.openjfx    | javafx-fxml    | 21-ea+24     |

## Installation

1. Ensure you have Java 21 and JDK 21 installed.
2. Clone the repository:
    ```sh
    git clone <repository_url>
    ```
3. Navigate to the project directory:
    ```sh
    cd tic-tac-toe
    ```

## Usage

1. Run the application:
    ```sh
    mvn javafx:run
    ```

## Steps to Pacakge the application

1. Clean any build files inside the `target` directory:
    ```sh
    mvn clean
    ```
2. Build the app and create the `target` directory which will contain a `.jar` file:
    ```sh
    mvn install
    ```
3. Run the application:
    ```sh
    java -jar target/tic-tac-toe-{VERSION}.jar
    ```


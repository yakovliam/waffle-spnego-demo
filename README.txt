# Waffle SPNEGO Spring Boot Demo (Gradle)

## Requirements
- Java 17 or newer
- Gradle (or use the Gradle wrapper)
- Windows OS (required by Waffle)
- Domain-joined machine for SPNEGO to function properly

## How to Build and Run

1. Open a terminal and navigate to the project root.

2. Build the project and create a runnable JAR file:
   ```bash
   gradle bootJar
   ```

   Or if using the wrapper:
   ```bash
   ./gradlew bootJar     # macOS/Linux
   gradlew.bat bootJar   # Windows
   ```

3. Run the JAR file from the command line:
   ```bash
   java -jar build/libs/waffle-spnego-demo-0.0.1-SNAPSHOT.jar
   ```

4. Access the application in your browser:
   http://localhost:9090/

Ensure your browser is configured for integrated Windows Authentication.

## Notes

- Works only on Windows systems due to native dependencies of Waffle.
- Be sure to add your server to your browser's "Intranet Zone" or enable Integrated Authentication.
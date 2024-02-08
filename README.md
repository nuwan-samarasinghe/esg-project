# esg-project

esg-project

# Running Maven and Gradle Projects with Tests

## Running a Maven Project with Test Build

1. **Navigate to Project Directory**: ```cd /ESG_Project ```
2. **Run the following**: ```mvn clean package ```

## Starting a Java Application through IntelliJ IDEA with Java 17

1. **Open IntelliJ IDEA**: Launch IntelliJ IDEA on your computer.

2. **Open Your Project**: Open the Java project in IntelliJ IDEA that contains the main class you want to run.

3. **Ensure Java 17 is Installed**: Make sure you have Java 17 installed on your system and configured in IntelliJ IDEA. You can check this by navigating to `File` > `Project Structure` > `Project` and verifying that the Project SDK is set to Java 17.

4. **Set the Main Class**: In the project explorer, locate the main class you want to run. This is typically a class with a `main` method.

5. **Configure Run Configuration**:
    - Go to the top menu and select `Run`.
    - Click on `Edit Configurations...`.
    - In the Run/Debug Configurations dialog, click on the `+` icon and select `Application`.
    - In the `Name` field, give your run configuration a meaningful name.
    - In the `Main class` field, specify the fully qualified name of the main class you want to run (e.g., `com.example.MainClass`).
    - If necessary, set any program arguments, VM options, or environment variables.
    - Click `OK` to save the configuration.

6. **Run the Application**:
    - Go to the top menu and select `Run`.
    - Click on the name of the run configuration you created in the previous step. Alternatively, you can use the green play button in the toolbar.

7. **View Output**: Once you run the application, you should see the output in the IntelliJ IDEA console window.


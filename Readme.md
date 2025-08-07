# Barrix QA

Mobile test automation framework using Java, Maven, Appium and Cucumber (Gherkin).

## Previous requirements

- Java 11 or higher
- Maven 3.6+
- Node.js and appium server installed
- Android or iOS emulator device configured

### Recommended environment variables

- `ANDROID_HOME = C:\Users\<YourUser>\AppData\Local\Android\Sdk`
- `JAVA_HOME = C:\Program Files\Java\jdk-22`
- `MAVEN_HOME = C:\Program Files\Maven\apache-maven-3.9.8`
- Add to `Path`: `%MAVEN_HOME%\bin`, `%ANDROID_HOME%\platform-tools`, `%ANDROID_HOME%\tools`

## Installation

1. Clone the repository:
   ```
    git clone https://github.com/Fer474/appiumtestsTFT.git
    cd appiumtestsTFT
   ```

2. Install the dependencies:
   ```
    mvn clean install
   ```

## Test Execution

1. Start Appium Server:
   ``` bash
    appium
   ```

2. Run the tests with Maven:
   ``` bash
    mvn test
   ```
3. Run specific Cucumber tests:
    #### Run all features
    ``` bash
    mvn test -Dcucumber.options="src/test/resources/features"
    ```
    #### Run a specific feature
    ``` bash
    mvn test -Dcucumber.options="src/test/resources/features/login.feature"
    ```

    #### Run by tags
    ``` bash
    mvn test -Dcucumber.options="--tags @login"
    ```

## Test Structure with Gherkin

The tests are written in `.feature` files using Gherkin syntax and executed with Cucumber.

Example scenario in `src/test/resources/features`:
```gherkin
Feature: User Login
    
    Scenario: Successful login
      Given the user is on the login screen
      When they enter valid credentials
      Then they access the application
```

## Customization

- Configure devices and capabilities in the properties files or configuration classes.
- Add new scenarios in the `features` folder.

## Support

For questions or issues, open an issue in the repository.
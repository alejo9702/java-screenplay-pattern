#BlanckFactor -Automation Challenge


# Prerequisites📋
1. Java v1.8 update 151 or higher and JDK (configured environment variables)
2. Eclipse IDE (Oxigen or Neon) or IntelliJ IDEA (2021)
3. Gradle v5.2.1 or higher 
4. Cucumber 
5. ChromeDriver 90.0 or latest stable version according to current chrome version 
6. Geckodriver 74.1 or latest stable version according to current chrome version


# Installation 🔧
- Import the project from Eclipse or Intellij IDE under the structure of an existing Gradle project.
- Configure JRE System Library with JavaSE-1.8
- Configure UTF-8 encoding to the project once it is imported.


# Compilar el proyecto y generar Wrapper 🔨
- Compile the project and generate Wrapper 🔨.
- To compile the project run the following command: gradle clean build -x test
- To generate the wrapper files of the project run the command: gradle wrapper --gradle-distribution-url https://services.gradle.org/distributions/gradle-5.2.1-all.zip

# To run the tests  ⚙️


## Execute commands  💻
- run all features: `gradle clean test aggregate --info`
- run specific feature: `gradle clean test clean test --tests "runners.runners.name" aggregate --info`

#General Implementation Details 💻

###🚧 The complete project structure is as follows:

**src/main/java**
+ exceptions

  Classes that catch custom exceptions when automation fails.

+ interactions

  Classes that perform the low-level actions, such as typing text in fields, clicking, among others.

+ models

  Classes that build data models, using a builder when necessary.

+ questions

  Classes used to perform test validations, it is done in the form of a question to know the result or state after having executed the tasks.

+ tasks

  Classes that perform high-level actions (business process), they are composed of calls to interactions or other tasks.

+ UserInterface

  Classes where the elements and objects of the user interface are mapped for the interaction with each of these elements.

+ util

  Classes containing common functionalities.


**src/test/java**
+ runners:

  Classes to run the automation with the scenarios indicated in the feature.

+ stepdefinitions:

  Classes where the Gherkin language with which the scenarios are written and the java code to be executed for the automation are coupled.


**src/test/resources**
+ datadrive:

  Test data used by the automation.

+ property:

  property files correspond to a class of text files, which allow the preloading of information to an application. for this case we use it to store authentication credentials used for each of the scenarios.

+ features:

  The project's features are found, which is where the scenarios or stories that the user carries out at the business level are written.


# Built With 🛠️
The automation was developed with:
- BDD - Development Strategy.
- Screenplay (Design pattern focused on automated test development).
- Gradle
- Selenium Web Driver
- Cucumber
- Serenity BDD - Open-source library for report generation
- Gherkin - Business Readable DSL (Business Readable Domain Specific Language)
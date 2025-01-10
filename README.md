# 🚀 Swag Labs Selenium Automation Framework

### *by Krushna*

---

## 📘 About the Project

### Overview
This project is a **Selenium-based Automation Framework** designed for **UI Automation** and **API Testing** with **TestNG**. The framework ensures high-quality testing by incorporating best practices like the **Page Object Model (POM)**, **data-driven testing**, and **report generation** using **Extent Reports**.

### Features
- 🛖 **UI Automation** for web applications using **Selenium WebDriver** with the **Edge Browser**.
- ✨ **API Testing** for validating APIs using tools like **RestAssured**.
- 📊 **Detailed Reporting** with **Extent Reports** that include logs, screenshots, and test results.
- 🗜️ **Logging** with **Log4j2** for traceable logs.
- ✅ **Data-Driven Testing** using **Excel files**, allowing for parameterized test cases.
- 🔁 **Reusable Page Objects** using **POM** design for easy maintenance and scalability.
- 🛠️ **Utility Methods** to simplify common tasks like browser handling, data handling, and reporting.

---

## 🛠️ Technologies Used
- **IDE**: IntelliJ IDEA, Eclipse
- **Programming Language**: Java
- **Test Framework**: TestNG
- **Browser Automation**: Selenium WebDriver (Edge)
- **API Testing**: RestAssured
- **Build Tool**: Maven
- **Reporting**: Extent Reports
- **Logging**: Log4j2
- **Data Handling**: Apache POI (for Excel data handling)

---

## 📂 Framework Structure

The project is organized into multiple directories for clear separation of concerns:

- **`src/main/java/`**: Contains the main framework codebase.
  - **`com/swagLabs/`**: Base package for the framework.
    - **`base/`**: Contains the `BaseClass.java` for setting up and tearing down the test environment.
    - **`pom/`**: Page Object Model (POM) classes representing the web pages.
    - **`utilities/`**: Utility classes for various functionalities like data providers, extent report generation, and log management.

- **`src/test/java/`**: Contains the test classes that implement the actual test cases.
  - **`com/swagLabs/tests/`**: Test classes for different application pages (e.g., Login, Cart, Checkout).
  
- **`test-output/`**: Stores the output from TestNG test execution, including:
  - **`index.html`**: Summary of the test execution.
  - **`testng-reports/`**: Detailed reports for TestNG test execution.
  - **`JUnitReports/`**: JUnit XML reports for CI tools like Jenkins.

- **`screenshots/`**: Contains screenshots taken during test execution, especially useful for debugging failed tests.

- **`logs/`**: Contains log files generated during the execution, useful for troubleshooting.

- **`testdata/`**: Stores external test data used for data-driven testing, including:
  - **`testdata.xlsx`**: Excel file for data-driven testing.
  
---

## 🚀 Setup and Usage

### Prerequisites
Make sure the following tools are installed:
- **Java 11+**
- **Maven 3.6+**
- **Edge WebDriver** (for Selenium)

### How to Set Up
1. **Clone the Repository**:
   ```bash
   git clone https://github.com/your-username/KrushnaPatare-Selenium-Automation-Framework.git
   ```

2. **Navigate to the project directory**:
   ```bash
   cd KrushnaPatare-Selenium-Automation-Framework
   ```

3. **Install dependencies**:
   ```bash
   mvn clean install
   ```

### Running the Tests

- **To execute all tests**:
   ```bash
   mvn test
   ```

- **To run a specific test**:
   Run individual test classes via your IDE or configure the `testng.xml` file to define which tests to run.

---

## 📊 Test Reporting

- **Extent Reports**: The framework generates detailed reports with logs, screenshots, and test statuses. To view the reports, navigate to the `test-output/` folder and open the **Extent Report HTML** file in your browser.

- **TestNG Reports**: These reports are also available in the `test-output/` folder, providing test execution details such as which tests passed, failed, or were skipped.

---

## 📁 Project Structure Overview

The project is structured using **Maven** conventions and organized into relevant directories to ensure clean code maintenance:

    KrushnaPatare-Selenium-Automation-Framework
    ├── src
    │   └── main
    │       └── java
    │           └── com/swagLabs
    │               ├── base           # Contains BaseClass for environment setup
    │               ├── pom            # Contains Page Object Model classes
    │               └── utilities      # Utility classes for common tasks
    ├── src
    │   └── test
    │       └── java
    │           └── com/swagLabs/tests # Contains test classes for the application
    ├── test-output                    # Contains generated TestNG and Extent Reports
    ├── screenshots                     # Contains screenshots taken during test execution
    ├── logs                            # Logs generated during test execution
    ├── testdata                        # External test data (e.g., Excel files)
    ├── pom.xml                         # Maven configuration
    └── testng.xml                      # TestNG suite configuration

---

## **Key Achievements**

#### ✅ **Cross-browser Testing**
- Automated UI testing across various browsers to ensure compatibility and functionality.

#### ✅ **Data-Driven Testing**
- Implemented parameterized tests using **Excel** for testing multiple data sets and scenarios.

#### ✅ **Page Object Model (POM) Design**
- Created a reusable and scalable framework using the **POM** design for organizing page elements and actions.

#### ✅ **Error Handling**
- Incorporated detailed logging and screenshot capturing to help identify and troubleshoot failures.

#### ✅ **Reporting**
- Generated **comprehensive Extent Reports** for tracking test execution results, including logs, screenshots, and success/failure statuses.

---

## **Screenshots of Extent Report**

The framework generates a comprehensive **Extent Report** that visually presents the test results. Below is a sample screenshot from the report:

![Extent Report Screenshot](https://github.com/your-username/KrushnaPatare-Selenium-Automation-Framework/blob/main/screenshots/ExtentReportImg01.png)

![Extent Report Screenshot](https://github.com/your-username/KrushnaPatare-Selenium-Automation-Framework/blob/main/screenshots/ExtentReportImg02.png)

For additional reports and logs, please check the `logs` and `test-output` folder.

---

This framework ensures that both UI and API testing are covered comprehensively, delivering reliable and detailed results, while maintaining clean and reusable code for future enhancements.

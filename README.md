# Java Selenium TestNG Cucumber Automation Framework

A professional automation testing framework built with Java, Selenium WebDriver, TestNG, and Cucumber BDD, following Page Object Model (POM) design pattern.

## 🚀 Features

- **Page Object Model (POM)** - Maintainable page objects
- **Cucumber BDD** - Business-readable test scenarios
- **TestNG Integration** - Parallel execution and reporting
- **Selenium WebDriver** - Cross-browser automation
- **Maven Build Management** - Dependency management
- **Comprehensive Logging** - Log4j2 integration
- **Screenshot Capture** - Automatic failure screenshots
- **Chrome Popup Handling** - Built-in popup management
- **Wait Utilities** - Robust element synchronization
- **Configuration Management** - Externalized configuration

## 📋 Prerequisites

- Java JDK 11+
- Maven 3.6+
- Chrome Browser (latest)
- ChromeDriver (auto-managed)

## 🛠️ Quick Start

### 1. Clone & Build
```bash
git clone <repository-url>
cd Java-Selenium-TestNG-Cucumber
mvn clean compile
```

### 2. Run Tests
```bash
# All login tests
mvn clean test -Dtest=CucumberTestRunner -Dcucumber.filter.tags="@login"

# Specific test categories
mvn clean test -Dtest=CucumberTestRunner -Dcucumber.filter.tags="@smoke"
mvn clean test -Dtest=CucumberTestRunner -Dcucumber.filter.tags="@positive"
mvn clean test -Dtest=CucumberTestRunner -Dcucumber.filter.tags="@negative"
```

## 🏗️ Project Structure

```
src/
├── main/java/com/automation/
│   ├── base/BasePage.java
│   ├── config/ConfigManager.java
│   ├── drivers/WebDriverFactory.java
│   ├── pages/ (LoginPage, InventoryPage, CartPage, CheckoutPage)
│   └── utils/ (ScreenshotUtils, WaitUtils)
├── test/java/com/automation/
│   ├── hooks/TestHooks.java
│   ├── stepdefinitions/ (Step definitions for each page)
│   └── tests/CucumberTestRunner.java
└── test/resources/
    ├── features/Login.feature
    └── disabled/ (Other feature files)
```

## 🎯 Current Status

### ✅ Implemented & Tested
- **Login Functionality**: 13 comprehensive test scenarios
  - Positive tests (valid credentials)
  - Negative tests (invalid credentials, locked users)
  - Edge cases (special characters, empty fields)
  - UI validation (form elements, placeholders)

### 🔄 Ready for Implementation
- Inventory page functionality
- Cart management
- Checkout process
- End-to-end user journeys

## 📊 Reports & Outputs

- **TestNG Reports**: `target/surefire-reports/`
- **Cucumber Reports**: `target/cucumber-reports/`
- **Screenshots**: `screenshots/` (failed tests)
- **Logs**: `logs/automation.log`

## 📄 License

MIT License

## 👨‍💻 Author

Gleb Plekanov - Senior QA Engineer

---

**Happy Testing! 🧪✨** 
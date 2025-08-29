# CI/CD Pipeline Documentation

## Overview

This document describes the Continuous Integration and Continuous Deployment (CI/CD) pipeline setup for the Java Selenium TestNG Cucumber automation framework using GitHub Actions.

## Pipeline Architecture

### Main Components

1. **Main CI/CD Pipeline** (`.github/workflows/ci-cd.yml`)
   - Automated builds and tests on push/PR
   - Multi-browser testing (Chrome, Firefox)
   - Parallel execution
   - Comprehensive reporting
   - Artifact management

2. **Manual Test Execution** (`.github/workflows/manual-test-execution.yml`)
   - On-demand test execution
   - Flexible parameter configuration
   - Custom test suite selection

3. **Supporting Files**
   - `src/main/resources/ci-config.properties` - CI-specific configuration
   - `scripts/run-tests.sh` - Local test execution script

## Workflows

### 1. Main CI/CD Pipeline

**Triggers:**
- Push to `master`, `develop`, or `main` branches
- Pull requests to these branches
- Manual dispatch with custom parameters

**Jobs:**
- **Build & Test**: Compiles code and runs tests across browser matrix
- **Smoke Tests**: Quick validation tests for PRs
- **Security Scan**: OWASP dependency check (master branch only)
- **Performance Tests**: Performance validation (master branch only)
- **Deploy Reports**: Publishes test reports to GitHub Pages
- **Notify**: Sends execution summary notifications

**Matrix Strategy:**
```yaml
strategy:
  matrix:
    browser: [chrome, firefox]
```

### 2. Manual Test Execution

**Parameters:**
- **Test Suite**: smoke, regression, full, login, inventory, checkout
- **Browser**: chrome, firefox, edge
- **Environment**: staging, production, dev
- **Parallel Execution**: Enable/disable parallel execution
- **Thread Count**: Number of parallel threads (1-10)

## Configuration

### Environment Variables

The pipeline uses the following environment variables:

```yaml
env:
  MAVEN_OPTS: "-Xmx3072m -XX:MaxPermSize=512m"
  JAVA_VERSION: '21'
  BROWSER: ${{ matrix.browser }}
  HEADLESS: true
  TEST_ENVIRONMENT: ${{ github.event.inputs.environment || 'staging' }}
```

### System Properties

Tests can be configured using Maven system properties:

- `-Dbrowser=chrome|firefox|edge`
- `-Dheadless=true|false`
- `-Dcucumber.filter.tags="@login or @inventory"`
- `-Dparallel.execution=true|false`
- `-Dthread.count=3`
- `-Dtest.environment=staging|production|dev`

### Browser Configuration

#### Chrome (CI-optimized)
```bash
--headless --no-sandbox --disable-dev-shm-usage --disable-gpu --window-size=1920,1080
```

#### Firefox (CI-optimized)
```bash
--headless --width=1920 --height=1080
```

## Usage

### Automatic Execution

The pipeline automatically runs when:
1. Code is pushed to main branches
2. Pull requests are created/updated
3. Manual trigger via GitHub Actions UI

### Manual Execution

1. Go to GitHub Actions tab in your repository
2. Select "Manual Test Execution" workflow
3. Click "Run workflow"
4. Configure parameters:
   - Select test suite
   - Choose browser
   - Set environment
   - Configure parallel execution
5. Click "Run workflow"

### Local Execution

Use the provided script for local testing:

```bash
# Basic execution
./scripts/run-tests.sh

# Custom parameters
./scripts/run-tests.sh -b chrome -h -t "@login or @inventory"

# Parallel execution
./scripts/run-tests.sh --parallel --thread-count 2

# Production environment
./scripts/run-tests.sh --environment production --tags "@smoke"

# Help
./scripts/run-tests.sh --help
```

## Reports and Artifacts

### Generated Artifacts

1. **Test Reports**
   - TestNG HTML/XML reports
   - Cucumber JSON/HTML reports
   - ExtentReports (if configured)

2. **Screenshots**
   - Failure screenshots
   - Custom screenshots (if enabled)

3. **Logs**
   - Application logs
   - WebDriver logs
   - Test execution logs

4. **Security Reports**
   - OWASP dependency check reports

### Artifact Retention

- Test reports: 30 days
- Screenshots: 30 days (failures), 15 days (manual)
- Logs: 15 days
- Security reports: 30 days

### Accessing Reports

1. **GitHub Actions UI**:
   - Go to Actions tab
   - Select workflow run
   - Download artifacts from the summary page

2. **GitHub Pages** (for master branch):
   - Reports are automatically deployed
   - Access via: `https://[username].github.io/[repository]/test-reports/[run-number]/`

## Test Tagging Strategy

Use Cucumber tags to organize and filter tests:

### Recommended Tags

- `@smoke` - Critical functionality tests
- `@regression` - Full regression suite
- `@login` - Authentication tests
- `@inventory` - Product catalog tests
- `@checkout` - Purchase flow tests
- `@performance` - Performance tests
- `@ignore` - Temporarily disabled tests

### Tag Combinations

```gherkin
# Smoke tests
@smoke @login
Scenario: User can login successfully

# Regression tests
@regression @inventory
Scenario: User can view product details

# Performance tests
@performance @checkout
Scenario: Checkout process completes within 5 seconds
```

## Environment Management

### Configuration Files

1. **Main Config**: `src/main/resources/config.properties`
2. **CI Config**: `src/main/resources/ci-config.properties`

### Environment-Specific URLs

- **Development**: `https://www.saucedemo.com/v1/`
- **Staging**: `https://www.saucedemo.com/v1/`
- **Production**: `https://www.saucedemo.com/`

### Secrets Management

Store sensitive data as GitHub Secrets:

1. Go to repository Settings → Secrets and variables → Actions
2. Add secrets:
   - `TEST_USERNAME`
   - `TEST_PASSWORD`
   - `SLACK_WEBHOOK` (for notifications)

## Troubleshooting

### Common Issues

#### 1. Tests Failing in CI but Passing Locally

**Possible Causes:**
- Timing issues in headless mode
- Different screen resolution
- Missing browser dependencies

**Solutions:**
- Increase wait times in CI config
- Use explicit waits instead of implicit waits
- Verify browser installation in workflow

#### 2. Browser Not Found

**Error**: `WebDriverException: unknown error: cannot find Chrome binary`

**Solution**: Ensure browser installation step in workflow:
```yaml
- name: Install Chrome
  run: |
    sudo apt-get update
    sudo apt-get install -y google-chrome-stable
```

#### 3. Out of Memory Errors

**Error**: `java.lang.OutOfMemoryError`

**Solution**: Increase Maven memory:
```yaml
env:
  MAVEN_OPTS: "-Xmx4096m -XX:MaxPermSize=1024m"
```

#### 4. Parallel Execution Issues

**Problems**: Tests interfering with each other

**Solutions:**
- Use thread-safe WebDriver management
- Implement proper test isolation
- Reduce thread count if needed

### Debugging Tips

1. **Enable Debug Logging**:
   ```bash
   -Dlog.level=DEBUG
   ```

2. **Save Screenshots on All Steps**:
   ```bash
   -Dscreenshot.on.pass=true
   ```

3. **Run Tests Locally with CI Settings**:
   ```bash
   ./scripts/run-tests.sh -h --environment staging
   ```

4. **Check Browser Logs**:
   Enable browser logging in WebDriver configuration

## Performance Optimization

### CI Performance Tips

1. **Use Caching**:
   - Maven dependencies are cached automatically
   - Browser binaries can be cached

2. **Parallel Execution**:
   - Use matrix strategy for different browsers
   - Enable parallel test execution within jobs

3. **Selective Test Execution**:
   - Use tags to run only relevant tests
   - Implement smart test selection based on changes

4. **Resource Management**:
   - Optimize memory usage
   - Use appropriate thread counts

### Recommended Matrix Strategy

```yaml
strategy:
  matrix:
    browser: [chrome, firefox]
    test-suite: [smoke, regression]
  max-parallel: 4
```

## Monitoring and Notifications

### GitHub Actions Status

- Status badges in README
- Email notifications (GitHub settings)
- Slack integration (using webhooks)

### Metrics to Monitor

1. **Test Execution Time**
2. **Test Pass/Fail Rates**
3. **Flaky Test Detection**
4. **Browser Performance**
5. **Resource Usage**

## Best Practices

### 1. Code Quality

- Use static code analysis
- Implement code coverage reporting
- Follow naming conventions

### 2. Test Design

- Write atomic, independent tests
- Use appropriate wait strategies
- Implement proper error handling

### 3. CI/CD Pipeline

- Keep workflows DRY (Don't Repeat Yourself)
- Use secrets for sensitive data
- Implement proper artifact management

### 4. Documentation

- Keep this README updated
- Document pipeline changes
- Maintain troubleshooting guides

## Future Enhancements

### Planned Features

1. **Cross-browser Testing**:
   - Safari support
   - Mobile browser testing

2. **Advanced Reporting**:
   - Allure reports integration
   - Historical trend analysis

3. **Test Distribution**:
   - Selenium Grid integration
   - Cloud testing platforms

4. **AI/ML Integration**:
   - Smart test selection
   - Failure prediction

### Integration Opportunities

- **SonarQube**: Code quality analysis
- **JIRA**: Test case management
- **Slack/Teams**: Real-time notifications
- **AWS/Azure**: Cloud infrastructure

## Support

For issues or questions regarding the CI/CD pipeline:

1. Check this documentation
2. Review workflow logs in GitHub Actions
3. Create an issue in the repository
4. Contact the QA automation team

---

**Last Updated**: [Current Date]
**Version**: 1.0.0 
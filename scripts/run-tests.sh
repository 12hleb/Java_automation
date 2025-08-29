#!/bin/bash

# Test Execution Script for Local Development and CI Debugging
# Usage: ./run-tests.sh [options]

set -e

# Default values
BROWSER="chrome"
HEADLESS="false"
TAGS="@login"
ENVIRONMENT="staging"
THREAD_COUNT="3"
PARALLEL="false"
CLEAN="true"
REPORTS="true"

# Colors for output
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
NC='\033[0m' # No Color

# Function to print colored output
print_info() {
    echo -e "${BLUE}[INFO]${NC} $1"
}

print_success() {
    echo -e "${GREEN}[SUCCESS]${NC} $1"
}

print_warning() {
    echo -e "${YELLOW}[WARNING]${NC} $1"
}

print_error() {
    echo -e "${RED}[ERROR]${NC} $1"
}

# Function to show usage
show_usage() {
    echo "Usage: $0 [OPTIONS]"
    echo ""
    echo "Options:"
    echo "  -b, --browser BROWSER      Browser to use (chrome, firefox, edge) [default: chrome]"
    echo "  -h, --headless             Run in headless mode [default: false]"
    echo "  -t, --tags TAGS            Cucumber tags to run [default: @login]"
    echo "  -e, --environment ENV      Environment to test (dev, staging, production) [default: staging]"
    echo "  -p, --parallel             Enable parallel execution [default: false]"
    echo "  -c, --thread-count COUNT   Number of parallel threads [default: 3]"
    echo "  --no-clean                 Skip clean before running tests [default: false]"
    echo "  --no-reports               Skip report generation [default: false]"
    echo "  --help                     Show this help message"
    echo ""
    echo "Examples:"
    echo "  $0 -b chrome -h -t '@login or @inventory'"
    echo "  $0 --browser firefox --parallel --thread-count 2"
    echo "  $0 --environment production --tags '@smoke'"
}

# Parse command line arguments
while [[ $# -gt 0 ]]; do
    case $1 in
        -b|--browser)
            BROWSER="$2"
            shift 2
            ;;
        -h|--headless)
            HEADLESS="true"
            shift
            ;;
        -t|--tags)
            TAGS="$2"
            shift 2
            ;;
        -e|--environment)
            ENVIRONMENT="$2"
            shift 2
            ;;
        -p|--parallel)
            PARALLEL="true"
            shift
            ;;
        -c|--thread-count)
            THREAD_COUNT="$2"
            shift 2
            ;;
        --no-clean)
            CLEAN="false"
            shift
            ;;
        --no-reports)
            REPORTS="false"
            shift
            ;;
        --help)
            show_usage
            exit 0
            ;;
        *)
            print_error "Unknown option: $1"
            show_usage
            exit 1
            ;;
    esac
done

# Validate browser
if [[ ! "$BROWSER" =~ ^(chrome|firefox|edge)$ ]]; then
    print_error "Invalid browser: $BROWSER. Must be chrome, firefox, or edge."
    exit 1
fi

# Validate environment
if [[ ! "$ENVIRONMENT" =~ ^(dev|staging|production)$ ]]; then
    print_error "Invalid environment: $ENVIRONMENT. Must be dev, staging, or production."
    exit 1
fi

# Print configuration
print_info "Test Execution Configuration:"
echo "  Browser: $BROWSER"
echo "  Headless: $HEADLESS"
echo "  Tags: $TAGS"
echo "  Environment: $ENVIRONMENT"
echo "  Parallel: $PARALLEL"
echo "  Thread Count: $THREAD_COUNT"
echo "  Clean Build: $CLEAN"
echo "  Generate Reports: $REPORTS"
echo ""

# Check if Maven is installed
if ! command -v mvn &> /dev/null; then
    print_error "Maven is not installed or not in PATH"
    exit 1
fi

# Check if Java is installed
if ! command -v java &> /dev/null; then
    print_error "Java is not installed or not in PATH"
    exit 1
fi

# Print Java and Maven versions
print_info "Environment Information:"
java -version 2>&1 | head -1
mvn -version | head -1
echo ""

# Create necessary directories
print_info "Creating necessary directories..."
mkdir -p screenshots
mkdir -p logs
mkdir -p reports
mkdir -p target/cucumber-reports

# Clean build if requested
if [[ "$CLEAN" == "true" ]]; then
    print_info "Cleaning previous build..."
    mvn clean
fi

# Compile the project
print_info "Compiling the project..."
mvn compile test-compile

# Prepare Maven command
MVN_CMD="mvn test"
MVN_CMD="$MVN_CMD -Dbrowser=$BROWSER"
MVN_CMD="$MVN_CMD -Dheadless=$HEADLESS"
MVN_CMD="$MVN_CMD -Dcucumber.filter.tags=\"$TAGS\""
MVN_CMD="$MVN_CMD -Dtest.environment=$ENVIRONMENT"
MVN_CMD="$MVN_CMD -Dmaven.test.failure.ignore=true"

if [[ "$PARALLEL" == "true" ]]; then
    MVN_CMD="$MVN_CMD -Dparallel.execution=true"
    MVN_CMD="$MVN_CMD -Dthread.count=$THREAD_COUNT"
fi

# Set environment variables
export BROWSER=$BROWSER
export HEADLESS=$HEADLESS
export TEST_ENVIRONMENT=$ENVIRONMENT

print_info "Executing tests..."
print_info "Command: $MVN_CMD"
echo ""

# Execute tests
if eval $MVN_CMD; then
    print_success "Tests completed successfully!"
else
    print_warning "Tests completed with failures. Check reports for details."
fi

# Generate reports summary
if [[ "$REPORTS" == "true" ]]; then
    print_info "Generating reports summary..."
    
    # Check for TestNG reports
    if [[ -d "target/surefire-reports" ]]; then
        TESTNG_REPORTS=$(find target/surefire-reports -name "*.xml" | wc -l)
        print_info "TestNG reports generated: $TESTNG_REPORTS files"
    fi
    
    # Check for Cucumber reports
    if [[ -d "target/cucumber-reports" ]]; then
        if [[ -f "target/cucumber-reports/CucumberTestReport.json" ]]; then
            print_info "Cucumber JSON report generated"
        fi
        if [[ -f "target/cucumber-reports/cucumber-pretty.html" ]]; then
            print_info "Cucumber HTML report generated"
        fi
    fi
    
    # Check for screenshots
    SCREENSHOT_COUNT=$(find screenshots -name "*.png" 2>/dev/null | wc -l)
    if [[ $SCREENSHOT_COUNT -gt 0 ]]; then
        print_info "Screenshots captured: $SCREENSHOT_COUNT"
    fi
    
    # Check for logs
    if [[ -d "logs" ]]; then
        LOG_COUNT=$(find logs -name "*.log" 2>/dev/null | wc -l)
        if [[ $LOG_COUNT -gt 0 ]]; then
            print_info "Log files generated: $LOG_COUNT"
        fi
    fi
fi

print_success "Test execution completed!"
print_info "Check the following locations for results:"
echo "  - TestNG Reports: target/surefire-reports/"
echo "  - Cucumber Reports: target/cucumber-reports/"
echo "  - Screenshots: screenshots/"
echo "  - Logs: logs/" 
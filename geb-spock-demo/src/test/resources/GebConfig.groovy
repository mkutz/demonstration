import org.openqa.selenium.firefox.FirefoxDriver
/*
 * Configuration script for Geb tests. Configuration values might be overwritten using system properties or may be
 * during runtime in Groovy code.
 * 
 * See http://www.gebish.org/manual/current/configuration.html for further details.
 */

/* put a directory to put reports in here (only filled by ReportingSpecs */
reportsDir = "target/geb-reports"
/* only report if test failed (not working for Spock yet) */
reportOnTestFailureOnly = false

/* clear cookies after each test */
autoClearCookies = true

/* Implicit waiting timings of any element */
waiting {
    timeout = 10
    retryInterval = 0.5
}

baseUrl = "http://localhost"

seleniumUrl = "http://10.86.2.36:4444/wd/hub"

/* use this driver for testing */
driver = { new FirefoxDriver() }

environments {
    'local' {
        baseUrl = "http://localhost/"
    }
    'pre-prod' {
        baseUrl = "http://preprod.myapp.com/"
    }
}

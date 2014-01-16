import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.htmlunit.HtmlUnitDriver
import org.openqa.selenium.remote.DesiredCapabilities
import org.openqa.selenium.remote.RemoteWebDriver

/*
* Configuration script for Geb tests. Configuration values might be overwritten using system properties or may be
* during runtime in Groovy code.
*
* See http://www.gebish.org/manual/current/configuration.html for further details.
*/

/* put a directory to put reports in here (only filled by ReportingSpecs */
reportsDir = "target/geb-reports"

/* only report if test failed (not working for Spock yet) */
reportOnTestFailureOnly = true

/* clear cookies after each test */
autoClearCookies = true

/* Implicit waiting timings of any element */
waiting {
    timeout = 10
    retryInterval = 0.5
}

serverUrl = "http://selenium.allesklar.de:4444/wd/hub"

/* Default driver */
//driver = { new RemoteWebDriver(new URL(serverUrl), DesiredCapabilities.chrome()) }
driver = { new RemoteWebDriver(new URL(serverUrl), DesiredCapabilities.firefox()) }

/* to be able to use multiple browsers multi threaded */
cacheDriverPerThread = true

environments {
    "headless" {
        driver = { new HtmlUnitDriver(true) }
    }

    "local-ff" {
        driver = { new FirefoxDriver() }
    }

    "remote-ff" {
        driver = { new RemoteWebDriver(new URL(serverUrl), DesiredCapabilities.firefox()) }
    }

    "remote-chrome" {
        driver = { new RemoteWebDriver(new URL(serverUrl), DesiredCapabilities.chrome()) }
    }

    "remote-ie" {
        driver = { new RemoteWebDriver(new URL(serverUrl), DesiredCapabilities.internetExplorer()) }
    }

    "remote-safari" {
        driver = { new RemoteWebDriver(new URL(serverUrl), DesiredCapabilities.safari()) }
    }

    "remote-emulated-android" {
        driver = { new RemoteWebDriver(new URL(serverUrl), DesiredCapabilities.android()) }
    }
}
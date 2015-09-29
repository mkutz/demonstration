import org.openqa.selenium.Platform
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.phantomjs.PhantomJSDriver

/*
 * Configuration script for Geb tests. Configuration values might be overwritten using system properties or may be
 * during runtime in Groovy code.
 * 
 * See http://www.gebish.org/manual/current/configuration.html for further details.
 */

/* put a directory to put reports in here (only filled by ReportingSpecs */
reportsDir = "target/geb-reports"

Map<Platform, String> phantomJsPath = [
        (Platform.LINUX): "target/phantomjs-maven-plugin/phantomjs-1.9.8-linux-x86_64/bin/phantomjs",
        (Platform.MAC): null, (Platform.WINDOWS): null

]


/* should prevent at checkers to fail when page is updated via JS */
atCheckWaiting = true

/* should prevent false NoSuchElementExceptions in Firefox */
baseNavigatorWaiting = true

/* close the browser at test end */
quitCachedDriverOnShutdown = true

/* waiting */
waiting {
    timeout = 5
    retryInterval = 0.1

    presets {
        slow {
            timeout = 7.5
            retryInterval = 0.5
        }
        quick {
            timeout = 1
        }
    }

    includeCauseInMessage = true // prevent surefire/failsafe from only showing WaitTimeoutException but the cause
}


/*
 * set system property for path to PhantomJS binary (currently not added by the maven plugin
 * see https://github.com/klieber/phantomjs-maven-plugin/issues/39
 */
System.setProperty('phantomjs.binary.path', phantomJsPath[Platform.current])

/* use this driver for testing */
driver = {
    new PhantomJSDriver()
}

environments {
    'firefox' {
        driver = {
            new FirefoxDriver()
        }
    }
    'chrome' {
        System.setProperty('webdriver.chrome.driver', '/Users/tomaslin/drivers/chromedriver')
        driver = {
            new ChromeDriver()
        }
    }
    'phantomjs' {
        driver = {
            new PhantomJSDriver()
        }
    }
}

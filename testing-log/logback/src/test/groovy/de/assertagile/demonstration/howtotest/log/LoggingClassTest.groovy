package de.assertagile.demonstration.howtotest.log

import ch.qos.logback.classic.Level
import ch.qos.logback.classic.Logger
import ch.qos.logback.classic.spi.LoggingEvent
import ch.qos.logback.core.Appender
import org.slf4j.LoggerFactory
import spock.lang.Specification

class LoggingClassTest extends Specification {

    List<LoggingEvent> loggingEvents = []

    Appender<LoggingEvent> appenderMock = Mock() {
        _ * getName() >> "MOCK"
        _ * doAppend({ loggingEvents << it })
    }

    void setup() {
        Logger root = LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME) as Logger
        root.addAppender(appenderMock)
    }

    void "log verification via appenderMock should work"() {
        given:
        LoggingEvent event = null

        when:
        new LoggingClass()

        then:
        1 * appenderMock.doAppend({ event = it })
        event.level == Level.DEBUG
    }

    void "log events should be added to the loggingEvents list"() {
        when:
        new LoggingClass()

        then:
        loggingEvents.size() == 1
        loggingEvents.find { it.level == Level.DEBUG && it.message == "Hello" }
    }
}

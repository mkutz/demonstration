package de.assertagile.demonstration.howtotest.spock

import spock.lang.Ignore
import spock.lang.Specification

class MockingDemoSpec extends Specification {

    def "mocking with spock is easy"() {
        given:
        String expectedString = "abc"

        and:
        Object objectMock = Mock()
        objectMock.toString() >> expectedString

        expect:
        "${objectMock}" == expectedString
    }

    def "verification is quite legible"() {
        given:
        Object objectMock = Mock()
        1 * objectMock.toString()

        expect:
        "${objectMock}"
    }

    @Ignore("this does not work since the verification overwrites the mocking")
    def "since verification and mocking is the same in spock, be careful"() {
        given:
        String expectedString = "abc"

        and:
        Object objectMock = Mock()
        objectMock.toString() >> expectedString

        when:
        String string = "${objectMock}"

        then:
        string == expectedString

        and:
        1 * objectMock.toString() // by the way: this is executed BEFORE the when block!
    }


    // TODO argument matching
}
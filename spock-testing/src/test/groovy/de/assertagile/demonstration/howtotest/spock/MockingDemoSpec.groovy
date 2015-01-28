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
        Object objectMock = Mock() {
            toString() >> expectedString
        }

        when:
        String string = "${objectMock}"

        then:
        string == expectedString

        and:
        1 * objectMock.toString() // by the way: this is executed BEFORE the when block!
    }


    def "argument matching"() {
        given:
        String expectedArgument = "abc"

        and:
        List<String> listMock = Mock()

        when:
        listMock.contains(expectedArgument)

        then:
        1 * listMock.contains(expectedArgument)
    }

    def "complex argument matching"() {
        given:
        Map<String, Object> mapMock = Mock()

        when:
        mapMock.put("bla", ["a", "b", "c"])

        then:
        1 * mapMock.put(_, { it[1] == "b" })
    }

    def "get complex arguments for closer inspection"() {
        given:
        List<String> extractedArgument = null
        Map<String, Object> mapMock = Mock()

        when:
        mapMock.put("bla", ["a", "b", "c"])

        then:
        1 * mapMock.put(_ as String, { List<Map> it -> extractedArgument = it })
        extractedArgument.contains("c")
        extractedArgument.size() == 3 // => better assertion feedback
    }
}
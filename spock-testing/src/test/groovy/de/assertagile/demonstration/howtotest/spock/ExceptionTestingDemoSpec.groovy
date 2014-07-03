package de.assertagile.demonstration.howtotest.spock

import spock.lang.Specification

class ExceptionTestingDemoSpec extends Specification {

    def "testing exception behaviour with spock is great too"() {
        when:
        new File("some/place/no/file/can/be/found/at").append("text")

        then:
        thrown(FileNotFoundException)
    }

    def "you can also verify the exception since it is returned by thrown"() {
        given:
        String wrongPath = "some/place/no/file/can/be/found/at"

        when:
        new File(wrongPath).append("text")

        then:
        FileNotFoundException e = thrown()

        and:
        e.message ==~ /^${wrongPath}.+/
    }
}
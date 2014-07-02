package de.assertagile.demonstration.howtotest.spock

import spock.lang.Specification

/**
 * Created by mkutz on 02.07.14.
 */
class MyClassSpec extends Specification {

    def "blocks make tests more structured"() {
        given:
        int testValue = 1

        when:
        testValue++

        then:
        testValue == 2
    }

    def "this demonstrates mocking"() {

    }

    def "blocks can also change the execution order"() {
        given:
        int testValue = 1 // as expected given is executed first

        when:
        testValue++ // when is executed AFTER old() was called!

        then:
        testValue == old(testValue) + 1 // old is executed BEFORE when to record the original value!
    }

    def "expect combines when and then"() {
        expect:
        1 != 2
    }
}

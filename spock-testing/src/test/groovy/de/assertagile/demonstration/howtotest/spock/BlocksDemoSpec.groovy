package de.assertagile.demonstration.howtotest.spock

import spock.lang.Specification

class BlocksDemoSpec extends Specification {

    def "given, when and then make tests more structured"() {
        given:
        int testValue = 1

        when:
        testValue++

        then:
        testValue == 2
    }

    def "expect combines when and then"() {
        expect:
        1 != 2
    }

    def "blocks can also change the execution order"() {
        given:
        int testValue = 1 // as expected given is executed first

        when:
        testValue++ // when is executed AFTER old() was called!

        then:
        testValue == old(testValue) + 1 // old is executed BEFORE when to record the original value!
    }
}


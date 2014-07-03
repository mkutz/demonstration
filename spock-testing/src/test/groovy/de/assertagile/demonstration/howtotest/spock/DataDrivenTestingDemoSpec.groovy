package de.assertagile.demonstration.howtotest.spock

import spock.lang.Specification
import spock.lang.Unroll

class DataDrivenTestingDemoSpec extends Specification {

    def "where blocks for data driven testing"(int testValue) {
        when:
        testValue++

        then:
        testValue == old(testValue) + 1

        where:
        testValue << [1, 100, 999999999]
    }

    @Unroll
    def "unroll gives you more information what caused a test to fail"(int testValue) {
        when:
        testValue++

        then:
        testValue == old(testValue) + 1

        where:
        testValue << [1, 100, 999999999]
    }

    @Unroll("incrementing #testValue should add 1 to it")
    def "you can also modify the generated tests' names"(int testValue) {
        when:
        testValue++

        then:
        testValue == old(testValue) + 1

        where:
        testValue << [1, 100, 999999999]
    }

    @Unroll("incrementing #testValue should set it to #expectedValue")
    def "where blocks can also be in table form"(int testValue, int expectedValue) {
        when:
        testValue++

        then:
        testValue == expectedValue

        where:
        testValue | expectedValue
        1         | 2
        100       | 101
        999999999 | 1000000000
    }
}
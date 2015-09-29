package de.assertagile.demonstration

import de.assertagile.demonstration.pages.GooglePage
import geb.spock.GebReportingSpec

class SelectorsUiSpec extends GebReportingSpec {

    GooglePage googlePage = to GooglePage

    def "warmup test"() {
        expect:
        at googlePage
    }

    def "selectors with attributes are awefully slow"() {
        expect:
        googlePage.logoAttribute
    }

    def "selectors with css are quite fast"() {
        expect:
        googlePage.logoCss
    }

    def "selectors with By are also good"() {
        expect:
        googlePage.logoBy
    }
}

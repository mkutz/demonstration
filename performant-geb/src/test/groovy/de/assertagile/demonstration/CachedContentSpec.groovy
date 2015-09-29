package de.assertagile.demonstration

import de.assertagile.demonstration.pages.GooglePage
import geb.spock.GebReportingSpec

class CachedContentSpec extends GebReportingSpec {

    GooglePage googlePage = to GooglePage

    def "warmup test"() {
        expect:
        at googlePage
    }

    def "multiple requests are evaluated every time"() {
        expect:
        googlePage.logoCss
        googlePage.logoCss
        googlePage.logoCss
        googlePage.logoCss
        googlePage.logoCss
        googlePage.logoCss
    }

    def "we can prevent this with the cache attribute"() {
        expect:
        googlePage.logoCssCached
        googlePage.logoCssCached
        googlePage.logoCssCached
        googlePage.logoCssCached
        googlePage.logoCssCached
        googlePage.logoCssCached
    }
}

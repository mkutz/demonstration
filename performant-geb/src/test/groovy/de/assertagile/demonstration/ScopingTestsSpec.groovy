package de.assertagile.demonstration

import de.assertagile.demonstration.pages.GooglePage
import de.assertagile.spockframework.extensions.Scope
import geb.spock.GebReportingSpec

class ScopingTestsSpec extends GebReportingSpec {

    GooglePage googlePage = browser.to(GooglePage)

    @Scope("logo")
    def "this is important for the logo scope"() {
        expect:
        googlePage.logoCss
    }

    @Scope("search")
    def "this is important for the search"() {
        expect:
        googlePage.searchInput
    }
}

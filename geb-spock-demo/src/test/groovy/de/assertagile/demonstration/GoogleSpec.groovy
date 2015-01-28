package de.assertagile.demonstration

import spock.lang.Unroll
import geb.spock.GebReportingSpec
import de.assertagile.demonstration.pages.GoogleResultsPage
import de.assertagile.demonstration.pages.GoogleStartPage

public class GoogleSpec extends GebReportingSpec {

    def "when calling google.de the user should be at the start page"() {
        when:
        browser.to(GoogleStartPage)

        then:
        browser.at(GoogleStartPage)
    }

    def "typing should take the user to the results page"() {
        given:
        browser.to(GoogleStartPage)

        when:
        browser.page.queryInput = "a"

        then:
        browser.at(GoogleResultsPage)
    }

    @Unroll("searching for \"#somethingPopular[0..2]\" should suggest \"#somethingPopular\"")
    def "searching for something popular should suggest it"() {
        given:
        GoogleStartPage startPage = browser.to(GoogleStartPage)

        when:
        startPage.queryInput = somethingPopular[0..2]

        then:
        GoogleResultsPage resultsPage = browser.at(GoogleResultsPage)

        and:
        resultsPage.suggestions[0].text == somethingPopular
        resultsPage.suggestions[0].supplement == somethingPopular[3..-1]

        where:
        somethingPopular << [
            "amazon",
            "wikipedia",
            "youtube"
        ]
    }

    def "searching for \"ama\" should suggest \"amazon\" and return amazon.de as top result"() {
        given:
        browser.to(GoogleStartPage)

        when:
        browser.page.queryInput = "ama"

        then:
        browser.at(GoogleResultsPage)

        and:
        browser.page.resultListItems[0].title.contains("Amazon.de")
        browser.page.resultListItems[0].href.contains("amazon.de")
    }
}

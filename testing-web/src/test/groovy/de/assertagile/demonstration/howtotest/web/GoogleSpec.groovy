package de.assertagile.demonstration.howtotest.web

import de.assertagile.demonstration.howtotest.web.pages.GoogleResultsPage
import de.assertagile.demonstration.howtotest.web.pages.GoogleStartPage
import geb.spock.GebReportingSpec
import org.sikuli.basics.SikuliScript
import org.sikuli.script.Region
import org.sikuli.script.Screen
import spock.lang.Unroll

public class GoogleSpec extends GebReportingSpec {

    Screen screen = new Screen()

    def setup() {

    }

    def "when calling google.de the user should be at the start page"() {
        when:
        browser.to(GoogleStartPage)

        then:
        browser.at(GoogleStartPage)

        and:
        screen.find("src/test/resources/GoogleStartPage_QueryAndButtons.png").find("src/test/resources/GoogleStartPage_Query.png").type("testing google")

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
        browser.to(GoogleStartPage)

        when:
        browser.page.queryInput = somethingPopular[0..2]

        then:
        browser.at(GoogleResultsPage)

        and:
        browser.page.suggestions[0].text == somethingPopular
        browser.page.suggestions[0].supplement == somethingPopular[3..-1]

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

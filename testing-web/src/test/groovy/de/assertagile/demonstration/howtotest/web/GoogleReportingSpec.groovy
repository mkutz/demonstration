package de.assertagile.demonstration.howtotest.web

import de.assertagile.demonstration.howtotest.web.pages.GoogleResultsPage
import de.assertagile.demonstration.howtotest.web.pages.GoogleStartPage

public class GoogleSpec extends SikuliGebReportingSpec {

    def "Sikuli should work as a part of GebSpecs"() {
        given:
        browser.to(GoogleStartPage)
        browser.at(GoogleStartPage)

        when:
        (browser.page as GoogleStartPage).queryInput = "ama"

        then:
        browser.at(GoogleResultsPage)

        and:
        isImageOnPage("GoogleResultPage_Logo.png")

        and:
        !getAllImageMatches("GoogleStartPage_QueryAndButtons.png")
    }
}

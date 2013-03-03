package de.tarent.demo

import spock.lang.Ignore
import geb.spock.GebReportingSpec
import de.tarent.demo.pages.W3SchoolsDragAndDropPage

class DragAndDropSpec extends GebReportingSpec {

    @Ignore("Not working, yet.")
    def "dragging the dragable toward the second div should place it there"() {
        given:
        browser.to(W3SchoolsDragAndDropPage)

        when:
        browser.page.interact {
            dragAndDrop(browser.page.drag, browser.page.div2)
        }

        then:
        !browser.page.div1.has("img")
        browser.page.div2.has("img")
    }
}
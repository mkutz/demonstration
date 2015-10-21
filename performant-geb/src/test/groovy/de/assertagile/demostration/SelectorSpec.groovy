package de.assertagile.demostration

import geb.spock.GebSpec
import org.openqa.selenium.By

class SelectorSpec extends GebSpec {

    def setup() {
        browser.go("http://www.google.de")
    }

    def "initialize"() {
        expect:
        $("body")
    }

    def "attribute matching on id is slow"() {
        expect:
        10.times { $("input", id: "lst-ib") }
    }

    def "css selectors on id is generally are faster"() {
        expect:
        10.times { $("input#lst-ib") }
    }

    def "by.id selectors are fast as well"() {
        expect:
        10.times { $(By.id("lst-ib")) }
    }

    def "attribute matching on class is slow as well"() {
        expect:
        10.times { $("input", class: "gsfi") }
    }

    def "css selectors on class is generally are faster as well"() {
        expect:
        10.times { $("input.gsfi") }
    }

    def "attribute matching on non-id attributes is even slower"() {
        expect:
        10.times { $("input", title: "Suche") }
    }
}
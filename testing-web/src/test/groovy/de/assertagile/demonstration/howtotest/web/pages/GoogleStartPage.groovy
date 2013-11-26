package de.assertagile.demonstration.howtotest.web.pages

import geb.Page

class GoogleStartPage extends Page {

    static url = "http://www.google.de"

    static at = { $("div#lga") }

    static content = {
        queryInput { $("input#gbqfq") }
    }
}

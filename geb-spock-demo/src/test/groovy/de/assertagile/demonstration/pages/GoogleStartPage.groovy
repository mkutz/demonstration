package de.assertagile.demonstration.pages

import geb.Page

class GoogleStartPage extends Page {

    static url = "http://www.google.de"

    static at = { $("div#lga") }

    static content = {
        queryInput { $("input#lst-ib") }
    }
}

package de.tarent.demo.pages

import geb.Module
import geb.Page

class GoogleResultsPage extends Page {

    static at = { $("div#gbqfw") }

    static content = {
        queryInput { $("input#gbqfq") }
        resultList(wait: true) { $("ol#rso") }
        resultListItems(wait: true) { moduleList GoogleResultItem, $("ol#rso li") }
        suggestions(wait: true) { moduleList GoogleSearchSuggestion, $("table.gssb_m") }
    }
}

class GoogleResultItem extends Module {

    static content = {
        title { $("h3.r a.l").text() }
        href { $("h3.r a.l").@href }
        description(required: false) { $("span.st") }
    }
}

class GoogleSearchSuggestion extends Module {

    static content = {
        text { $("span").text() }
        supplement { $("span b").text() }
    }
}

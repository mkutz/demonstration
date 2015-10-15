package de.assertagile.demonstration.pages

import geb.Module
import geb.Page

class GoogleResultsPage extends Page {

    static at = { $("a#logo") }

    static content = {
        queryInput { $("input#gbqfq") }
        resultList(wait: true) { $("ol#rso") }
        resultListItems(wait: true) { $("ol#rso div.g").collect { it.module(GoogleResultItem) } }
        suggestions(wait: true) { $("ul.sbsb_b").collect { it.module(GoogleSearchSuggestion) } }
    }
}

class GoogleResultItem extends Module {

    static content = {
        title { $("h3.r").text() }
        href { $("cite._Rm").text() }
        description(required: false) { $("div.s") }
    }
}

class GoogleSearchSuggestion extends Module {

    static content = {
        fullText { $("div.sbqs_c").text() }
        supplement { $("div.sbqs_c b").text() }
    }
}

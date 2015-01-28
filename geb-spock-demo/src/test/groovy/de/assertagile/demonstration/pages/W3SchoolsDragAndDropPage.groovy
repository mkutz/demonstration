package de.assertagile.demonstration.pages

import geb.Page

class W3SchoolsDragAndDropPage extends Page {

    static url = "http://www.w3schools.com/html/html5_draganddrop.asp"

    static content = {
        div1 { $("div#div1") }
        div2 { $("div#div2") }
        drag { $("img#drag1") }
    }
}

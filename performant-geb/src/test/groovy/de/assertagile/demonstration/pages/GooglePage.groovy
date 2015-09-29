package de.assertagile.demonstration.pages

import geb.Page
import org.openqa.selenium.By

class GooglePage extends Page {

    static url = "https://www.google.com/"

    static at = {
        logoCss
    }

    static content = {
        logoAttribute { $(id: "hplogo") }
        logoCss { $("#hplogo") }
        logoBy { $(By.id("hplogo")) }

        logoCssCached(cache: true) { $("#hplogo") }
    }
}

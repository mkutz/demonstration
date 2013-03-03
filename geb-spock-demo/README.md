# Groovy web testing with Geb and Spock

## Facts
* web testing framework for Groovy
* using Selenium WebDriver for browser automation
* may be combinded with other testing frameworks like *Spock*, JUnit, TestNG 
* Webseite: [gebhome]
* current version: 0.7.2 (0.9.0-RC-1 with support Spock 0.7 and Groovy 2.x)

## Some Advantages
* uses Groovy and combines well with Spock full power of it, i.e. combined back- and frontend testing
* provides page and module classes to abstract from page structurs
* ... TODO

## Demonstation Contents
This demonstation project is using Maven and combines Geb with Spock.

### General Poject Setup
... TODO

### Page Classes
1. Geb/Spock tests must subclass *Geb(Reporting)Spec*.
   The Class provides a `browser` object which is a wrapper for a Selenium `WebDriver`
2. *Page objects* abstract the page structure from the test (the workflow).
   The Page The `GoogleStartPage` defines the structur of Google's start page;
   `GoogleResultsPage` defindes the results page.
3. ... TODO

[gebhome]: http://www.gebish.org/ "Geb's homepage"
[gebmanual]: http://www.gebish.org/manual/0.7.2/ "The Book of Geb"

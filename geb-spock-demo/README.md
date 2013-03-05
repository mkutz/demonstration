# Groovy web testing with Geb and Spock

## Facts
* web testing framework for Groovy
* using Selenium WebDriver for browser automation
* may be combinded with other testing frameworks like *Spock* (see [spock]), JUnit, TestNG 
* Webseite: [gebhome], Manual: [gebmanual]
* current release version: 0.7.2; *release candidate: 0.9.0-RC-1 (support Spock 0.7 and Groovy 2.x)*

## Some Advantages
* Uses Groovy and combines well with Spock full power of it, i.e. combined back- and frontend testing. For instance (true story):
  * Fill a registration form using Geb,
  * than use an IMAP library to check for the confimation mail,
  * than use Groovy's regex powers to parse the confirmation link
  * and than again use Geb to call that link.
* Provides page and module classes to abstract from page structurs.
* May (and IMHO should) be combined with Spock.
* ...

## Demonstation
This demonstation project is using Maven and combines Geb with Spock.

### Maven Setup
* Needed dependencies: [Groovy, Spock, Selenium and Geb](pom.xml#L270-L292). Other dependencies are optional and provide some generally good mocking magic -- though are not *needed*.
* [maven-compiler-plugin](pom.xml#L61-L86) *needs to be* configured to use `groovy-eclipse-compiler` to compile `.groovy` classes.
* [maven-surefire-plugin](pom.xml#L88-L99) *may be* configured to regard `*Spec` classes as tests (not only `*Test*`).
* [build-helper-maven-plugin](pom.xml#L101-L134) *may be* used to name several test/source folders -- in this case for Java *and* Groovy tests/sources. Also helps IDE's to find the folders.
* [maven-eclipse-plugin](pom.xml#L136-L153) *may be* used for better integration in Eclipse using `mvn eclipse:eclipse`.

### Geb Configuration
[GebConfig.groovy](src/test/resources/GebConfig.groovy) allows to configure several details of Geb's behaviour. There are other ways linke using the [pom.xml](pom.xml) etc. See [gebmanual] for more details.

### Geb Spec's
Geb/Spock tests must subclass *Geb(Reporting)Spec*. This provides a `browser` object which is a wrapper for a Selenium `WebDriver`

### Page Classes
*Page classes* abstract the page structure from the test (the workflow).
The [GoogleStartPage](src/test/groovy/de/tarent/demo/pages/GoogleStartPage.groovy) defines the structur of Google's start page:
* The `url` tells the browser how to get to the page (used [here](src/test/groovy/de/tarent/demo/GoogleSpec.groovy#L12)).
* The `at` defines what is expected to be present when the browser is at the page (used [here](src/test/groovy/de/tarent/demo/GoogleSpec.groovy#L15)).
* The `content` describes the structure of the page. It gives aliases to all the element we want to interact with -- so when the structure of the page changes we may only change the Page but not the Spec.
* Elements are generally addressed using the `$` method (like in JQuery).

### Module Classes
Modules are like Pages but reusable across differend Pages and/or on the same Page. The [GoogleResultsPage](src/test/groovy/de/tarent/demo/pages/GoogleResultsPage.groovy#L18-L25) uses them to define the structure of a Google result item.

### Filling Forms
Geb provides several convenience shortcuts to fill forms; as demonstrated [here](src/test/groovy/de/tarent/demo/GoogleSpec.groovy#L23).

[gebhome]: http://www.gebish.org/ "Geb's homepage"
[gebmanual]: http://www.gebish.org/manual/0.7.2/ "The Book of Geb"
[spock]: http://spockframework.org/ "Spock homepage"

package de.assertagile.demonstration.howtotest.spock
import spock.lang.IgnoreIf
import spock.lang.Requires
import spock.lang.Specification
/**
 * Most of this is taken from
 * http://stackoverflow.com/questions/18636421/executing-specific-geb-tests-according-to-environement
 */
class ConditialExecutionSpec extends Specification {

    @Requires({ System.getProperty("geb.env") == "local" })
    def "this requires a certain geb.env"() {
        expect:
        false
    }

    @Requires(LocalCondition)
    def "if you oftern require one thing, you can use a Closure class"() {
        expect:
        false
    }

    @IgnoreIf(PreProdCondition)
    def "the same works in IgnoreIf annotations"() {
        expect:
        true
    }
}

class LocalCondition extends Closure<Boolean> {

    LocalCondition(Object owner, Object thisObject) { super(owner, thisObject) }

    Boolean call() {
        System.getProperty("geb.env") == "local"
    }
}

class PreProdCondition extends Closure<Boolean> {

    PreProdCondition(Object owner, Object thisObject) { super(owner, thisObject) }

    Boolean call() {
        System.getProperty("geb.env") == "pre-prod"
    }
}

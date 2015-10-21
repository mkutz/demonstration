package de.assertagile.demonstration
import geb.ConfigurationLoader
import geb.spock.GebSpec
import spock.lang.IgnoreIf
import spock.lang.Requires
/**
 * Most of this is taken from
 * http://stackoverflow.com/questions/18636421/executing-specific-geb-tests-according-to-environement
 */
class ConditionalExecutionSpec extends GebSpec {

    @Requires(LocalCondition)
    def "this requires a certain geb.env"() {
        expect:
        true
    }

    @Requires(PreProdCondition)
    def "if you oftern require one thing, you can use a Closure class"() {
        expect:
        false
    }

    @IgnoreIf(LocalCondition)
    def "the same works in IgnoreIf annotations"() {
        expect:
        false
    }
}

class LocalCondition extends Closure<Boolean> {

    String prefix = "http://localhost"

    LocalCondition(Object owner, Object thisObject) { super(owner, thisObject) }

    Boolean call() {
        new ConfigurationLoader().conf.getBaseUrl().startsWith(prefix)
    }
}

class PreProdCondition extends Closure<Boolean> {

    String prefix = "http://preprod"

    PreProdCondition(Object owner, Object thisObject) { super(owner, thisObject) }

    Boolean call() {
        new ConfigurationLoader().conf.getBaseUrl().startsWith(prefix)
    }
}

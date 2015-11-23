package de.assertagile.demonstration

class ExtendedGebConfigSpec extends MyBaseGebSpec {

    def "getting my own config parameters should work"() {
        expect:
        !getConfig().myOwnFalseParameter
        getConfig().getMyOwnIntParameter() == 42
        getConfig().getMyOwnStringParameter() == "myOwn"
    }

    def "overwriting values is other environments should be possible as well"() {
        given:
        gebConfEnv = 'local'

        expect:
        !getConfig().myOwnFalseParameter
        getConfig().getMyOwnIntParameter() == 23
        getConfig().getMyOwnStringParameter() == "myOwn"
    }
}

package de.assertagile.demonstration

import geb.spock.GebReportingSpec

class MyBaseGebSpec extends GebReportingSpec {

    protected ExtendedGebConfiguration getConfig() {
        new ExtendedGebConfiguration(createConf())
    }

    @Override
    ExtendedGebConfiguration createConf() {
        return new ExtendedGebConfigurationLoader(gebConfEnv, System.properties, new GroovyClassLoader(getClass().classLoader)).getConf(gebConfScript)
    }

}

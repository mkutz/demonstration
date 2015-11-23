package de.assertagile.demonstration

import geb.Configuration
import geb.spock.GebReportingSpec

abstract class MyBaseGebSpec extends GebReportingSpec {

    @Override
    protected ExtendedGebConfiguration getConfig() {
        new ExtendedGebConfiguration(createConf())
    }

    @Override
    Configuration createConf() {
        new ExtendedGebConfigurationLoader(gebConfEnv, System.properties, new GroovyClassLoader(getClass().classLoader)).getConf(gebConfScript)
    }
}

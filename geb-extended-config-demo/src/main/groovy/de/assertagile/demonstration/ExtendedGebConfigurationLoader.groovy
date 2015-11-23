package de.assertagile.demonstration

import geb.Configuration
import geb.ConfigurationLoader
import geb.error.UnableToLoadException

class ExtendedGebConfigurationLoader extends ConfigurationLoader {

    ExtendedGebConfigurationLoader() {
    }

    ExtendedGebConfigurationLoader(String environment) {
        super(environment)
    }

    ExtendedGebConfigurationLoader(String environment, Properties properties, GroovyClassLoader classLoader) {
        super(environment, properties, classLoader)
    }

    @Override
    Configuration getConf() throws UnableToLoadException {
        return new ExtendedGebConfiguration(super.getConf());
    }
}

package de.assertagile.demonstration
import geb.Configuration

class ExtendedGebConfiguration extends Configuration {

    public ExtendedGebConfiguration(Configuration gebConfig) {
        super(gebConfig.rawConfig, gebConfig.properties, gebConfig.buildAdapter, gebConfig.classLoader)
    }

    public boolean isMyOwnFalseParameter() {
        readValue(rawConfig, "myOwnFalseParameter", false)
    }

    public Integer getMyOwnIntParameter() {
        readValue(rawConfig.get("myOwn"), "intParameter", 0) as Integer;
    }

    public String getMyOwnStringParameter() {
        readValue(rawConfig.get("myOwn"), "stringParameter", "");
    }
}

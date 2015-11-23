# Demonstration on extending GebConfig.groovy with own parameters

This project demonstrates a way how to extend the [GebConfig.groovy](http://www.gebish.org/manual/current/#the-config-script) script with your own custom config parameters.

In my case this was to configure a [Wiremock](http://wiremock.org/) standalone mock service endpoint which was different for the different environments and might even not be there in certain cases.

## Concept

I just created two classes
- [ExtendedGebConfiguration](src/main/groovy/de/assertagile/demonstration/ExtendedGebConfiguration.groovy), which overrides the Configuration class of Geb, to provide additional config getters
- [ExtendedGebConfigurationLoader](src/main/groovy/de/assertagile/demonstration/ExtendedGebConfigurationLoader.groovy), which overrides the ConfigationLoader class of Geb, to return a ExtendedGebConfiguration instead of a Configuration

To make my tests use these classes, I just had to create my own abstract base test class [MyBaseGebSpec](src/test/groovy/de/assertagile/demonstration/MyBaseGebSpec.groovy) and override `getConfig` and `createConfig` to return my own classes.

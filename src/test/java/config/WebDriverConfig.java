package config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:${env}.properties",
})

public interface WebDriverConfig extends Config {

    @Key("remote")
    String getRemoteWebDriver();

    @Key("isRemote")
    @DefaultValue("false")
    boolean isRemote();
}

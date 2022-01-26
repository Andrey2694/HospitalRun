package config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "file:src/test/resources/local.properties"
})
public interface ProjectConfig extends Config {
    @Key("username")
    String username();

    @Key("password")
    String password();

    @Key("browser")
    @DefaultValue("chrome")
    String browser();
}

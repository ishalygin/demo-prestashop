 package config.interfaces;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.ConfigFactory;
import org.aeonbits.owner.Reloadable;


@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "system:env",
        "file:src/test/resources/general.properties"
})
public interface Props extends Reloadable {

    Props props = ConfigFactory.create(Props.class);

    @Key("demo.prestashop.url")
    String demoPrestashopUrl();

}
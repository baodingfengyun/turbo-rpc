package rpc.turbo.config.client;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import java.util.List;

public class ClientConfig {
    private List<AppConfig> appConfigList;

    public List<AppConfig> getAppConfigList() {
        return appConfigList;
    }

    public void setAppConfigList(List<AppConfig> appConfigList) {
        this.appConfigList = appConfigList;
    }

    @Override
    public String toString() {
        return "ClientConfig{" + "appConfigList=" + appConfigList + '}';
    }

    public static ClientConfig parse(String resourceName) {

        ClientConfig clientConfig = new ClientConfig();

        Config config = ConfigFactory.load(resourceName);

        List<AppConfig> appConfigList = AppConfig.parse(config.getConfigList("apps"));

        clientConfig.setAppConfigList(appConfigList);

        return clientConfig;
    }
}

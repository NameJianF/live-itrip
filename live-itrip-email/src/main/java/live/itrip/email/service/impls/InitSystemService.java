package live.itrip.email.service.impls;

import live.itrip.email.common.Config;
import live.itrip.common.Logger;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.util.Properties;

/**
 * Created by Feng on 2016/7/14.
 * <p/>
 * 1. 初始化系统配置参数
 */
@Service
public class InitSystemService {

    public InitSystemService() {
        initSystem();
    }

    private void initSystem() {
        this.loadConfig();
    }

    private void loadConfig() {
        try {
            Properties prop = new Properties();

            String file_name = this.getClass().getClassLoader()
                    .getResource("resource/local.properties").getFile();

            prop.load(new FileInputStream(file_name));

            Config.MODULE_APIKEY = prop.getProperty("module.apikey");
            Config.MODULE_SECRET = prop.getProperty("module.secret");
            Config.FILE_SAVE_PATH = prop.getProperty("file.save.path");

            Config.SERVER_HOST = prop.getProperty("server.host");
            Config.SERVER_PORT = prop.getProperty("server.port");
            Config.SERVER_USER_NAME = prop.getProperty("server.username");
            Config.SERVER_USER_PWD = prop.getProperty("server.pwd");

        } catch (Exception e) {
            Logger.error(e.getMessage());
        }
    }


}

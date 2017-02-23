package live.itrip.web.service.impls;

import live.itrip.web.common.Config;
import live.itrip.common.Logger;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.util.Properties;

/**
 * Created by Feng on 2016/7/14.
 * <p>
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

//            if (file_name.toString().startsWith("file:")) {
//
//                file_name = file_name.substring(5);
//                if (file_name.indexOf("!") != -1) {
//                    file_name = file_name.substring(0, file_name.indexOf("!"));
//                }
//
//                JarFile currentJar = new JarFile(file_name);
//                JarEntry configEntry = currentJar.getJarEntry("config_test.properties");
//
//                InputStream in = currentJar.getInputStream(configEntry);
//                if (in != null) {
//                    prop.load(in);
//                }
//
//            } else {
            prop.load(new FileInputStream(file_name));
//            }

            Config.MODULE_APP_APIKEY = prop.getProperty("module.app.apikey");
            Config.MODULE_APP_SECRET = prop.getProperty("module.app.secret");
            Config.SSO_URL = prop.getProperty("sso.url");
            Config.FILE_SAVE_PATH = prop.getProperty("file.save.path");

        } catch (Exception e) {
            Logger.error(e.getMessage());
        }
    }


}

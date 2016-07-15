package live.itrip.sso.service.impls;

import live.itrip.common.Logger;
import live.itrip.sso.common.Config;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Created by Feng on 2016/7/14.
 * <p>
 * 1. 初始化系统配置参数
 */
@Service
public class InitSystemService {

    private static final Long THREAD_SLEEP = 1000L * 60 * 5;

    public InitSystemService() {
        initSystem();
    }

    private void initSystem() {
        this.loadConfig();

        this.loadListApiKey();
    }

    private void loadConfig() {
        try {
            Properties prop = new Properties();

            String file_name = this.getClass().getClassLoader()
                    .getResource("resource/local.properties").getFile();

            if (file_name.toString().startsWith("file:")) {

                file_name = file_name.substring(5);
                if (file_name.indexOf("!") != -1) {
                    file_name = file_name.substring(0, file_name.indexOf("!"));
                }

                JarFile currentJar = new JarFile(file_name);
                JarEntry configEntry = currentJar.getJarEntry("config_test.properties");

                InputStream in = currentJar.getInputStream(configEntry);
                if (in != null) {
                    prop.load(in);
                }

            } else {
                prop.load(new FileInputStream(file_name));
            }

            Config.MODULE_APP_APIKEY = prop.getProperty("module.app.apikey");
            Config.MODULE_APP_SECRET = prop.getProperty("module.app.secret");
            Config.ADMIN_URL = prop.getProperty("admin.url");

        } catch (Exception e) {
            Logger.error(e.getMessage());
        }
    }

    private void loadListApiKey() {
        Thread loadAdminListApiKey = new Thread(new Runnable() {
            @Override
            public void run() {


                try {
                    Thread.sleep(THREAD_SLEEP);
                } catch (InterruptedException e) {
                    Logger.error(e);
                }
            }
        }, "SSOLoadAdminListApiKey");
        loadAdminListApiKey.start();
    }
}

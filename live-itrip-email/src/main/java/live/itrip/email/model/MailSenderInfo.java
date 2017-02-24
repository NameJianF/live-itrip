package live.itrip.email.model;

import live.itrip.email.common.Config;

import java.util.Properties;
import java.util.List;

/**
 * Created by Feng on 2017/2/24.
 */
public class MailSenderInfo extends SendEmail {
    public static final String FLAG_VALIDATE = "1";
    public static final String TYPE_TEXT = "text";
    public static final String TYPE_HTML = "html";

    public String emailType = TYPE_TEXT;

    // 邮件接收者的地址
    private List<String> listToAddress;
    // 邮件附件的文件名
    private List<String> listAttachFileNames;

    public List<String> getListToAddress() {
        return listToAddress;
    }

    public void setListToAddress(List<String> listToAddress) {
        this.listToAddress = listToAddress;
    }

    public List<String> getListAttachFileNames() {
        return listAttachFileNames;
    }

    public void setListAttachFileNames(List<String> listAttachFileNames) {
        this.listAttachFileNames = listAttachFileNames;
    }

    public String getEmailType() {
        return emailType;
    }

    public void setEmailType(String emailType) {
        this.emailType = emailType;
    }

    public Properties getProperties() {
        Properties p = new Properties();
        p.put("mail.smtp.host", Config.SERVER_HOST);
        p.put("mail.smtp.port", Config.SERVER_PORT);
        p.put("mail.smtp.auth", MailSenderInfo.FLAG_VALIDATE.equals(this.getValidate()) ? "true" : "false");
        p.put("mail.mime.address.strict", "false");
        return p;
    }
}

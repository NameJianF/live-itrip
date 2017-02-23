package live.itrip.web.email;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by Feng on 2016/12/30.
 * <p>
 * 邮件发送
 */
public class EmailHandler {
    private volatile static EmailHandler emailHandler;

//    static {
//        emailHandler = new EmailHandler();
//    }

    public static EmailHandler getInstance() {
        if (emailHandler == null) {
            synchronized (EmailHandler.class) {
                emailHandler = new EmailHandler();
            }
        }

        return emailHandler;
    }

    private BlockingQueue<EmailEntity> blockingQueue = new ArrayBlockingQueue<EmailEntity>(100);

    private Thread threadSend;
    private boolean stop = false;

    public EmailHandler() {
        threadSend = new Thread(new Runnable() {
            @Override
            public void run() {
                send();
            }
        }, "SendEmail");
        threadSend.start();
    }

    private void send() {
        while (!stop) {
            try {
                EmailEntity entity = this.blockingQueue.take();

                Thread.sleep(10);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }


    public void put(EmailEntity entity) {
        try {
            blockingQueue.put(entity);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    /**
     * 邮件信息
     */
    public static class EmailEntity {
        private String content;

        public EmailEntity() {

        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}

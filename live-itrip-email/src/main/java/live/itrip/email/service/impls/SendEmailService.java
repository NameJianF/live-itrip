package live.itrip.email.service.impls;

import live.itrip.common.response.BaseResult;
import live.itrip.email.dao.SendEmailMapper;
import live.itrip.email.model.MailSenderInfo;
import live.itrip.email.service.BaseService;
import live.itrip.email.service.interfaces.ISendEmailService;
import live.itrip.email.utils.SimpleMailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by Feng on 2017/2/24.
 */
@Service
public class SendEmailService extends BaseService implements ISendEmailService {
    @Autowired
    private SendEmailMapper sendEmailMapper;


    private BlockingQueue<MailSenderInfo> blockingQueue = new ArrayBlockingQueue<MailSenderInfo>(100);

    private Thread threadSend;
    private boolean stop = false;

    public SendEmailService() {
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
                MailSenderInfo entity = this.blockingQueue.take();
                boolean result = SimpleMailSender.send(entity);
                Thread.sleep(10);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    private void put(MailSenderInfo entity) {
        try {
            blockingQueue.put(entity);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    @Override
    public void sendEmail(String decodeJson, HttpServletResponse response, HttpServletRequest request) {
        BaseResult result = new BaseResult();



        this.writeResponse(response, result);
    }
}

package live.itrip.email.service.impls;

import com.alibaba.fastjson.JSON;
import live.itrip.common.ErrorCode;
import live.itrip.common.Logger;
import live.itrip.common.response.BaseResult;
import live.itrip.email.common.Config;
import live.itrip.email.common.Constants;
import live.itrip.email.dao.SendEmailMapper;
import live.itrip.email.model.MailSenderInfo;
import live.itrip.email.service.BaseService;
import live.itrip.email.service.interfaces.ISendEmailService;
import live.itrip.email.utils.SimpleMailSender;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by Feng on 2017/2/24.
 * <p/>
 * 1. email 发送服务
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
                if (result) {
                    // update db
                    entity.setStatus(Constants.Email_Status.SUCCESS);
                } else {
                    // TODO 添加发送失败策略

                    // update db
                    entity.setStatus(Constants.Email_Status.FAILD);
                }
                entity.setSendTimes(entity.getSendTimes() + 1);
                entity.setUpdateTime(System.currentTimeMillis());
                this.sendEmailMapper.updateByPrimaryKey(entity);

                Thread.sleep(5);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    private void put(MailSenderInfo entity) {
        try {
            blockingQueue.put(entity);
        } catch (Exception ex) {
            Logger.error(ex.getMessage(), ex);
        }
    }


    @Override
    public void sendEmail(String decodeJson, HttpServletResponse response, HttpServletRequest request) {
        BaseResult result = new BaseResult();

        if (StringUtils.isEmpty(Config.SERVER_HOST)
                || StringUtils.isEmpty(Config.SERVER_PORT)
                || StringUtils.isEmpty(Config.SERVER_USER_NAME)
                || StringUtils.isEmpty(Config.SERVER_USER_PWD)) {
            result.setError(ErrorCode.SERVICE_INITING);
            this.writeResponse(response, result);
            return;
        }


        MailSenderInfo senderInfo = JSON.parseObject(decodeJson, MailSenderInfo.class);

        if (senderInfo != null) {
            senderInfo.setStatus(Constants.Email_Status.INIT);
            senderInfo.setServerHost(Config.SERVER_HOST);
            senderInfo.setServerPort(Config.SERVER_PORT);
            senderInfo.setUserName(Config.SERVER_USER_NAME);
            senderInfo.setSendTimes(0);
            senderInfo.setCreateTime(System.currentTimeMillis());

            // save to db
            int ret = this.sendEmailMapper.insertSelective(senderInfo);

            if (ret > 0) {
                // add to blockingQueue
                this.put(senderInfo);
            }
        }

        result.setError(ErrorCode.SUCCESS);
        this.writeResponse(response, result);
    }
}

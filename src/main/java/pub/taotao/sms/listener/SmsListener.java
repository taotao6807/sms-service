package pub.taotao.sms.listener;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;
import pub.taotao.sms.pojo.ResResult;
import pub.taotao.sms.service.SmsService;

import java.util.Map;

/**
 * 通过接收消息发送短信
 */
@Component
public class SmsListener {
    @Autowired
    JmsMessagingTemplate jmsMessagingTemplate;
    @Autowired
    SmsService smsService;

    private static final Logger logger = LoggerFactory.getLogger(SmsListener.class);

    @JmsListener(destination = "smsQueue")
    public void receiveMessage(Map<String,String> smsInfo){
        try {
            smsService.sendSms(smsInfo);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

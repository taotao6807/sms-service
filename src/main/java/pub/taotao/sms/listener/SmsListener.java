package pub.taotao.sms.listener;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;
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

    @JmsListener(destination = "smsQueue")
    public void receiveMessage(Map<String,String> smsInfo){
        try {
            SendSmsResponse sendSmsResponse = smsService.sendSms(smsInfo);

        }catch (ClientException clientException){
            clientException.printStackTrace();
        }
    }
}

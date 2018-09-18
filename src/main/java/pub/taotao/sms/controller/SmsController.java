package pub.taotao.sms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 通过直接调api发送短信
 */
@RestController
@RequestMapping("/sms")
public class SmsController {

    @Autowired
    JmsMessagingTemplate jmsMessagingTemplate;

    @RequestMapping("/send")
    public String send(){
        jmsMessagingTemplate.convertAndSend("smsQueue","18681276807");
        return "success";
    }
}

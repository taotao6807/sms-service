package pub.taotao.sms.controller;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pub.taotao.sms.pojo.ResResult;
import pub.taotao.sms.service.SmsService;
import pub.taotao.sms.util.SmsResponseHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * 通过直接调api发送短信
 */
@RestController
@RequestMapping("/sms")
public class SmsController {

    @Autowired
    SmsService smsService;
    @Value("${SMS.INFO.KEY.PHONENUMBER}")
    String SMS_INFO_PHONENUMBER;
    @Value("${SMS.INFO.KEY.SIGNNAME}")
    String SMS_INFO_SIGNNAME;
    @Value("${SMS.INFO.KEY.TEMPLATECODE}")
    String SMS_INFO_TEMPLATECODE;
    @Value("${SMS.INFO.KEY.PARAMJSON}")
    String SMS_INFO_PARAMJSON;

    @RequestMapping(value="/send",method = RequestMethod.POST)
    public ResResult send(String phoneNumber,String signName,String templateCode,String paramJson){
        Map<String,String> smsInfo = new HashMap<>();
        smsInfo.put(SMS_INFO_PHONENUMBER,phoneNumber);
        smsInfo.put(SMS_INFO_SIGNNAME,signName);
        smsInfo.put(SMS_INFO_TEMPLATECODE,templateCode);
        smsInfo.put(SMS_INFO_PARAMJSON,paramJson);
        try {
            return smsService.sendSms(smsInfo);
        }catch (Exception e){
            e.printStackTrace();
            return ResResult.fail("未知错误发生了");
        }
    }
}

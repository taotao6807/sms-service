package pub.taotao.sms.service;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import pub.taotao.sms.pojo.ResResult;
import pub.taotao.sms.util.SmsResponseHandler;

import java.util.Map;

@Service
public class SmsService {

    @Value("${SMS.INFO.KEY.PHONENUMBER}")
    String SMS_INFO_PHONENUMBER;
    @Value("${SMS.INFO.KEY.SIGNNAME}")
    String SMS_INFO_SIGNNAME;
    @Value("${SMS.INFO.KEY.TEMPLATECODE}")
    String SMS_INFO_TEMPLATECODE;
    @Value("${SMS.INFO.KEY.PARAMJSON}")
    String SMS_INFO_PARAMJSON;

    //产品名称:云通信短信API产品,开发者无需替换
    static final String product = "Dysmsapi";
    //产品域名,开发者无需替换
    static final String domain = "dysmsapi.aliyuncs.com";

    // TODO 此处需要替换成开发者自己的AK(在阿里云访问控制台寻找)
    static final String accessKeyId = "LTAISej41Uz2MKGa";
    static final String accessKeySecret = "NK7gxCJXDFiZ52yrU88S0Tl0zy0Ayw";

    public ResResult sendSms(Map<String,String> smsInfo) throws ClientException {

        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        //初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);

        //组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();
        //必填:待发送手机号
        request.setPhoneNumbers(smsInfo.get(SMS_INFO_PHONENUMBER));
        //必填:短信签名-可在短信控制台中找到
        request.setSignName(smsInfo.get(SMS_INFO_SIGNNAME));
        //必填:短信模板-可在短信控制台中找到
        request.setTemplateCode(smsInfo.get(SMS_INFO_TEMPLATECODE));
        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        request.setTemplateParam(smsInfo.get(SMS_INFO_PARAMJSON));

        //选填-上行短信扩展码(无特殊需求用户请忽略此字段)
        //request.setSmsUpExtendCode("90997");

        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        request.setOutId("yourOutId");

        //hint 此处可能会抛出异常，注意catch
        SendSmsResponse smsResponse = acsClient.getAcsResponse(request);
        ResResult resResult = SmsResponseHandler.handle(smsResponse);
        return resResult;
    }

}

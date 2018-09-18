package pub.taotao.sms.util;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import pub.taotao.sms.pojo.ResResult;

public class SmsResponseHandler {
    public static ResResult handle(SendSmsResponse smsResponse){
        if(smsResponse == null) return ResResult.fail("未知错误！");
        switch (smsResponse.getCode()){
            case "isp.RAM_PERMISSION_DENY":	        return ResResult.fail("RAM权限DENY");
            case "isv.OUT_OF_SERVICE":	            return ResResult.fail("业务停机");
            case "isv.PRODUCT_UN_SUBSCRIPT":	    return ResResult.fail("未开通云通信产品的阿里云客户");
            case "isv.PRODUCT_UNSUBSCRIBE":	        return ResResult.fail("产品未开通");
            case "isv.ACCOUNT_NOT_EXISTS":  	    return ResResult.fail("账户不存在");
            case "isv.ACCOUNT_ABNORMAL":	        return ResResult.fail("账户异常");
            case "isv.SMS_TEMPLATE_ILLEGAL":	    return ResResult.fail("短信模板不合法");
            case "isv.SMS_SIGNATURE_ILLEGAL":	    return ResResult.fail("短信签名不合法");
            case "isv.INVALID_PARAMETERS":	        return ResResult.fail("参数异常");
            case "isp.SYSTEM_ERROR":	            return ResResult.fail("系统错误");
            case "isv.MOBILE_NUMBER_ILLEGAL":	    return ResResult.fail("非法手机号");
            case "isv.MOBILE_COUNT_OVER_LIMIT":	    return ResResult.fail("手机号码数量超过限制");
            case "isv.TEMPLATE_MISSING_PARAMETERS":	return ResResult.fail("模板缺少变量");
            case "isv.BUSINESS_LIMIT_CONTROL":	    return ResResult.fail("业务限流");
            case "isv.INVALID_JSON_PARAM":	        return ResResult.fail("JSON参数不合法，只接受字符串值");
            case "isv.BLACK_KEY_CONTROL_LIMIT":	    return ResResult.fail("黑名单管控");
            case "isv.PARAM_LENGTH_LIMIT":	        return ResResult.fail("参数超出长度限制");
            case "isv.PARAM_NOT_SUPPORT_URL":	    return ResResult.fail("不支持URL");
            case "isv.AMOUNT_NOT_ENOUGH":	        return ResResult.fail("账户余额不足");
        }
        return ResResult.ok("请求成功");
    }
}

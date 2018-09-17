package pub.taotao.sms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Autowired
    Environment environment;

    @RequestMapping("test")
    public String test(){
        return environment.getProperty("HELLOWORLD");
    }
}

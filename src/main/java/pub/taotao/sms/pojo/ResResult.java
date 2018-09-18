package pub.taotao.sms.pojo;

public class ResResult {
    private Integer code;//0为成功，1为失败
    private String message;

    private ResResult(){
        code = 0;
        message = "";
    }
    private ResResult(Integer code,String message){
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static ResResult ok(){
        return new ResResult();
    }

    public static ResResult ok(String message){
        return new ResResult(1,message);
    }

    public static ResResult fail(){
        return new ResResult(1,"");
    }
    public static ResResult fail(String message){
        return new ResResult(1,message);
    }
}

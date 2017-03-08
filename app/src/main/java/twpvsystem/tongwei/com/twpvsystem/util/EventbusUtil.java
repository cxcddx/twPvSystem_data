package twpvsystem.tongwei.com.twpvsystem.util;

/**
 * Created by CX on 2017/2/17.
 */
public class EventbusUtil {
    private String msg;

    public EventbusUtil(String msg) {//事件传递参数
        this.msg = msg;
    }

    public String getMsg() {//取出事件参数
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

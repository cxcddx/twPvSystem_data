package twpvsystem.tongwei.com.twpvsystem.util;

import com.amap.api.maps2d.model.LatLng;

public class Constants {

    /*
     *接口地址
     */
    public static final String SERVER_URL = "http://172.20.90.8:8080/TWwebFrame/mobile";// 域名解析
    //登录
    public static final String URL_Login = SERVER_URL + "/login/validate";
    //	检查版本更新
    public static final String URL_Version = SERVER_URL + "/mobileVersion";
    // 下载更新
    public static final String URL_APK = SERVER_URL + "/mobileDown";
    //	获取用户信息
    public static final String URL_USERLIST = SERVER_URL + "/user/queryUserInfo";
    //获取总电量、节能减排等信息
    public static final String URL_ELEC = SERVER_URL + "/user/queryPowerData";
    //获取年、月、日发电量
    public static final String URL_CHART = SERVER_URL + "/user/totalPowerData";
    //修改密码
    public static final String URL_CHANGE_PWD = SERVER_URL + "/changePwd";

    public static final LatLng XIAN = new LatLng(34.341568, 108.940174);// 西安市经纬度

    /*
     *sharePreference保存参数键值
     */
    public static final String Shared_NAME = "twpvsystem";
    public static final String UserName = "UserName";
    public static final String UserPwd = "UserPwd";
    public static final String UserId = "userId";
    public static final String CommonUser = "choicevalue";
    public static final String Type = "type";
    public static final String ChoiceId = "choiceId";

    public static final String Common = "CommonUser";
    public static final String Admin = "Admin";
    public static final String SaveUserCode = "SaveUserCode";// 是否保存用户名,默认保存
    public static final String SavePassword = "SavePassword";// 是否保存密码,默认不保存

    /*
     *网络设置
     */
    public static final int FRESH_TIME = 5*60*1000;//刷新页面间隔
    public static final long TIMEOUT = 10000;//网络连接超时
}

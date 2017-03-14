package twpvsystem.tongwei.com.twpvsystem.util;

import com.amap.api.maps2d.model.LatLng;

public class Constants {

	public static final long TIMEOUT = 5000;//网络连接超时
	public static final String SERVER_URL = "http://m2m.tongwei.com";// 域名解析
//	检查版本更新
	public static final String URL_Version = SERVER_URL + "/Android/AndroidVersionServlet";
	// 下载更新
	public static final String URL_APK = SERVER_URL + "/Android/AndroidDownServlet";
	public static final LatLng XIAN = new LatLng(34.341568, 108.940174);// 西安市经纬度

	public static final String Shared_NAME = "twpvsystem";
}

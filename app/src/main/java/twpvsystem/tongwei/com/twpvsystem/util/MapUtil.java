package twpvsystem.tongwei.com.twpvsystem.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;

import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.MarkerOptions;
import com.amap.api.maps2d.model.TextOptions;

import org.litepal.crud.DataSupport;

import java.util.List;

import twpvsystem.tongwei.com.twpvsystem.R;
import twpvsystem.tongwei.com.twpvsystem.bean.UserInf;

/**
 * Created by CX on 2017/2/17.
 */
public class MapUtil {
    public static void addMarkersToMap(Context context,  AMap aMap, LatLng latlng, int id, boolean isShowtext) {
        String title = "";

        List<UserInf> userinf = DataSupport.where("userId = ?","" +id).find(UserInf.class, true);
        if(userinf.size()>0) {
            title = userinf.get(0).getInfo().getPowerName();
        }
//        News news = DataSupport.find(News.class, 1, true);
//        List<Comment> commentList = news.getCommentList();

//        aMap.addMarker(new MarkerOptions().position(latlng).title(""+id).icon(BitmapDescriptorFactory
//                .fromResource(R.drawable.location_icon)));

        Bitmap bitmap = BitmapFactory
                .decodeResource(context.getResources(),R.drawable.location_icon);
        Bitmap bigBitMap = scaleBitmap(bitmap, 1.0f);
        aMap.addMarker(new MarkerOptions().position(latlng).title(""+id).icon(BitmapDescriptorFactory.
                fromBitmap(bigBitMap)));

        if(isShowtext) {
            TextOptions textOptions = new TextOptions().position(latlng).text(title).backgroundColor(Color.TRANSPARENT).fontSize(30).fontColor(Color.GREEN);
            aMap.addText(textOptions);
        }

    }

    /**
     * 根据给定的宽和高进行拉伸
     *
     * @param origin    原图
     * @param newWidth  新图的宽
     * @param newHeight 新图的高
     * @return new Bitmap
     */
    private static Bitmap scaleBitmap(Bitmap origin, int newWidth, int newHeight) {
        if (origin == null) {
            return null;
        }
        int height = origin.getHeight();
        int width = origin.getWidth();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);// 使用后乘
        Bitmap newBM = Bitmap.createBitmap(origin, 0, 0, width, height, matrix, false);
        if (!origin.isRecycled()) {
            origin.recycle();
        }
        return newBM;
    }
    /**
     * 按比例缩放图片
     *
     * @param origin 原图
     * @param ratio  比例
     * @return 新的bitmap
     */
    private static Bitmap scaleBitmap(Bitmap origin, float ratio) {
        if (origin == null) {
            return null;
        }
        int width = origin.getWidth();
        int height = origin.getHeight();
        Matrix matrix = new Matrix();
        matrix.preScale(ratio, ratio);
        Bitmap newBM = Bitmap.createBitmap(origin, 0, 0, width, height, matrix, false);
        if (newBM.equals(origin)) {
            return newBM;
        }
        origin.recycle();
        return newBM;
    }


}

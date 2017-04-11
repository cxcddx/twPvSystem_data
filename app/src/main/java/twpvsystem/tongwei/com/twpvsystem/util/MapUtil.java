package twpvsystem.tongwei.com.twpvsystem.util;

import android.graphics.Color;

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
    public static void addMarkersToMap(AMap aMap, LatLng latlng, int id) {
        String title = "";

        List<UserInf> userinf = DataSupport.where("userId = ?","" +id).find(UserInf.class, true);
        if(userinf.size()>0) {
            title = userinf.get(0).getInfo().getPowerName();
        }
//        News news = DataSupport.find(News.class, 1, true);
//        List<Comment> commentList = news.getCommentList();

        aMap.addMarker(new MarkerOptions().position(latlng).title(""+id).icon(BitmapDescriptorFactory
                .fromResource(R.drawable.blu_circle)));

        TextOptions textOptions = new TextOptions().position(latlng).text(title).backgroundColor(Color.TRANSPARENT).fontSize(20).fontColor(Color.BLUE);
        aMap.addText(textOptions);

    }
}

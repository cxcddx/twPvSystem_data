package twpvsystem.tongwei.com.twpvsystem.util;

import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;

import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.MarkerOptions;
import com.amap.api.maps2d.model.Text;
import com.amap.api.maps2d.model.TextOptions;

import twpvsystem.tongwei.com.twpvsystem.R;

/**
 * Created by CX on 2017/2/17.
 */
public class MapUtil {
    public static void addMarkersToMap(AMap aMap, LatLng latlng, String title) {
        aMap.addMarker(new MarkerOptions().position(latlng).title(title).icon(BitmapDescriptorFactory
                .fromResource(R.drawable.blu_circle)));
        TextOptions textOptions = new TextOptions().position(latlng).text(title).backgroundColor(Color.TRANSPARENT).fontSize(20).fontColor(Color.BLUE);
        aMap.addText(textOptions);

    }
}

package twpvsystem.tongwei.com.twpvsystem.activity;



import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.view.animation.Interpolator;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.Projection;
import com.amap.api.maps2d.model.BitmapDescriptor;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.Marker;
import com.amap.api.maps2d.model.MarkerOptions;
import com.amap.api.maps2d.model.Text;
import com.amap.api.maps2d.model.TextOptions;

import java.util.ArrayList;
import java.util.List;

import twpvsystem.tongwei.com.twpvsystem.R;
import twpvsystem.tongwei.com.twpvsystem.fragment.ChartMainFragment;
import twpvsystem.tongwei.com.twpvsystem.util.Constants;

import static twpvsystem.tongwei.com.twpvsystem.R.id.btn_one;

public class MapActivity extends AppCompatActivity implements View.OnClickListener, AMap.OnMarkerDragListener, AMap.OnMapLoadedListener, AMap.OnMarkerClickListener, AMap.OnInfoWindowClickListener, AMap.InfoWindowAdapter, AMap.OnMapTouchListener {
    private MarkerOptions markerOption;
    private TextView markerText;
    private Button markerButton;// 获取屏幕内所有marker的button
    private RadioGroup radioOption;
    private AMap aMap;
    private MapView mapView;
    private Marker marker2;// 有跳动效果的marker对象
    private LatLng latlng = new LatLng(36.061, 103.834);
    private ScrollView mScrollView;

    private Fragment fragment_chart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_map);
        mapView = (MapView) findViewById(R.id.map);
        mapView.onCreate(savedInstanceState); // 此方法必须重写
        init();
    }

    /**
     * 初始化AMap对象
     */
    private void init() {
        mScrollView = (ScrollView) findViewById(R.id.view_content);
        markerText = (TextView) findViewById(R.id.mark_listenter_text);
        radioOption = (RadioGroup) findViewById(R.id.custom_info_window_options);
        markerButton = (Button) findViewById(R.id.marker_button);
        markerButton.setOnClickListener(this);
        Button clearMap = (Button) findViewById(R.id.clearMap);
        clearMap.setOnClickListener(this);
        Button resetMap = (Button) findViewById(R.id.resetMap);
        resetMap.setOnClickListener(this);
        if (aMap == null) {
            aMap = mapView.getMap();
            setUpMap();
        }

        //设置表格fragment
        setDefaultFragment();

    }

    //设置表格fragment
    private void setDefaultFragment()
    {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        fragment_chart = new ChartMainFragment();
        transaction.replace(R.id.fragment_chart, fragment_chart);
        transaction.commit();
    }

    private void setUpMap() {
        aMap.setOnMarkerDragListener(this);// 设置marker可拖拽事件监听器
        aMap.setOnMapLoadedListener(this);// 设置amap加载成功事件监听器
        aMap.setOnMarkerClickListener(this);// 设置点击marker事件监听器
        aMap.setOnInfoWindowClickListener(this);// 设置点击infoWindow事件监听器
        aMap.setInfoWindowAdapter(this);// 设置自定义InfoWindow样式

        aMap.setOnMapTouchListener(this);

        addMarkersToMap();// 往地图上添加marker
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    /**
     * 在地图上添加marker
     */
    private void addMarkersToMap() {

        //文字显示标注，可以设置显示内容，位置，字体大小颜色，背景色旋转角度,Z值等
        TextOptions textOptions = new TextOptions().position(Constants.BEIJING)
                .text("Text").fontColor(Color.BLACK)
                .backgroundColor(Color.BLUE).fontSize(30).rotate(20).align(Text.ALIGN_CENTER_HORIZONTAL, Text.ALIGN_CENTER_VERTICAL)
                .zIndex(1.f).typeface(Typeface.DEFAULT_BOLD)
                ;
        aMap.addText(textOptions);

        aMap.addMarker(new MarkerOptions().anchor(0.5f, 0.5f)
                .position(Constants.CHENGDU).title("成都市")
                .snippet("成都市:30.679879, 104.064855").draggable(true));

        markerOption = new MarkerOptions();
        markerOption.position(Constants.XIAN);
        markerOption.title("西安市").snippet("西安市：34.341568, 108.940174");
        markerOption.draggable(true);
        markerOption.icon(BitmapDescriptorFactory
                .fromResource(R.mipmap.arrow));
        marker2 = aMap.addMarker(markerOption);
        marker2.showInfoWindow();
        // marker旋转90度
        marker2.setRotateAngle(90);

        // 动画效果
        ArrayList<BitmapDescriptor> giflist = new ArrayList<BitmapDescriptor>();
        giflist.add(BitmapDescriptorFactory
                .defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
        giflist.add(BitmapDescriptorFactory
                .defaultMarker(BitmapDescriptorFactory.HUE_RED));
        giflist.add(BitmapDescriptorFactory
                .defaultMarker(BitmapDescriptorFactory.HUE_YELLOW));
        aMap.addMarker(new MarkerOptions().anchor(0.5f, 0.5f)
                .position(Constants.ZHENGZHOU).title("郑州市").icons(giflist)
                .draggable(true).period(10));
        drawMarkers();// 添加10个带有系统默认icon的marker
    }

    /**
     * 绘制系统默认的1种marker背景图片
     */
    public void drawMarkers() {
        Marker marker = aMap.addMarker(new MarkerOptions()
                .position(latlng)
                .title("好好学习")
                .icon(BitmapDescriptorFactory
                        .defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
                .draggable(true));
        marker.showInfoWindow();// 设置默认显示一个infowinfow
    }

    /**
     * 对marker标注点点击响应事件
     */
    @Override
    public boolean onMarkerClick(final Marker marker) {
        if (marker.equals(marker2)) {
            if (aMap != null) {
                jumpPoint(marker);
            }
        }
        markerText.setText("你点击的是" + marker.getTitle());
        return false;
    }

    /**
     * marker点击时跳动一下
     */
    public void jumpPoint(final Marker marker) {
        final Handler handler = new Handler();
        final long start = SystemClock.uptimeMillis();
        Projection proj = aMap.getProjection();
        Point startPoint = proj.toScreenLocation(Constants.XIAN);
        startPoint.offset(0, -100);
        final LatLng startLatLng = proj.fromScreenLocation(startPoint);
        final long duration = 1500;

        final Interpolator interpolator = new BounceInterpolator();
        handler.post(new Runnable() {
            @Override
            public void run() {
                long elapsed = SystemClock.uptimeMillis() - start;
                float t = interpolator.getInterpolation((float) elapsed
                        / duration);
                double lng = t * Constants.XIAN.longitude + (1 - t)
                        * startLatLng.longitude;
                double lat = t * Constants.XIAN.latitude + (1 - t)
                        * startLatLng.latitude;
                marker.setPosition(new LatLng(lat, lng));
                aMap.invalidate();// 刷新地图
                if (t < 1.0) {
                    handler.postDelayed(this, 16);
                }
            }
        });

    }

    /**
     * 监听点击infowindow窗口事件回调
     */
    @Override
    public void onInfoWindowClick(Marker marker) {
//        ToastUtil.show(this, "你点击了infoWindow窗口" + marker.getTitle());
    }

    /**
     * 监听拖动marker时事件回调
     */
    @Override
    public void onMarkerDrag(Marker marker) {
        String curDes = marker.getTitle() + "拖动时当前位置:(lat,lng)\n("
                + marker.getPosition().latitude + ","
                + marker.getPosition().longitude + ")";
        markerText.setText(curDes);
    }

    /**
     * 监听拖动marker结束事件回调
     */
    @Override
    public void onMarkerDragEnd(Marker marker) {
        markerText.setText(marker.getTitle() + "停止拖动");
    }

    /**
     * 监听开始拖动marker事件回调
     */
    @Override
    public void onMarkerDragStart(Marker marker) {
        markerText.setText(marker.getTitle() + "开始拖动");
    }

    /**
     * 监听amap地图加载成功事件回调
     */
    @Override
    public void onMapLoaded() {
        // 设置所有maker显示在当前可视区域地图中
//		LatLngBounds bounds = new LatLngBounds.Builder()
//				.include(Constants.XIAN).include(Constants.CHENGDU)
//				.include(latlng).include(Constants.ZHENGZHOU).include(Constants.BEIJING).build();
//		aMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, 10));
        aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.XIAN, 4));
    }

    /**
     * 监听自定义infowindow窗口的infocontents事件回调
     */
    @Override
    public View getInfoContents(Marker marker) {
        if (radioOption.getCheckedRadioButtonId() != R.id.custom_info_contents) {
            return null;
        }
        View infoContent = getLayoutInflater().inflate(
                R.layout.content_map, null);
//        render(marker, infoContent);
        return infoContent;
    }

    /**
     * 监听自定义infowindow窗口的infowindow事件回调
     */
    @Override
    public View getInfoWindow(Marker marker) {
        if (radioOption.getCheckedRadioButtonId() != R.id.custom_info_window) {
            return null;
        }
        View infoWindow = getLayoutInflater().inflate(
                R.layout.content_map, null);

//        render(marker, infoWindow);
        return infoWindow;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            /**
             * 清空地图上所有已经标注的marker
             */
            case R.id.clearMap:
                if (aMap != null) {
                    aMap.clear();
                }
                break;
            /**
             * 重新标注所有的marker
             */
            case R.id.resetMap:
                if (aMap != null) {
                    aMap.clear();
                    addMarkersToMap();
                }
                break;
            // 获取屏幕所有marker
            case R.id.marker_button:
                if (aMap != null) {
                    List<Marker> markers = aMap.getMapScreenMarkers();
                    if (markers == null || markers.size() == 0) {
//                        ToastUtil.show(this, "当前屏幕内没有Marker");
                        return;
                    }
                    String tile = "屏幕内有：";
                    for (Marker marker : markers) {
                        tile = tile + " " + marker.getTitle();

                    }
//                    ToastUtil.show(this, tile);

                }
                break;

//            case R.id.btn_one:
////                Toast.makeText(BaseActivity.this, "点击了按钮1", Toast.LENGTH_LONG).show();
//                System.out.println("点击了按钮1");
//                break;
//            case R.id.btn_two:
////                Toast.makeText(BaseActivity.this, "点击了按钮2", Toast.LENGTH_LONG).show();
//                System.out.println("点击了按钮2");
//                break;
//            case R.id.btn_three:
////                Toast.makeText(BaseActivity.this, "点击了按钮3", Toast.LENGTH_LONG).show();
//                System.out.println("点击了按钮3");
//                break;

            default:
                break;
        }
    }

    //与scrollView冲突解决
    @Override
    public void onTouch(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        switch (action) {
            case MotionEvent.ACTION_UP:
                mScrollView.requestDisallowInterceptTouchEvent(false);
                break;
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                mScrollView.requestDisallowInterceptTouchEvent(true);
                break;
        }
    }
}

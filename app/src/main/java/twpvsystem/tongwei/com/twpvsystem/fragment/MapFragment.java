package twpvsystem.tongwei.com.twpvsystem.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.Marker;

import org.litepal.crud.DataSupport;

import java.util.List;

import twpvsystem.tongwei.com.twpvsystem.R;
import twpvsystem.tongwei.com.twpvsystem.activity.CustomerActivity;
import twpvsystem.tongwei.com.twpvsystem.activity.MapActivity;
import twpvsystem.tongwei.com.twpvsystem.bean.InfoBean;
import twpvsystem.tongwei.com.twpvsystem.bean.UserInf;
import twpvsystem.tongwei.com.twpvsystem.util.Constants;
import twpvsystem.tongwei.com.twpvsystem.util.MapUtil;

public class MapFragment extends Fragment implements AMap.OnMapLoadedListener, AMap.OnMarkerClickListener, AMap.OnMapTouchListener, AMap.InfoWindowAdapter {

    private AMap aMap;
    private MapView mapView;
    private LatLng l_cd = new LatLng(30.67, 104.06);
    private LatLng l_bj = new LatLng(39.9, 116.3);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_map, container, false);
        init(v, savedInstanceState);
        return v;
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    private void init(View v, Bundle savedInstanceState) {
        mapView = (MapView) v.findViewById(R.id.map);
        mapView.onCreate(savedInstanceState); // 此方法必须重写
        if (aMap == null) {
            aMap = mapView.getMap();
            setUpMap();
        }
    }

    private void setUpMap() {
        aMap.setOnMapLoadedListener(this);// 设置amap加载成功事件监听器
        aMap.setOnMarkerClickListener(this);// 设置点击marker事件监听器
        aMap.setInfoWindowAdapter(this);// 设置自定义InfoWindow样式
        aMap.setOnMapTouchListener(this);//设置点击监听，解决与scrollview滑动冲突
        addMarkersToMap();// 往地图上添加marker
    }

    /**
     * 在地图上添加marker
     */
    private void addMarkersToMap() {
//        MapUtil.addMarkersToMap(aMap, l_cd, "成都");
//        MapUtil.addMarkersToMap(aMap, l_bj, "北京");
//        News news = DataSupport.find(News.class, 1, true);
//        List<Comment> commentList = news.getCommentList();
//        List<UserInf> uList = DataSupport.findAll(UserInf.class);
//        for(int i = 0; i<uList.size();i++) {
//            MapUtil.addMarkersToMap(aMap, new LatLng(uList.get(i).getInfo().getLatitude(), uList.get(i).getInfo().getLongitude()), ""+uList.get(i).getUserId());
//        }

        List<UserInf> uList = DataSupport.findAll(UserInf.class);
        for(int i = 0; i<uList.size();i++) {
            List<UserInf> List = DataSupport.findAll(UserInf.class, true);
            InfoBean infList = List.get(i).getInfo();
            if (!(infList == null)) {
                double Latitude = infList.getLatitude();
                double getLongitude = infList.getLongitude();
                MapUtil.addMarkersToMap(aMap, new LatLng(Latitude, getLongitude), uList.get(i).getUserId());
            }
        }


    }

    /**
     * 监听amap地图加载成功事件回调
     */
    @Override
    public void onMapLoaded() {
        aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Constants.XIAN, 4));
    }

    /**
     * 对marker标注点点击响应事件
     */
    @Override
    public boolean onMarkerClick(Marker marker) {
        String choiceName = "";
//        Toast.makeText(getActivity(), "你点击的是" + marker.getTitle()+"id=" + marker.getId(), Toast.LENGTH_SHORT).show();
        MapActivity.m_sharedHelper.putValue(Constants.ChoiceId, Long.parseLong(marker.getTitle()));
        List<UserInf> userinf = DataSupport.where("userId = ?",marker.getTitle()).find(UserInf.class, true);
        if(userinf.size()>0) {
            choiceName = userinf.get(0).getInfo().getPowerName();
            MapActivity.m_sharedHelper.putValue(Constants.CommonUser, choiceName);
        }
        Intent intent = new Intent(getActivity(), CustomerActivity.class);
        getActivity().startActivity(intent);
        return false;
    }

    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    /**
     * 监听自定义infowindow窗口的infocontents事件回调
     */
    @Override
    public View getInfoContents(Marker marker) {

        View infoContent = getActivity().getLayoutInflater().inflate(
                null, null);
        return infoContent;
    }


    //与scrollView冲突解决
    @Override
    public void onTouch(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        switch (action) {
            case MotionEvent.ACTION_UP:
                MapActivity.mScrollView.requestDisallowInterceptTouchEvent(false);
                break;
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                MapActivity.mScrollView.requestDisallowInterceptTouchEvent(true);
                break;
        }
    }
}

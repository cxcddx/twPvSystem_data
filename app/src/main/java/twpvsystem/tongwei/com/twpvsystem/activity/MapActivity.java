package twpvsystem.tongwei.com.twpvsystem.activity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.widget.ScrollView;

import twpvsystem.tongwei.com.twpvsystem.R;
import twpvsystem.tongwei.com.twpvsystem.fragment.ChartMainFragment;
import twpvsystem.tongwei.com.twpvsystem.fragment.MapFragment;

//public class MapActivity extends AppCompatActivity implements View.OnClickListener, AMap.OnMarkerDragListener, AMap.OnMapLoadedListener, AMap.OnMarkerClickListener, AMap.OnInfoWindowClickListener, AMap.InfoWindowAdapter, AMap.OnMapTouchListener {
public class MapActivity extends AppCompatActivity {
    public static ScrollView mScrollView;

    private Fragment fragment_chart, fragment_energy, fragment_elec, fragment_map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_map);
        init();
    }

    /**
     * 初始化AMap对象
     */
    private void init() {
        mScrollView = (ScrollView) findViewById(R.id.view_content);

//        //设置地图栏fragment
        setMapDefaultFragment();
        //设置表格fragment
        setChartDefaultFragment();
    }

    //设置累计发电栏fragment
    private void setMapDefaultFragment() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        fragment_map = new MapFragment();
        transaction.replace(R.id.fragment_map, fragment_map);
        transaction.commit();
    }

    //设置表格栏fragment
    private void setChartDefaultFragment() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        fragment_chart = new ChartMainFragment();
        transaction.replace(R.id.fragment_chart, fragment_chart);
        transaction.commit();
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onResume() {
        super.onResume();
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onPause() {
        super.onPause();
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}

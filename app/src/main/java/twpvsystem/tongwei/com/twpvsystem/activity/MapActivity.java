package twpvsystem.tongwei.com.twpvsystem.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ScrollView;
import android.widget.Toast;

import twpvsystem.tongwei.com.twpvsystem.R;
import twpvsystem.tongwei.com.twpvsystem.fragment.ChartMainFragment;
import twpvsystem.tongwei.com.twpvsystem.fragment.MapFragment;
import twpvsystem.tongwei.com.twpvsystem.util.MessageUtils;

//public class MapActivity extends AppCompatActivity implements View.OnClickListener, AMap.OnMarkerDragListener, AMap.OnMapLoadedListener, AMap.OnMarkerClickListener, AMap.OnInfoWindowClickListener, AMap.InfoWindowAdapter, AMap.OnMapTouchListener {
public class MapActivity extends BaseActivity implements View.OnClickListener {
    public static ScrollView mScrollView;

    private Fragment fragment_chart, fragment_energy, fragment_elec, fragment_map;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getToolbarTitle().setText("分布式光伏系统");
        init();

}

//    /**
//     * 设置不显示返回按钮
//     *
//     * @return
//     */
//    protected boolean isShowBacking() {
//        return false;
//    }
    /**
     * 设置显示菜单按钮
     *
     * @return
     */
    protected boolean isShowMore() {
        return true;
    }

    /**
     * 设置布局
     *
     * @return
     */
    @Override
    protected int getLayoutId() {
        return R.layout.content_map;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.base_toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        switch (item.getItemId()) {
            case R.id.action_search:
                Intent intent = new Intent(MapActivity.this, ChoiceCustomerActivity.class);
                    MapActivity.this.startActivity(intent);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public int Dp2Px(Context context, float dp) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    /**
     * 初始化AMap对象
     */
    private void init() {
        mScrollView = (ScrollView) findViewById(R.id.view_content);
//        //设置地图栏fragment，当为单个用户登入时，不显示该栏
        setMapDefaultFragment();
        //设置表格fragment
        setChartDefaultFragment();
    }

    //设置地图栏fragment
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

    // 监视键盘的返回键
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            MessageUtils.ShowExitMessage(this);
            return false;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }

//    /**
//     * 方法必须重写
//     */
//    @Override
//    protected void onResume() {
//        super.onResume();
//    }
//
//    /**
//     * 方法必须重写
//     */
//    @Override
//    protected void onPause() {
//        super.onPause();
//    }
//
//    /**
//     * 方法必须重写
//     */
//    @Override
//    protected void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//    }
//
//    /**
//     * 方法必须重写
//     */
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.drop_one:
                Toast.makeText(MapActivity.this, "点击了drop_one", Toast.LENGTH_SHORT).show();
                break;
            case R.id.drop_two:
                Toast.makeText(MapActivity.this, "点击了drop_two", Toast.LENGTH_SHORT).show();
                break;
            case R.id.drop_three:
                Toast.makeText(MapActivity.this, "点击了drop_three", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
}

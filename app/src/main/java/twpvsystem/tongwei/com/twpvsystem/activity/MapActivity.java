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
        setContentView(R.layout.content_map);

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        }

        toolbar.setNavigationIcon(R.mipmap.menu_icon1);//设置导航栏图标
//        toolbar.setLogo(R.mipmap.ic_launcher);//设置app logo
//        toolbar.setTitle("Title");//设置主标题
//        toolbar.setSubtitle("Subtitle");//设置子标题

        toolbar.inflateMenu(R.menu.base_toolbar_menu);//设置右上角的填充菜单
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MapActivity.this, "点击了navigation按钮", Toast.LENGTH_SHORT).show();
                popUpMyOverflow();//弹出自定义overflow
            }
        });
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int menuItemId = item.getItemId();
                if (menuItemId == R.id.action_search) {
                    Toast.makeText(MapActivity.this, "menu_search", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MapActivity.this, ChoiceCustomerActivity.class);
                    MapActivity.this.startActivity(intent);

                }
//                else if (menuItemId == R.id.action_notification) {
//                    Toast.makeText(MapActivity.this , "menu_notifications" , Toast.LENGTH_SHORT).show();
//
//                } else if (menuItemId == R.id.action_item1) {
//                    Toast.makeText(MapActivity.this , "item_01" , Toast.LENGTH_SHORT).show();
//
//                } else if (menuItemId == R.id.action_item2) {
//                    Toast.makeText(MapActivity.this , "item_02" , Toast.LENGTH_SHORT).show();
//
//                }
                return true;
            }
        });


        init();




}


    public void popUpMyOverflow() {
        /**
         * 定位PopupWindow，让它恰好显示在Action Bar的下方。 通过设置Gravity，确定PopupWindow的大致位置。
         * 首先获得状态栏的高度，再获取Action bar的高度，这两者相加设置y方向的offset样PopupWindow就显示在action
         * bar的下方了。 通过dp计算出px，就可以在不同密度屏幕统一X方向的offset.但是要注意不要让背景阴影大于所设置的offset，
         * 否则阴影的宽度为offset.
         */
        // 获取状态栏高度
        Rect frame = new Rect();
        getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
//    状态栏高度：frame.top
//        int xOffset = frame.top + toolbar.getHeight() - 25;//减去阴影宽度，适配UI.
        int xOffset = frame.top + toolbar.getHeight();//减去阴影宽度，适配UI.
//        int yOffset = Dp2Px(this, 5f); //设置x方向offset为5dp
        int yOffset = 0; //设置x方向offset为5dp
        View parentView = getLayoutInflater().inflate(R.layout.activity_main,
                null);
        View popView = getLayoutInflater().inflate(
                R.layout.action_overflow_popwindow, null);
        LinearLayout drop_one = (LinearLayout) popView.findViewById(R.id.drop_one);
        drop_one.setOnClickListener(this);
        PopupWindow popWind = new PopupWindow(popView,
                Toolbar.LayoutParams.WRAP_CONTENT, Toolbar.LayoutParams.WRAP_CONTENT, true);//popView即popupWindow的布局，ture设置focusAble.

        //必须设置BackgroundDrawable后setOutsideTouchable(true)才会有效。这里在XML中定义背景，所以这里设置为null;
        popWind.setBackgroundDrawable(new BitmapDrawable(getResources(),
                (Bitmap) null));
        popWind.setOutsideTouchable(true); //点击外部关闭。
        popWind.setAnimationStyle(android.R.style.Animation_Dialog);  //设置一个动画。
        //设置Gravity，让它显示在右上角。
//        popWind.showAtLocation(parentView, Gravity.RIGHT | Gravity.TOP,
//                yOffset, xOffset);
        //设置Gravity，让它显示在右上角。
        popWind.showAtLocation(parentView, Gravity.LEFT|Gravity.TOP,
                yOffset, xOffset);


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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.drop_one:
                Toast.makeText(MapActivity.this, "点击了drop_one", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
}

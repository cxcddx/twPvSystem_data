package twpvsystem.tongwei.com.twpvsystem.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.google.gson.Gson;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import twpvsystem.tongwei.com.twpvsystem.R;
import twpvsystem.tongwei.com.twpvsystem.bean.MainData;
import twpvsystem.tongwei.com.twpvsystem.fragment.ChartMainFragment;
import twpvsystem.tongwei.com.twpvsystem.fragment.ElecFragment;
import twpvsystem.tongwei.com.twpvsystem.fragment.EnergyFragment;
import twpvsystem.tongwei.com.twpvsystem.fragment.MapFragment;
import twpvsystem.tongwei.com.twpvsystem.util.Constants;
import twpvsystem.tongwei.com.twpvsystem.util.MessageUtils;
import twpvsystem.tongwei.com.twpvsystem.util.myOkhttpUtil;

//public class MapActivity extends AppCompatActivity implements View.OnClickListener, AMap.OnMarkerDragListener, AMap.OnMapLoadedListener, AMap.OnMarkerClickListener, AMap.OnInfoWindowClickListener, AMap.InfoWindowAdapter, AMap.OnMapTouchListener {
public class MapActivity extends BaseActivity implements View.OnClickListener {
    public static ScrollView mScrollView;
    private RelativeLayout nodata;

    private Fragment fragment_chart, fragment_energy, fragment_elec, fragment_map;
    private static final String TAG = "MapActivity";
    private Gson gson;
    public static String totalPower, dailyPower, installedCapacity, earn, reduce, coalSaving, reduceDeforestation;
    public static String totalPowerUnit, dailyPowerUnit, installedCapacityUnit, earnUnit, reduceUnit, coalSavingUnit, reduceDeforestationUnit;
    private long userId;
    private boolean isFirst;
    private boolean isFinishing;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getToolbarTitle().setText(R.string.app_name);
        initBaseActivity();
        init();
        getDataPost();


    }

    @Override
    protected void refreshAction() {
        super.refreshAction();
        /*
        执行刷新数据操作
         */
        freshData();
    }

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
    protected void onStart() {
        super.onStart();
        isFinishing = false;
    }

    @Override
    protected void onStop() {
        super.onStop();
        isFinishing = true;
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

    /**
     * 初始化AMap对象
     */
    private void init() {
        isFirst = true;
        nodata = (RelativeLayout) this.findViewById(R.id.nodata);
        mScrollView = (ScrollView) this.findViewById(R.id.view_content);
//        //设置地图栏fragment，当为单个用户登入时，不显示该栏
        setMapDefaultFragment();
        //设置表格fragment
//        setChartDefaultFragment();

    }

    private void freshData() {
        getDataPost();
    }
    /*
    根据id获取电量等数据
     */

    private void getDataPost() {
        gson = new Gson();
        userId = m_sharedHelper.getLongValue(Constants.UserId);
        Map<String, String> params = new HashMap<>();
        params.put("userId", "" + userId);
        myOkhttpUtil.postMethod(Constants.URL_ELEC, params, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                MessageUtils.ShowToast(MapActivity.this, "总数据获取失败");
            }

            @Override
            public void onResponse(String response, int id) {
                if (!(response == null || response.length() <= 0)) {
//                    TotalData total = gson.fromJson(response, TotalData.class);
                    MainData total = gson.fromJson(response, MainData.class);
                    if (total.getCode() == 200) {
                        nodata.setVisibility(View.GONE);
                        MapActivity.totalPower = total.getData().getTotalPower();
                        MapActivity.dailyPower = total.getData().getDailyPower();
                        MapActivity.installedCapacity = total.getData().getInstalledCapacity();
                        MapActivity.earn = total.getData().getEarn();
                        MapActivity.reduce = total.getData().getReduce();
                        MapActivity.coalSaving = total.getData().getCoalSaving();
                        MapActivity.reduceDeforestation = total.getData().getReduceDeforestation();

                        MapActivity.totalPowerUnit = total.getData().getTotalPowerUnit();
                        MapActivity.dailyPowerUnit = total.getData().getDailyPowerUnit();
                        MapActivity.installedCapacityUnit = total.getData().getInstalledCapacityUnit();
                        MapActivity.earnUnit = total.getData().getEarnUnit();
                        MapActivity.reduceUnit = total.getData().getReduceUnit();
                        MapActivity.coalSavingUnit = total.getData().getCoalSavingUnit();
                        MapActivity.reduceDeforestationUnit = total.getData().getReduceDeforestationUnit();

                        if(!isFinishing) {
                            if (isFirst) {
                                isFirst = false;
                                //设置电量fragment
                                setElecDefaultFragment();
                                //设置表格fragment
                                setChartDefaultFragment();
                                //设置节能减排fragment
                                setEnergyDefaultFragment();
                            } else {
                                //设置电量fragment
                                setElecDefaultFragment();
                                //设置节能减排fragment
                                setEnergyDefaultFragment();
                            }
                        }
                    } else {
                        nodata.setVisibility(View.VISIBLE);
                    }
                }

            }
        });
    }


    //设置地图栏fragment
    private void setMapDefaultFragment() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        fragment_map = new MapFragment();
        transaction.replace(R.id.fragment_map, fragment_map);
        transaction.commitAllowingStateLoss();
    }

    //设置电量fragment
    private void setElecDefaultFragment() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        fragment_elec = new ElecFragment();
        transaction.replace(R.id.fragment_elec, fragment_elec);
        transaction.commitAllowingStateLoss();
    }

    //设置表格栏fragment
    private void setChartDefaultFragment() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        fragment_chart = new ChartMainFragment();
        Bundle bundle = new Bundle();
        bundle.putLong(Constants.UserId, userId);
        fragment_chart.setArguments(bundle);
        transaction.replace(R.id.fragment_chart, fragment_chart);
        transaction.commitAllowingStateLoss();
    }

    //设置节能减排fragment
    private void setEnergyDefaultFragment() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        fragment_energy = new EnergyFragment();
        transaction.replace(R.id.fragment_energy, fragment_energy);
        transaction.commit();
    }

    // 监视键盘的返回键
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            MessageUtils.exitMessage(this);
            return false;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.drop_switch:
                m_sharedHelper.putValue(Constants.Switch_Customer, true);
                Intent intent1 = new Intent(MapActivity.this, LoginActivity.class);
                MapActivity.this.startActivity(intent1);
                MapActivity.this.finish();
                break;
            case R.id.drop_cpwd:
                Intent intent = new Intent(MapActivity.this, ChangePwdActivity.class);
                MapActivity.this.startActivity(intent);
                break;
            case R.id.drop_about:
                Intent intent2 = new Intent(MapActivity.this, AboutActivity.class);
                MapActivity.this.startActivity(intent2);
                break;
            case R.id.drop_quit:
                MessageUtils.exitMessage(this);
                break;
            default:
                break;
        }
    }
}

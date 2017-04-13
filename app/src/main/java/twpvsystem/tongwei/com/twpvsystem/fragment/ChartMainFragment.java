package twpvsystem.tongwei.com.twpvsystem.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.gson.Gson;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import okhttp3.Call;
import twpvsystem.tongwei.com.twpvsystem.R;
import twpvsystem.tongwei.com.twpvsystem.bean.ChartData;
import twpvsystem.tongwei.com.twpvsystem.util.Constants;
import twpvsystem.tongwei.com.twpvsystem.util.MessageUtils;
import twpvsystem.tongwei.com.twpvsystem.util.Util;
import twpvsystem.tongwei.com.twpvsystem.util.myOkhttpUtil;


public class ChartMainFragment extends Fragment implements View.OnClickListener {

    private Button btn_one, btn_two, btn_three;
    /**
     * 作为页面容器的ViewPager
     */
    ViewPager mViewPager;
    /**
     * 页面集合
     */
    List<Fragment> fragmentList;

    /**
     * 四个Fragment（页面）
     */
    ChartOneFragment oneFragment;
    ChartTwoFragment twoFragment;
    ChartThreeFragment threeFragment;
    private static final String TAG = "ChartMainFragment";

    //当前选中的项
    int currenttab = -1;
    private Long id;
    private Gson gson;
    public ChartData chart_data;
    private boolean isFirst;
    private MyFrageStatePagerAdapter m_adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chart_main, null, false);
        initView(view);
        initData();
//        initFragment();
        return view;

    }

    private void initView(View view) {
        btn_one = (Button) view.findViewById(R.id.btn_one);
        btn_two = (Button) view.findViewById(R.id.btn_two);
        btn_three = (Button) view.findViewById(R.id.btn_three);
        mViewPager = (ViewPager) view.findViewById(R.id.viewpager);

        btn_one.setOnClickListener(this);
        btn_two.setOnClickListener(this);
        btn_three.setOnClickListener(this);
    }

    private void initData() {
        isFirst = true;
        id = (Long) getArguments().get(Constants.UserId);
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                getDataPost();
            }
        }, 0, Constants.FRESH_TIME);


    }

     /*
    根据id获取电量等数据
     */

    private void getDataPost() {
        gson = new Gson();
        Map<String, String> params = new HashMap<>();
        params.put("userId", "" + id);
        params.put("date", Util.today());
        myOkhttpUtil.postMethod(Constants.URL_CHART, params, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                MessageUtils.ShowToast(getActivity(), "总数据获取失败");
            }

            @Override
            public void onResponse(String response, int id) {
                if (response != null) {
                    Log.i(TAG, "onResponse: "+ response);
                    chart_data = gson.fromJson(response, ChartData.class);
                    if(chart_data.getCode() == 200) {
                        if(isFirst) {
                            isFirst = false;
                            initFragment();
                        } else {
                            freshFragment();
                        }
                    }
                }

            }
        });
    }

    public ChartData getChartData() {
        ChartData a = this.chart_data;
        return a;
    }

    private void initFragment() {

        fragmentList = new ArrayList<Fragment>();
        oneFragment = new ChartOneFragment();
        twoFragment = new ChartTwoFragment();
        threeFragment = new ChartThreeFragment();

        fragmentList.add(oneFragment);
        fragmentList.add(twoFragment);
        fragmentList.add(threeFragment);
        m_adapter = new MyFrageStatePagerAdapter(getChildFragmentManager());

        mViewPager.setAdapter(m_adapter);
        btn_one.setBackgroundResource(R.drawable.tab_down);
        btn_one.setTextColor(this.getResources().getColor(R.color.white));
    }

    private void freshFragment() {
       m_adapter.notifyDataSetChanged();

    }

    /**
     * 定义自己的ViewPager适配器。
     * 也可以使用FragmentPagerAdapter。关于这两者之间的区别，可以自己去搜一下。
     */
    class MyFrageStatePagerAdapter extends FragmentStatePagerAdapter {

        public MyFrageStatePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        /**
         * 每次更新完成ViewPager的内容后，调用该接口，此处复写主要是为了让导航按钮上层的覆盖层能够动态的移动
         */
        @Override
        public void finishUpdate(ViewGroup container) {
            super.finishUpdate(container);//这句话要放在最前面，否则会报错
            //获取当前的视图是位于ViewGroup的第几个位置，用来更新对应的覆盖层所在的位置
            int currentItem = mViewPager.getCurrentItem();
            if (currentItem == currenttab) {
                return;
            }
            //切换按钮选中状态
            changeBtnStyle(mViewPager.getCurrentItem());
            currenttab = mViewPager.getCurrentItem();
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_one:
                changeView(0);
                break;
            case R.id.btn_two:
                changeView(1);
                break;
            case R.id.btn_three:
                changeView(2);
                break;
            default:
                break;
        }

    }

    //手动设置ViewPager要显示的视图
    private void changeView(int desTab) {
        mViewPager.setCurrentItem(desTab, true);
        changeBtnStyle(desTab);
    }
    //切换按钮背景
    private void changeBtnStyle(int desTab) {
        switch (desTab) {
            case 0:
//                btn_one.setBackgroundColor(Color.parseColor("#F522DC"));
                btn_one.setBackgroundResource(R.drawable.tab_down);
                btn_two.setBackgroundColor(Color.parseColor("#ffffff"));
                btn_three.setBackgroundColor(Color.parseColor("#ffffff"));
                btn_one.setTextColor(this.getResources().getColor(R.color.white));
                btn_two.setTextColor(this.getResources().getColor(R.color.black));
                btn_three.setTextColor(this.getResources().getColor(R.color.black));
                break;
            case 1:
                btn_one.setBackgroundColor(Color.parseColor("#ffffff"));
                btn_two.setBackgroundResource(R.drawable.tab_down);
                btn_three.setBackgroundColor(Color.parseColor("#ffffff"));
                btn_one.setTextColor(this.getResources().getColor(R.color.black));
                btn_two.setTextColor(this.getResources().getColor(R.color.white));
                btn_three.setTextColor(this.getResources().getColor(R.color.black));
                break;
            case 2:
                btn_one.setBackgroundColor(Color.parseColor("#ffffff"));
                btn_two.setBackgroundColor(Color.parseColor("#ffffff"));
                btn_three.setBackgroundResource(R.drawable.tab_down);
                btn_one.setTextColor(this.getResources().getColor(R.color.black));
                btn_two.setTextColor(this.getResources().getColor(R.color.black));
                btn_three.setTextColor(this.getResources().getColor(R.color.white));
                break;
            default:
                break;
        }
    }
}

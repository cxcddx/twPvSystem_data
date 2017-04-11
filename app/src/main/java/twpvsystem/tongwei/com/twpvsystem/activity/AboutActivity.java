package twpvsystem.tongwei.com.twpvsystem.activity;

import android.os.Bundle;

import twpvsystem.tongwei.com.twpvsystem.R;

/**
 * 登录界面
 */
public class AboutActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getToolbarTitle().setText("关于");

    }

//    /**
//     * 设置不显示返回按钮
//     *
//     * @return
//     */
    protected boolean isShowBacking() {
        return true;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_about;
    }
    /**
     * 设置显示菜单按钮
     *
     * @return
     */
//    protected boolean isShowMore() {
//        return true;
//    }

}
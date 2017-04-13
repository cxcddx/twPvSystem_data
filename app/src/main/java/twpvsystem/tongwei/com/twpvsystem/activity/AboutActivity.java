package twpvsystem.tongwei.com.twpvsystem.activity;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.TextView;

import twpvsystem.tongwei.com.twpvsystem.R;

/**
 * 登录界面
 */
public class AboutActivity extends BaseActivity {

    private TextView m_txtVersion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getToolbarTitle().setText("关于");
        m_txtVersion = (TextView) this.findViewById(R.id.txtVersion);
        setVersion();

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
    // 设置版本
    private void setVersion()
    {
        try
        {
            PackageManager manager = this.getPackageManager();
            PackageInfo info = manager.getPackageInfo(this.getPackageName(), 0);
            m_txtVersion.setText(getString(R.string.app_name) + " Version:" + info.versionName);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}
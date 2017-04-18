package twpvsystem.tongwei.com.twpvsystem.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import twpvsystem.tongwei.com.twpvsystem.R;
import twpvsystem.tongwei.com.twpvsystem.bean.ChangePwd;
import twpvsystem.tongwei.com.twpvsystem.util.Constants;
import twpvsystem.tongwei.com.twpvsystem.util.MessageUtils;
import twpvsystem.tongwei.com.twpvsystem.util.myOkhttpUtil;

/**
 * 登录界面
 */
public class ChangePwdActivity extends BaseActivity{

    private EditText editOldPwd, editNewPwd1,editNewPwd2;
    private Button okBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getToolbarTitle().setText("修改密码");
        initBaseActivity();
        init();

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
        return R.layout.activity_cpwd;
    }

    private void init() {
        editOldPwd = (EditText) this.findViewById(R.id.txtOldPsw);
        editNewPwd1 = (EditText) this.findViewById(R.id.txtNewPsw1);
        editNewPwd2 = (EditText) this.findViewById(R.id.txtNewPsw2);
        okBtn = (Button) this.findViewById(R.id.btnOK);
        okBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.btnOK :
                doChangePwdPost();
                break;
            default:
                break;
        }
    }

    private void doChangePwdPost() {

        String savePwd = m_sharedHelper.getValue(Constants.UserPwd);
        String oldPwd = editOldPwd.getText().toString().trim();
        if (!savePwd.equals(oldPwd)) {
//            MessageUtils.ShowToast(this, "旧密码输入不正确!");
            MessageUtils.infoMessage(ChangePwdActivity.this, "旧密码输入不正确!");
            return;
        }

        String newPwd1 = editNewPwd1.getText().toString().trim();
        String newPwd2 = editNewPwd2.getText().toString().trim();

        if (!newPwd1.equals(newPwd2)) {
//            MessageUtils.ShowToast(this, "新密码和确认密码不一致!");
            MessageUtils.infoMessage(ChangePwdActivity.this, "新密码和确认密码不一致!");
            return;
        }
        Map<String, String> datas = new HashMap<String, String>();
        datas.put("username", m_sharedHelper.getValue(Constants.UserName));
        datas.put("newPwd", newPwd1);
        datas.put("oldPwd", oldPwd);
        myOkhttpUtil.postMethod(Constants.URL_CHANGE_PWD, datas, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                if(!TextUtils.isEmpty(response)) {
                    Gson gson = new Gson();
                    ChangePwd cPwd = gson.fromJson(response, ChangePwd.class);
                    if(cPwd.getCode() == 200) {
                        MessageUtils.ShowToast(ChangePwdActivity.this, cPwd.getMsg());
                        ChangePwdActivity.this.finish();
                    } else if(cPwd.getCode() == 1003||cPwd.getCode() == 1007) {
                        MessageUtils.infoMessage(ChangePwdActivity.this, cPwd.getMsg());
                    } else {
                        MessageUtils.infoMessage(ChangePwdActivity.this, "修改密码失败");
                    }
                }

            }
        });
    }
}
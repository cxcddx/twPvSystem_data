package twpvsystem.tongwei.com.twpvsystem.activity;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.Request;
import twpvsystem.tongwei.com.twpvsystem.R;
import twpvsystem.tongwei.com.twpvsystem.bean.User;
import twpvsystem.tongwei.com.twpvsystem.util.EventbusUtil;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_content;
    private Button btn_get, btn_post, btn_intent,btn_eventbus,btn_map, btn_tab;
    private ProgressBar mProgressBar;

    public class MyStringCallback extends StringCallback
    {
        @Override
        public void onBefore(Request request, int id)
        {
            mProgressBar.setVisibility(View.VISIBLE);
            setTitle("loading...");
        }

        @Override
        public void onAfter(int id)
        {
            setTitle("Sample-okHttp");
            mProgressBar.setVisibility(View.GONE);

        }

        @Override
        public void onError(Call call, Exception e, int id)
        {
            e.printStackTrace();
            tv_content.setText("onError:" + e.getMessage());
        }

        @Override
        public void onResponse(String response, int id)
        {
//            Log.e(TAG, "onResponse：complete");
            tv_content.setText("onResponse:" + response);

            switch (id)
            {
                case 100:
                    Toast.makeText(MainActivity.this, "http", Toast.LENGTH_SHORT).show();
                    break;
                case 101:
                    Toast.makeText(MainActivity.this, "https", Toast.LENGTH_SHORT).show();
                    break;
            }
        }

        @Override
        public void inProgress(float progress, long total, int id)
        {
//            Log.e(TAG, "inProgress:" + progress);
//            mProgressBar.setProgress((int) (100 * progress));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        regist();
    }

    private void init() {
        tv_content = (TextView) this.findViewById(R.id.tv_content);
        btn_get = (Button) this.findViewById(R.id.btn_get);
        btn_post = (Button) this.findViewById(R.id.btn_post);
        btn_intent = (Button) this.findViewById(R.id.btn_intent);
        btn_eventbus = (Button) this.findViewById(R.id.btn_eventbus);
        btn_map = (Button) this.findViewById(R.id.btn_map);
        btn_tab = (Button) this.findViewById(R.id.btn_tab);
        mProgressBar = (ProgressBar) findViewById(R.id.id_progress);

        btn_get.setOnClickListener(this);
        btn_post.setOnClickListener(this);
        btn_intent.setOnClickListener(this);
        btn_eventbus.setOnClickListener(this);
        btn_map.setOnClickListener(this);
        btn_tab.setOnClickListener(this);
    }

    private void regist() {
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe          //订阅事件FirstEvent
    public  void onEventMainThread(EventbusUtil event){
        String msg=event.getMsg();
        tv_content.setText("从Eventbus页面返回的值为："+msg);//获取事件中传递的参数
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_get:
                getMethod();
                break;
            case R.id.btn_post:
                postStringMethod();
                break;
            case R.id.btn_intent:
                Intent intent = new Intent(MainActivity.this, ChartActivity.class);
                MainActivity.this.startActivity(intent);
                break;
            case R.id.btn_eventbus:
                Intent intent2 = new Intent(MainActivity.this, EventbusActivity.class);
                MainActivity.this.startActivity(intent2);
                break;
            case R.id.btn_map:
                Intent intent3 = new Intent(MainActivity.this, MapActivity.class);
                MainActivity.this.startActivity(intent3);
                break;
            case R.id.btn_tab:
                Intent intent4 = new Intent(MainActivity.this, TabActivity.class);
                MainActivity.this.startActivity(intent4);
            default:
                break;
        }
    }

    private void getMethod() {

        String url = "http://www.zhiyun-tech.com/App/Rider-M/changelog-zh.txt";
        url="http://172.20.157.126:8080/web/Android/AlertLog/UnreadCount?custId=df1e27ff-95e6-4549-952f-f95e74e9a11e";
        OkHttpUtils
                .get()
                .url(url)
//                .id(100)
                .build()
                .execute(new MyStringCallback());
    }

    private void postStringMethod() {

        String url = "http://172.20.157.126:8080/web/Android/AlertLog/ToViewed";
        OkHttpUtils
                .post()
                .url(url)
                .addParams("custId", "df1e27ff-95e6-4549-952f-f95e74e9a11e")
                .build()
                .execute(new MyStringCallback());
    }
}

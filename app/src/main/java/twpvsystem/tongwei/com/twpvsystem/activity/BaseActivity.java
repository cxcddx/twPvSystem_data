package twpvsystem.tongwei.com.twpvsystem.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import twpvsystem.tongwei.com.twpvsystem.R;

public class BaseActivity extends AppCompatActivity {

    Button btn_one,btn_two,btn_three;

    public void baseLoadView() {
        btn_one = (Button) this.findViewById(R.id.btn_one);
        btn_two = (Button) this.findViewById(R.id.btn_two);
        btn_three = (Button) this.findViewById(R.id.btn_three);
//
//        btn_one.setOnClickListener(this);
//        btn_two.setOnClickListener(this);
//        btn_three.setOnClickListener(this);

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        Toast.makeText(this, "继承自baseactivity", Toast.LENGTH_SHORT).show();

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//
//        toolbar.setNavigationIcon(R.mipmap.threshold_icon10);//设置导航栏图标
//        toolbar.setLogo(R.mipmap.ic_launcher);//设置app logo
//        toolbar.setTitle("Title");//设置主标题
//        toolbar.setSubtitle("Subtitle");//设置子标题
//
//        toolbar.inflateMenu(R.menu.base_toolbar_menu);//设置右上角的填充菜单
//        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem item) {
//                int menuItemId = item.getItemId();
//                if (menuItemId == R.id.action_search) {
//                    Toast.makeText(BaseActivity.this , "menu_search" , Toast.LENGTH_SHORT).show();
//
//                } else if (menuItemId == R.id.action_notification) {
//                    Toast.makeText(BaseActivity.this , "menu_notifications" , Toast.LENGTH_SHORT).show();
//
//                } else if (menuItemId == R.id.action_item1) {
//                    Toast.makeText(BaseActivity.this , "item_01" , Toast.LENGTH_SHORT).show();
//
//                } else if (menuItemId == R.id.action_item2) {
//                    Toast.makeText(BaseActivity.this , "item_02" , Toast.LENGTH_SHORT).show();
//
//                }
//                return true;
//            }
//        });


    }


}

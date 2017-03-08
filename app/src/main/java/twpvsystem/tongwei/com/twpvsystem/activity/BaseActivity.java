package twpvsystem.tongwei.com.twpvsystem.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import twpvsystem.tongwei.com.twpvsystem.R;

public class BaseActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn_one,btn_two,btn_three;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
////        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
////        setSupportActionBar(toolbar);
//
//        init();
//    }

    public void baseLoadView() {
        btn_one = (Button) this.findViewById(R.id.btn_one);
        btn_two = (Button) this.findViewById(R.id.btn_two);
        btn_three = (Button) this.findViewById(R.id.btn_three);

        btn_one.setOnClickListener(this);
        btn_two.setOnClickListener(this);
        btn_three.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
//        switch(v.getId()) {
////            case R.id.btn_one:
//////                Toast.makeText(BaseActivity.this, "点击了按钮1", Toast.LENGTH_LONG).show();
////                System.out.println("点击了按钮1");
////                break;
////            case R.id.btn_two:
//////                Toast.makeText(BaseActivity.this, "点击了按钮2", Toast.LENGTH_LONG).show();
////                System.out.println("点击了按钮2");
////                break;
////            case R.id.btn_three:
//////                Toast.makeText(BaseActivity.this, "点击了按钮3", Toast.LENGTH_LONG).show();
////                System.out.println("点击了按钮3");
////                break;
//            default:
//                break;
//        }
    }
}

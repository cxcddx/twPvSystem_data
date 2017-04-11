package twpvsystem.tongwei.com.twpvsystem.activity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

import twpvsystem.tongwei.com.twpvsystem.R;
import twpvsystem.tongwei.com.twpvsystem.util.Constants;
import twpvsystem.tongwei.com.twpvsystem.util.SharedHelper;

public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = BaseActivity.class.getSimpleName();
    private TextView mToolbarTitle;
    public Toolbar mToolbar;
    public static SharedHelper m_sharedHelper;

    protected void initBaseActivity() {
        m_sharedHelper = new SharedHelper(this, Constants.Shared_NAME);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(getLayoutId());

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
       /*
        toolbar.setLogo(R.mipmap.ic_launcher);
        toolbar.setTitle("Title");
        toolbar.setSubtitle("Sub Title");
        */
        mToolbarTitle = (TextView) findViewById(R.id.title);
//        mToolbarSubTitle = (TextView) findViewById(R.id.toolbar_subtitle);
        if (mToolbar != null) {
            //将Toolbar显示到界面
            setSupportActionBar(mToolbar);
        }
        if (mToolbarTitle != null) {
            //getTitle()的值是activity的android:lable属性值
            mToolbarTitle.setText(getTitle());
            //设置默认的标题不显示
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        //每隔一段时间刷新页面
        fresh();


    }

    private void fresh() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                refreshAction();
            }
        }, 1000, Constants.FRESH_TIME);
    }

    protected void refreshAction() {
    }

    @Override
    protected void onStart() {
        super.onStart();
        /**
         * 判断是否有Toolbar,并默认显示返回按钮
         */
        if(null != getToolbar() && isShowBacking()||!isShowMore()){
            showBack();
        } else {
            showmore();
        }
    }

    /**
     * 获取头部标题的TextView
     * @return
     */
    public TextView getToolbarTitle(){
        return mToolbarTitle;
    }

    /**
     * 设置头部标题
     * @param title
     */
    public void setToolBarTitle(CharSequence title) {
        if(mToolbarTitle != null){
            mToolbarTitle.setText(title);
        }else{
            getToolbar().setTitle(title);
            setSupportActionBar(getToolbar());
        }
    }

    /**
     * this Activity of tool bar.
     * 获取头部.
     * @return support.v7.widget.Toolbar.
     */
    public Toolbar getToolbar() {
        return (Toolbar) findViewById(R.id.toolbar);
    }

    /**
     * 版本号小于21的后退按钮图片
     */
    private void showBack(){
        //setNavigationIcon必须在setSupportActionBar(toolbar);方法后面加入
        getToolbar().setNavigationIcon(R.mipmap.return_icon1);
        getToolbar().setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    onBackPressed();
            }
        });
    }

    /**
     * 更多功能按钮图片
     */
    public void showmore(){
        //setNavigationIcon必须在setSupportActionBar(toolbar);方法后面加入
        getToolbar().setNavigationIcon(R.mipmap.menu_icon1);
        getToolbar().setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                onBackPressed();
                popUpMyOverflow();
            }
        });
    }


    /**
     * 是否显示后退按钮,默认显示,可在子类重写该方法.
     * @return
     */
    protected boolean isShowBacking(){
        return false;
    }

    /**
     * 是否显示菜单按钮,默认显示,可在子类重写该方法.
     * @return
     */
    protected boolean isShowMore(){
        return true;
    }

    /**
     * this activity layout res
     * 设置layout布局,在子类重写该方法.
     * @return res layout xml id
     */
    protected abstract int getLayoutId();

    @Override
    protected void onDestroy() {
        super.onDestroy();

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
        int xOffset = frame.top + mToolbar.getHeight();//减去阴影宽度，适配UI.
//        int yOffset = Dp2Px(this, 5f); //设置x方向offset为5dp
        int yOffset = 0; //设置x方向offset为5dp
        View parentView = getLayoutInflater().inflate(R.layout.activity_main,
                null);
        View popView = getLayoutInflater().inflate(
                R.layout.action_overflow_popwindow, null);
        initDrop(popView);

        PopupWindow popWind = new PopupWindow(popView,
                Toolbar.LayoutParams.WRAP_CONTENT, Toolbar.LayoutParams.WRAP_CONTENT, true);//popView即popupWindow的布局，ture设置focusAble.

        //必须设置BackgroundDrawable后setOutsideTouchable(true)才会有效。这里在XML中定义背景，所以这里设置为null;
        popWind.setBackgroundDrawable(new BitmapDrawable(getResources(),
                (Bitmap) null));
        popWind.setOutsideTouchable(true); //点击外部关闭。
        popWind.setAnimationStyle(android.R.style.Animation_Dialog);  //设置一个动画。

        /*
        设置popwind弹出后背景模糊
         */


////        /在PopupWindow里面就加上下面代码，让键盘弹出时，不会挡住pop窗口。
//        popWind.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
//        popWind.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
////点击空白处时，隐藏掉pop窗口
//        popWind.setFocusable(true);
//        popWind.setBackgroundDrawable(new BitmapDrawable());
//        backgroundAlpha(0.5f);
//
//        //添加pop窗口关闭事件
//        popWind.setOnDismissListener(new poponDismissListener());



        //设置Gravity，让它显示在右上角。
//        popWind.showAtLocation(parentView, Gravity.RIGHT | Gravity.TOP,
//                yOffset, xOffset);
        //设置Gravity，让它显示在左上角。
        popWind.showAtLocation(parentView, Gravity.LEFT|Gravity.TOP,
                yOffset, xOffset);


    }


//    /**
//     * 添加新笔记时弹出的popWin关闭的事件，主要是为了将背景透明度改回来
//     * @author cg
//     *
//     */
//    class poponDismissListener implements PopupWindow.OnDismissListener{
//
//        @Override
//        public void onDismiss() {
//            // TODO Auto-generated method stub
//            //Log.v("List_noteTypeActivity:", "我是关闭事件");
//            backgroundAlpha(1f);
//        }
//
//    }
//
//
//
//    /**
//     * 设置添加屏幕的背景透明度
//     * @param bgAlpha
//     */
//    public void backgroundAlpha(float bgAlpha)
//    {
//        WindowManager.LayoutParams lp = getWindow().getAttributes();
//        lp.alpha = bgAlpha; //0.0-1.0
//        getWindow().setAttributes(lp);
//    }


    public int Dp2Px(Context context, float dp) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    private void initDrop(View popView) {
        LinearLayout drop_one = (LinearLayout) popView.findViewById(R.id.drop_one);
        LinearLayout drop_cpwd = (LinearLayout) popView.findViewById(R.id.drop_cpwd);
        LinearLayout drop_about = (LinearLayout) popView.findViewById(R.id.drop_about);
        drop_one.setOnClickListener(this);
        drop_cpwd.setOnClickListener(this);
        drop_about.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {

    }



}

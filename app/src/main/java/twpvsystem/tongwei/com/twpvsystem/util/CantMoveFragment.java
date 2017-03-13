package twpvsystem.tongwei.com.twpvsystem.util;

/**
 * Created by CX on 2017/3/10.
 */


import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * 不可以滑动，但是可以setCurrentItem的ViewPager。
 */
public class CantMoveFragment extends ViewPager {
    public CantMoveFragment(Context context) {
        super(context);
    }

    public CantMoveFragment(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent arg0) {
        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent arg0) {
        return false;
    }
}
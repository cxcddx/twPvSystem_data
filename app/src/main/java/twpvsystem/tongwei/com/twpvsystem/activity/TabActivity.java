package twpvsystem.tongwei.com.twpvsystem.activity;

import android.app.ActionBar;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.kekstudio.dachshundtablayout.DachshundTabLayout;

import twpvsystem.tongwei.com.twpvsystem.R;
import twpvsystem.tongwei.com.twpvsystem.adapter.B_PhModeHomeAdapter;


public class TabActivity extends AppCompatActivity {

    private static final String DOG_BREEDS[] = {"Pug", "Beagle", "Bulldog", "Poodle"};

    private ViewPager viewPager;
    private DachshundTabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

        viewPager = (ViewPager) findViewById(R.id.view_pager);
//        viewPager.setAdapter(new PagerAdapter(getSupportFragmentManager()));
        viewPager.setAdapter(new B_PhModeHomeAdapter(getSupportFragmentManager(), this));

        tabLayout = (DachshundTabLayout) findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);

    }

//    public void onClickDachshund(View view){
//        tabLayout.setAnimatedIndicator(new DachshundIndicator(tabLayout));
//    }
//
//    public void onClickPointMove(View view){
//        tabLayout.setAnimatedIndicator(new PointMoveIndicator(tabLayout));
//    }
//
//    public void onClickPointMoveAccelerate(View view){
//        PointMoveIndicator pointMoveIndicator = new PointMoveIndicator(tabLayout);
//        pointMoveIndicator.setInterpolator(new AccelerateInterpolator());
//        tabLayout.setAnimatedIndicator(pointMoveIndicator);
//    }
//
//    public void onClickLineMove(View view){
//        tabLayout.setAnimatedIndicator(new LineMoveIndicator(tabLayout));
//    }
//
//    public void onClickPointFade(View view){
//        tabLayout.setAnimatedIndicator(new PointFadeIndicator(tabLayout));
//    }
//
//    public void onClickLineFade(View view){
//        LineFadeIndicator lineFadeIndicator = new LineFadeIndicator(tabLayout);
//        tabLayout.setAnimatedIndicator(lineFadeIndicator);
//
//        lineFadeIndicator.setSelectedTabIndicatorHeight(HelperUtils.dpToPx(2));
//        lineFadeIndicator.setEdgeRadius(0);
//    }

//    public class PagerAdapter extends FragmentStatePagerAdapter {
//        public PagerAdapter(FragmentManager fm) {
//            super(fm);
//        }
//
//        @Override
//        public Fragment getItem(int i) {
//            return new PageFragment();
//        }
//
//        @Override
//        public int getCount() {
//            return DOG_BREEDS.length;
//        }
//
//        @Override
//        public CharSequence getPageTitle(int position) {
//            return DOG_BREEDS[position];
//        }
//    }
//
//    public static class PageFragment extends Fragment {
//
//        public PageFragment() {
//        }
//
//        @Override
//        public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                                 Bundle savedInstanceState) {
//            return inflater.inflate(R.layout.fragment_page, container, false);
//        }
//    }

}

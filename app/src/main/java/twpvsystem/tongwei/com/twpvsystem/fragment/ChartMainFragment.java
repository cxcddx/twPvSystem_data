package twpvsystem.tongwei.com.twpvsystem.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import twpvsystem.tongwei.com.twpvsystem.R;


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

	//当前选中的项
	int currenttab=-1;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View view = inflater.inflate(R.layout.fragment_chart_main, null, false);
		initView(view);
		initFragment();
		return view;

	}

	private void initView(View view) {
		btn_one = (Button) view.findViewById(R.id.btn_one);
		btn_two = (Button) view.findViewById(R.id.btn_two);
		btn_three = (Button) view.findViewById(R.id.btn_three);
		mViewPager=(ViewPager) view.findViewById(R.id.viewpager);

		btn_one.setOnClickListener(this);
		btn_two.setOnClickListener(this);
		btn_three.setOnClickListener(this);
	}

	private void initFragment() {


		fragmentList=new ArrayList<Fragment>();
		oneFragment=new ChartOneFragment();
		twoFragment=new ChartTwoFragment();
		threeFragment=new ChartThreeFragment();

		fragmentList.add(oneFragment);
		fragmentList.add(twoFragment);
		fragmentList.add(threeFragment);

		mViewPager.setAdapter(new MyFrageStatePagerAdapter(getFragmentManager()));
	}

	/**
	 * 定义自己的ViewPager适配器。
	 * 也可以使用FragmentPagerAdapter。关于这两者之间的区别，可以自己去搜一下。
	 */
	class MyFrageStatePagerAdapter extends FragmentStatePagerAdapter
	{

		public MyFrageStatePagerAdapter(FragmentManager fm)
		{
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
		public void finishUpdate(ViewGroup container)
		{
			super.finishUpdate(container);//这句话要放在最前面，否则会报错
			//获取当前的视图是位于ViewGroup的第几个位置，用来更新对应的覆盖层所在的位置
			int currentItem=mViewPager.getCurrentItem();
			if (currentItem==currenttab)
			{
				return ;
			}
			currenttab=mViewPager.getCurrentItem();
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
	private void changeView(int desTab)
	{
		mViewPager.setCurrentItem(desTab, true);
	}
}

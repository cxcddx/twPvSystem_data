package twpvsystem.tongwei.com.twpvsystem.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import twpvsystem.tongwei.com.twpvsystem.fragment.B_PhModeFragment1;
import twpvsystem.tongwei.com.twpvsystem.fragment.B_PhModeFragment2;
import twpvsystem.tongwei.com.twpvsystem.fragment.B_PhModeFragment3;
import twpvsystem.tongwei.com.twpvsystem.fragment.B_PhModeFragment4;


public class B_PhModeHomeAdapter extends FragmentPagerAdapter
{
	// [start]
	private String[] titleStr = { "计算参数", "当前指标", "自然pH", "期望pH" };
	private B_PhModeFragment1 m_frageFragment1;
	private B_PhModeFragment2 m_frageFragment2;
	private B_PhModeFragment3 m_frageFragment3;
	private B_PhModeFragment4 m_frageFragment4;

	// [end]

	// [start] constructor
	public B_PhModeHomeAdapter(FragmentManager fm)
	{
		super(fm);
		initFragment();
	}

	public B_PhModeHomeAdapter(FragmentManager fm, Context context)
	{
		super(fm);
		initFragment();
	}

	public B_PhModeHomeAdapter(FragmentManager fm, Fragment fragment)
	{
		super(fm);
		initFragment();
	}

	private void initFragment()
	{
		m_frageFragment1 = B_PhModeFragment1.newInstance();
		m_frageFragment2 = B_PhModeFragment2.newInstance();
		m_frageFragment3 = B_PhModeFragment3.newInstance();
		m_frageFragment4 = B_PhModeFragment4.newInstance();
	}

	// [end]

	// [start] override methods
	@Override
	public Fragment getItem(int position)
	{
		switch (position)
		{
		case 0:
			return m_frageFragment1;
		case 1:
			return m_frageFragment2;
		case 2:
			return m_frageFragment3;
		case 3:
			return m_frageFragment4;
		}
		return null;
	}

	@Override
	public CharSequence getPageTitle(int position)
	{
		return titleStr[position];
	}

	@Override
	public int getItemPosition(Object object)
	{
		return POSITION_NONE;
	}

	@Override
	public int getCount()
	{
		return titleStr.length;
	}
	// [end]
}

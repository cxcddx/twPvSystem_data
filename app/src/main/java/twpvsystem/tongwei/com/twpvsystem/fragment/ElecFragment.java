package twpvsystem.tongwei.com.twpvsystem.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import twpvsystem.tongwei.com.twpvsystem.R;
import twpvsystem.tongwei.com.twpvsystem.activity.MapActivity;

public class ElecFragment extends Fragment {

	private TextView content_total, content_today, content_install, content_earnings;
	private TextView title_total, title_today, title_install, title_earnings;

	private static ElecFragment fragment;
	public static ElecFragment newInstance() {
		if (fragment == null) {
			fragment = new ElecFragment();
		}
		return fragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
//		System.out.println("ChartOneFragment_old  onCreate");
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		View v = inflater.inflate(R.layout.fragment_elec, container, false);
		initView(v);
		notifyUpdateElecFragmentData();
		return v;
	}

	private void initView(View v) {
		content_total = (TextView) v.findViewById(R.id.content_total);
		content_today = (TextView) v.findViewById(R.id.content_today);
		content_install = (TextView) v.findViewById(R.id.content_install);
		content_earnings = (TextView) v.findViewById(R.id.content_earnings);

		title_total = (TextView) v.findViewById(R.id.title_total);
		title_today = (TextView) v.findViewById(R.id.title_today);
		title_install = (TextView) v.findViewById(R.id.title_install);
		title_earnings = (TextView) v.findViewById(R.id.title_earnings);
//		notifyUpdateElecFragmentData();
	}

	public void notifyUpdateElecFragmentData() {
		content_total.setText(MapActivity.totalPower);
		content_today.setText(MapActivity.dailyPower);
		content_install.setText(MapActivity.installedCapacity);
		content_earnings.setText(MapActivity.earn);

		title_total.setText("总发电量("+MapActivity.totalPowerUnit+")");
		title_today.setText("当日发电量("+MapActivity.dailyPowerUnit+")");
		title_install.setText("装机容量("+MapActivity.installedCapacityUnit+")");
		title_earnings.setText("收益("+MapActivity.earnUnit+")");

	}

	
	@Override
	public void onPause() {
//		System.out.println("ChartOneFragment_old  onPause");
		// TODO Auto-generated method stub
		super.onPause();
	}
	
	@Override
	public void onResume() {
//		System.out.println("ChartOneFragment_old  onResume");
		// TODO Auto-generated method stub
		super.onResume();
	}
	
	@Override
	public void onDestroy() {
//		System.out.println("ChartOneFragment_old  onDestroy");
		// TODO Auto-generated method stub
		super.onDestroy();
	}
	
	@Override
	public void onDestroyView() {
//		System.out.println("ChartOneFragment_old  onDestroyView");
		// TODO Auto-generated method stub
		super.onDestroyView();
	}
	
	@Override
	public void onStop() {
//		System.out.println("ChartOneFragment_old  onStop");
		// TODO Auto-generated method stub
		super.onStop();
	}
	
	@Override
	public void onStart() {
//		System.out.println("ChartOneFragment_old  onStart");
		// TODO Auto-generated method stub
		super.onStart();
	}
	
}

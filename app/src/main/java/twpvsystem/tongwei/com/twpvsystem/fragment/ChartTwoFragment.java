package twpvsystem.tongwei.com.twpvsystem.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import twpvsystem.tongwei.com.twpvsystem.R;

public class ChartTwoFragment extends Fragment {

	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
//		System.out.println("ChartTwoFragment onCreate");
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
//		System.out.println("ChartTwoFragment onCreateView");
		return inflater.inflate(R.layout.chart_two, container, false);
	}
	
	
	@Override
	public void onPause() {
//		System.out.println("ChartTwoFragment  onPause");
		// TODO Auto-generated method stub
		super.onPause();
	}
	
	@Override
	public void onResume() {
//		System.out.println("ChartTwoFragment  onResume");
		// TODO Auto-generated method stub
		super.onResume();
	}
	
	@Override
	public void onDestroy() {
//		System.out.println("ChartTwoFragment  onDestroy");
		// TODO Auto-generated method stub
		super.onDestroy();
	}
	
	@Override
	public void onDestroyView() {
//		System.out.println("ChartTwoFragment  onDestroyView");
		// TODO Auto-generated method stub
		super.onDestroyView();
	}
	
	@Override
	public void onStop() {
//		System.out.println("ChartTwoFragment  onStop");
		// TODO Auto-generated method stub
		super.onStop();
	}
	
	@Override
	public void onStart() {
//		System.out.println("ChartTwoFragment  onStart");
		// TODO Auto-generated method stub
		super.onStart();
	}
}

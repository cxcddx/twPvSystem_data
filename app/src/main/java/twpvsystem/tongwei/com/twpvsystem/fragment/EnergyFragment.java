package twpvsystem.tongwei.com.twpvsystem.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import twpvsystem.tongwei.com.twpvsystem.R;
import twpvsystem.tongwei.com.twpvsystem.activity.MapActivity;

public class EnergyFragment extends Fragment {
	private TextView content_co2, content_coal, content_forest;

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
//		System.out.println("ChartOneFragment_old  onCreateView");
		View v = inflater.inflate(R.layout.fragment_energy, container, false);
		initView(v);
		notifyUpdateEnergyFragmentData();
		return v;
	}

	private void initView(View v) {
		content_co2 = (TextView) v.findViewById(R.id.content_co2);
		content_coal = (TextView) v.findViewById(R.id.content_coal);
		content_forest = (TextView) v.findViewById(R.id.content_forest);
	}

	public void notifyUpdateEnergyFragmentData() {
		content_co2.setText(MapActivity.reduce + " " + MapActivity.reduceUnit);
		content_coal.setText(MapActivity.coalSaving+ " " + MapActivity.coalSavingUnit);
		content_forest.setText(MapActivity.reduceDeforestation+ " " + MapActivity.reduceDeforestationUnit);

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

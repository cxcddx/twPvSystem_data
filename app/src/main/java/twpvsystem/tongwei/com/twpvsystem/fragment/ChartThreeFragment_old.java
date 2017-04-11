package twpvsystem.tongwei.com.twpvsystem.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import lecho.lib.hellocharts.listener.ColumnChartOnValueSelectListener;
import lecho.lib.hellocharts.model.ColumnChartData;
import lecho.lib.hellocharts.model.SubcolumnValue;
import lecho.lib.hellocharts.view.ColumnChartView;
import twpvsystem.tongwei.com.twpvsystem.R;

public class ChartThreeFragment_old extends Fragment {

	public final static String[] months = new String[]{"1", "2", "3", "4", "5", "6", "7", "8",
			"9", "10", "11", "12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"
	};
	public final static float[] m_datas = new float[]{1f, 2f, 3f, 4f, 5f, 6f, 7f, 8f,
			9f, 10f, 11f, 12f, 13f, 14f, 15f,16f,17f,18f,19f,20f,21f,22f,23f,24f,25f,26f,27f,28f,29f,30f,31f};
	private ColumnChartView chartBottom;
	private ColumnChartData columnData;

	public ChartThreeFragment_old() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
//		System.out.println("ChartThreeFragment_old onCreate");
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
//		System.out.println("ChartThreeFragment_old onCreateView");
		View rootView = inflater.inflate(R.layout.chart_three, container, false);
		chartBottom = (ColumnChartView) rootView.findViewById(R.id.chart);
		// Set value touch listener that will trigger changes for chartTop.
		chartBottom.setOnValueTouchListener(new ValueTouchListener());

//        ColumnChartUtil.generateColumnData(months, m_datas, chartBottom);
		return rootView;
	}



	private class ValueTouchListener implements ColumnChartOnValueSelectListener {

		@Override
		public void onValueSelected(int columnIndex, int subcolumnIndex, SubcolumnValue value) {
			Toast.makeText(getActivity(), "columnIndex ="+ months[columnIndex]+ ""+value, Toast.LENGTH_SHORT).show();
//			generateLineData(value.getColor(), 100);
		}

		@Override
		public void onValueDeselected() {

//			generateLineData(ChartUtils.COLOR_GREEN, 0);

		}
	}
	
	

	
}

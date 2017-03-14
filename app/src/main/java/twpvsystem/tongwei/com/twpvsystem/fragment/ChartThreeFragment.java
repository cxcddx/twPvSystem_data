package twpvsystem.tongwei.com.twpvsystem.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.listener.ColumnChartOnValueSelectListener;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Column;
import lecho.lib.hellocharts.model.ColumnChartData;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.SubcolumnValue;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.ColumnChartView;
import lecho.lib.hellocharts.view.LineChartView;
import twpvsystem.tongwei.com.twpvsystem.R;
import twpvsystem.tongwei.com.twpvsystem.util.ColumnChartUtil;

public class ChartThreeFragment extends Fragment {

	public final static String[] months = new String[]{"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug",
			"Sep", "Oct", "Nov", "Dec","qq","ww","ee","rr","tt","yy","uu","ii","oo","pp"
	};
	public final static float[] m_datas = new float[]{1f, 2f, 3f, 4f, 5f, 6f, 7f, 8f,
			9f, 10f, 11f, 12f, 13f, 14f, 15f,16f,17f,18f,19f,20f,21f,22f};
	private ColumnChartView chartBottom;
	private ColumnChartData columnData;

	public ChartThreeFragment() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
//		System.out.println("ChartThreeFragment onCreate");
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
//		System.out.println("ChartThreeFragment onCreateView");
		View rootView = inflater.inflate(R.layout.chart_three, container, false);
		chartBottom = (ColumnChartView) rootView.findViewById(R.id.chart);
		// Set value touch listener that will trigger changes for chartTop.
		chartBottom.setOnValueTouchListener(new ValueTouchListener());

        ColumnChartUtil.generateColumnData(months, m_datas, chartBottom);
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

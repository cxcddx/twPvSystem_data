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

import lecho.lib.hellocharts.listener.ColumnChartOnValueSelectListener;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.Column;
import lecho.lib.hellocharts.model.ColumnChartData;
import lecho.lib.hellocharts.model.SubcolumnValue;
import lecho.lib.hellocharts.view.ColumnChartView;
import twpvsystem.tongwei.com.twpvsystem.R;

public class ChartTwoFragment_old extends Fragment {
	private static final int DEFAULT_DATA = 0;

	private ColumnChartView chart;
	private ColumnChartData data;
	private boolean hasAxes = true;
	private boolean hasAxesNames = true;
	private boolean hasLabels = false;
	private boolean hasLabelForSelected = false;

	public ChartTwoFragment_old() {
	}



	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		View rootView = inflater.inflate(R.layout.chart_two, container, false);
		chart = (ColumnChartView) rootView.findViewById(R.id.chart);
		chart.setOnValueTouchListener(new ValueTouchListener());
//		chart.setZoomType(ZoomType.HORIZONTAL);//缩放x轴
		chart.setZoomEnabled(false);//禁止缩放

		toggleLabelForSelected();
		return rootView;
	}


	//点击数据回调
	private class ValueTouchListener implements ColumnChartOnValueSelectListener {

		@Override
		public void onValueSelected(int columnIndex, int subcolumnIndex, SubcolumnValue value) {
			Toast.makeText(getActivity(), "Selected: " + value, Toast.LENGTH_SHORT).show();
		}

		@Override
		public void onValueDeselected() {
			// TODO Auto-generated method stub

		}

	}

	/**
	 * Generates columns with subcolumns, columns have larger separation than subcolumns.
	 */
	private void generateSubcolumnsData() {
		int numSubcolumns = 1;
		int numColumns = 31;
		// Column can have many subcolumns, here I use 4 subcolumn in each of 8 columns.
		List<Column> columns = new ArrayList<Column>();
		List<SubcolumnValue> values;
		for (int i = 0; i < numColumns; ++i) {

			values = new ArrayList<SubcolumnValue>();
			for (int j = 0; j < numSubcolumns; ++j) {
				values.add(new SubcolumnValue((float) Math.random() * 50f + 5, Color.argb(255,220,120,188)));
			}

			Column column = new Column(values);
			column.setHasLabels(hasLabels);
			column.setHasLabelsOnlyForSelected(hasLabelForSelected);
			columns.add(column);
		}

		data = new ColumnChartData(columns);

		if (hasAxes) {
			Axis axisX = new Axis();
			Axis axisY = new Axis().setHasLines(true);
			if (hasAxesNames) {
//				axisX.setName("Axis X");
//				axisY.setName("Axis Y");
			}
			data.setAxisXBottom(axisX);
			data.setAxisYLeft(axisY);
		} else {
			data.setAxisXBottom(null);
			data.setAxisYLeft(null);
		}

		chart.setColumnChartData(data);

	}

	private void toggleLabelForSelected() {
		hasLabelForSelected = !hasLabelForSelected;
		chart.setValueSelectionEnabled(hasLabelForSelected);

		if (hasLabelForSelected) {
			hasLabels = false;
		}

		generateSubcolumnsData();
	}


}

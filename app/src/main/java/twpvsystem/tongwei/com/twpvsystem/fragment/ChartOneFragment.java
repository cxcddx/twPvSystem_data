package twpvsystem.tongwei.com.twpvsystem.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.listener.ColumnChartOnValueSelectListener;
import lecho.lib.hellocharts.model.ColumnChartData;
import lecho.lib.hellocharts.model.SubcolumnValue;
import lecho.lib.hellocharts.view.ColumnChartView;
import twpvsystem.tongwei.com.twpvsystem.R;
import twpvsystem.tongwei.com.twpvsystem.bean.ChartData;
import twpvsystem.tongwei.com.twpvsystem.util.ColumnChartUtil;
import twpvsystem.tongwei.com.twpvsystem.util.MessageUtils;

public class ChartOneFragment extends Fragment {

    private ColumnChartView chartBottom;
    private ColumnChartData columnData;
    private  List<String> date;
    private List<Float> data;
    private TextView unit;

    private List<ChartData.DataBean.DailyPowerBean> dayList;

    public ChartOneFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.chart_one, container, false);
        unit = (TextView)rootView.findViewById(R.id.unit);
        chartBottom = (ColumnChartView) rootView.findViewById(R.id.chart);
        // Set value touch listener that will trigger changes for chartTop.
        chartBottom.setOnValueTouchListener(new ValueTouchListener());
        initData();

        return rootView;
    }

    private void initData() {
        ChartMainFragment a = (ChartMainFragment) getParentFragment();
        ChartData chart_data = a.getChartData();

        dayList = chart_data.getData().getDailyPower();
        date = new ArrayList<String>();
        data = new ArrayList<Float>();
        for(int i =0; i<dayList.size()-1; i++) {
            date.add(dayList.get(i).getHour());
            data.add(Float.valueOf(dayList.get(i).getDaily()));
        }
        ColumnChartUtil.generateColumnData(date, data, chartBottom);
        unit.setText("单位(" + dayList.get(dayList.size()-1).getUnit()+ ")");

    }

    private class ValueTouchListener implements ColumnChartOnValueSelectListener {

        @Override
        public void onValueSelected(int columnIndex, int subcolumnIndex, SubcolumnValue value) {
            MessageUtils.ShowToast(getActivity(), date.get(columnIndex) + "时的值为:" + value.getValue() + dayList.get(dayList.size()-1).getUnit());
        }

        @Override
        public void onValueDeselected() {

//			generateLineData(ChartUtils.COLOR_GREEN, 0);

        }
    }


}

package twpvsystem.tongwei.com.twpvsystem.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import twpvsystem.tongwei.com.twpvsystem.R;

public class B_PhModeFragment1 extends Fragment
{
	private EditText m_txtTemperature, m_txtAlkalinity, m_txtCalcium, m_txtCurPh, m_txtSale, m_txtHopePh;
	private Button m_btnCalulate;

	private static B_PhModeFragment1 fragment;

	public static B_PhModeFragment1 newInstance()
	{
		if (fragment == null)
		{
			fragment = new B_PhModeFragment1();
		}
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View v = inflater.inflate(R.layout.b_fragment_phomode1, null);
		return v;
	}

//	@Override
//	public void onViewCreated(View view, Bundle savedInstanceState)
//	{
//		m_txtTemperature = (EditText) view.findViewById(R.id.txtTemperature);
//		m_txtAlkalinity = (EditText) view.findViewById(R.id.txtAlkalinity);
//		m_txtCalcium = (EditText) view.findViewById(R.id.txtCalcium);
//		m_txtCurPh = (EditText) view.findViewById(R.id.txtCurPh);
//		m_txtSale = (EditText) view.findViewById(R.id.txtSale);
//		m_txtHopePh = (EditText) view.findViewById(R.id.txtHopePh);
//		m_btnCalulate = (Button) view.findViewById(R.id.btnCalulate);
//		m_btnCalulate.setOnClickListener(this);
//
//		super.onViewCreated(view, savedInstanceState);
//	}
//
//	@Override
//	public void onClick(View v)
//	{
//		switch (v.getId())
//		{
//		case R.id.btnCalulate:
//			calculate();
//			break;
//		}
//	}
//
//	private void calculate()
//	{
//		if(m_txtTemperature.getText().toString().trim().equals("")
//		 ||  m_txtAlkalinity.getText().toString().trim().equals("")
//		 ||  m_txtCalcium.getText().toString().trim().equals("")
//		 ||  m_txtCurPh.getText().toString().trim().equals("")
//		 ||  m_txtSale.getText().toString().trim().equals("")
//		 ||  m_txtHopePh.getText().toString().trim().equals(""))
//		{
//			MessageUtils.ShowToast(getActivity(), "计算参数输入不正确,请重新输入!");
//			return;
//		}
//
//		B_BaseActivity context = (B_BaseActivity) getActivity();
//		context.m_httpService.doPost(Constants.URL_PhMode,  getQueryJson());
//	}
//
//	protected String getQueryJson()
//	{
//		Map<String, String> datas = new HashMap<String, String>();
//		datas.put("temperature", m_txtTemperature.getText().toString());
//		datas.put("alkalinity",  m_txtAlkalinity.getText().toString());
//		datas.put("calcium",  m_txtCalcium.getText().toString());
//		datas.put("ph",  m_txtCurPh.getText().toString());
//		datas.put("salinity",  m_txtSale.getText().toString());
//		datas.put("adjustPh",  m_txtHopePh.getText().toString());
//
//		return new Gson().toJson(datas);
//	}
}

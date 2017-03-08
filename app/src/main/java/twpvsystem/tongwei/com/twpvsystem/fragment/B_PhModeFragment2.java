package twpvsystem.tongwei.com.twpvsystem.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import twpvsystem.tongwei.com.twpvsystem.R;

public class B_PhModeFragment2 extends Fragment
{
	private EditText m_txtEpH, m_txtEDIC, m_txtECa2, m_txtCaCO3pH1, m_txtCaCO3pH2;
	private EditText m_txtDIC, m_txtTCO2, m_txtRadio;

	private static B_PhModeFragment2 fragment;

	public static B_PhModeFragment2 newInstance()
	{
		if (fragment == null)
		{
			fragment = new B_PhModeFragment2();
		}
		return fragment;
	}

//	public void setValue(String json)
//	{
//		Map<String, String> map = JsonTools.GetJsonMap(json);
//
//		m_txtDIC.setText(Util.formatNumber(map.get("dic"), 4));
//		m_txtTCO2.setText(Util.formatNumber(map.get("tco2"), 4));
//		double radio = Double.parseDouble(Util.formatNumber(map.get("ratio"), 4)) * 100;
//		m_txtRadio.setText(String.valueOf(radio));
//
//		m_txtEpH.setText(Util.formatNumber(map.get("eph"), 4));
//		m_txtEDIC.setText(Util.formatNumber(map.get("edic"), 4));
//		m_txtECa2.setText(Util.formatNumber(map.get("eca2"), 4));
//		m_txtCaCO3pH1.setText(Util.formatNumber(map.get("caco3Begin"), 4));
//		m_txtCaCO3pH2.setText(Util.formatNumber(map.get("caco3End"), 4));
//	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View v = inflater.inflate(R.layout.b_fragment_phomode2, null);
		return v;
	}

//	@Override
//	public void onViewCreated(View view, Bundle savedInstanceState)
//	{
//		m_txtEpH = (EditText) view.findViewById(R.id.txtEpH);
//		m_txtEDIC = (EditText) view.findViewById(R.id.txtEDIC);
//		m_txtECa2 = (EditText) view.findViewById(R.id.txtECa2);
//		m_txtCaCO3pH1 = (EditText) view.findViewById(R.id.txtCaCO3pH1);
//		m_txtCaCO3pH2 = (EditText) view.findViewById(R.id.txtCaCO3pH2);
//		m_txtDIC = (EditText) view.findViewById(R.id.txtDIC);
//		m_txtTCO2 = (EditText) view.findViewById(R.id.txtTCO2);
//		m_txtRadio = (EditText) view.findViewById(R.id.txtRadio);
//
//		super.onViewCreated(view, savedInstanceState);
//	}

}

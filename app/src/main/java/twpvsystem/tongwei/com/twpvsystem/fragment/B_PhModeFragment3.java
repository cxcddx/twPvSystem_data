package twpvsystem.tongwei.com.twpvsystem.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import twpvsystem.tongwei.com.twpvsystem.R;

public class B_PhModeFragment3 extends Fragment
{
	private EditText m_txtEpH, m_txtEDIC, m_txtECa2, m_txtCaCO3pH1, m_txtCaCO3pH2;
	private TextView m_tvAdd;

	private static B_PhModeFragment3 fragment;

	public static B_PhModeFragment3 newInstance()
	{
		if (fragment == null)
		{
			fragment = new B_PhModeFragment3();
		}
		return fragment;
	}

//	public void setValue(String json)
//	{
//		Map<String, String> map = JsonTools.GetJsonMap(json);
//
//		m_txtEpH.setText(Util.formatNumber(map.get("eph"), 4));
//		m_txtEDIC.setText(Util.formatNumber(map.get("edic"), 4));
//		m_txtECa2.setText(Util.formatNumber(map.get("eca2"), 4));
//		m_txtCaCO3pH1.setText(Util.formatNumber(map.get("caco3Begin"), 4));
//		m_txtCaCO3pH2.setText(Util.formatNumber(map.get("caco3End"), 4));
//	}
//
//	public void setAdd(String json)
//	{
//		Map<String, String> map = JsonTools.GetJsonMap(json);
//		StringBuffer sb = new StringBuffer();
//
//		String addNaturalCaO = Util.formatNumber(map.get("addNaturalCaO"), 4);
//		if(Util.isValid(addNaturalCaO, 4))
//		{
//			sb.append("CaO(mg/m3):"+addNaturalCaO);
//		}
//
//		if (sb.length() <= 0)
//		{
//			m_tvAdd.setText("不需要调节");
//		}
//		else
//		{
//			m_tvAdd.setText(sb.toString());
//		}
//	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View v = inflater.inflate(R.layout.b_fragment_phomode3, null);
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
//		m_tvAdd = (TextView) view.findViewById(R.id.tvAdd);
//
//		super.onViewCreated(view, savedInstanceState);
//	}

}

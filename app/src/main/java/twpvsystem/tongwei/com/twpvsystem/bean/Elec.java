package twpvsystem.tongwei.com.twpvsystem.bean;

import org.litepal.crud.DataSupport;

/**
 * Created by CX on 2017/2/27.
 */
public class Elec extends DataSupport {

    private int id;
    private String elec_total;
    private String elec_today;
    private String elec_install;
    private String elec_earnings;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getElec_total() {
        return elec_total;
    }

    public void setElec_total(String elec_total) {
        this.elec_total = elec_total;
    }

    public String getElec_today() {
        return elec_today;
    }

    public void setElec_today(String elec_today) {
        this.elec_today = elec_today;
    }

    public String getElec_install() {
        return elec_install;
    }

    public void setElec_install(String elec_install) {
        this.elec_install = elec_install;
    }

    public String getElec_earnings() {
        return elec_earnings;
    }

    public void setElec_earnings(String elec_earnings) {
        this.elec_earnings = elec_earnings;
    }
}

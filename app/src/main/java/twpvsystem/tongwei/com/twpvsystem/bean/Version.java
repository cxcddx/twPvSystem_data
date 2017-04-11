package twpvsystem.tongwei.com.twpvsystem.bean;

/**
 * Created by CX on 2017/4/11.
 */

public class Version {

    /**
     * code : 200
     * data : {"update":"true"}
     */

    private int code;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * update : true
         */

        private String update;

        public String getUpdate() {
            return update;
        }

        public void setUpdate(String update) {
            this.update = update;
        }
    }
}

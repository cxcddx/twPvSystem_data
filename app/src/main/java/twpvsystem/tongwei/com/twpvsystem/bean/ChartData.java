package twpvsystem.tongwei.com.twpvsystem.bean;

import java.util.List;

/**
 * Created by CX on 2017/4/10.
 */

public class ChartData {

    /**
     * code : 200
     * data : {"dailyPower":[{"hour":"00","daily":0},{"hour":"01","daily":0},{"hour":"02","daily":0},{"hour":"03","daily":0},{"hour":"04","daily":0},{"hour":"05","daily":0},{"hour":"06","daily":1.35},{"hour":"07","daily":4.5},{"hour":"08","daily":9.45},{"hour":"09","daily":16.2},{"hour":"10","daily":24.75},{"hour":"11","daily":35.1},{"hour":"12","daily":45.45},{"hour":"13","daily":54},{"hour":"14","daily":60.75},{"hour":"15","daily":65.7},{"hour":"16","daily":68.85},{"hour":"17","daily":70.2},{"hour":"18","daily":70.2},{"hour":"19","daily":70.2},{"hour":"20","daily":70.2},{"hour":"21","daily":70.2},{"hour":"22","daily":70.2},{"hour":"23","daily":70.2},{"unit":"kw.h"}],"monthlyPower":[{"dayPower":105.3,"dayTime":"01"},{"dayPower":58.5,"dayTime":"02"},{"dayPower":58.5,"dayTime":"03"},{"dayPower":93.6,"dayTime":"04"},{"dayPower":23.4,"dayTime":"05"},{"dayPower":70.2,"dayTime":"06"},{"dayPower":23.4,"dayTime":"07"},{"dayPower":81.9,"dayTime":"08"},{"dayPower":23.4,"dayTime":"09"},{"dayPower":70.2,"dayTime":"10"},{"dayPower":58.5,"dayTime":"11"},{"dayPower":23.4,"dayTime":"12"},{"dayPower":93.6,"dayTime":"13"},{"dayPower":46.8,"dayTime":"14"},{"dayPower":46.8,"dayTime":"15"},{"dayPower":46.8,"dayTime":"16"},{"dayPower":11.7,"dayTime":"17"},{"dayPower":93.6,"dayTime":"18"},{"dayPower":35.1,"dayTime":"19"},{"dayPower":11.7,"dayTime":"20"},{"dayPower":11.7,"dayTime":"21"},{"dayPower":105.3,"dayTime":"22"},{"dayPower":93.6,"dayTime":"23"},{"dayPower":46.8,"dayTime":"24"},{"dayPower":70.2,"dayTime":"25"},{"dayPower":46.8,"dayTime":"26"},{"dayPower":70.2,"dayTime":"27"},{"dayPower":70.2,"dayTime":"28"},{"dayPower":105.3,"dayTime":"29"},{"dayPower":46.8,"dayTime":"30"},{"unit":"kw.h"}],"annualPower":[{"monthTime":"01","monthPower":2695.7},{"monthTime":"02","monthPower":1994.7},{"monthTime":"03","monthPower":1959.5},{"monthTime":"04","monthPower":1743.3},{"monthTime":"05","monthPower":304.2},{"unit":"kw.h"}]}
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
        private List<DailyPowerBean> dailyPower;
        private List<MonthlyPowerBean> monthlyPower;
        private List<AnnualPowerBean> annualPower;

        public List<DailyPowerBean> getDailyPower() {
            return dailyPower;
        }

        public void setDailyPower(List<DailyPowerBean> dailyPower) {
            this.dailyPower = dailyPower;
        }

        public List<MonthlyPowerBean> getMonthlyPower() {
            return monthlyPower;
        }

        public void setMonthlyPower(List<MonthlyPowerBean> monthlyPower) {
            this.monthlyPower = monthlyPower;
        }

        public List<AnnualPowerBean> getAnnualPower() {
            return annualPower;
        }

        public void setAnnualPower(List<AnnualPowerBean> annualPower) {
            this.annualPower = annualPower;
        }

        public static class DailyPowerBean {
            /**
             * hour : 00
             * daily : 0.0
             * unit : kw.h
             */

            private String hour;
            private String daily;
            private String unit;

            public String getHour() {
                return hour;
            }

            public void setHour(String hour) {
                this.hour = hour;
            }

            public String getDaily() {
                return daily;
            }

            public void setDaily(String daily) {
                this.daily = daily;
            }

            public String getUnit() {
                return unit;
            }

            public void setUnit(String unit) {
                this.unit = unit;
            }
        }

        public static class MonthlyPowerBean {
            /**
             * dayPower : 105.3
             * dayTime : 01
             * unit : kw.h
             */

            private String dayPower;
            private String dayTime;
            private String unit;

            public String getDayPower() {
                return dayPower;
            }

            public void setDayPower(String dayPower) {
                this.dayPower = dayPower;
            }

            public String getDayTime() {
                return dayTime;
            }

            public void setDayTime(String dayTime) {
                this.dayTime = dayTime;
            }

            public String getUnit() {
                return unit;
            }

            public void setUnit(String unit) {
                this.unit = unit;
            }
        }

        public static class AnnualPowerBean {
            /**
             * monthTime : 01
             * monthPower : 2695.7
             * unit : kw.h
             */

            private String monthTime;
            private String monthPower;
            private String unit;

            public String getMonthTime() {
                return monthTime;
            }

            public void setMonthTime(String monthTime) {
                this.monthTime = monthTime;
            }

            public String getMonthPower() {
                return monthPower;
            }

            public void setMonthPower(String monthPower) {
                this.monthPower = monthPower;
            }

            public String getUnit() {
                return unit;
            }

            public void setUnit(String unit) {
                this.unit = unit;
            }
        }
    }
}

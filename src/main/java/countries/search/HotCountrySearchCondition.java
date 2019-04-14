package countries.search;


import common.solutions.utils.Months;

public class HotCountrySearchCondition extends CountrySearchCondition {
    private Months hottestMonth;
    private int averageTemp;

    public Months getHottestMonth() {
        return hottestMonth;
    }

    public void setHottestMonth(Months hottestMonth) {
        this.hottestMonth = hottestMonth;
    }

    public int getAverageTemp() {
        return averageTemp;
    }

    public void setAverageTemp(int averageTemp) {
        this.averageTemp = averageTemp;
    }
}

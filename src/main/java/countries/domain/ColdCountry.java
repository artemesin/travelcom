package countries.domain;


import common.solutions.utils.Months;

public class ColdCountry extends BaseCountry {
    private Months theMostSnowingMonth;
    private int averageSnowLevel;
    private boolean polarNight;


    public ColdCountry(String name, String language, String telephoneCode, Months theMostSnowingMonth, int averageSnowLevel, boolean polarNight) {
        super(name, language, CountryType.COLD, telephoneCode);
        this.theMostSnowingMonth = theMostSnowingMonth;
        this.averageSnowLevel = averageSnowLevel;
        this.polarNight = polarNight;
    }

    public ColdCountry() {
        this.setType(CountryType.COLD);
    }

    @Override
    public void setHottestMonth(Months month) {

    }

    public Months getTheMostSnowingMonth() {
        return theMostSnowingMonth;
    }

    public void setTheMostSnowingMonth(Months theMostSnowingMonth) {
        this.theMostSnowingMonth = theMostSnowingMonth;
    }

    public int getAverageSnowLevel() {
        return averageSnowLevel;
    }

    public void setAverageSnowLevel(int averageSnowLevel) {
        this.averageSnowLevel = averageSnowLevel;
    }

    public boolean isPolarNight() {
        return polarNight;
    }

    public void setPolarNight(boolean polarNight) {
        this.polarNight = polarNight;
    }
}

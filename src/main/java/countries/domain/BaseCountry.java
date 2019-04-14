package countries.domain;


import cities.domain.City;
import common.buisness.domain.BaseDomain;
import common.solutions.utils.Months;

import java.util.List;


public abstract class BaseCountry extends BaseDomain {
    private String name;
    private String language;
    private List<City> cities;
    private CountryType type;
    private String telephoneCode;

    public String getTelephoneCode() {
        return telephoneCode;
    }

    public void setTelephoneCode(String telephoneCode) {
        this.telephoneCode = telephoneCode;
    }

    public BaseCountry(String name, String language, CountryType type) {
        this.name = name;
        this.language = language;
        this.type = type;
    }

    public BaseCountry(String name, String language, CountryType type, String telephoneCode) {
        this.name = name;
        this.language = language;
        this.type = type;
        this.telephoneCode = telephoneCode;
    }

    public CountryType getType() {
        return type;
    }

    public void setType(CountryType type) {
        this.type = type;
    }

    public BaseCountry(String name, String language) {
        this.name = name;
        this.language = language;
    }

    public BaseCountry() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }

    @Override
    public String toString() {
        return "Country{\n" +
                ", id=" + id + '\'' +
                "name='" + name + '\'' +
                ", language='" + language + '\'' +
                ", cities=" + getCitiesAsString() +
                '}';
    }

    private String getCitiesAsString(){
        StringBuilder sb = new StringBuilder();
        for(City city : cities){
            sb.append(city.toString()).append("\n");
        }
        return sb.toString();
    }

    public abstract void setHottestMonth(Months month);

    public abstract void setTheMostSnowingMonth(Months month);
}

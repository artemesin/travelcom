package order.domain;

import cities.domain.City;

import common.buisness.domain.BaseDomain;
import countries.domain.BaseCountry;
import user.domain.User;

public class Order extends BaseDomain {
    private City city;
    private BaseCountry baseCountry;
    private User user;
    private final int i;
    private String description;
    private int price;
    private long userId;
    private long cityId;
    private long countryId;

    public Order(City city, BaseCountry baseCountry, User user, int i) {
        this.city = city;
        this.baseCountry = baseCountry;
        this.user = user;
        this.i = i;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getCityId() {
        return cityId;
    }

    public void setCityId(long cityId) {
        this.cityId = cityId;
    }

    public long getCountryId() {
        return countryId;
    }

    public void setCountryId(long countryId) {
        this.countryId = countryId;
    }


    public Order(City city, BaseCountry baseCountry, User user, int i, int price) {
        this.city = city;
        this.baseCountry = baseCountry;
        this.user = user;
        this.i = i;
        this.price = price;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public BaseCountry getBaseCountry() {
        return baseCountry;
    }

    public void setBaseCountry(BaseCountry baseCountry) {
        this.baseCountry = baseCountry;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}

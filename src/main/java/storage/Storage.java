package storage;

import cities.domain.City;
import countries.domain.BaseCountry;
import order.domain.Order;
import user.domain.User;

import java.util.ArrayList;
import java.util.List;

public class Storage {
    private static final int CAPACITY = 5;
    public static User[] users = new User[CAPACITY];
    public static Order[] orders = new Order[CAPACITY];
    public static BaseCountry[] countries = new BaseCountry[CAPACITY];
    public static City[] cities = new City[CAPACITY];

    public static List<User> userList = new ArrayList<>();
    public static List<BaseCountry> baseCountryList = new ArrayList<>();
    public static List<Order> ordersList = new ArrayList<>();
    public static List<City> cityList = new ArrayList<>();
}

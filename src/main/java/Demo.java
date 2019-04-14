

import cities.domain.City;
import cities.search.CitySearchCondition;
import cities.service.CityService;
import common.buisness.application.StorageType;
import common.buisness.application.servicefactory.ServiceSupplier;
import countries.domain.BaseCountry;
import countries.search.CountrySearchCondition;
import countries.service.CountryService;
import order.domain.Order;
import order.service.OrderService;
import storage.initialisation.Initializator;
import user.domain.User;
import user.search.UserSearchCondition;
import user.service.UserService;

import java.util.ArrayList;
import java.util.Collection;

public class Demo {

    public static class Application {
        static {
            ServiceSupplier.newSupplier(StorageType.MEMORY_COLLECTION);
        }

        private UserService userService = ServiceSupplier.setSupplier().getUserService();
        private OrderService orderService = ServiceSupplier.setSupplier().getOrderService();
        private CountryService countryService = ServiceSupplier.setSupplier().getCountryService();
        private CityService cityService = ServiceSupplier.setSupplier().getCityService();

        private void addUsers() {
            userService.insert(new User("Ivan", "Ivanov"));
            userService.insert(new User("Petya", "Petrov"));
        }

        private void addCountriesAndCities() {
            Initializator initializator = new Initializator(countryService);
            try {
                initializator.concurrentInit(Initializator.DataSourceType.XML_FILE);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        public void printUsers() {
            userService.printAll();
        }

        public void printCountries() {
            countryService.printAll();
        }


        public Collection<User> searchUser(String name) {
            UserSearchCondition usc = new UserSearchCondition();
            usc.setName(name);
            return userService.search(usc);
        }

        public Collection<City> searchCity(String name) {
            CitySearchCondition csc = new CitySearchCondition();
            csc.setName(name);
            return cityService.search(csc);
        }

        public Collection<BaseCountry> searchCountry(String name) {
            CountrySearchCondition csc = new CountrySearchCondition();
            csc.setName(name);
            return countryService.search(csc);
        }

        public void makeOrders() {
            ArrayList<User> users = (ArrayList<User>) searchUser("Petya");
            ArrayList<BaseCountry> countries = (ArrayList<BaseCountry>) searchCountry("");
            ArrayList<City> city = (ArrayList<City>) searchCity("");
            orderService.insert(new Order(city.get(city.size() - 1), countries.get(countries.size() - 1), users.get(users.size() - 1), 1200));
        }

    }

    public static void main(String[] args) {


    }
}

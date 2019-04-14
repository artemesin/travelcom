package common.buisness.application.servicefactory;


import cities.service.CityService;
import countries.service.CountryService;
import order.service.OrderService;
import user.service.UserService;

public interface ServiceFactory {
    UserService getUserService();
    OrderService getOrderService();
    CountryService getCountryService();
    CityService getCityService();
}

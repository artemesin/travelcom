package common.buisness.application.servicefactory;


import cities.repo.impl.memory.CityMemoryArrayRepo;
import cities.service.CityService;
import cities.service.impl.CityDefaultService;
import countries.repo.impl.memory.CountryMemoryArrayRepo;
import countries.service.CountryService;
import countries.service.impl.CountryDefaultService;
import order.repo.impl.memory.OrderMemoryArrayRepo;
import order.service.OrderService;
import order.service.impl.OrderDefaultService;
import user.repo.impl.memory.UserMemoryArrayRepo;
import user.service.UserService;
import user.service.impl.UserDefaultService;

public class MemoryArrayServiceFactory implements ServiceFactory {
    @Override
    public UserService getUserService() {
        return new UserDefaultService(new UserMemoryArrayRepo());
    }

    @Override
    public OrderService getOrderService() {
        return new OrderDefaultService(new OrderMemoryArrayRepo());
    }

    @Override
    public CountryService getCountryService() {
        return new CountryDefaultService(new CountryMemoryArrayRepo(), new CityMemoryArrayRepo());
    }

    @Override
    public CityService getCityService() {
        return new CityDefaultService(new CityMemoryArrayRepo());
    }
}

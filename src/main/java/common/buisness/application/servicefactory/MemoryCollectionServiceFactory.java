package common.buisness.application.servicefactory;


import cities.repo.impl.memory.CityMemoryCollectionRepo;
import cities.service.CityService;
import cities.service.impl.CityDefaultService;
import countries.repo.impl.memory.CountryMemoryCollectionRepo;
import countries.service.CountryService;
import countries.service.impl.CountryDefaultService;
import order.repo.impl.memory.OrderMemoryCollectionRepo;
import order.service.OrderService;
import order.service.impl.OrderDefaultService;
import user.repo.impl.memory.UserMemoryCollectionRepo;
import user.service.UserService;
import user.service.impl.UserDefaultService;

public class MemoryCollectionServiceFactory implements ServiceFactory {
    @Override
    public UserService getUserService() {
        return new UserDefaultService(new UserMemoryCollectionRepo());
    }

    @Override
    public OrderService getOrderService() {
        return new OrderDefaultService(new OrderMemoryCollectionRepo());
    }

    @Override
    public CountryService getCountryService() {
        return new CountryDefaultService(new CountryMemoryCollectionRepo(), new CityMemoryCollectionRepo());
    }

    @Override
    public CityService getCityService() {
        return new CityDefaultService(new CityMemoryCollectionRepo());
    }
}

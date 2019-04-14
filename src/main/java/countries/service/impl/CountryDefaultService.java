package countries.service.impl;

import cities.domain.City;
import cities.repo.CityRepo;
import cities.repo.impl.memory.CityMemoryArrayRepo;
import countries.domain.BaseCountry;
import countries.repo.CountryRepo;
import countries.repo.impl.memory.CountryMemoryArrayRepo;
import countries.search.CountrySearchCondition;
import countries.service.CountryService;
import countries.service.exceptions.CountryIsContainedInSomeOrdersException;
import order.domain.Order;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import static storage.Storage.ordersList;


public class CountryDefaultService implements CountryService {
    private final CountryRepo countryRepo;
    private final CityRepo cityRepo;

    public CountryDefaultService(CountryMemoryArrayRepo countryRepo, CityMemoryArrayRepo cityRepo) {
        this.countryRepo = countryRepo;
        this.cityRepo = cityRepo;
    }

    @Override
    public List<BaseCountry> search(CountrySearchCondition searchCondition) {
        return countryRepo.search(searchCondition);
    }

    @Override
    public BaseCountry insert(BaseCountry baseCountry) {

        countryRepo.insert(baseCountry);

        if(baseCountry.getCities() != null){
            for(City city : baseCountry.getCities()){
                city.setCountryId(baseCountry.getId());
                cityRepo.insert(city);
            }
        }
        return baseCountry;
    }

    @Override
    public void insert(Collection<BaseCountry> items) {
        if (!items.isEmpty()) {
            countryRepo.insert(items);

            for(BaseCountry country : items) {
               if(country.getCities() != null) {
                    country.getCities().replaceAll(city -> {
                        city.setCountryId(country.getId());
                        return city;
                    });
                   cityRepo.insert(country.getCities());
               }
           }
        }
    }

    @Override
    public BaseCountry findById(Long id) {
        if(id != null){
            return countryRepo.findById(id);
        }else {
            return null;
        }
    }

    @Override
    public void update(BaseCountry baseCountry) {
        countryRepo.update(baseCountry);
    }

    @Override
    public void delete(BaseCountry baseCountry) {
        if(baseCountry.getId() != null){
            try {
                for(Order order: ordersList) {
                    if (order.getBaseCountry().getId().equals(baseCountry.getId())) {

                        throw new CountryIsContainedInSomeOrdersException("smth happend", 20);
                    }
                }
            }catch (CountryIsContainedInSomeOrdersException e) {
                System.out.println(e.getMessage());
            } finally {
                Iterator<Order> it = ordersList.iterator();
                while (it.hasNext()) {
                    if (it.next().getBaseCountry().getId().equals(baseCountry.getId())) {
                        it.remove();
                    }
                }
                this.deleteById(baseCountry.getId());
            }
        }
    }

    @Override
    public void deleteById(Long id) {
        if(id != null){
            countryRepo.deleteById(id);
        }
    }



    @Override
    public void printAll() {
        countryRepo.printAll();
    }

    @Override
    public List<BaseCountry> search(CountrySearchCondition searchCondition) {
        return null;
    }
}
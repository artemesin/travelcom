package cities.service.impl;

import com.roman.shilov.hw22.travelagency.cities.domain.City;
import com.roman.shilov.hw22.travelagency.cities.repo.CityRepo;
import com.roman.shilov.hw22.travelagency.cities.search.CitySearchCondition;
import cities.service.CityService;
import cities.service.exceptions.CityIsContainedInSomeOrdersException;
import com.roman.shilov.hw22.travelagency.order.domain.Order;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import static com.roman.shilov.hw22.travelagency.storage.Storage.ordersList;

public class CityDefaultService implements CityService {

    private final CityRepo repo;

    public CityDefaultService(CityRepo repo) {
        this.repo = repo;
    }

    @Override
    public City insert(City city) {
        if(city != null)
            repo.insert(city);
        return city;
    }

    @Override
    public void insert(Collection<City> items) {
        if (!items.isEmpty()) {
            repo.insert(items);
        }
    }

    @Override
    public City findById(Long id) {
        if(id != null){
            return repo.findById(id);
        }else {
            return null;
        }
    }

    @Override
    public void update(City city) {
        repo.update(city);
    }

    @Override
    public void delete(City city) {
        if(city.getId() != null){
            try {
                for(Order order: ordersList) {
                    if (order.getCity().getId().equals(city.getId())) {
                        throw new CityIsContainedInSomeOrdersException("There are still orders which contains city that should be deleted", 40);
                    }
                }
            }catch (CityIsContainedInSomeOrdersException e) {
                System.out.println(e.getMessage());
            } finally {
                Iterator<Order> it = ordersList.iterator();
                while (it.hasNext()){
                    if(it.next().getCity().getId().equals(city.getId())){
                        it.remove();
                    }
                }
                this.deleteById(city.getId());
            }
        }
    }

    @Override
    public List<City> search(CitySearchCondition searchCondition) {
        return repo.search(searchCondition);
    }

    @Override
    public void deleteById(Long id) {
        if(id != null){
            repo.deleteById(id);
        }
    }

    @Override
    public void printAll() {
        repo.printAll();
    }
}

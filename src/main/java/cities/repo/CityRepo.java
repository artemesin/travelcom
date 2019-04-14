package cities.repo;

import cities.domain.City;
import cities.search.CitySearchCondition;
import common.solutions.repo.BaseRepo;


import java.util.List;

public interface CityRepo extends BaseRepo<City, Long> {
    List<City> search(CitySearchCondition searchCondition);

}

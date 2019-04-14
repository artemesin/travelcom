package cities.service;



import cities.domain.City;
import cities.search.CitySearchCondition;
import common.solutions.service.BaseService;

import java.util.List;

public interface CityService extends BaseService<City, Long> {

    List<City> search(CitySearchCondition searchCondition);
}

package countries.service;


import common.solutions.service.BaseService;
import countries.domain.BaseCountry;
import countries.search.CountrySearchCondition;

import java.util.List;

public interface CountryService extends BaseService<BaseCountry, Long> {


    List<BaseCountry> search(CountrySearchCondition searchCondition);
}

package countries.repo;


import common.solutions.repo.BaseRepo;
import countries.domain.BaseCountry;
import countries.search.CountrySearchCondition;

import java.util.List;

public interface CountryRepo extends BaseRepo<BaseCountry, Long> {


    List<BaseCountry> search(CountrySearchCondition searchCondition);

}

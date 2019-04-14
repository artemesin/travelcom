package countries.repo.impl.memory;

import countries.domain.BaseCountry;
import countries.search.CountryOrderByField;
import countries.search.CountrySearchCondition;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CountryOrderingComponent {

    public void applyOrdering(List<BaseCountry> countries, CountrySearchCondition searchCondition) {
        Comparator<BaseCountry> countryComparator = null;

        CountryOrderByField field = searchCondition.getOrderByField();

        switch (searchCondition.getOrderType()) {

            case SIMPLE: {
                countryComparator = CountryComparatorComponent.getInstance().getComparatorForField(field);
                break;
            }

            case COMPLEX: {
                countryComparator = CountryComparatorComponent.getInstance().getComplexComparator(field);
                break;
            }
        }

        if(countryComparator != null) {
            switch (searchCondition.getOrderDirection()){
                case ASC:
                    Collections.sort(countries, countryComparator);
                    break;

                case DESC:
                    Collections.sort(countries, countryComparator.reversed());
                    break;
            }
        }
    }
}

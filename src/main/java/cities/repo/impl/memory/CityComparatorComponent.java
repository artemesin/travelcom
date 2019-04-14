package cities.repo.impl.memory;

import cities.domain.City;
import cities.search.CityOrderByField;

import java.util.*;

import static cities.search.CityOrderByField.NAME;
import static cities.search.CityOrderByField.POPULATION;
import static com.roman.shilov.hw22.travelagency.common.buisness.repo.memory.CommonComparatorHolder.getComparatorForNullableStrings;
import static com.roman.shilov.hw22.travelagency.common.buisness.repo.memory.CommonComparatorHolder.getIntegerComparator;

public class CityComparatorComponent {

    private static final CityComparatorComponent INSTANCE = new CityComparatorComponent();
    private static Map<CityOrderByField, Comparator<City>> comparatorsByField = new HashMap<>();

    private static Set<CityOrderByField> fieldComparePriorityOrder = new LinkedHashSet<>(Arrays.asList(NAME, POPULATION));

    static {
        comparatorsByField.put(NAME, getComparatorForNameField());
        comparatorsByField.put(POPULATION, getComparatorForPopulationField());
    }

    public CityComparatorComponent() {
    }

    public static CityComparatorComponent getInstance(){
        return INSTANCE;
    }

    private static Comparator<City> getComparatorForNameField() {
        return new Comparator<City>() {
            @Override
            public int compare(City o1, City o2) {
                return getComparatorForNullableStrings().compare(o1.getName(), o2.getName());
            }
        };
    }

    private static Comparator<City> getComparatorForPopulationField() {
        return new Comparator<City>() {
            @Override
            public int compare(City o1, City o2) {
                return getIntegerComparator().compare(o1.getPopulation(), o2.getPopulation());
            }
        };
    }

    public Comparator<City> getComparatorForField(CityOrderByField field){
        return comparatorsByField.get(field);
    }

    public Comparator<City> getComplexComparator(CityOrderByField field){
        return new Comparator<City>() {
            @Override
            public int compare(City o1, City o2) {
                int result = 0;
                Comparator<City> cityComparator = comparatorsByField.get(field);

                if(cityComparator != null) {
                    result = cityComparator.compare(o1, o2);

                    if(result == 0) {

                        for(CityOrderByField otherField : fieldComparePriorityOrder) {
                            if (!otherField.equals(field)) {

                                result = comparatorsByField.get(otherField).compare(o1, o2);
                                if(result != 0){
                                    break;
                                }
                            }
                        }
                    }
                }

                return result;
            }
        };
    }

}

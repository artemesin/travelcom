package countries.repo.impl.memory;

import countries.domain.BaseCountry;
import countries.search.CountryOrderByField;

import java.util.*;

import static com.roman.shilov.hw22.travelagency.common.buisness.repo.memory.CommonComparatorHolder.getComparatorForNullableStrings;
import static countries.search.CountryOrderByField.LANGUAGE;
import static countries.search.CountryOrderByField.NAME;

public class CountryComparatorComponent {

    private static final CountryComparatorComponent INSTANCE = new CountryComparatorComponent();
    private static Map<CountryOrderByField, Comparator<BaseCountry>> comparatorsByField = new HashMap<>();

    private static Set<CountryOrderByField> fieldComparePriorityOrder = new LinkedHashSet<>(Arrays.asList(NAME, LANGUAGE));

    static {
        comparatorsByField.put(NAME, getComparatorForNameField());
        comparatorsByField.put(LANGUAGE, getComparatorForLanguageField());
    }

    public CountryComparatorComponent() {
    }

    public static CountryComparatorComponent getInstance(){
        return INSTANCE;
    }

    private static Comparator<BaseCountry> getComparatorForNameField() {
        return new Comparator<BaseCountry>() {
            @Override
            public int compare(BaseCountry o1, BaseCountry o2) {
                return getComparatorForNullableStrings().compare(o1.getName(), o2.getName());
            }
        };
    }

    private static Comparator<BaseCountry> getComparatorForLanguageField(){
        return new Comparator<BaseCountry>() {
            @Override
            public int compare(BaseCountry o1, BaseCountry o2) {
                return getComparatorForNullableStrings().compare(o1.getLanguage(), o2.getLanguage());
            }
        };
    }

    public Comparator<BaseCountry> getComparatorForField(CountryOrderByField field){
        return comparatorsByField.get(field);
    }

    public Comparator<BaseCountry> getComplexComparator(CountryOrderByField field){
        return new Comparator<BaseCountry>() {
            @Override
            public int compare(BaseCountry o1, BaseCountry o2) {
                int result = 0;
                Comparator<BaseCountry> countryComparator = comparatorsByField.get(field);

                if(countryComparator != null){
                    result = countryComparator.compare(o1, o2);

                    if(result == 0){

                        for(CountryOrderByField otherField : fieldComparePriorityOrder){
                            if(!otherField.equals(field)){

                                result = comparatorsByField.get(otherField).compare(o1, o2);
                            }
                        }
                    }
                }

                return result;
            }
        };
    }
}

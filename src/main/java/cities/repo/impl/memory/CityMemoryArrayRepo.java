package cities.repo.impl.memory;

import cities.domain.City;
import cities.repo.CityRepo;
import cities.search.CitySearchCondition;
import common.buisness.application.sequencecreator.SequenceCreator;
import common.solutions.utils.ArrayUtils;


import java.util.*;


public class CityMemoryArrayRepo implements CityRepo {

    private static final City[] EMPTY_CITIES_ARR = new City[0];
    private CityOrderingComponent orderingComponent = new CityOrderingComponent();
    private int cityIndex = -1;



    @Override
    public City findById(Long id, City[] cities) {
        Integer cityIndex = findCityIndexId(id);
        if(cityIndex != null){
            return cities[cityIndex];
        }
        return null;
    }


    @Override
    public void insert(Collection<City> items) {
        for (City city : items) {
            insert(city);
        }
    }

    @Override
    public List<City> search(CitySearchCondition searchCondition) {
         {
            List<City> result = doSearch(searchCondition);
            boolean needOrdering = !result.isEmpty() && searchCondition.needOrdering();

            if(needOrdering) {
                orderingComponent.applyOrdering(result, searchCondition);
            }

            return result;
        }
    }

    private List<City> doSearch(CitySearchCondition searchCondition){

        City[] result = new City[cities.length];
        int resultIndex = 0;

        for (City city : cities) {
            if (city != null) {
                boolean found = true;

                if (searchCondition.searchByPopulation()) {
                    found = searchCondition.getPopulation() == city.getPopulation();
                }

                if (found && searchCondition.searchByName()) {
                    found = searchCondition.getName().equals(city.getName());
                }

                if (found) {
                    result[resultIndex] = city;
                    resultIndex++;
                }
            }
        }

        if (resultIndex > 0) {
            City toReturn[] = new City[resultIndex];
            System.arraycopy(result, 0, toReturn, 0, resultIndex);
            return new ArrayList<>(Arrays.asList(toReturn));
        }

        return Collections.emptyList();
    }

    @Override
    public void update(City city) {
        City found = findById(city.getId(), cities);
        found.setName(city.getName());
        found.setPopulation(city.getPopulation());
    }

    @Override
    public void deleteById(Long id) {
        Integer cityIndex = findCityIndexId(id);
        if(cityIndex != null){
            deleteCityByIndex(cityIndex);
        }
    }

    private void deleteCityByIndex(int index){
        ArrayUtils.removeElement(cities, index);
        cityIndex--;
    }

    @Override
    public void printAll() {
        Arrays.stream(cities).forEach(System.out::println);
    }

    private Integer findCityIndexId(Long cityId){
        for (int i = 0; i < cities.length; i++) {
            if(cities[i].getId().equals(cityId)){
                return i;
            }
        }
        return null;
    }
}

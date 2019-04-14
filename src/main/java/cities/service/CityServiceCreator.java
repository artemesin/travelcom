package cities.service;

import com.roman.shilov.hw22.travelagency.cities.repo.impl.memory.CityMemoryArrayRepo;
import com.roman.shilov.hw22.travelagency.cities.repo.impl.memory.CityMemoryCollectionRepo;
import com.roman.shilov.hw22.travelagency.cities.service.impl.CityDefaultService;
import com.roman.shilov.hw22.travelagency.common.buisness.application.StorageType;

public final class CityServiceCreator {

    public CityServiceCreator() {
    }

    public static CityService getCityService(StorageType storageType){
        switch (storageType){

            case MEMORY_ARRAY:
                return new CityDefaultService(new CityMemoryArrayRepo());

            case MEMORY_COLLECTION:
                return new CityDefaultService(new CityMemoryCollectionRepo());

            default: return null;
        }
    }
}

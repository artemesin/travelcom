package countries.service;


import cities.repo.impl.memory.CityMemoryArrayRepo;
import cities.repo.impl.memory.CityMemoryCollectionRepo;
import common.buisness.application.StorageType;
import countries.repo.impl.memory.CountryMemoryArrayRepo;
import countries.repo.impl.memory.CountryMemoryCollectionRepo;
import countries.service.impl.CountryDefaultService;

public final class CountryServiceCreator {
    private CountryServiceCreator() {
    }

    public static CountryService getCityService(StorageType storageType){
        switch (storageType){

            case MEMORY_ARRAY:
                return new CountryDefaultService(new CountryMemoryArrayRepo(), new CityMemoryArrayRepo());

            case MEMORY_COLLECTION:
                return new CountryDefaultService(new CountryMemoryCollectionRepo(), new CityMemoryCollectionRepo());

            default: return null;
        }
    }
}

package user.service;

import common.buisness.application.StorageType;
import user.repo.impl.memory.UserMemoryArrayRepo;
import user.repo.impl.memory.UserMemoryCollectionRepo;
import user.service.impl.UserDefaultService;

public final class UserServiceCreator {
    private UserServiceCreator() {
    }

    public static UserService getCityService(StorageType storageType){
        switch (storageType){

            case MEMORY_ARRAY:
                return new UserDefaultService(new UserMemoryArrayRepo());

            case MEMORY_COLLECTION:
                return new UserDefaultService(new UserMemoryCollectionRepo());

            default: return null;
        }
    }
}

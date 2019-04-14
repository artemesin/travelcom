package order.service;

import common.buisness.application.StorageType;
import order.repo.impl.memory.OrderMemoryArrayRepo;
import order.repo.impl.memory.OrderMemoryCollectionRepo;
import order.service.impl.OrderDefaultService;

public final class OrderServiceCreator {
    private OrderServiceCreator() {
    }

    public static OrderService getCityService(StorageType storageType){
        switch (storageType){

            case MEMORY_ARRAY:
                return new OrderDefaultService(new OrderMemoryArrayRepo());

            case MEMORY_COLLECTION:
                return new OrderDefaultService(new OrderMemoryCollectionRepo());

            default: return null;
        }
    }
}

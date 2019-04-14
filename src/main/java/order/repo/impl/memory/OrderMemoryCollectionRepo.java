package order.repo.impl.memory;


import common.buisness.application.sequencecreator.SequenceCreator;
import order.domain.Order;
import order.repo.OrderRepo;
import order.search.OrderSearchCondition;

import java.util.*;


public class OrderMemoryCollectionRepo implements OrderRepo {
    private OrdersOrderingComponent orderingComponent = new OrdersOrderingComponent();

    @Override
    public Order insert(Order order) {
        order.setId(SequenceCreator.getNextId());
        ArrayDeque<Order> ordersList;
        ordersList.add(order);
        return order;
    }

    @Override
    public void insert(Collection<Order> items) {
        for (Order order : items) {
            insert(order);
        }
    }

    @Override
    public Order findById(Long id) {
        return findOrderById(id);
    }

    @Override
    public List<Order> search(OrderSearchCondition searchCondition) {
        if (searchCondition.getId() != null) {
            return Collections.singletonList(findById(searchCondition.getId()));
        } else {
            List<Order> result = doSearch(searchCondition);
            boolean needOrdering = !result.isEmpty() && searchCondition.needOrdering();

            if (needOrdering) {
                orderingComponent.applyOrdering(result, searchCondition);
            }

            return result;
        }
    }

    private List<Order> doSearch(OrderSearchCondition searchCondition){

        List<Order> result = new ArrayList<>();
        for (Order order : ordersList) {
            if (order != null) {
                boolean found = true;
                if (searchCondition.searchByUser()) {
                    found = searchCondition.getUser().equals(order.getUser());
                }

                if (found && searchCondition.searchByCountry()) {
                    found = searchCondition.getBaseCountry().equals(order.getBaseCountry());
                }

                if (found && searchCondition.searchByCity()) {
                    found = searchCondition.getCity().equals(order.getCity());
                }

                if (found && searchCondition.searchByDescription()) {
                    found = searchCondition.getDescription().equals(order.getDescription());
                }

                if (found && searchCondition.searchByPrice()) {
                    found = searchCondition.getPrice() == order.getPrice();
                }

                if (found) {
                    result.add(order);
                }
            }
        }
        return result;
    }

    @Override
    public void update(Order order) {
        Order found = findById(order.getId());
        found.setUser(order.getUser());
        found.setCity(order.getCity());
        found.setBaseCountry(order.getBaseCountry());
        found.setPrice(order.getPrice());
        found.setDescription(order.getDescription());
    }

    @Override
    public void deleteById(Long id) {
        Order found = findOrderById(id);

        if (found != null) {
            ordersList.remove(found);
        }
    }

    @Override
    public void printAll() {
        for(Order order: ordersList){
            System.out.println(order);
        }
    }

    private Order findOrderById(long userId) {
        for (Order order : ordersList) {
            if (Long.valueOf(userId).equals(order.getId())) {
                return order;
            }
        }
        return null;
    }
}

package order.repo.impl.memory;


import common.buisness.application.sequencecreator.SequenceCreator;
import common.solutions.utils.ArrayUtils;
import order.domain.Order;
import order.repo.OrderRepo;
import order.search.OrderSearchCondition;

import java.util.*;




public class OrderMemoryArrayRepo implements OrderRepo {
    private static final Order[] EMPTY_ORDERS_ARR = new Order[0];
    private OrdersOrderingComponent orderingComponent = new OrdersOrderingComponent();
    private int orderIndex = -1;

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
            Order[] result = new Order[orders.length];
            int resultIndex = 0;

            for (Order order : orders) {
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
                        result[resultIndex] = order;
                        resultIndex++;
                    }
                }
            }

            if (resultIndex > 0) {
                Order[] toReturn = new Order[resultIndex];
                System.arraycopy(result, 0, toReturn, 0, resultIndex);
                return new ArrayList<>(Arrays.asList(toReturn));
            }
        return Collections.emptyList();
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
    public Order insert(Order order) {
        if(orderIndex == orders.length - 1) {
            Order[] newArrOrders = new Order[orders.length * 2];
            System.arraycopy(orders,0, newArrOrders, 0, orders.length);
            orders = newArrOrders;
        }

        order.setId(SequenceCreator.getNextId());
        orderIndex++;
        orders[orderIndex] = order;
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
        Integer orderIndex = findOrderIndexById(id);
        if(orderIndex != null){
            return orders[orderIndex];
        }
        return null;
    }

    @Override
    public void deleteById(Long id) {
        Integer orderIndex = findOrderIndexById(id);

        if(orderIndex != null){
            deleteOrderByIndex(orderIndex);
        }
    }

    private void deleteOrderByIndex(int index){
        ArrayUtils.removeElement(orders, index);
        orderIndex--;
    }

    @Override
    public void printAll() {
        Arrays.stream(orders).forEach(System.out::println);
    }

    private Integer findOrderIndexById(Long orderId){
        for (int i = 0; i < orders.length; i++) {
            if(orders[i].getId().equals(orderId)){
                return i;
            }
        }
        return null;
    }
}

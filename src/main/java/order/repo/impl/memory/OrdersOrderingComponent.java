package order.repo.impl.memory;

import order.domain.Order;
import order.search.OrderSearchCondition;
import order.search.OrdersOrderByField;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static common.buisness.search.OrderDirection.ASC;
import static common.buisness.search.OrderDirection.DESC;
import static common.buisness.search.OrderType.COMPLEX;
import static common.buisness.search.OrderType.SIMPLE;

public class OrdersOrderingComponent {

    public void applyOrdering(List<Order> orders, OrderSearchCondition searchCondition) {
        Comparator<Order> orderComparator = null;

        OrdersOrderByField field = searchCondition.getOrderByField();

        switch (searchCondition.getOrderType()) {
            case SIMPLE: {
                orderComparator = OrderComparatorComponent.getInstance().getComparatorForField(field);
                break;
            }

            case COMPLEX: {
                orderComparator = OrderComparatorComponent.getInstance().getComplexComparator(field);
                break;
            }
        }

        if(orderComparator != null){
            switch (searchCondition.getOrderDirection()){
                case ASC:
                    Collections.sort(orders, orderComparator);
                    break;
                case DESC:
                    Collections.sort(orders, orderComparator.reversed());
                    break;
            }
        }
    }
}

package order.repo.impl.memory;

import order.domain.Order;
import order.search.OrdersOrderByField;

import java.util.*;

import static common.buisness.repo.memory.CommonComparatorHolder.getComparatorForNullableStrings;
import static common.buisness.repo.memory.CommonComparatorHolder.getIntegerComparator;
import static order.search.OrdersOrderByField.*;

public class OrderComparatorComponent {

    private static final OrderComparatorComponent INSTANCE = new OrderComparatorComponent();
    private static Map<OrdersOrderByField, Comparator<Order>> comparatorsByField = new HashMap<>();

    private static Set<OrdersOrderByField> fieldComparePriorityOrder = new LinkedHashSet<>(Arrays.asList(PRICE, USER, COUNTRY, CITY));

    static {
        comparatorsByField.put(PRICE, getComparatorForPriceField());
        comparatorsByField.put(USER, getComparatorForUserField());
        comparatorsByField.put(COUNTRY, getComparatorForCountryField());
        comparatorsByField.put(CITY, getComparatorForCityField());
    }

    public OrderComparatorComponent() {
    }

    public static OrderComparatorComponent getInstance(){
        return INSTANCE;
    }

    private static Comparator<Order> getComparatorForPriceField(){
        return new Comparator<Order>(){
            @Override
            public int compare(Order o1, Order o2) {
                return getIntegerComparator().compare(o1.getPrice(), o2.getPrice());
            }
        };
    }

    private static Comparator<Order> getComparatorForUserField(){
        return new Comparator<Order>(){
            @Override
            public int compare(Order o1, Order o2) {
                return getComparatorForNullableStrings().compare(o1.getUser().getName(), o2.getUser().getName());
            }
        };
    }

    private static Comparator<Order> getComparatorForCountryField(){
        return new Comparator<Order>(){
            @Override
            public int compare(Order o1, Order o2) {
                return getComparatorForNullableStrings().compare(o1.getBaseCountry().getName(), o2.getBaseCountry().getName());
            }
        };
    }

    private static Comparator<Order> getComparatorForCityField(){
        return new Comparator<Order>(){
            @Override
            public int compare(Order o1, Order o2) {
                return getComparatorForNullableStrings().compare(o1.getCity().getName(), o2.getCity().getName());
            }
        };
    }

    public Comparator<Order> getComparatorForField(OrdersOrderByField field){
        return comparatorsByField.get(field);
    }

    public Comparator<Order> getComplexComparator(OrdersOrderByField field){
        return new Comparator<Order>() {
            @Override
            public int compare(Order o1, Order o2) {
                int result = 0;
                Comparator<Order> orderComparator = comparatorsByField.get(field);

                if(orderComparator != null){
                    result = orderComparator.compare(o1, o2);

                    if(result == 0){

                        for(OrdersOrderByField otherField : fieldComparePriorityOrder){
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

package order.service;


import common.solutions.service.BaseService;
import order.domain.Order;
import order.search.OrderSearchCondition;

import java.util.List;

public interface OrderService extends BaseService<Order, Long> {

    List<Order> search(OrderSearchCondition searchCondition);
}

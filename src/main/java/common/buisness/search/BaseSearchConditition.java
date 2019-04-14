package common.buisness.search;

public abstract class BaseSearchConditition {
    protected Long id;
    protected OrderType orderType = OrderType.SIMPLE;
    protected OrderDirection orderDirection;

    public OrderDirection getOrderDirection() {
        return orderDirection;
    }

    public void setOrderDirection(OrderDirection orderDirection) {
        this.orderDirection = orderDirection;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }

    public boolean needOrdering(){
        return orderDirection != null && orderType != null;
    }
}

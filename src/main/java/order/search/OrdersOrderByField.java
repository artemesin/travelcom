package order.search;

public enum OrdersOrderByField {
    PRICE("Price"), CITY("City"), COUNTRY("Country"), USER("User");

    private String description;

    OrdersOrderByField(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}

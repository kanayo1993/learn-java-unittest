public class Order {
    private final String orderId;
    private final String customerName;
    private final int quantity;

    public Order(String orderId, String customerName, int quantity) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.quantity = quantity;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return "Order{id='" + orderId + "', customer='" + customerName + "', qty=" + quantity + "}";
    }
}

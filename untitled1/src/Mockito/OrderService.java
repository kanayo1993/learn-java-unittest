package Mockito;

public class OrderService {
    private final InventoryClient inventory;
    private final PaymentClient payment;

    public OrderService(InventoryClient inventory, PaymentClient payment){
        this.inventory = inventory;
        this.payment = payment;
    }

    public boolean placeOrder(String itemId, int qty, double amount){
        if(!inventory.isInStock(itemId, qty)){
            return false;
        }
        try{
            payment.charge(itemId, amount);
            return true;
        } catch (PaymentException e){
            return false;
        }
    }
}

package Mockito;

public interface PaymentClient {
    void charge(String itemId, double amount) throws PaymentException;
}

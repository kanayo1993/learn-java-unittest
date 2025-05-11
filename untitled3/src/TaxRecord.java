import java.time.LocalDate;

public class TaxRecord {
    private final int id;
    private final String taxpayerId;
    private final LocalDate dueDate;
    private boolean paid;
    private final double amount;

    public TaxRecord(int id, String taxpayerId, LocalDate dueDate, double amount) {
        if(id <= 0) throw new IllegalArgumentException("Invalid record ID");
        this.id = id;
        this.taxpayerId = taxpayerId;
        this.dueDate = dueDate;
        this.amount = amount;
        this.paid = false;
    }

    public int getId() {
        return id;
    }

    public String getTaxpayerId() {
        return taxpayerId;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public boolean isPaid() {
        return paid;
    }

    public double getAmount() {
        return amount;
    }

    public void markPaid(){
        if(paid) throw new IllegalStateException("Already paid");
        paid = true;
    }


}

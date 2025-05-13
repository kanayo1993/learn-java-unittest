import java.time.LocalDate;
import java.util.NoSuchElementException;

public class TaxService {
    private final TaxRepository repo;
    private final NotificationClient notifier;

    public TaxService(TaxRepository repo, NotificationClient notifier) {
        this.repo = repo;
        this.notifier = notifier;
    }

    public TaxRecord getRecord(int id){
        if(id <= 0)throw new IllegalArgumentException("Invalid record ID");
        return repo.findById(id).orElseThrow(() -> new NoSuchElementException("TaxRecord not found: " + id));
    }

    public void markAsPaid(int id){
        TaxRecord rec = getRecord(id);
        rec.markPaid();
        repo.save(rec);
    }

    public boolean processOverdue(int id, String email){
        TaxRecord rec = getRecord(id);
        if(rec.isPaid() || !LocalDate.now().isAfter(rec.getDueDate())){
            return false;
        }
        try{
            notifier.sendOverdueNotice(id, email);
            return true;
        } catch (NotificationException e) {
            return false;
        }
    }
}

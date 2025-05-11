import java.util.Optional;

public interface TaxRepository {
    Optional<TaxRecord> findById(int recordId);
    void save(TaxRecord record);
}

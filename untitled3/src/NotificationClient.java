public interface NotificationClient {
    void sendOverdueNotice(int recordId, String email) throws NotificationException;
}
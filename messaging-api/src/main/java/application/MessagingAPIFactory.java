package application;

public class MessagingAPIFactory {
    public static MessagingAPI createAPI() {
        return new StandardMessagingAPI();
    }
}

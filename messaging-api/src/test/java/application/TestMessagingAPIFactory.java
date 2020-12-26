package application;

public class TestMessagingAPIFactory {
    public static MessagingAPI createTestAPI(){
        return MessagingAPIFactory.createAPI(ApplicationProfile.TESTING);
    }
}

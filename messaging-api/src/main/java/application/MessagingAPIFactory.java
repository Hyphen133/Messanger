package application;

public class MessagingAPIFactory {

    private static MessagingAPI instance = null;

    private MessagingAPIFactory() {
    }

    public static MessagingAPI createAPI(){
        if(instance == null){
            instance = new StandardMessagingAPI();
        }
        return instance;
    }
}

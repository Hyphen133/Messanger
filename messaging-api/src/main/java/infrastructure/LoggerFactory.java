package infrastructure;



public class LoggerFactory {

    private static Logger instance = null;

    private LoggerFactory() {
    }

    public static Logger getInstance(){
        if(instance == null){
            instance = new ConsoleInfoLogger();
        }
        return instance;
    }
}
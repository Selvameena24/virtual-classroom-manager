class SimpleLogger {
    private static SimpleLogger instance;

    private SimpleLogger() { }

    public static SimpleLogger getInstance() {
        if (instance == null) {
            instance = new SimpleLogger();
        }
        return instance;
    }

    public void log(String message) {
        System.out.println("Log: " + message);
    }
}
public class SingletonExample {
    public static void main(String[] args) {
        SimpleLogger logger1 = SimpleLogger.getInstance();
        SimpleLogger logger2 = SimpleLogger.getInstance();

        logger1.log("This is the first log message.");
        logger2.log("This is the second log message.");

        System.out.println(logger1 == logger2); // true
    }
}

